package io.lostpacket.breadit.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Resolutions (

  @Json(name = "url"    ) var url    : String? = null,
  @Json(name = "width"  ) var width  : Int?    = null,
  @Json(name = "height" ) var height : Int?    = null

)