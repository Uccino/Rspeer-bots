package com.duck.flyfishingbot.Tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Idle extends Task {
    @Override
    public boolean validate() {
        return Players.getLocal().isAnimating() || Players.getLocal().getAnimation() == 623;
    }

    @Override
    public int execute() {
        while (true)
        {
            if(Players.getLocal().isAnimating())
            {
                Time.sleep(Random.nextInt(3000,6000));
            }
            else {
                return 0;
            }
        }
    }
}
