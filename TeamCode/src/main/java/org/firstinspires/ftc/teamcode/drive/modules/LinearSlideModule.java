package org.firstinspires.ftc.teamcode.drive.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LinearSlideModule {
    public static final int MAX_SLIDE_POS = 1550;
    private static final int SLIDE_MOTORS = 2;
    public List<DcMotorEx> linearSlide = new ArrayList<>();

    public LinearSlideModule(HardwareMap hwMap) {
        List<DcMotorSimple.Direction> motorDirections = Arrays.asList(DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);

        for (int i = 1; i <= SLIDE_MOTORS; i++) {
            DcMotorEx slideMotor = hwMap.get(DcMotorEx.class, "linearSlide" + i);
            slideMotor.setDirection(motorDirections.get(i - 1));
            linearSlide.add(slideMotor);
        }

        linearSlide.forEach(motor -> {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });

    }

    /**
     * Moves the linear slide with a power
     *
     * @param power Power to move the linear slide with
     */
    public void move(float power, boolean disableStop) {
        linearSlide.forEach(motor -> {
            if (power > 0) {
                if (linearSlide.get(0).getCurrentPosition() < MAX_SLIDE_POS - 10 || disableStop) {
                    motor.setPower(power);
                    motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                } else {
                    motor.setTargetPosition(MAX_SLIDE_POS);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor.setPower(linearSlide.get(0).getCurrentPosition() < MAX_SLIDE_POS ? 0.1 : -0.1);
                }
            } else {
                if (linearSlide.get(0).getCurrentPosition() > 10 || disableStop) {
                    motor.setPower(power);
                    motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                } else {
                    motor.setTargetPosition(0);
                    motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    motor.setPower(linearSlide.get(0).getCurrentPosition() < 0 ? 0.1 : -0.1);
                }
            }
        });
    }

    /**
     * Sets the position of the linear slide
     *
     * @param pos Position of linear slide to go to. 0-1: 0 = bottom, 1 = top
     */
    public void setPos(float pos) {
        int position = (int) (pos * MAX_SLIDE_POS);
        linearSlide.forEach(motor -> {
            motor.setTargetPosition(position);
            if (pos > linearSlide.get(0).getCurrentPosition()) {
                motor.setPower(.7);//Math.min(.7 + (position - linearSlide.get(0).getCurrentPosition()) / 200f, 0.9f));
            }
            if (pos < linearSlide.get(0).getCurrentPosition()) {
                motor.setPower(-.7);//Math.min(-.7 + (position - linearSlide.get(0).getCurrentPosition()) / 200f, 0.9f));
            }
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        });
    }

    /**
     * Go to a position of the linear slide
     *
     * @param pos Position of linear slide to go to. 0-1: 0 = bottom, 1 = top
     */
    public void goToPos(float pos) {
        int position = (int) (pos * MAX_SLIDE_POS);
        while (Math.abs(position - linearSlide.get(0).getCurrentPosition()) > 25) {
            setPos(pos);
        }
    }

    public void resetEncoders() {
        linearSlide.forEach(motor -> {
            motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });
    }

    public void wiggle() throws InterruptedException {
        linearSlide.forEach(motor -> {
            motor.setPower(0.6);
            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        });
        Thread.sleep(300);
        linearSlide.forEach(motor -> {
            motor.setPower(-0.5);
        });
        Thread.sleep(300);
        linearSlide.forEach(motor -> {
            motor.setPower(0);
        });

    }

    public int getPos() {
        return linearSlide.get(0).getCurrentPosition() / MAX_SLIDE_POS;
    }
}
