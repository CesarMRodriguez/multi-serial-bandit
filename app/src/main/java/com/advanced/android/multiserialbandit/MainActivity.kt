package com.advanced.android.multiserialbandit

import android.content.Intent
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
import com.advanced.android.multiserialbandit.examples.SimpleExamples
import com.advanced.android.multiserialbandit.serialization.Block
import com.advanced.android.multiserialbandit.ui.theme.MultiserialbanditTheme
import java.io.Serializable
import kotlin.random.Random

class MainActivity : ComponentActivity() {
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        val myBlock: Block? = intent.getSerializableExtra("intent_block") as Block?
        CasinoStrategy.setIntentBlock(myBlock)
        setContent {
            MainScreen(
                example = myBlock,
                navigateToEmulator = { val intent = Intent(applicationContext, EmulatorActivity::class.java)
                    startActivity(intent) },
                executeJavaMethod = { SimpleExamples.addTest() },
                runActionOnExample = { CasinoStrategy.runIntentBlock() }
            )
        }
    }
}
}

@Composable
fun MainScreen(
    example: Block? = null,
    navigateToEmulator: () -> Unit,
    executeJavaMethod: () -> Unit,
    runActionOnExample: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting()
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = navigateToEmulator) {
            Text("Navigate to EmulatorActivity")
        }
        Button(onClick = executeJavaMethod) {
            Text("Execute Java Method")
        }
        example?.let {
            Button(onClick = { runActionOnExample() }) {
                Text("Run Action on Example")
            }
        }

    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "Let's serialize!",
        modifier = modifier
    )
}
