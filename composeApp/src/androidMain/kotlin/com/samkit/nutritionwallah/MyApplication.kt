package com.samkit.nutritionwallah

import android.app.Application
import com.nutritionwallah.di.iniKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        iniKoin(
            config = {
                androidContext(this@MyApplication)
            }
        )
    }
}