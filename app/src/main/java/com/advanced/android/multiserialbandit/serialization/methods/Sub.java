package com.advanced.android.multiserialbandit.serialization.methods;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class Sub extends Statement {

    private static final long serialVersionUID = 1L;

    private Statement operator1;
    private Statement operator2;

    public Sub(Statement operator1, Statement operator2) {
        this.operator1 = operator1;
        this.operator2 = operator2;
    }

    @Override
    public Serializable run() {

        Serializable val1 = operator1.run();
        Serializable val2 = operator2.run();
        if (val1 instanceof Integer && val2 instanceof Integer) {
            Integer int1 = (Integer) val1;
            Integer int2 = (Integer) val2;
            return int1 - int2;
        }
        if (val1 instanceof Double && val2 instanceof Double) {
            Double var1 = (Double) val1;
            Double var2 = (Double) val2;
            return var1 - var2;
        }
        if (val1 instanceof Float && val2 instanceof Float) {
            Float var1 = (Float) val1;
            Float var2 = (Float) val2;
            return var1 - var2;
        }
        if (val1 instanceof Long && val2 instanceof Long) {
            Long var1 = (Long) val1;
            Long var2 = (Long) val2;
            return var1 - var2;
        }

        Number number1 = (Number) val1;
        Number number2 = (Number) val2;
        return number1.doubleValue() - number2.doubleValue();
    }
}
