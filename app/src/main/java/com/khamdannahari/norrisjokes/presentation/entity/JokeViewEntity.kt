package com.khamdannahari.norrisjokes.presentation.entity

import com.khamdannahari.norrisjokes.domain.Joke

data class JokeViewEntity(
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String,
    val updatedAt: String
) {

    companion object {

        fun from(joke: Joke) = JokeViewEntity(
            joke.iconUrl,
            joke.id,
            joke.url,
            joke.value,
            joke.updatedAt
        )
    }
}