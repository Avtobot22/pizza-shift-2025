package com.example.pizza_shift_intensive

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pizza_shift_intensive.presentation.ui.screens.main.MyApp
import com.example.pizza_shift_intensive.ui.theme.PizzaShiftIntensiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzaShiftIntensiveTheme {
                MyApp()
            }
        }
    }
}