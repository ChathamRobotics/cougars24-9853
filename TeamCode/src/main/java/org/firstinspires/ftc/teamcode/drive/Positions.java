package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class Positions {
    public static final float BOT_WIDTH = 17.25f;
    public static final float BOT_LENGTH = 17;
    public static final Pose2d blueLeft = new Pose2d(24 - BOT_WIDTH / 2, 70 - BOT_LENGTH / 2, Math.toRadians(-90));
    public static final Pose2d blueRight = new Pose2d(-24 + BOT_WIDTH / 2, 70 - BOT_LENGTH / 2, Math.toRadians(-90));
    public static final Pose2d redLeft = new Pose2d(-24 + BOT_WIDTH / 2, -70 + BOT_LENGTH / 2, Math.toRadians(90));
    public static final Pose2d redRight = new Pose2d(24 - BOT_WIDTH / 2, -70 + BOT_LENGTH / 2, Math.toRadians(90));

    public static final Pose2d backdropBlue = new Pose2d(40 + 13.5 - BOT_LENGTH / 2, 36, Math.toRadians(180));
    public static final Pose2d backdropRed = new Pose2d(40 + 13.5 - BOT_LENGTH / 2, -36, Math.toRadians(180));
}
