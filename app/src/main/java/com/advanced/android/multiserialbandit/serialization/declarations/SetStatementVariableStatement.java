package com.advanced.android.multiserialbandit.serialization.declarations;

import com.advanced.android.multiserialbandit.engine.Environment;
import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class SetStatementVariableStatement extends DeclarationStatement implements Serializable {

    private static final long serialVersionUID = 1L;


    private String name;
    private Statement value;

    public SetStatementVariableStatement(String name, Statement value) {
        this.name = name;
        this.value = value;
    }


    public Serializable run() {
        Environment.getEnvironment().getVariableList().setVariable(name, value.run());
        return null;
    }
}
