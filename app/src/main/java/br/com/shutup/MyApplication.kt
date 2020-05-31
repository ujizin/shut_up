package br.com.shutup

import android.app.Application
import br.com.shutup.di.appModules
import br.com.shutup.di.repositoryModules
import br.com.shutup.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

class MyApplication : Application() {

    private val modules: MutableList<Module> = mutableListOf(
        appModules,
        repositoryModules,
        viewModelModules
    )

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(modules)
        }
    }

    fun add(module: Module) {
        loadKoinModules(module)
        modules.add(module)
    }
}