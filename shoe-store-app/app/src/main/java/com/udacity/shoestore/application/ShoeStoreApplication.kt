package com.udacity.shoestore.application

import android.app.Application
import com.udacity.shoestore.BuildConfig
import timber.log.Timber
import timber.log.Timber.Forest.plant


class ShoeStoreApplication : Application() {

    override fun onCreate() {
        super.onCreate()
            plant(Timber.DebugTree())
    }
}