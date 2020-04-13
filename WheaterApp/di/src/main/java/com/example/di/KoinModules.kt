package com.example.di

import com.example.data.repositories.CreateCityListUseCaseImpl
import com.example.domain.usecases.GetCityByIdUseCase
import com.example.data.repositories.GetCityByIdUseCaseImpl
import com.example.domain.usecases.CreateCityListUseCase
import org.koin.dsl.module

val useCasesModule = module {
    single<GetCityByIdUseCase> { GetCityByIdUseCaseImpl() }
    single<CreateCityListUseCase> { CreateCityListUseCaseImpl() }
}