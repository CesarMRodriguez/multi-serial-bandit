package com.advanced.android.multiserialbandit.serialization.declarations;

import com.advanced.android.multiserialbandit.engine.Environment;

import java.io.Serializable;

public class GetVariableStatement extends DeclarationStatement implements Serializable {

    private static final long serialVersionUID = 1L;


    private String name;

    public GetVariableStatement(String name) {
        this.name = name;
    }


    public Serializable run() {
        return Environment.getEnvironment().getVariableList().getVariable(name);
    }
}
