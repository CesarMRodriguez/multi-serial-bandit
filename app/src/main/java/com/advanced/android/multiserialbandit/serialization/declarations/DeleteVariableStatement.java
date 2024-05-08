package com.advanced.android.multiserialbandit.serialization.declarations;

import com.advanced.android.multiserialbandit.engine.Environment;

import java.io.Serializable;

public class DeleteVariableStatement extends DeclarationStatement implements Serializable {

    private static final long serialVersionUID = 1L;


    private String name;

    public DeleteVariableStatement(String name) {
        this.name = name;
    }

    public Serializable run() {
        Environment.getEnvironment().getVariableList().deleteVariable(name);
        return null;
    }
}
