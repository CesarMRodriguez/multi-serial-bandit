package com.advanced.android.multiserialbandit.serialization.declarations;

import com.advanced.android.multiserialbandit.engine.Environment;

import java.io.Serializable;

public class SetVariableStatement extends DeclarationStatement implements Serializable {

    private static final long serialVersionUID = 1L;


    private String name;
    private Serializable value;

    public SetVariableStatement(String name, Serializable value) {
        this.name = name;
        this.value = value;
    }


    public Serializable run() {
        Environment.getEnvironment().getVariableList().setVariable(name, value);
        return null;
    }

}
