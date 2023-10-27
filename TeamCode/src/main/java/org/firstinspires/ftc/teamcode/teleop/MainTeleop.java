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
    public void runOpMode() {
        robot = new TeleopDrive(hardwareMap, Arrays.asList(FullMecanumDrive.Module.INTAKE, FullMecanumDrive.Module.LINEAR_SLIDE, FullMecanumDrive.Module.ARM, FullMecanumDrive.Module.CLAW) , SPEED);

        waitForStart();

        while (opModeIsActive()) {
            robot.intake.toggle(-gamepad2.left_stick_y);
            robot.linearSlide.move(-gamepad2.right_stick_y * 0.5f);
            if (gamepad2.left_bumper) robot.claw.setState(0);
            if (gamepad2.right_bumper) robot.claw.setState(1);
            if (gamepad2.dpad_up) robot.arm.setState(1);
            if (gamepad2.dpad_down) robot.arm.setState(0);


            robot.move(-gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, gamepad1.right_stick_x);
            if (gamepad1.left_bumper) robot.setSpeed(0.2f);
            if (gamepad1.right_bumper) robot.setSpeed(0.9f);
            if (!gamepad1.left_bumper  && !gamepad1.right_bumper) robot.setSpeed(0.5f);

            Pose2d poseEstimate = robot.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.addData("slide1Pos", robot.linearSlide.linearSlide.get(0).getCurrentPosition());
            telemetry.addData("slide2Pos", robot.linearSlide.linearSlide.get(1).getCurrentPosition());
            telemetry.addData("slide1Pow", robot.linearSlide.linearSlide.get(0).getPower());
            telemetry.addData("slide2Pow", robot.linearSlide.linearSlide.get(1).getPower());
            telemetry.addData("slideJoystick", -gamepad2.right_stick_y * 0.5f);
            telemetry.update();
        }
    }
}
