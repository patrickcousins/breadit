package io.lostpacket.breadit.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LinkFlairRichtext (

  @Json(name = "a" ) var a : String? = null,
  @Json(name = "e" ) var e : String? = null,
  @Json(name = "u" ) var u : String? = null

)