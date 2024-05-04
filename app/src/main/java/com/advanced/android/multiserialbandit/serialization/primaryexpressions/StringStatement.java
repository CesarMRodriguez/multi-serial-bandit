package com.advanced.android.multiserialbandit.serialization.primaryexpressions;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class StringStatement extends Statement {

    private String value;

    public StringStatement(String value) {
        this.value = value;
    }

    @Override
    public Serializable run() {
        return value;
    }
}
