package io.lostpacket.breadit.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media (

  @Json(name = "reddit_video"  ) var images  : RedditVideo? = null,

)