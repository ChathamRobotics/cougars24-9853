package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.FullMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.Positions;

import java.util.Arrays;

@Autonomous (name = "Test Start Pos")
public class TestStartPos extends LinearOpMode {
    private AutonDrive robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new AutonDrive(hardwareMap, Arrays.asList(FullMecanumDrive.Module.ARM));
        Pose2d startPos = Positions.blueLeft;

        robot.setPoseEstimate(startPos);

        Trajectory forward = robot.trajectoryBuilder(startPos)
                .forward(50)
                .build();
        Trajectory toCenter = robot.trajectoryBuilder(forward.end())
                .lineTo(new Vector2d(0, 0))
                .build();

        waitForStart();

        robot.arm.setState(0);

        robot.followTrajectory(forward);
        robot.followTrajectory(toCenter);
    }
}
