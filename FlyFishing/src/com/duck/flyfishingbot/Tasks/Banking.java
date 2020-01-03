package com.duck.flyfishingbot.Tasks;

import org.rspeer.runetek.adapter.scene.Npc;
import org.rspeer.runetek.api.commons.BankLocation;
import org.rspeer.runetek.api.commons.Time;
import org.rspeer.runetek.api.commons.math.Random;
import org.rspeer.runetek.api.component.Bank;
import org.rspeer.runetek.api.component.tab.Inventory;
import org.rspeer.runetek.api.movement.position.Area;
import org.rspeer.runetek.api.scene.Npcs;
import org.rspeer.runetek.api.scene.Players;
import org.rspeer.script.task.Task;
import org.rspeer.ui.Log;

public class Banking extends Task {

    Area BankArea = Area.rectangular(3092,3488,3094,3493);

    @Override
    public boolean validate() {
        boolean bankWalk =  Inventory.isFull();
        boolean isInBankArea = BankArea.contains(Players.getLocal());
        Npc banker = Npcs.getNearest(npc->npc.containsAction("Bank"));

        Log.fine("Bankwalk: " + bankWalk);

        return bankWalk && isInBankArea && banker != null;
    }

    @Override
    public int execute() {
        org.rspeer.runetek.api.component.Bank.open(BankLocation.EDGEVILLE);
        Time.sleepWhile(()-> Bank.isClosed(), 5000);
        if(org.rspeer.runetek.api.component.Bank.isOpen())
        {
            org.rspeer.runetek.api.component.Bank.depositAll(335);
            Time.sleep(Random.nextInt(300,600));
            org.rspeer.runetek.api.component.Bank.depositAll(331);

            Time.sleep(500, Random.nextInt(600,1200));
        }

        return 0;
    }
}
