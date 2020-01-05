package com.bot.Splasher;

import com.bot.Splasher.Tasks.AttackTask;
import com.bot.Splasher.Tasks.IdleTask;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.script.Script;
import org.rspeer.script.ScriptMeta;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

@ScriptMeta(name = "Lesser demon splasher", desc = "Splashes at the lesser demon at the wizard's tower", developer = "TheDuck", version = 1.0)
public class Main extends Script {

    Task[] Tasks = {
            new IdleTask(),
            new AttackTask()
    };

    public void onStart()
    {
        Log.fine("Make sure you're in the wizard's tower with mind runes and an air battlestaff");
        Log.fine("I haven't done much to really make this a good bot, it works and that was my intention.");
    }

    @Override
    public int loop() {

        if(Inventory.getCount(item -> item.getName().equals("Mind rune"))> 0 )
        {
            for (int i = 0; i < Tasks.length; i++) {
                if(Tasks[i].validate()){
                    Tasks[i].execute();
                }
            }
        }
        else
        {
            Log.fine("No mind runes found!");
        }

        return Random.nextInt(3000,5000);

    }
}
