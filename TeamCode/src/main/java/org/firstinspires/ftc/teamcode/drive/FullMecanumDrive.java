package org.firstinspires.ftc.teamcode.drive;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.drive.modules.ArmModule;
import org.firstinspires.ftc.teamcode.drive.modules.DoorModule;
import org.firstinspires.ftc.teamcode.drive.modules.HangingModule;
import org.firstinspires.ftc.teamcode.drive.modules.IntakeModule;
import org.firstinspires.ftc.teamcode.drive.modules.LinearSlideModule;
import org.firstinspires.ftc.teamcode.drive.modules.PlaneModule;
import org.firstinspires.ftc.teamcode.drive.modules.ScoreMacro;

import java.util.List;

public class FullMecanumDrive extends BaseMecanumDrive {

    public enum Module {
        CLAW,
        INTAKE,
        LINEAR_SLIDE,
        ARM,
        PLANE,
        DOOR,
        HANGING
    }
    
    public enum Macro {
        SCORE
    }

    public DoorModule claw;
    public IntakeModule intake;
    public LinearSlideModule linearSlide;
    public ArmModule arm;
    public PlaneModule plane;
    public DoorModule door;
    public HangingModule hanging;

    public ScoreMacro scoreMacro;

    public FullMecanumDrive(HardwareMap hwMap, List<Module> modules) {
        super(hwMap);
        if (modules.contains(Module.CLAW)) {
            claw = new DoorModule(hwMap);
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
        if (modules.contains(Module.PLANE)) {
            plane = new PlaneModule(hwMap);
        }
        if (modules.contains(Module.DOOR)) {
            door = new DoorModule(hwMap);
        }
        if (modules.contains(Module.HANGING)) {
            hanging = new HangingModule(hwMap);
        }
        
        if (door != null && arm != null) {
            scoreMacro = new ScoreMacro(linearSlide, arm, door);
        }
    }
}

