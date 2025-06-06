package com.nutritionwallah.di

import com.nutritionwallah.auth.AuthViewModel
import com.nutritionwallah.data.CustomerRepositoryImpl
import com.nutritionwallah.data.domain.CustomerRepository
import com.nutritiwallah.home.HomeGraphViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val sharedModule = module {
    single<CustomerRepository> {
        CustomerRepositoryImpl()
    }
    viewModelOf(::AuthViewModel)
    viewModel {
        HomeGraphViewModel(get())
    }
}
fun iniKoin(
    config : (KoinApplication.() -> Unit)?  = null
){
    startKoin {
        config?.invoke(this)
        modules(sharedModule)
    }

}