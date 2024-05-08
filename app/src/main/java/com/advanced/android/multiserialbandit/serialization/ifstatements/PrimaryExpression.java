package com.advanced.android.multiserialbandit.serialization.ifstatements;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class PrimaryExpression extends ConditionExpression {

    private static final long serialVersionUID = 1L;

    private Statement value1;

    public PrimaryExpression(Statement value1) {
        this.value1 = value1;
    }

    @Override
    public Serializable run() {
        return value1.run();
    }
}