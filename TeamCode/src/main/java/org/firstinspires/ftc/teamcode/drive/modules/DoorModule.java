package org.firstinspires.ftc.teamcode.drive.modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DoorModule {
    private final Servo door;

    public DoorModule(HardwareMap hwMap) {
        door = hwMap.get(Servo.class, "door");
        door.scaleRange(0.27, 0.74);
        door.setDirection(Servo.Direction.FORWARD);
    }

    /**
     * Set the state of the claw
     * @param state How open or closed the claw is (0-1), 0 is open, 1 is closed
     */
    public void setState(float state) {
        door.setPosition(state);
    }
}
