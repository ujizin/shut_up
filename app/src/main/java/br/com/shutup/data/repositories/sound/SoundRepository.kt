package br.com.shutup.data.repositories.sound

import br.com.shutup.data.local.PreferencesHelper
import br.com.shutup.data.network.api.ApiHelperContract

class SoundRepository(
    private var apiHelper: ApiHelperContract,
    private val preferencesHelper: PreferencesHelper
): SoundRepositoryContract {

    override fun refreshBaseUrl() { apiHelper.refreshBaseUrl(preferencesHelper) }

    override fun getIpConfig() = preferencesHelper.getIpConfig()

    override fun setIpConfig(ipConfig: String) {
        preferencesHelper.setIpConfig(ipConfig)
        refreshBaseUrl()
    }

    override suspend fun getVolume() = apiHelper.getVolume()

    override suspend fun getStatus() = apiHelper.getStatus()

    override suspend fun setVolume(volume: Int) = apiHelper.setVolume(volume)

    override suspend fun unlockDevice() = apiHelper.unlockVolume()

    override suspend fun lockDevice() = apiHelper.lockVolume()
}