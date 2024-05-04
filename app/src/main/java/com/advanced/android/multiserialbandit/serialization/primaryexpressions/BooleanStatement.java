package com.advanced.android.multiserialbandit.serialization.primaryexpressions;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class BooleanStatement extends Statement {

    private Boolean value;

    public BooleanStatement(Boolean value) {
        this.value = value;
    }

    @Override
    public Serializable run() {
        return value;
    }
}
