package com.advanced.android.multiserialbandit.serialization.ifstatements;

import com.advanced.android.multiserialbandit.serialization.Block;
import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class If extends Statement {

    private static final long serialVersionUID = 1L;

    private ConditionExpression conditionExpression;

    private Block trueBlock;

    private Block falseBlock;

    public If(ConditionExpression conditionExpression, Block trueBlock, Block falseBlock) {
        this.conditionExpression = conditionExpression;
        this.trueBlock = trueBlock;
        this.falseBlock = falseBlock;
    }

    @Override
    public Serializable run() {
        Boolean result = (Boolean) conditionExpression.run();
        if (result && trueBlock != null) {
            trueBlock.run();
        }
        if (!result && falseBlock != null) {
            falseBlock.run();
        }
        return null;
    }
}
