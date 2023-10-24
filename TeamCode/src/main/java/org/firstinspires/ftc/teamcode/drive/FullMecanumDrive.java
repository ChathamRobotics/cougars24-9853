package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.modules.ArmModule;
import org.firstinspires.ftc.teamcode.drive.modules.ClawModule;
import org.firstinspires.ftc.teamcode.drive.modules.IntakeModule;
import org.firstinspires.ftc.teamcode.drive.modules.LinearSlideModule;

import java.util.List;

public class FullMecanumDrive extends BaseMecanumDrive {

    public enum Module {
        CLAW,
        INTAKE,
        LINEAR_SLIDE,
        ARM
    }

    public ClawModule claw;
    public IntakeModule intake;
    public LinearSlideModule linearSlide;
    public ArmModule arm;


    public FullMecanumDrive(HardwareMap hwMap, List<Module> modules) {
        super(hwMap);
        if (modules.contains(Module.CLAW)) {
            claw = new ClawModule(hwMap);
        }
        if (modules.contains(Module.INTAKE)) {
            intake = new IntakeModule(hwMap);
        }
        if (modules.contains(Module.LINEAR_SLIDE)) {
            linearSlide = new LinearSlideModule(hwMap);
        }
        if (modules.contains(Module.ARM)) {
            arm = new ArmModule(hwMap);
        }
    }
}

