package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="LED Test", group="Testing")

public class LEDTest extends LinearOpMode {
    public RevBlinkinLedDriver leds;
    @Override
    public void runOpMode() throws InterruptedException {
        leds = hardwareMap.get(RevBlinkinLedDriver.class, "leds");

        waitForStart();

        
            leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.VIOLET);
            Thread.sleep(2000);
            leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.GREEN);
            Thread.sleep(2000);
            leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.ORANGE);
            Thread.sleep(2000);
            leds.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);
            Thread.sleep(2000);

    }
}
