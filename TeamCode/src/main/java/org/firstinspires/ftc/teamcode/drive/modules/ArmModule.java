package org.firstinspires.ftc.teamcode.drive.modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmModule {
    private Servo arm;
    public ArmModule(HardwareMap hwMap) {
        arm = hwMap.get(Servo.class, "arm");
        arm.scaleRange(0.58, 0.88);

    }

    /**
     * Set the state of the arm
     * @param state How far up or down the arm is (0-1) 0 = inside, 1 = outside
     */
    public void setState(float state) {
        arm.setPosition(state);
    }

    public void wiggle() {
        arm.setPosition(0.75);
        arm.setPosition(1);
    }
}
