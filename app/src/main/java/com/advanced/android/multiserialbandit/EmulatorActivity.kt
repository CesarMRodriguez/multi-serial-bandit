package com.advanced.android.multiserialbandit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.advanced.android.multiserialbandit.engine.Casino
import com.advanced.android.multiserialbandit.engine.CasinoStrategy
import com.advanced.android.multiserialbandit.serialization.Block
import com.advanced.android.multiserialbandit.ui.theme.MultiserialbanditTheme

class EmulatorActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myBlock: Block? = intent.getSerializableExtra("intent_block") as Block?
        myBlock?.let {
            CasinoStrategy.setIntentBlock(myBlock)
        }

        setContent {
            EmulatorScreen()
        }
    }
}

@Composable
fun EmulatorScreen() {

    var rows = remember { mutableStateOf(listOf<DataRow>()) }
    var runs = remember { mutableStateOf(0) }
    var dollars = remember { mutableStateOf(0) }

    MultiserialbanditTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { start(rows, runs, dollars) }) {
                        Text("Start")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { nextStep(rows, runs, dollars) }) {
                        Text("Next Step")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { next100(rows, runs, dollars) }) {
                        Text("Next 100")
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Runs: ${runs.value}")
                    Text("$$: ${dollars.value}")
                }
                Spacer(modifier = Modifier.height(16.dp))
                rows.value.forEachIndexed() { index, row ->
                    RowItem(row)
                    if (index < rows.value.size - 1) {
                        Spacer(modifier = Modifier.height(8.dp)) // Add space between rows
                    }
                }
            }
        }
    }
}

@Composable
fun RowItem(row: DataRow) {
    Row (
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        row.squares.forEach { square ->
            Square(square)
        }
    }
}

@Composable
fun Square(square: DataSquare) {
    val borderColor = Color.Black
    val backgroundColor = if (square.selected) {
        if (square.random <= square.percentage) {
            Color.Green
        } else {
            Color.Red
        }
    } else {
        Color.White
    }

    Box(
        modifier = Modifier
            .size(50.dp)
            .background(backgroundColor)
            .border(1.dp, borderColor)
    ) {
        Text(
            text = "${square.content}",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

data class DataRow(val squares: List<DataSquare>)

data class DataSquare(val percentage: Int, val random: Int, val selected: Boolean, val content: String)

fun start(rows: MutableState<List<DataRow>>, runs: MutableState<Int>, dollars: MutableState<Int>) {
    val casino = Casino.getCasinoRun();
    Casino.resetCasino()
    rows.value = listOf(generateInitialRow())
    runs.value = 0
    dollars.value = casino.amountMoney
}

fun nextStep(rows: MutableState<List<DataRow>>, runs: MutableState<Int>, dollars: MutableState<Int>) {
    val casino = Casino.getCasinoRun()

    //TODO: here goes the strategy to deserialize
    val machineToPull = CasinoStrategy.getMachineToRun()
    casino.pullMachine(machineToPull)
    rows.value = rows.value + generateRow()

    runs.value += 1
    dollars.value = casino.amountMoney
}

fun next100(rows: MutableState<List<DataRow>>, runs: MutableState<Int>, dollars: MutableState<Int>) {
    var newRows = rows.value
    val casino = Casino.getCasinoRun()

    var cargar = 0
    repeat(100) {
        if (!casino.finished()) {
            val machineToPull = CasinoStrategy.getMachineToRun()
            casino.pullMachine(machineToPull)
            newRows += generateRow()
            cargar += 1
        }
    }
    rows.value = newRows
    runs.value += cargar
    dollars.value = casino.amountMoney
}

fun generateRow(): DataRow {
    val squares = mutableListOf<DataSquare>()
    val casino = Casino.getCasinoRun()
    val lastRun = casino.lastShot
    casino.slotMachines.forEachIndexed() { index, slotMachine ->
        squares.add(generateSquare(lastRun.selectedMachine == index, lastRun.isWasSuccessful, slotMachine.quantitySuccess.toString() + "/" + slotMachine.quantityRun.toString()))
    }
    return DataRow(squares)
}

fun generateInitialRow(): DataRow {
    val squares = mutableListOf<DataSquare>()
    val casino = Casino.getCasinoRun()
    casino.slotMachines.forEach {
            slotMachine -> squares.add(generateInitialSquare(slotMachine.successRate))
    }
    return DataRow(squares)
}

fun generateSquare(selected: Boolean, wasSuccessful: Boolean, content: String): DataSquare {

    if (selected && wasSuccessful) {
        return DataSquare(50, 1, selected, content)
    } else {
        return DataSquare(1, 50, selected, content)
    }
}

fun generateInitialSquare(succesValue: Int): DataSquare {
    return DataSquare(succesValue, 150, selected = false, content = succesValue.toString())
}