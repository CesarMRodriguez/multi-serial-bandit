package com.advanced.android.multiserialbandit.serialization.methods;

import android.util.Log;

import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class Print extends Method {

    private String tag;

    private Statement statement;


    public Print(String tag, Statement statement) {
        this.tag = tag;
        this.statement = statement;
    }

    @Override
    public Serializable run() {
        Serializable toPrint = statement.run();
        if (toPrint != null) {
            Log.d(tag, toPrint.toString());
        } else {
            Log.d(tag, "null");
        }
        return null;
    }
}
