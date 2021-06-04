package com.khamdannahari.norrisjokes.data.entity

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

class JokesEntity @JsonCreator constructor(
    @JsonProperty("total") val total: Int,
    @JsonProperty("result") val jokes: List<JokeEntity>
)
