package com.advanced.android.multiserialbandit.engine;

import java.util.LinkedList;
import java.util.List;

import kotlin.random.Random;

public class Casino {

    private static Casino casinoRun = null;

    private Shot lastShot = null;

    private int amountMoney = 0;

    public static Casino getCasinoRun() {
        if (casinoRun == null) {
            //TODO: set this as a parameter
            casinoRun = new Casino(5, 100);
        }
        return casinoRun;
    }
    private List<SlotMachine> slotMachines;

    private List<Shot> historicShots;

    public Casino (int amountMachines, int money) {
        startUp(amountMachines, money);
    }

    private void startUp(int amountMachines, int money) {
        slotMachines = new LinkedList<>();
        historicShots = new LinkedList<>();
        amountMoney = money;
        lastShot = null;
        for (int i = 0; i < amountMachines; i++) {
            slotMachines.add(new SlotMachine(Random.Default.nextInt(1,49)));
        }
    }

    public static void resetCasino() {
       Casino casino = Casino.casinoRun.getCasinoRun();
        if (casino.historicShots.size() > 0) {
            casino.startUp(5, 100);
        }
    }

    public Shot pullMachine(int machine) {
        this.amountMoney -= 1;
        int value = Random.Default.nextInt(1, 100);
        SlotMachine slotMachine = slotMachines.get(machine);
        Shot shot = null;
        if (slotMachine.getSuccessRate() >= value) {
            slotMachine.setSuccessFulRun();
            this.amountMoney += 3;
            shot = new Shot(machine, true);
        } else {
            slotMachine.setFailureRun();
            shot = new Shot(machine, false);
        }
        this.lastShot = shot;
        this.historicShots.add(shot);
        return shot;
    }

    public boolean finished() {
        return amountMoney <= 0;
    }

    public boolean isFirstTime() { return lastShot == null; }
    public Shot getLastShot() {
        return lastShot;
    }

    public void setLastShot(Shot lastShot) {
        this.lastShot = lastShot;
    }

    public int getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(int amountMoney) {
        this.amountMoney = amountMoney;
    }

    public List<SlotMachine> getSlotMachines() {
        return slotMachines;
    }

    public void setSlotMachines(List<SlotMachine> slotMachines) {
        this.slotMachines = slotMachines;
    }

    public List<Shot> getHistoricShots() {
        return historicShots;
    }

    public void setHistoricShots(List<Shot> historicShots) {
        this.historicShots = historicShots;
    }
}
