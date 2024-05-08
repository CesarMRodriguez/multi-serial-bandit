package com.advanced.android.multiserialbandit.serialization.ifstatements;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class LeExpression extends ConditionExpression {

    private static final long serialVersionUID = 1L;

    private Statement value1;

    private Statement value2;

    public LeExpression(Statement value1, Statement value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public Serializable run() {

        Serializable val1 = value1.run();
        Serializable val2 = value2.run();
        if (val1 instanceof Integer && val2 instanceof Integer) {
            Integer int1 = (Integer) val1;
            Integer int2 = (Integer) val2;
            return new Boolean(int1 <= int2);
        }
        if (val1 instanceof Double && val2 instanceof Double) {
            Double var1 = (Double) val1;
            Double var2 = (Double) val2;
            return new Boolean(var1 <= var2);
        }
        if (val1 instanceof Float && val2 instanceof Float) {
            Float var1 = (Float) val1;
            Float var2 = (Float) val2;
            return new Boolean(var1 <= var2);
        }
        if (val1 instanceof Long && val2 instanceof Long) {
            Long var1 = (Long) val1;
            Long var2 = (Long) val2;
            return new Boolean(var1 <= var2);
        }
        return new Boolean(false);
    }
}
