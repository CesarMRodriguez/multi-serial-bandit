package com.advanced.android.multiserialbandit.serialization;

import java.io.Serializable;
import java.util.LinkedList;

public class Block implements Serializable {

    private static final long serialVersionUID = 1L;

    private LinkedList<Statement> statements;

    public Block(LinkedList<Statement> statements) {
        this.statements = statements;
    }

    public Block() {
        this.statements = new LinkedList<>();
    }

    public Block addStatement(Statement statement) {
        this.statements.add(statement);
        return this;
    }

    public void run() {
        for (Statement statement:
             statements) {
            statement.run();
        }
    }
}
