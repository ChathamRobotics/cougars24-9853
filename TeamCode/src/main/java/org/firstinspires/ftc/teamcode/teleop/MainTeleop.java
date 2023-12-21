package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.FullMecanumDrive;

import java.util.Arrays;

@TeleOp(name = "Main Drive")
public class MainTeleop extends LinearOpMode {
    float SPEED = 0.5f;
    TeleopDrive robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new TeleopDrive(hardwareMap, Arrays.asList(FullMecanumDrive.Module.INTAKE, FullMecanumDrive.Module.LINEAR_SLIDE, FullMecanumDrive.Module.ARM, FullMecanumDrive.Module.PLANE, FullMecanumDrive.Module.DOOR, FullMecanumDrive.Module.HANGING) , SPEED);

        waitForStart();

        while (opModeIsActive()) {
            robot.intake.setState(-(float)Math.pow(gamepad2.left_stick_y, 3) * 0.25f);
            if (gamepad2.left_trigger > 0) robot.intake.setState(0.11f);
            if (gamepad2.left_bumper) robot.arm.setState(0);
            if (gamepad2.right_bumper) robot.arm.setState(1);
            if (gamepad2.y) robot.arm.setState(0.2f);
            if (gamepad2.x) {
                robot.arm.wiggle();
                robot.linearSlide.wiggle();
            };
            if (gamepad2.a) robot.door.setState(0);
            else robot.door.setState(1);
            if (gamepad2.b) robot.scoreMacro.run(false);


            float power = 0;
            if (gamepad2.dpad_up) power += 0.6;
            if (gamepad2.dpad_down) power -= 0.4;
            if (power != 0) {
                robot.linearSlide.move(power, true);
                //robot.linearSlide.resetEncoders();
            } else {
                robot.linearSlide.move(-gamepad2.right_stick_y * .95f, false);
            }


            robot.move(-gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, gamepad1.right_stick_x);
            if (gamepad1.left_bumper) robot.setSpeed(0.2f);
            if (gamepad1.right_bumper) robot.setSpeed(0.95f);
            if (!gamepad1.left_bumper  && !gamepad1.right_bumper) robot.setSpeed(0.5f);
            if (gamepad1.x) robot.plane.fire();
            if (gamepad1.a) robot.hanging.toggle();
            if (gamepad1.y) robot.hanging.move(-1);

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
