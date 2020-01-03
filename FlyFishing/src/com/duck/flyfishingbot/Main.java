package com.duck.flyfishingbot;

import com.duck.flyfishingbot.Tasks.*;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;


@ScriptMeta(developer = "TheDuck", name = "DuckyFish", desc = "Fishes trout and salmon in barbarian village", version = 1.03)
public class Main extends Script {

    public static Area FishingArea = Area.rectangular(3097,3424,3110,3436);
    public static Area BankArea = Area.rectangular(3092, 3489,3094,3492);

    private static final Task[] BotTasks = {new FishingWalk(), new Fish(), new BankWalk(), new Banking()};
    private Idle idleTask;

    @Override
    public void onStart()
    {
        idleTask  = new Idle();
    }

    @Override
    public int loop()
    {
        // Don't do anything if we're fishing already
        if(!Players.getLocal().isAnimating() || Players.getLocal().getAnimation() != 623)
        {
            for (int i = 0; i < BotTasks.length ; i ++)
            {
                if(BotTasks[i].validate())
                {
                    BotTasks[i].execute();
                }

                Time.sleep(Random.nextInt(250,350));
            }
        }

        if(idleTask.validate())
        {
            idleTask.execute();
        }

        if(!Movement.isRunEnabled() && Movement.getRunEnergy() > Random.nextInt(35,50)){
            Movement.toggleRun(true);
        }

        return Random.nextInt(500,2500);
    }
}
