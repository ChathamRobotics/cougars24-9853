package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.ProfileAccelerationConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.drive.FullMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.Positions;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.tfod.TfodProcessor;

import java.util.Arrays;
import java.util.List;

@Autonomous (name = "Detect Prop Blue Right")
public class DetectPropBlueRight extends LinearOpMode {

    // TFOD_MODEL_ASSET points to a model file stored in the project Asset location,
    // this is only used for Android Studio when using models in Assets.
    private static final String TFOD_MODEL_ASSET = "detect-blue-prop.tflite";
    // Define the labels recognized in the model for TFOD (must be in training order!)
    private static final String[] LABELS = {
            "blueProp",
    };

    private TfodProcessor tfod;
    private VisionPortal visionPortal;

    private AutonDrive robot;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData(">", "Robot Initializing...");
        telemetry.update();
        initTfod();


        robot = new AutonDrive(hardwareMap, Arrays.asList(FullMecanumDrive.Module.INTAKE, FullMecanumDrive.Module.ARM, FullMecanumDrive.Module.DOOR, FullMecanumDrive.Module.LINEAR_SLIDE));
        Pose2d startPos = Positions.redRight;

        robot.setPoseEstimate(startPos);


        Trajectory forward = robot.trajectoryBuilder(startPos)
                .forward(24, new TranslationalVelocityConstraint(20), new ProfileAccelerationConstraint(20))
                .build();

        Trajectory toRight = robot.trajectoryBuilder(forward.end())
                .lineToConstantHeading(new Vector2d(26, -44), new TranslationalVelocityConstraint(15), new ProfileAccelerationConstraint(20))
                .build();

        Trajectory toCenter = robot.trajectoryBuilder(forward.end())
                .lineToConstantHeading(new Vector2d(12, -36), new TranslationalVelocityConstraint(15), new ProfileAccelerationConstraint(20))
                .build();

        Trajectory toLeft = robot.trajectoryBuilder(forward.end())
                .lineToLinearHeading(new Pose2d(13, -31, Math.toRadians(180)), new TranslationalVelocityConstraint(15), new ProfileAccelerationConstraint(20))
                .build();

        Trajectory fromLeft = robot.trajectoryBuilder(toLeft.end())
                .back(5)
                .build();

        TrajectorySequence fromCenter = robot.trajectorySequenceBuilder(toCenter.end())
                .forward(4)
                .back(10)
                .build();

        Trajectory fromRight = robot.trajectoryBuilder(toRight.end())
                .back(5)
                .build();


        telemetry.addData(">", "Robot Initialized!");
        telemetry.addData(">", "Press start to start");
        telemetry.update();


        while (opModeInInit()) {
            tfod.getRecognitions();
            sleep(50);
        }

        runtime.reset();

        Recognition propRecognition = null;

        while(runtime.seconds() < 5) {
            List<Recognition> currentRecognitions = tfod.getRecognitions();
            telemetry.addData("# Objects Detected", currentRecognitions.size());

            if (currentRecognitions.size() > 0) {
                propRecognition = currentRecognitions.get(0);
            }

            // Step through the list of recognitions and display info for each one.
            for (Recognition recognition : currentRecognitions) {
                double x = (recognition.getLeft() + recognition.getRight()) / 2 ;
                double y = (recognition.getTop()  + recognition.getBottom()) / 2 ;

                telemetry.addData(""," ");
                telemetry.addData("Image", "%s (%.0f %% Conf.)", recognition.getLabel(), recognition.getConfidence() * 100);
                telemetry.addData("- Position", "%.0f / %.0f", x, y);
                telemetry.addData("- Size", "%.0f x %.0f", recognition.getWidth(), recognition.getHeight());
            }   // end for() loop
            
            // Push telemetry to the Driver Station.
            telemetry.update();

            // Save CPU resources; can resume streaming when needed.
            if (gamepad1.dpad_down) {
                visionPortal.stopStreaming();
            } else if (gamepad1.dpad_up) {
                visionPortal.resumeStreaming();
            }

            // Share the CPU.
            sleep(20);
        }
        visionPortal.close();

        int direction = 0;

        if (propRecognition == null) {
            // left
            direction = 1;
        } else {
            double x = (propRecognition.getLeft() + propRecognition.getRight()) / 2;
            double y = (propRecognition.getTop()  + propRecognition.getBottom()) / 2;

            if (x > 450) {
                // right
                direction = 3;
            } else if (x > 100) {
                // center
                direction = 2;
            } else {
                // left
                direction = 1;
            }
        }

        robot.arm.setState(0);
        robot.linearSlide.goToPos(0.1f);
        telemetry.addLine("Going forward...");
        telemetry.update();
        robot.followTrajectory(forward);
        telemetry.addLine("Went forward");
        telemetry.addLine("Turning" + direction);
        telemetry.update();
        switch (direction) {
            case 1:
                robot.followTrajectory(toLeft);
                break;
            case 2:
                robot.followTrajectory(toCenter);
                break;
            case 3:
                robot.followTrajectory(toRight);
                break;
        }
        telemetry.addLine("Pushing out pixel...");
        telemetry.update();
        robot.intake.onForXSeconds(-0.05f, 2);

        switch (direction) {
            case 1:
                robot.followTrajectory(fromLeft);
                break;
            case 2:
                robot.followTrajectorySequence(fromCenter);
                break;
            case 3:
                robot.followTrajectory(fromRight);
                break;
        }

        robot.linearSlide.goToPos(0);
        double timeStart = runtime.milliseconds();
        while (timeStart + 1000 > runtime.milliseconds()) {
            robot.linearSlide.move(-0.3f, true);
        }
    }

    /**
     * Initialize the TensorFlow Object Detection processor.
     */
    private void initTfod() {

        // Create the TensorFlow processor by using a builder.
        tfod = new TfodProcessor.Builder()

                // With the following lines commented out, the default TfodProcessor Builder
                // will load the default model for the season. To define a custom model to load,
                // choose one of the following:
                //   Use setModelAssetName() if the custom TF Model is built in as an asset (AS only).
                //   Use setModelFileName() if you have downloaded a custom team model to the Robot Controller.
                .setModelAssetName(TFOD_MODEL_ASSET)

                // The following default settings are available to un-comment and edit as needed to
                // set parameters for custom models.
                //.setModelLabels(LABELS)
                //.setIsModelTensorFlow2(true)
                //.setIsModelQuantized(true)
                //.setModelInputSize(300)
                //.setModelAspectRatio(16.0 / 9.0)

                .build();

        // Create the vision portal by using a builder.
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam).
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));

        // Choose a camera resolution. Not all cameras support all resolutions.
        //builder.setCameraResolution(new Size(640, 480));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        //builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        //builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        //builder.setAutoStopLiveView(false);

        // Set and enable the processor.
        builder.addProcessor(tfod);

        // Build the Vision Portal, using the above settings.
        visionPortal = builder.build();

        // Set confidence threshold for TFOD recognitions, at any time.
        //tfod.setMinResultConfidence(0.75f);

        // Disable or re-enable the TFOD processor at any time.
        //visionPortal.setProcessorEnabled(tfod, true);

    }   // end method initTfod()
}
