package com.khamdannahari.norrisjokes.domain

data class Joke(
    val iconUrl: String,
    val id: String,
    val url: String,
    val value: String,
    val updatedAt: String
)