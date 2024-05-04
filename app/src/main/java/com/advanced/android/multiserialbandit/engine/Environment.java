package com.advanced.android.multiserialbandit.engine;

public class Environment {

    //I create for this example only one environment at a time for a run
    private static Environment environment;

    public static Environment getEnvironment() {
        if (environment == null) {
            environment = new Environment();
        }
        return environment;
    }

    private VariableList variableList;

    private Environment() {
        variableList = new VariableList();
    }

    public VariableList getVariableList() {
        return variableList;
    }

    public void cleanList() {
        variableList.clean();
    }

}
