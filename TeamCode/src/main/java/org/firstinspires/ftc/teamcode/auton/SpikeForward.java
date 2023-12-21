package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.Positions;

import java.util.Collections;

@Autonomous (name = "Spike Forward")
public class SpikeForward extends LinearOpMode {
    private AutonDrive robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new AutonDrive(hardwareMap, Collections.emptyList());
        Pose2d startPos = Positions.blueLeft;

        robot.setPoseEstimate(startPos);

        Trajectory traj1 = robot.trajectoryBuilder(startPos)
                //.forward(28)
                .back(20)
                .build();

        waitForStart();

        robot.followTrajectory(traj1);
        robot.intake.onForXSeconds(-0.2f, 1.5f);
        
    }
}
