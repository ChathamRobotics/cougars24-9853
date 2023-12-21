package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.FullMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.Positions;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

@Autonomous(name = "Score Preloads Red Right")
public class ScorePreloadsRedRight extends LinearOpMode {
    private AutonDrive robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new AutonDrive(hardwareMap, Arrays.asList(FullMecanumDrive.Module.ARM, FullMecanumDrive.Module.LINEAR_SLIDE));
        Pose2d startPos = Positions.redRight;

        robot.setPoseEstimate(startPos);

        TrajectorySequence forward = robot.trajectorySequenceBuilder(startPos)
                .splineToLinearHeading(Positions.backdropRed, Math.toRadians(-90))
                .strafeLeft(2)
                .back(11)
                .build();

        waitForStart();

        robot.arm.setState(0);
        robot.linearSlide.goToPos(0.3f);
        robot.followTrajectorySequence(forward);
        robot.linearSlide.goToPos(1);
        robot.arm.setState(1);
        sleep(500);
        robot.linearSlide.wiggle();
        robot.linearSlide.wiggle();
        robot.linearSlide.wiggle();
        robot.arm.setState(0);
        sleep(500);
        robot.linearSlide.goToPos(0);
    }
}
