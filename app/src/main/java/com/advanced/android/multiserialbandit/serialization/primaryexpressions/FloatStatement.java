package com.advanced.android.multiserialbandit.serialization.primaryexpressions;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class FloatStatement extends Statement {

    private Float value;

    public FloatStatement(Float value) {
        this.value = value;
    }

    @Override
    public Serializable run() {
        return value;
    }
}
