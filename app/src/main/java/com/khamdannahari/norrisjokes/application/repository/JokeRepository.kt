package com.khamdannahari.norrisjokes.application.repository

import com.khamdannahari.norrisjokes.domain.Joke
import io.reactivex.Single

interface JokeRepository {

    fun categories(): Single<List<String>>
    fun joke(): Single<Joke>
    fun joke(category: String): Single<Joke>
    fun searchJokes(query:String): Single<List<Joke>>
}