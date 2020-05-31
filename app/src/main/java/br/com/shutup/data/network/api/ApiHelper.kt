package br.com.shutup.data.network.api

import br.com.shutup.data.local.PreferencesHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiHelper(
    private var api: Api
) : ApiHelperContract {

    companion object {
        fun apiInstance(preferencesHelper: PreferencesHelper): Api {
            val baseUrl = preferencesHelper.getIpConfig().ifEmpty { "127.0.0.1:5000" }
            val retrofit = Retrofit.Builder()
                .baseUrl("http://${baseUrl}/")
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .client(getOkHttpClient())
                .build()

            return retrofit.create(Api::class.java)
        }

        fun refresh(preferencesHelper: PreferencesHelper) = apiInstance(preferencesHelper)

        private fun getOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor())
                .readTimeout(1000, TimeUnit.SECONDS)
                .build()
        }

        fun retrofitMockedInstance() {
            //
        }
    }

    override suspend fun getVolume() = api.getVolume()

    override suspend fun setVolume(volume: Int) = api.volume(volume)

    override suspend fun getStatus() = api.getStatus()

    override suspend fun lockVolume() = api.lockVolume()

    override suspend fun unlockVolume() = api.unlockVolume()

    override fun refreshBaseUrl(preferencesHelper: PreferencesHelper)  {
        this.api = refresh(preferencesHelper)
    }
}