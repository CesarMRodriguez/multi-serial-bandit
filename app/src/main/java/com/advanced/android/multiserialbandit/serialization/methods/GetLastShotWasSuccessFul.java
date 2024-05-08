package com.advanced.android.multiserialbandit.serialization.methods;

import com.advanced.android.multiserialbandit.engine.Casino;
import com.advanced.android.multiserialbandit.engine.Shot;
import com.advanced.android.multiserialbandit.serialization.Statement;

import java.io.Serializable;

public class GetLastShotWasSuccessFul  extends Statement {

    private static final long serialVersionUID = 1L;

    @Override
    public Serializable run() {

        Shot lastShot = Casino.getCasinoRun().getLastShot();
        if (lastShot == null) {
            return false;
        } else {
            return lastShot.isWasSuccessful();
        }
    }
}
