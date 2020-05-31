package br.com.shutup.data.models

import com.squareup.moshi.Json

class VolumeStatus {

    @Json(name = "lockStatus")
    var lockStatus: Boolean? = false
}