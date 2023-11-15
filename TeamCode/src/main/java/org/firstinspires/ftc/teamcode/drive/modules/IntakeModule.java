package org.firstinspires.ftc.teamcode.drive.modules;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


// intake reversed; be able to drive it both ways
public class IntakeModule {
     private DcMotorEx intake;
     public IntakeModule(HardwareMap hwMap) {
             intake = hwMap.get(DcMotorEx.class, "intake");

             intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

             intake.setDirection(DcMotorSimple.Direction.REVERSE);
     }

     /**
          * Turns intake on for a set number of seconds
          * @param power Power to set intake to (on = 1, off = 0, backwards = -1)
          * @param time The amount of time that the intake is to be turned on for
          * @throws InterruptedException
          */
     public void onForXSeconds(float power, float time) throws InterruptedException {
             intake.setPower(power);
             Thread.sleep((long) time * 1000);
             intake.setPower(0);
         }

     /**
          * Sets the intake's power
          * @param power Power to set intake to (on = 1, off = 0, backwards = -1)
          */
     public void setState(float power)  {
             intake.setPower(power);
         }
}