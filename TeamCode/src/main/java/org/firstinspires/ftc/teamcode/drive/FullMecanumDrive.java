package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FullMecanumDrive extends BaseMecanumDrive {

    private DcMotorEx intakeLeft, intakeRight;
    public FullMecanumDrive(HardwareMap hwMap) {
        super(hwMap);
        intakeLeft = hwMap.get(DcMotorEx.class, "intakeLeft");
        intakeRight = hwMap.get(DcMotorEx.class, "intakeRight");


    }
}
