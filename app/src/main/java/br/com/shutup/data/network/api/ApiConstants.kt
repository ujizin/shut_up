package br.com.shutup.data.network.api

object ApiConstants {
    // Prefix
    private const val API_PREFIX = "/api"

    // Parameters
    const val VOLUME_PARAM = "volume"

    const val SOUND = "${API_PREFIX}/sound"


    const val SOUND_VOLUME = "${SOUND}/{${VOLUME_PARAM}}"
    const val SOUND_LOCK = "${SOUND}/lock"
    const val SOUND_UNLOCK = "${SOUND}/unlock"
    const val SOUND_STATUS = "${SOUND}/lock/status"
}