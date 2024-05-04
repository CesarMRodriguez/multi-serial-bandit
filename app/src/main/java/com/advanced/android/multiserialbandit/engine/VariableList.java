package com.advanced.android.multiserialbandit.engine;

import java.io.Serializable;
import java.util.HashMap;

public class VariableList {

    private final HashMap<String, Serializable> variableList = new HashMap<>();

    public Serializable getVariable(String key) {
        if (variableList.containsKey(key)) {
            return variableList.get(key);
        } else {
            return null;
        }
    }

    public void setVariable(String key, Serializable value) {
        variableList.put(key, value);
    }

    public void deleteVariable(String key) {
        variableList.remove(key);
    }

    public void clean() {
        variableList.clear();
    }
}
