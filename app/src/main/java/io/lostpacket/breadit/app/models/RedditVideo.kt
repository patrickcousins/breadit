package io.lostpacket.breadit.app.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RedditVideo (

  @Json(name = "bitrate_kbps"       ) var bitrateKbps       : Int?     = null,
  @Json(name = "fallback_url"       ) var fallbackUrl       : String?  = null,
  @Json(name = "has_audio"          ) var hasAudio          : Boolean? = null,
  @Json(name = "height"             ) var height            : Int?     = null,
  @Json(name = "width"              ) var width             : Int?     = null,
  @Json(name = "scrubber_media_url" ) var scrubberMediaUrl  : String?  = null,
  @Json(name = "dash_url"           ) var dashUrl           : String?  = null,
  @Json(name = "duration"           ) var duration          : Int?     = null,
  @Json(name = "hls_url"            ) var hlsUrl            : String?  = null,
  @Json(name = "is_gif"             ) var isGif             : Boolean? = null,
  @Json(name = "transcoding_status" ) var transcodingStatus : String?  = null

)