package com.khamdannahari.norrisjokes.data.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.khamdannahari.norrisjokes.domain.Joke

@JsonIgnoreProperties(ignoreUnknown = true)
data class JokeEntity @JsonCreator constructor(
    @JsonProperty("icon_url") val iconUrl: String,
    @JsonProperty("id") val id: String,
    @JsonProperty("url") val url: String,
    @JsonProperty("value") val value: String,
    @JsonProperty("updated_at") val updatedAt: String = "",
) {

    fun toJoke(): Joke = Joke(
        iconUrl,
        id,
        url,
        value,
        updatedAt
    )
}
