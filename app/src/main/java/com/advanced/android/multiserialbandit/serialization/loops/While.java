package com.advanced.android.multiserialbandit.serialization.loops;

import com.advanced.android.multiserialbandit.serialization.Block;
import com.advanced.android.multiserialbandit.serialization.Statement;
import com.advanced.android.multiserialbandit.serialization.ifstatements.ConditionExpression;

import java.io.Serializable;

public class While extends Statement {

    private static final long serialVersionUID = 1L;

    private ConditionExpression condition;

    private Block block;

    public While(ConditionExpression condition, Block block) {
        this.condition = condition;
        this.block = block;
    }

    @Override
    public Serializable run() {
        while ((Boolean) condition.run()) {
            block.run();
        }
        return null;
    }
}
