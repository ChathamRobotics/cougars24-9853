package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(group = "drive")
public class SimpleHDrive extends LinearOpMode {
    DcMotorEx left, right, center;
    float speed = 0.5f;

    @Override
    public void runOpMode() throws InterruptedException {
        left = hardwareMap.get(DcMotorEx.class, "left");
        right = hardwareMap.get(DcMotorEx.class, "right");
        center = hardwareMap.get(DcMotorEx.class, "center");

        right.setDirection(DcMotorSimple.Direction.REVERSE);

        left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        center.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (!isStopRequested()) {
            float leftPower = 0;
            float rightPower = 0;
            float centerPower = 0;

            leftPower -= gamepad1.left_stick_y;
            rightPower -= gamepad1.left_stick_y;

            leftPower += gamepad1.right_stick_x;
            rightPower -= gamepad1.right_stick_x;

            centerPower += gamepad1.left_stick_x;

            left.setPower(leftPower * speed);
            right.setPower(rightPower * speed);
            center.setPower(centerPower * speed);

            telemetry.addData("rightPower", rightPower);
            telemetry.addData("leftPower", leftPower);
            telemetry.addData("centerPower", centerPower);
            telemetry.update();
        }
    }
}
