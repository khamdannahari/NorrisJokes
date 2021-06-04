package com.khamdannahari.norrisjokes.data.net

import com.khamdannahari.norrisjokes.application.repository.JokeRepository
import com.khamdannahari.norrisjokes.domain.Joke
import io.reactivex.Single
import javax.inject.Inject

/*
 * Implementation of the joke repository based on the network API
 */
class NetJokeRepository @Inject constructor(private val jokeApiService: JokeApiService) : JokeRepository {

    override fun categories(): Single<List<String>> =
        jokeApiService.categories()

    override fun joke(): Single<Joke> =
        jokeApiService.joke().map { it.toJoke() }

    override fun joke(category: String): Single<Joke> =
        jokeApiService.joke(category).map { it.toJoke() }

    override fun searchJokes(query: String): Single<List<Joke>> =
        jokeApiService.searchJokes(query)
            .map { it.jokes }
            .map { jokes -> jokes.map { it.toJoke() } }
}