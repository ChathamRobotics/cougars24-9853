package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.FullMecanumDrive;

import java.util.Arrays;

@TeleOp(name = "Training Wheels")
public class TrainingWheels extends LinearOpMode {
    float SPEED = 0.4f;
    TeleopDrive robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new TeleopDrive(hardwareMap, Arrays.asList(FullMecanumDrive.Module.INTAKE, FullMecanumDrive.Module.LINEAR_SLIDE, FullMecanumDrive.Module.ARM, FullMecanumDrive.Module.PLANE, FullMecanumDrive.Module.DOOR, FullMecanumDrive.Module.HANGING) , SPEED);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad2.left_stick_y != 0) robot.intake.setState(0.11f);
            if (gamepad2.a) {
                robot.scoreMacro.run(false);
                telemetry.speak("SCORE!");
            };
            robot.linearSlide.move(-gamepad2.right_stick_y * 0.8f, false);

            if (gamepad2.x) telemetry.speak("Hello, I am ruedee", "ga", "ie");


            robot.move(-gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, gamepad1.right_stick_x);

            Pose2d poseEstimate = robot.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.addData("slide1Pos", robot.linearSlide.linearSlide.get(0).getCurrentPosition());
            telemetry.addData("slide2Pos", robot.linearSlide.linearSlide.get(1).getCurrentPosition());
            telemetry.addData("slide1Pow", robot.linearSlide.linearSlide.get(0).getPower());
            telemetry.addData("slide2Pow", robot.linearSlide.linearSlide.get(1).getPower());
            telemetry.addData(robot.motors.get(0).getDeviceName(), robot.motors.get(0).getCurrentPosition());
            telemetry.addData(robot.motors.get(1).getDeviceName(), robot.motors.get(1).getCurrentPosition());
            telemetry.addData(robot.motors.get(2).getDeviceName(), robot.motors.get(2).getCurrentPosition());
            telemetry.addData(robot.motors.get(3).getDeviceName(), robot.motors.get(3).getCurrentPosition());
            telemetry.addData("slideJoystick", -gamepad2.right_stick_y * 0.5f);
            telemetry.update();
        }
    }
}
