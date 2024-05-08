package com.advanced.android.multiserialbandit.serialization.methods;

import com.advanced.android.multiserialbandit.engine.Casino;
import com.advanced.android.multiserialbandit.engine.Shot;
import com.advanced.android.multiserialbandit.engine.SlotMachine;
import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class GetMachineTries extends Statement {

    private Statement machineSelector;

    private static final long serialVersionUID = 1L;

    public GetMachineTries(Statement machineSelector) {
        this.machineSelector = machineSelector;
    }

    @Override
    public Serializable run() {

        Serializable machineNumber = machineSelector.run();
        if (machineNumber instanceof Integer) {
            SlotMachine slotMachine = Casino.getCasinoRun().getSlotMachines().get((Integer)machineNumber);
            return slotMachine.getQuantityRun();
        } else {
            return 0;
        }
    }
}
