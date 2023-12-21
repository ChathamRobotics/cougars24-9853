package org.firstinspires.ftc.teamcode.drive.modules;

import static org.firstinspires.ftc.teamcode.drive.modules.LinearSlideModule.MAX_SLIDE_POS;

public class ScoreMacro {

    private LinearSlideModule linearSlide;
    private ArmModule arm;
    private DoorModule door;


    public ScoreMacro(LinearSlideModule linearSlideModule, ArmModule armModule, DoorModule doorModule) {
        linearSlide = linearSlideModule;
        arm = armModule;
        door = doorModule;
    }

    /**
     * Macro to score at either a short or tall height
     */
    public void run(/*boolean tall*/ boolean down) throws InterruptedException {
        /*
        if (tall) {
        linearSlide.goToPos(MAX_SLIDE_POS);
        }
        else {
        linearSlide.goToPos(MAX_SLIDE_POS/2f);
        }
        */

        arm.setState(1);
        Thread.sleep(500);

        if(down) linearSlide.goToPos(linearSlide.getPos() - 0.2f);

        door.setState(0);
        Thread.sleep(750);

        if(down) linearSlide.goToPos(linearSlide.getPos() + 0.2f);

        door.setState(1);

        arm.setState(0);
        //Thread.sleep(500);

        //linearSlide.goToPos(0f);

    }

}
