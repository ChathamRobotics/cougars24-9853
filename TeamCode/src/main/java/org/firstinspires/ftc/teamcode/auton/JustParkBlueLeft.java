package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.StartPositions;

import java.util.Collections;

@Autonomous (name = "Just Park Blue Left")
public class JustParkBlueLeft extends LinearOpMode {
    private AutonDrive robot;

    @Override
    public void runOpMode() {
        robot = new AutonDrive(hardwareMap, Collections.emptyList());
        Pose2d startPos = StartPositions.blueLeft;

        robot.setPoseEstimate(startPos);

        Trajectory traj1 = robot.trajectoryBuilder(startPos)
                .lineTo(new Vector2d(0, -20))
                .build();

        waitForStart();

        robot.followTrajectory(traj1);
    }
}
