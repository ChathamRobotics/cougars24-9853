package org.firstinspires.ftc.teamcode.drive.modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawModule {
    private Servo clawLeft, clawRight;
    public ClawModule(HardwareMap hwMap) {
        clawLeft = hwMap.get(Servo.class, "clawLeft");
        clawLeft.scaleRange(0, 1);

        clawRight = hwMap.get(Servo.class, "clawRight");
        clawRight.scaleRange(0, 1);
    }

    /**
     * Set the state of the claw
     * @param state How open or closed the claw is (0-1)
     */
    public void setState(float state) {
        clawLeft.setPosition(state);
        clawRight.setPosition(1-state);
    }
}
