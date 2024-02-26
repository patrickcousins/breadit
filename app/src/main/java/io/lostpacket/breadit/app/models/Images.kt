package io.lostpacket.breadit.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Images (

  @Json(name = "source"      ) var source      : Source?                = Source(),
  @Json(name = "resolutions" ) var resolutions : List<Resolutions> = listOf(),
  //@Json(name = "variants"    ) var variants    : Variants?              = Variants(),
  @Json(name = "id"          ) var id          : String?                = null

)