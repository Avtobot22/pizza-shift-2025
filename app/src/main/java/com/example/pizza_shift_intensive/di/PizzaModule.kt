package com.example.pizza_shift_intensive.di

import com.example.pizza_shift_intensive.data.api.PizzaApi
import com.example.pizza_shift_intensive.data.converter.PizzaCatalogConverter
import com.example.pizza_shift_intensive.data.repository.PizzaRepository
import com.example.pizza_shift_intensive.data.repository.PizzaRepositoryImpl
import com.example.pizza_shift_intensive.domain.usecase.*
import com.example.pizza_shift_intensive.presentation.viewmodel.PizzaViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit

val pizzaModule = module {
    single { get<Retrofit>().create(PizzaApi::class.java) }

    factoryOf(::PizzaCatalogConverter)

    factoryOf(::PizzaRepositoryImpl) bind PizzaRepository::class

    factory<InitialPriceStrategy>(named("smallest")) { SmallestSizePriceStrategy() }
    factory<InitialPriceStrategy>(named("first")) { FirstAvailablePriceStrategy() }

    factory {
        GetInitialPizzaPriceUseCase(
            strategies = getAll()
        )
    }
    factoryOf(::GetPizzasUseCase)
    factoryOf(::GetPizzaDetailsUseCase)
    factoryOf(::GetPizzasWithInitialPriceUseCase)

    viewModelOf(::PizzaViewModel)
}