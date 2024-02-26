package io.lostpacket.breadit.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Children (

  @Json(name = "kind" ) var kind : String? = null,
  @Json(name = "data" ) var data : ChildData?   = ChildData()

)