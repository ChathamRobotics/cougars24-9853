package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.FullMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.Positions;

import java.util.Arrays;

@Autonomous (name = "Just Park Red Right")
public class JustParkRedRight extends LinearOpMode {
    private AutonDrive robot;

    @Override
    public void runOpMode() {
        robot = new AutonDrive(hardwareMap, Arrays.asList(FullMecanumDrive.Module.ARM));
        Pose2d startPos = Positions.redRight;

        robot.setPoseEstimate(startPos);

        Trajectory traj1 = robot.trajectoryBuilder(startPos)
                .strafeRight(36)
                .build();

        waitForStart();

        robot.arm.setState(0);

        robot.followTrajectory(traj1);
    }
}
