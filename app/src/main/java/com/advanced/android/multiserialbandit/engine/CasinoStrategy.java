package com.advanced.android.multiserialbandit.engine;

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

    public static int getMachineToRun() {

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

        if (Casino.getCasinoRun().isFirstTime()) {
            Environment.getEnvironment().cleanList();
        }

        Block wasSuccessFullTrueBlock = new Block().addStatement(new SetStatementVariableStatement("ReturnValue",new GetLastShotMachineNumber()));

        Block equalMachineTrueBlock = new Block().addStatement(new SetVariableStatement("ReturnValue",0));

        Block equalMachineFalseBlock = new Block().addStatement(new SetStatementVariableStatement("ReturnValue",new Add(new GetLastShotMachineNumber(),new IntStatement(1))));
        Block wasSuccessFullFalseBlock = new Block().addStatement(new If(new EqualExpression(new GetLastShotMachineNumber(),new IntStatement(4)),equalMachineTrueBlock, equalMachineFalseBlock));

        Block isFirstFalseBlock = new Block().addStatement(new If(new PrimaryExpression(new GetLastShotWasSuccessFul()),wasSuccessFullTrueBlock, wasSuccessFullFalseBlock));

        Block isFirstTrueBlock = new Block().addStatement(new SetVariableStatement("ReturnValue", 0));
        Block block = new Block().addStatement(new If(new PrimaryExpression(new IsFirstTime()), isFirstTrueBlock, isFirstFalseBlock));

        block.run();

        Environment environment = Environment.getEnvironment();
        return (Integer) environment.getVariableList().getVariable("ReturnValue");
    }
}
