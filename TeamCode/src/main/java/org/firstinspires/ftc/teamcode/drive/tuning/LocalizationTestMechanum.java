package org.firstinspires.ftc.teamcode.drive.tuning;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.drive.BaseMecanumDrive;
import org.firstinspires.ftc.teamcode.util.Encoder;

/**
 * This is a simple teleop routine for testing localization. Drive the robot around like a normal
 * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
 * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
 * exercise is to ascertain whether the localizer has been configured properly (note: the pure
 * encoder localizer heading may be significantly off if the track width has not been tuned).
 */
@TeleOp(group = "tuning")
public class LocalizationTestMechanum extends LinearOpMode {
    Encoder leftEncoder,rightEncoder,frontEncoder;

    @Override
    public void runOpMode() throws InterruptedException {
        BaseMecanumDrive drive = new BaseMecanumDrive(hardwareMap);
        DcMotorEx leftIntake = hardwareMap.get(DcMotorEx.class, "leftIntake");
        DcMotorEx rightIntake = hardwareMap.get(DcMotorEx.class, "rightIntake");

        /*
        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftFront"));
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightBack"));
        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftBack"));
*/


        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightIntake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (!isStopRequested()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y * 0.4,
                            -gamepad1.left_stick_x * 0.4,
                            -gamepad1.right_stick_x * 0.4
                    )
            );

            drive.update();

            if (gamepad1.x) {
                telemetry.addLine("X");
                leftIntake.setPower(1);
                rightIntake.setPower(-1);
            } else {
                leftIntake.setPower(0);
                rightIntake.setPower(0);
            }

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());

            /*
            telemetry.addData("rawLCV", leftEncoder.getCorrectedVelocity());
            telemetry.addData("rawRCV", rightEncoder.getCorrectedVelocity());
            telemetry.addData("rawFCV", frontEncoder.getCorrectedVelocity());


            telemetry.addData("rawLRV", leftEncoder.getRawVelocity());
            telemetry.addData("rawRRV", rightEncoder.getRawVelocity());
            telemetry.addData("rawFRV", frontEncoder.getRawVelocity());


            telemetry.addData("rawLP", leftEncoder.getCurrentPosition());
            telemetry.addData("rawRP", rightEncoder.getCurrentPosition());
            telemetry.addData("rawFP", frontEncoder.getCurrentPosition());
*/
            telemetry.update();
        }
    }
}