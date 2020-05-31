package br.com.shutup.data.repositories.sound

import br.com.shutup.data.models.Volume
import br.com.shutup.data.models.VolumeStatus
import okhttp3.ResponseBody

interface SoundRepositoryContract {
    fun getIpConfig(): String
    fun setIpConfig(ipConfig: String)
    fun refreshBaseUrl()
    suspend fun getVolume(): Volume
    suspend fun getStatus(): VolumeStatus
    suspend fun setVolume(volume: Int): ResponseBody
    suspend fun unlockDevice(): ResponseBody
    suspend fun lockDevice(): ResponseBody
}
