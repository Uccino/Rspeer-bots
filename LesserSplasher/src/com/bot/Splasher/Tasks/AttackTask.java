package com.bot.Splasher.Tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class AttackTask extends Task {
    @Override
    public boolean validate() {
        boolean isDemonPresent = Npcs.getNearest(npc->npc.getName().equals("Lesser demon")) != null;
        boolean hasTarget =  Players.getLocal().getTarget() != null;

        Log.fine("Has target: " + hasTarget );
        Log.fine("Is demon present: " + isDemonPresent );

        return isDemonPresent && !hasTarget;
    }

    @Override
    public int execute() {
        Log.fine("Attacking!");
        Npc demon = Npcs.getNearest(npc->npc.getName().equals("Lesser demon"));
        demon.interact("Attack");
        return 0;
    }
}
