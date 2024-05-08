package com.advanced.android.multiserialbandit.serialization.ifstatements;

import java.io.Serializable;

public class NotExpression extends ConditionExpression {

    private static final long serialVersionUID = 1L;

    private ConditionExpression conditionExpression;

    public NotExpression(ConditionExpression conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    @Override
    public Serializable run() {
        Boolean value = (Boolean) conditionExpression.run();
        return !value;
    }
}
