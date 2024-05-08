package com.advanced.android.multiserialbandit.serialization.methods;

import com.advanced.android.multiserialbandit.engine.Casino;
import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

import kotlin.random.Random;

public class RandomInt extends Statement {

    private Statement minimumValue;

    private Statement maximumValue;
    private static final long serialVersionUID = 1L;

    public RandomInt(Statement minimumValue, Statement maximumValue) {
        this.minimumValue = minimumValue;
        this.maximumValue = maximumValue;
    }

    @Override
    public Serializable run() {
        Integer minInt = (Integer) minimumValue.run();
        Integer maxInt = (Integer) maximumValue.run();
        return Random.Default.nextInt(minInt,maxInt);
    }


}
