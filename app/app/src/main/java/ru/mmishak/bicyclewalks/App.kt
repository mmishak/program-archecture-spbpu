package ru.mmishak.bicyclewalks

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference

class App : Application() {

    companion object {
        private lateinit var contextReference: WeakReference<Context>
        fun getContext(): Context = contextReference.get()!!
    }

    override fun onCreate() {
        super.onCreate()
        contextReference = WeakReference(this)
    }
}