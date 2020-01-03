package com.duck.flyfishingbot.Tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.runetek.api.scene.SceneObjects;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class FishingWalk extends Task {




    @Override
    public boolean validate(){

        boolean invIsEmpty = !Inventory.isFull();
        boolean isInFishingSpot = com.duck.flyfishingbot.Main.FishingArea.contains(Players.getLocal()) || SceneObjects.getNearest(sceneObject -> sceneObject.containsAction("Lure")) != null;
        boolean isFishing = Players.getLocal().getAnimation() == 623;

        if(invIsEmpty && !isInFishingSpot && !isFishing)
        {
            return true;
        }

        return false;
    }

    @Override
    public int execute()
    {
        Log.fine("Walking to fishing spot");
        Movement.walkToRandomized(com.duck.flyfishingbot.Main.FishingArea.getCenter().randomize(Random.nextInt(1,6)));
        Time.sleepUntil(()-> com.duck.flyfishingbot.Main.FishingArea.contains(Players.getLocal()), Random.nextInt(3000,6000));

        return 0;
    }
}
