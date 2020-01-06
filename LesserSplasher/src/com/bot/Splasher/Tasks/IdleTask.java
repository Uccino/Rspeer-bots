package com.bot.Splasher.Tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class IdleTask extends Task {
    @Override
    public boolean validate() {
        // Is the player animating?
        // Is the target null?
        // Is there a lesser demon in the area?
        return Players.getLocal().isAnimating() && Players.getLocal().getTarget() != null || Npcs.getNearest(npc -> npc.getName().equals("Lesser demon")) == null;
    }

    @Override
    public int execute() {
        Log.fine("Idleing");
        Time.sleepUntil(()->Npcs.getNearest(npc -> npc.containsAction("Attack")) != null || !Players.getLocal().isAnimating(), 5000);
        return 0;
    }
}
