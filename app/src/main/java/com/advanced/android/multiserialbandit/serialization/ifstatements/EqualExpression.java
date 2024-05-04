package com.advanced.android.multiserialbandit.serialization.ifstatements;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class EqualExpression extends ConditionExpression {


    private Statement value1;

    private Statement value2;

    public EqualExpression(Statement value1, Statement value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public Serializable run() {

        Serializable val1 = value1.run();
        Serializable val2 = value2.run();
        if (val1 == null) {
            if (val2 == null) {
                return new Boolean(true);
            } else {
                return new Boolean(false);
            }
        }
        return val1.equals(val2);
    }
}
