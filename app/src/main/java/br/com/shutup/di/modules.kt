package br.com.shutup.di

import br.com.shutup.data.local.PreferencesHelper
import br.com.shutup.data.network.api.Api
import br.com.shutup.data.network.api.ApiHelper
import br.com.shutup.data.network.api.ApiHelperContract
import br.com.shutup.data.repositories.sound.SoundRepository
import br.com.shutup.data.repositories.sound.SoundRepositoryContract
import br.com.shutup.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModules = module {
    single { PreferencesHelper(get()) }
    single { ApiHelper.apiInstance(get()) } bind Api::class
    single { ApiHelper(get()) } bind ApiHelperContract::class
    // not implemented yet
    single { ApiHelper.retrofitMockedInstance()}
}

val repositoryModules = module {
    single { SoundRepository(get(), get()) } bind SoundRepositoryContract::class
}

val viewModelModules = module {
    viewModel { MainViewModel(get()) }
}
