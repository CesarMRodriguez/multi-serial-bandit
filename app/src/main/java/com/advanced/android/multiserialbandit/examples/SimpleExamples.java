package com.advanced.android.multiserialbandit.examples;

import android.util.Log;

import com.advanced.android.multiserialbandit.engine.Environment;
import com.advanced.android.multiserialbandit.engine.VariableList;
import com.advanced.android.multiserialbandit.serialization.Block;
import com.advanced.android.multiserialbandit.serialization.Statement;
import com.advanced.android.multiserialbandit.serialization.declarations.GetVariableStatement;
import com.advanced.android.multiserialbandit.serialization.declarations.SetStatementVariableStatement;
import com.advanced.android.multiserialbandit.serialization.declarations.SetVariableStatement;
import com.advanced.android.multiserialbandit.serialization.ifstatements.GtExpression;
import com.advanced.android.multiserialbandit.serialization.ifstatements.If;
import com.advanced.android.multiserialbandit.serialization.ifstatements.LeExpression;
import com.advanced.android.multiserialbandit.serialization.loops.While;
import com.advanced.android.multiserialbandit.serialization.methods.Add;
import com.advanced.android.multiserialbandit.serialization.methods.Print;
import com.advanced.android.multiserialbandit.serialization.primaryexpressions.IntStatement;
import com.advanced.android.multiserialbandit.serialization.primaryexpressions.StringStatement;

import java.util.LinkedList;

public class SimpleExamples {

    public static void addTest() {
        Environment environment = Environment.getEnvironment();
        VariableList variableList = environment.getVariableList();

        LinkedList<Statement> statements = new LinkedList<>();
        statements.add(
                new SetStatementVariableStatement("add_result",
                        new Add(new IntStatement(5),
                                new IntStatement(5))));
        Block block = new Block(statements);
        block.run();

        Log.d("Add Result:", variableList.getVariable("add_result").toString());
    }

    public static void whileExample() {

        /**
         * i=100
         *  while(i<=200):
         *    print(i)
         *    i+=20
         */
        Environment environment = Environment.getEnvironment();


        Statement initStament = new SetStatementVariableStatement("i", new IntStatement(100));

        //internal while block
        Block internalWhileBlock = new Block().addStatement(new Print("test",new GetVariableStatement("i")))
                .addStatement(new Add(new GetVariableStatement("i"),
                        new IntStatement(20))
                );
        While whileStatement = new While(new LeExpression(
                                            new GetVariableStatement("i"), new IntStatement(200)),internalWhileBlock);

        Block block = new Block().addStatement(initStament)
                .addStatement(whileStatement);
        block.run();

    }

    public static void ifExample() {
        /**
         * income=2500
         * if income>2500:
         *  tax=500
         * else
         *  tax=200
         * print(tax)
         */
        Environment environment = Environment.getEnvironment();


        Statement setIncome = new SetStatementVariableStatement("income", new IntStatement(2500));

        //if statement
        Block ifBlockTrue = new Block().addStatement(new SetVariableStatement("tax", 500));
        Block ifBlockFalse = new Block().addStatement(new SetVariableStatement("tax",200));
        If ifStatement = new If(new GtExpression(new GetVariableStatement("income"),new IntStatement(2500)),ifBlockTrue,ifBlockFalse);

        Block block = new Block().addStatement(setIncome)
                .addStatement(ifStatement)
                .addStatement(new Print("print_tax", new GetVariableStatement("tax")));

        block.run();
    }
}
