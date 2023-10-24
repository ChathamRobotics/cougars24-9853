package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.FullMecanumDrive;

import java.util.List;

public class AutonDrive extends FullMecanumDrive {
    public AutonDrive(HardwareMap hwMap, List<Module> modules) {
        super(hwMap, modules);
    }
}
