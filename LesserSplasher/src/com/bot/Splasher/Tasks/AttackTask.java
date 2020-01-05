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
        return Npcs.getNearest(npc->npc.getName().equals("Lesser demon")) != null;
    }

    @Override
    public int execute() {
        Log.fine("Attacking!");
        Npc demon = Npcs.getNearest(npc->npc.getName().equals("Lesser demon"));
        demon.interact("Attack");
        Time.sleepWhile(()->Players.getLocal().getTarget() != null, 30000);

        return 0;
    }
}
