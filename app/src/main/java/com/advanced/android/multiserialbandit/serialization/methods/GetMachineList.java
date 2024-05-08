package com.advanced.android.multiserialbandit.serialization.methods;

import com.advanced.android.multiserialbandit.engine.Casino;
import com.advanced.android.multiserialbandit.engine.SlotMachine;
import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class GetMachineList extends Statement {

    private static final long serialVersionUID = 1L;

    @Override
    public Serializable run() {

        return (Serializable) Casino.getCasinoRun().getSlotMachines();
    }
}
