package io.lostpacket.breadit.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Preview (

  @Json(name = "images"  ) var images  : List<Images> = listOf(),
  @Json(name = "enabled" ) var enabled : Boolean?          = null

)