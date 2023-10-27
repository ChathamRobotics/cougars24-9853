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
          * @param time The amount of time that the intake is to be turned on for
          * @throws InterruptedException
          */
     public void onForXSeconds(long time) throws InterruptedException {
             intake.setPower(1);
             Thread.sleep(time * 1000);
             intake.setPower(0);
         }

     /**
          * Toggle the intake
          * @param state what to change the state of the intake to (on = 1, off = 0, backwards = -1)
          */
     public void toggle(float state)  {
             intake.setPower(state);
         }
}