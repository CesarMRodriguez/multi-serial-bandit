package com.advanced.android.multiserialbandit.serialization.primaryexpressions;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class LongStatement extends Statement {

    private static final long serialVersionUID = 1L;

    private Long value;

    public LongStatement(Long value) {
        this.value = value;
    }

    @Override
    public Serializable run() {
        return value;
    }
}
