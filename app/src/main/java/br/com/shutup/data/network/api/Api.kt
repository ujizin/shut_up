package br.com.shutup.data.network.api

import br.com.shutup.data.models.Volume
import br.com.shutup.data.models.VolumeStatus
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET(ApiConstants.SOUND)
    suspend fun getVolume(): Volume

    @GET(ApiConstants.SOUND_LOCK)
    suspend fun lockVolume(): ResponseBody

    @GET(ApiConstants.SOUND_UNLOCK)
    suspend fun unlockVolume(): ResponseBody

    @GET(ApiConstants.SOUND_STATUS)
    suspend fun getStatus(): VolumeStatus

    @GET(ApiConstants.SOUND_VOLUME)
    suspend fun volume(
        @Path(ApiConstants.VOLUME_PARAM) volume: Int
    ): ResponseBody
}