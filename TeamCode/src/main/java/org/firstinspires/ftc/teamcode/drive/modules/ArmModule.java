package org.firstinspires.ftc.teamcode.drive.modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ArmModule {
    private Servo armLeft, armRight;
    public ArmModule(HardwareMap hwMap) {
        armLeft = hwMap.get(Servo.class, "armLeft");
        armLeft.scaleRange(0, 1);

        armRight = hwMap.get(Servo.class, "armRight");
        armRight.scaleRange(0, 1);
    }

    /**
     * Set the state of the arm
     * @param state How far up or down the arm is (0-1) 0 = down, 1 = up
     */
    public void setState(float state) {
        armLeft.setPosition(state);
        armRight.setPosition(1-state);
    }
}