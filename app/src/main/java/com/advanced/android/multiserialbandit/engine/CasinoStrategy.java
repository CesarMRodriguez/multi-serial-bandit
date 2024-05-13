package com.advanced.android.multiserialbandit.engine;

import android.util.Log;

import com.advanced.android.multiserialbandit.serialization.Block;
import com.advanced.android.multiserialbandit.serialization.declarations.SetStatementVariableStatement;
import com.advanced.android.multiserialbandit.serialization.declarations.SetVariableStatement;
import com.advanced.android.multiserialbandit.serialization.ifstatements.EqualExpression;
import com.advanced.android.multiserialbandit.serialization.ifstatements.If;
import com.advanced.android.multiserialbandit.serialization.ifstatements.PrimaryExpression;
import com.advanced.android.multiserialbandit.serialization.methods.Add;
import com.advanced.android.multiserialbandit.serialization.methods.GetLastShotMachineNumber;
import com.advanced.android.multiserialbandit.serialization.methods.GetLastShotWasSuccessFul;
import com.advanced.android.multiserialbandit.serialization.methods.IsFirstTime;
import com.advanced.android.multiserialbandit.serialization.primaryexpressions.IntStatement;

import kotlin.random.Random;

public class CasinoStrategy {

    private static Block intentBlock = null;

    private static void runInternalExample() {
        /*
        if (Casino.getCasinoRun().isFirstTime()) {
            return 1;
        } else {
            if (Casino.getCasinoRun().getLastShot().isWasSuccessful()) {
                return Casino.getCasinoRun().getLastShot().getSelectedMachine();
            } else {
                int selectedMachine = Casino.getCasinoRun().getLastShot().getSelectedMachine();
                if (selectedMachine == 5) {
                    return 1;
                } else {
                    return selectedMachine + 1;
                }
            }
        }*/

        Block wasSuccessFullTrueBlock = new Block().addStatement(new SetStatementVariableStatement("ReturnValue",new GetLastShotMachineNumber()));

        Block equalMachineTrueBlock = new Block().addStatement(new SetVariableStatement("ReturnValue",0));

        Block equalMachineFalseBlock = new Block().addStatement(new SetStatementVariableStatement("ReturnValue",new Add(new GetLastShotMachineNumber(),new IntStatement(1))));
        Block wasSuccessFullFalseBlock = new Block().addStatement(new If(new EqualExpression(new GetLastShotMachineNumber(),new IntStatement(4)),equalMachineTrueBlock, equalMachineFalseBlock));

        Block isFirstFalseBlock = new Block().addStatement(new If(new PrimaryExpression(new GetLastShotWasSuccessFul()),wasSuccessFullTrueBlock, wasSuccessFullFalseBlock));

        Block isFirstTrueBlock = new Block().addStatement(new SetVariableStatement("ReturnValue", 0));
        Block block = new Block().addStatement(new If(new PrimaryExpression(new IsFirstTime()), isFirstTrueBlock, isFirstFalseBlock));

        block.run();
    }

    public static int getMachineToRun() {

        if (Casino.getCasinoRun().isFirstTime()) {
            Environment.getEnvironment().cleanList();
        }

        if (intentBlock != null) {
            intentBlock.run();
        } else {
            runInternalExample();
        }

        Environment environment = Environment.getEnvironment();
        return (Integer) environment.getVariableList().getVariable("ReturnValue");
    }

    public static void setIntentBlock(Block block) {
        intentBlock = block;
    }

    public static void runIntentBlock() {
        if (intentBlock != null) {
            intentBlock.run();
        } else {
            Log.i("CASINO_STRAT","No intent block code was added");
        }
    }
}
