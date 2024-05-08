package com.advanced.android.multiserialbandit.engine;

public class SlotMachine {

    private int successRate;

    private int quantityRun;

    private int quantitySuccess;


    public SlotMachine(int successRate) {
        this.successRate = successRate;
    }

    public int getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(int successRate) {
        this.successRate = successRate;
    }

    public int getQuantityRun() {
        return quantityRun;
    }

    public void setQuantityRun(int quantityRun) {
        this.quantityRun = quantityRun;
    }

    public int getQuantitySuccess() {
        return quantitySuccess;
    }

    public void setQuantitySuccess(int quantitySuccess) {
        this.quantitySuccess = quantitySuccess;
    }

    public void setSuccessFulRun() {
        this.quantitySuccess += 1;
        this.quantityRun += 1;
    }

    public void setFailureRun() {
        this.quantityRun += 1;
    }
}
