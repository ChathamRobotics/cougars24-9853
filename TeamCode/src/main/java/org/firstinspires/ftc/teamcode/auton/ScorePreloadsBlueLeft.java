package org.firstinspires.ftc.teamcode.auton;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.constraints.MinVelocityConstraint;
import com.acmerobotics.roadrunner.trajectory.constraints.TranslationalVelocityConstraint;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.FullMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.Positions;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

import java.util.Arrays;

@Autonomous(name = "Score Preloads Blue Left")
public class ScorePreloadsBlueLeft extends LinearOpMode {
    private AutonDrive robot;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new AutonDrive(hardwareMap, Arrays.asList(FullMecanumDrive.Module.ARM, FullMecanumDrive.Module.LINEAR_SLIDE, FullMecanumDrive.Module.DOOR));
        Pose2d startPos = Positions.blueLeft;

        robot.setPoseEstimate(startPos);

        TrajectorySequence forward = robot.trajectorySequenceBuilder(startPos)
                .setVelConstraint(new MinVelocityConstraint(Arrays.asList(new TranslationalVelocityConstraint(20))))
                .splineToLinearHeading(Positions.backdropBlue, Math.toRadians(180))
                .back(8)
                .build();

        waitForStart();

        robot.arm.setState(0);
        robot.linearSlide.goToPos(0.3f);
        robot.followTrajectorySequence(forward);
        robot.linearSlide.goToPos(0.6f);
        robot.scoreMacro.run(true);
        robot.arm.setState(0);
        sleep(500);
        robot.linearSlide.goToPos(0);
    }
}
