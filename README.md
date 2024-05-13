# multi-serial-bandit

In this application you will find a way to practice "gadget chains", which consists on finding different classes and chain them in order to get the desired effect. I wanted to create an Android application that could be fun or interesting. 

The basic idea is to generate an application that will send a serialized algorithm (based on classes that are in the current project) and find the best algorithm to solve the multi arm bandit problem. 

In order to trigger the application you could generate the following code (as an example):

```kotlin
fun sendBlockToActivity(context: Context) {
    val intent = Intent().apply {
        // Set the package name of the target application
        setPackage("com.advanced.android.multiserialbandit")

        // Set the fully qualified class name (activity name) of the target component
        setClassName("com.advanced.android.multiserialbandit", "com.advanced.android.multiserialbandit.EmulatorActivity")

        val wasSuccessFullTrueBlock = Block().addStatement(
            SetStatementVariableStatement(
                "ReturnValue",
                GetLastShotMachineNumber()
            )
        )

        val equalMachineTrueBlock = Block().addStatement(SetVariableStatement("ReturnValue", 0))

        val equalMachineFalseBlock = Block().addStatement(
            SetStatementVariableStatement(
                "ReturnValue",
                Add(GetLastShotMachineNumber(), IntStatement(1))
            )
        )
        val wasSuccessFullFalseBlock = Block().addStatement(
            If(
                EqualExpression(GetLastShotMachineNumber(), IntStatement(4)),
                equalMachineTrueBlock,
                equalMachineFalseBlock
            )
        )

        val isFirstFalseBlock = Block().addStatement(
            If(
                PrimaryExpression(GetLastShotWasSuccessFul()),
                wasSuccessFullTrueBlock,
                wasSuccessFullFalseBlock
            )
        )

        val isFirstTrueBlock = Block().addStatement(SetVariableStatement("ReturnValue", 0))
        val block = Block().addStatement(
            If(
                PrimaryExpression(IsFirstTime()),
                isFirstTrueBlock,
                isFirstFalseBlock
            )
        )


        // Add any extra data if required
        putExtra("intent_block", block)

        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

// Verify if there's an activity that can handle the intent
    if (intent.resolveActivity(context.packageManager) != null) {
        // Start the activity with the intent
        context.startActivity(intent)
    }
}
```

If you want to test some code I left a boilerplate on the MainActivity that you could change:

```kotlin
setContent {
    MainScreen(
		...
        executeJavaMethod = { SimpleExamples.addTest() },
```

Also if you want to test sending simple gadgets you could use the MainActivity as well:

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        val myBlock: Block? = intent.getSerializableExtra("intent_block") as Block?
        CasinoStrategy.setIntentBlock(myBlock)
        setContent {
            MainScreen(
                ...
                runActionOnExample = { CasinoStrategy.runIntentBlock() }
```

If you want to see some of the context on the project and how to build simple examples, please check the following youtube videos (in spanish):

The videos are not specific, they are recorded sessions on the stream of https://www.twitch.tv/ageofentropy

- https://youtu.be/pSUIlLjS8z8?si=w4R81dVOmjqJmdPG
- https://youtu.be/fBJheije0kI?si=kAMhARXRqLOiIlm6

If you have any question or comment or you detected any bug you can reach me at:

- https://twitter.com/warlockk87
- https://www.linkedin.com/in/cesar-mario-rodriguez/
- https://twitter.com/Age_Of_Entropy

