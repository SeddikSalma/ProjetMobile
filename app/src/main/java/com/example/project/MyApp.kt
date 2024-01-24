package com.example.project

import android.app.Application
import android.content.Context

class MyApp: Application() {
    companion object {
        private lateinit var context: MyApp
        fun getContext(): Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}