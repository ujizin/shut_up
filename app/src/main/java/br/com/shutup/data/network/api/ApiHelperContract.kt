package br.com.shutup.data.network.api

import br.com.shutup.data.local.PreferencesHelper
import br.com.shutup.data.models.Volume
import br.com.shutup.data.models.VolumeStatus
import okhttp3.ResponseBody

interface ApiHelperContract {
    suspend fun getVolume(): Volume
    suspend fun setVolume(volume: Int): ResponseBody
    suspend fun getStatus(): VolumeStatus
    suspend fun lockVolume() : ResponseBody
    suspend fun unlockVolume(): ResponseBody
    fun refreshBaseUrl(preferencesHelper: PreferencesHelper)
}