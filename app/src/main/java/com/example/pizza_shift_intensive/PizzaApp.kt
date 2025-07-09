package com.example.pizza_shift_intensive

import android.app.Application
import com.example.pizza_shift_intensive.di.NetworkModule
import com.example.pizza_shift_intensive.di.pizzaDetailsModule
import com.example.pizza_shift_intensive.di.pizzaListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PizzaApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@PizzaApp)

            modules(pizzaListModule, NetworkModule, pizzaDetailsModule)
        }
    }
}

