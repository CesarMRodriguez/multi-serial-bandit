package com.advanced.android.multiserialbandit.engine;

import java.io.Serializable;

public class Shot implements Serializable {

    private int selectedMachine;

    private boolean wasSuccessful = false;


    public Shot(int selectedMachine, boolean wasSuccessful) {
        this.selectedMachine = selectedMachine;
        this.wasSuccessful = wasSuccessful;
    }

    public int getSelectedMachine() {
        return selectedMachine;
    }

    public void setSelectedMachine(int selectedMachine) {
        this.selectedMachine = selectedMachine;
    }

    public boolean isWasSuccessful() {
        return wasSuccessful;
    }

    public void setWasSuccessful(boolean wasSuccessful) {
        this.wasSuccessful = wasSuccessful;
    }
}
