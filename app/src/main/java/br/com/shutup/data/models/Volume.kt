package br.com.shutup.data.models

import com.squareup.moshi.Json

class Volume {

    @Json(name = "volume")
    var volume: Int? = 0
}