package com.advanced.android.multiserialbandit.serialization.primaryexpressions;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class BooleanStatement extends Statement {

    private static final long serialVersionUID = 1L;

    private Boolean value;

    public BooleanStatement(Boolean value) {
        this.value = value;
    }

    @Override
    public Serializable run() {
        return value;
    }
}
