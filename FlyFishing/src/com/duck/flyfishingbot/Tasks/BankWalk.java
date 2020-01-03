package com.duck.flyfishingbot.Tasks;

import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.Movement;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class BankWalk extends Task {

    @Override
    public boolean validate() {

        boolean bankWalk =  Inventory.isFull();
        boolean isInBankArea = com.duck.flyfishingbot.Main.BankArea.contains(Players.getLocal());
        Log.fine("Bankwalk: " + bankWalk);
        return bankWalk && !isInBankArea;
    }

    @Override
    public int execute() {
        Log.fine("Walking to bank!");
        Movement.walkToRandomized(com.duck.flyfishingbot.Main.BankArea.getCenter().randomize(Random.nextInt(1,3)));
        Time.sleepUntil(()-> com.duck.flyfishingbot.Main.BankArea.contains(Players.getLocal()) == true, Random.nextInt(3000,6000));

        return 0;
    }
}
