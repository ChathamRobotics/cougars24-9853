package org.firstinspires.ftc.teamcode.drive.modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PlaneModule {
    private Servo plane;
    public PlaneModule(HardwareMap hwMap) {
            plane = hwMap.get(Servo.class, "plane");
            plane.scaleRange(0, 0.8);
            plane.setPosition(0);
        }


    /**
         * Sets the position of the 'fire' servo to 1 to fire the drone
         */
    public void fire() {

    plane.setPosition(1);

    }


}
