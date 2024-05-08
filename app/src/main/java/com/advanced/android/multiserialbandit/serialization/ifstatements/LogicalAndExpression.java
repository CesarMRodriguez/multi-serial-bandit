package com.advanced.android.multiserialbandit.serialization.ifstatements;

import java.io.Serializable;

public class LogicalAndExpression extends ConditionExpression {

    private static final long serialVersionUID = 1L;

    private ConditionExpression conditionExpression1;

    private ConditionExpression conditionExpression2;

    public LogicalAndExpression(ConditionExpression conditionExpression1, ConditionExpression conditionExpression2) {
        this.conditionExpression1 = conditionExpression1;
        this.conditionExpression2 = conditionExpression2;
    }

    public Serializable run() {

        Boolean value1 = (Boolean) conditionExpression1.run();
        Boolean value2 = (Boolean) conditionExpression2.run();
        return value1 && value2;
    }
}
