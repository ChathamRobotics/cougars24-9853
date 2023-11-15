package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.util.Encoder;

@TeleOp(group = "Testing")

public class EncoderTest extends LinearOpMode {
    public Encoder leftEncoder,rightEncoder,frontEncoder;
    @Override
    public void runOpMode() throws InterruptedException {
        // r -> 2 -> leftFront
        // f -> 1 -> rightBack
        // l -> 0 -> rightFront
        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightFront"));
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftFront"));
        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightBack"));

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("left", leftEncoder.getCurrentPosition());
            telemetry.addData("right", rightEncoder.getCurrentPosition());
            telemetry.addData("center", frontEncoder.getCurrentPosition());
            telemetry.update();
        }
    }
}
