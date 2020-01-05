package com.bot.Splasher.Tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.script.task.Task;
import org.rspeer.script.task.TaskScript;

public class RandomEventTask extends Task {
    @Override
    public boolean validate() {
        // Is there a random event happening?
        return Npcs.getLoaded(npc -> npc.containsAction("Dismiss")) != null;
    }

    @Override
    public int execute() {
        Npc randomNpc = Npcs.getNearest(npc -> npc.containsAction("Dismiss"));
        Time.sleep(Random.nextInt(1000,5000));
        randomNpc.interact("Dismiss");

        return 0;
    }
}
