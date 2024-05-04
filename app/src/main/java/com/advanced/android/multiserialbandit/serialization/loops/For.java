package com.advanced.android.multiserialbandit.serialization.loops;

import com.advanced.android.multiserialbandit.serialization.Block;
import com.advanced.android.multiserialbandit.serialization.Statement;
import com.advanced.android.multiserialbandit.serialization.ifstatements.ConditionExpression;

import java.io.Serializable;

public class For extends Statement {

    private Statement forInit;

    private ConditionExpression forValidation;

    private Statement forLoopExecutor;

    private Block forBlock;

    public For(Statement forInit, ConditionExpression forValidation, Statement forLoopExecutor, Block forBlock) {
        this.forInit = forInit;
        this.forValidation = forValidation;
        this.forLoopExecutor = forLoopExecutor;
        this.forBlock = forBlock;
    }

    @Override
    public Serializable run() {

        forInit.run();

        while ((Boolean) forValidation.run()) {
            forBlock.run();
            forLoopExecutor.run();
        }
        return null;
    }
}
