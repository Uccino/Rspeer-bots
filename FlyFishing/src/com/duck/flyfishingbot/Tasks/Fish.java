package com.duck.flyfishingbot.Tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;

public class Fish extends Task {



    @Override
    public boolean validate() {

        boolean fishSpot =  Npcs.getNearest(npc -> npc.containsAction("Lure")) != null;
        boolean invFull = Inventory.isEmpty();

        return fishSpot && !invFull;
    }

    @Override
    public int execute() {
        if(!Players.getLocal().isAnimating() && !Inventory.isFull())
        {
            Npc fishSpot = Npcs.getNearest(npc -> npc.containsAction("Lure"));
            if(fishSpot != null)
            {
                fishSpot.interact("Lure");
                Time.sleep(Random.nextInt(300,600));
            }
        }

        return 0;
    }
}
