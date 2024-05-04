package com.advanced.android.multiserialbandit.serialization.primaryexpressions;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class DoubleStatement extends Statement {

    private Double value;

    public DoubleStatement(Double value) {
        this.value = value;
    }

    @Override
    public Serializable run() {
        return value;
    }
}
