package com.example.stocks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.stocks.navigation.AppNavigation
import com.example.stocks.ui.theme.StocksTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockApp {
                AppNavigation()
            }
        }
    }
}

@Composable
fun StockApp(content: @Composable () -> Unit) {
    StocksTheme {
        content.invoke()
    }
}