package io.lostpacket.breadit.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data (

  @Json(name = "after"      ) var after     : String?             = null,
  @Json(name = "dist"       ) var dist      : Int?                = null,
  @Json(name = "modhash"    ) var modhash   : String?             = null,
  @Json(name = "geo_filter" ) var geoFilter : String?             = null,
  @Json(name = "children"   ) var children  : List<Children>      = listOf(),
  @Json(name = "before"     ) var before    : String?             = null

)