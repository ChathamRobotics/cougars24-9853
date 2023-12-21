package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import java.lang.reflect.Array;
import java.util.ArrayList;

@TeleOp(name="Dance Test", group="Testing")

public class DanceTest extends LinearOpMode {
    int soundPlaying = 0;
    int CLIPS = 49;
    ArrayList soundIds = new ArrayList();

    @Override
    public void runOpMode() throws InterruptedException {
        SoundPlayer.PlaySoundParams params = new SoundPlayer.PlaySoundParams();
        params.loopControl = 0;
        params.waitForNonLoopingSoundsToFinish = true;
        params.volume = 2;

        for (int i = 0; i < CLIPS; i++) {
            String number = "" + i;
            while (number.length() < 3) {
                number = "0" + number;
            }
            int id = hardwareMap.appContext.getResources().getIdentifier("christmas_" + number, "raw", hardwareMap.appContext.getPackageName());
            soundIds.add(id);
            telemetry.addData("number", number);
            telemetry.addData("id", id);

        }

        telemetry.addData("soundIDs:", soundIds);
        telemetry.update();

        waitForStart();

        soundPlaying = 0;
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, (int) soundIds.get(soundPlaying), params, null,
                new Runnable() {
                    public void run() {
                        soundPlaying++;
                        runNextSound(params);
                    }} );

        while (soundPlaying < CLIPS);

    }

    private void runNextSound(SoundPlayer.PlaySoundParams params) {
        SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, (int) soundIds.get(soundPlaying), params, null,
                new Runnable() {
                    public void run() {
                        soundPlaying++;
                        if(soundPlaying < CLIPS) {
                            runNextSound(params);
                        }
                    }} );
    }
}