package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.StartPositions;

import java.util.Collections;

@Autonomous(name = "No RR - Just Park Blue Left")
public class NoRR_JustParkBlueLeft extends LinearOpMode {
    private final double COUNTS_PER_IN = 28 / 0.09585062638384342 / (2 * Math.PI * 1.4763);
    private AutonDrive robot;

    @Override
    public void runOpMode() {
        robot = new AutonDrive(hardwareMap, Collections.emptyList());


        waitForStart();

        robot.motors.forEach(motor -> {
            motor.setPower(-0.5);
            motor.setTargetPosition((int) COUNTS_PER_IN * -36);
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        });

        while (opModeIsActive()) {

        }
    }
}
