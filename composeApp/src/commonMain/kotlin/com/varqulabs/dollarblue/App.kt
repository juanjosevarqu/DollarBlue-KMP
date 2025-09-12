package com.varqulabs.dollarblue

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.varqulabs.dollarblue.core.ui.navigation.Routes
import com.varqulabs.feature.calculator.navigation.calculatorGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {

    val navController = rememberNavController()



    MaterialTheme {
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = Routes.CalculatorGraph,
        ) {
            calculatorGraph(
                navController = navController,
                goToHistory = { /*navController.navigateTo(Routes.History)*/ },
                goToWithoutCredits = { /*navController.navigateTo(Routes.Credits(hasEnoughCredits = false))*/ },
            )
        }
        /*var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me! ")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                    Image(
                        painter = painterResource(Currency.DOLLAR.symbolImage), contentDescription = null
                    )
                    Text("Compose: $greeting")
                }
            }
        }*/
    }
}