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
        robot = new TeleopDrive(hardwareMap, Arrays.asList(FullMecanumDrive.Module.INTAKE, FullMecanumDrive.Module.LINEAR_SLIDE) , SPEED);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.x) robot.intake.toggle(1); else robot.intake.toggle(0);
            robot.linearSlide.move(-gamepad2.right_stick_y);

            robot.move(-gamepad1.left_stick_y, gamepad1.right_trigger - gamepad1.left_trigger, gamepad1.right_stick_x);

            Pose2d poseEstimate = robot.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());

        }
    }
}
