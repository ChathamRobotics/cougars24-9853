package org.firstinspires.ftc.teamcode.drive.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.List;

public class HangingModule {
    public DcMotorEx hangingMotor;
    public boolean on = false;
    public HangingModule(HardwareMap hwMap) {
        hangingMotor = hwMap.get(DcMotorEx.class, "hanging");
        
        hangingMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hangingMotor.setPower(0);
        hangingMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void move(float power) {
        hangingMotor.setPower((double)power);
    }
    public void start() {
        hangingMotor.setPower(1);
        on = true;
    }
    public void stop() {
        hangingMotor.setPower(0);
        on = false;
    }
    public void toggle() {
        if (on) stop();
        else start();
    }
}
