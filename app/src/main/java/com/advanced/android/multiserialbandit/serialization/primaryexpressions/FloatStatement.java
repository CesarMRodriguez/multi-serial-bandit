package com.advanced.android.multiserialbandit.serialization.primaryexpressions;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class FloatStatement extends Statement {

    private static final long serialVersionUID = 1L;

    private Float value;

    public FloatStatement(Float value) {
        this.value = value;
    }

    @Override
    public Serializable run() {
        return value;
    }
}
