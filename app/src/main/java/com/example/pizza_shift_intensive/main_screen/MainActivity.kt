package com.example.pizza_shift_intensive.main_screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pizza_shift_intensive.ui.theme.Pizza_shift_intensiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pizza_shift_intensiveTheme {
                MyApp()
            }
        }
    }
}