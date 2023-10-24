package org.firstinspires.ftc.teamcode.drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public class StartPositions {
    public static final float BOT_WIDTH = 17;
    public static final float BOT_LENGTH = 16;
    public static final Pose2d blueLeft = new Pose2d(70 - BOT_WIDTH / 2, -24 + BOT_LENGTH / 2, Math.toRadians(90));
    public static final Pose2d blueRight = new Pose2d(70 - BOT_WIDTH / 2, 24 - BOT_LENGTH / 2, Math.toRadians(90));
    public static final Pose2d redLeft = new Pose2d(-70 + BOT_WIDTH / 2, 24 - BOT_LENGTH / 2, Math.toRadians(-90));
    public static final Pose2d redRight = new Pose2d(-70 + BOT_WIDTH / 2, -24 + BOT_LENGTH / 2, Math.toRadians(-90));
}
