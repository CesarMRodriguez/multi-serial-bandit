package com.advanced.android.multiserialbandit.serialization.primaryexpressions;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class IntStatement extends Statement {

    private static final long serialVersionUID = 1L;

    private Integer value;

    public IntStatement(Integer value) {
        this.value = value;
    }

    @Override
    public Serializable run() {
        return value;
    }
}
