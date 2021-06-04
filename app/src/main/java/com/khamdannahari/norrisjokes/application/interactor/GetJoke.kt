package com.khamdannahari.norrisjokes.application.interactor

import com.khamdannahari.norrisjokes.application.repository.JokeRepository
import com.khamdannahari.norrisjokes.domain.Joke
import com.khamdannahari.norrisjokes.infrastructure.platform.SchedulersProvider
import io.reactivex.Single
import javax.inject.Inject

class GetJoke @Inject constructor(
    private val jokeRepository: JokeRepository,
    schedulersProvider: SchedulersProvider
) : UseCaseSingle<Joke, String>(schedulersProvider) {

    override fun buildUseCaseSingle(params: String): Single<Joke> =
        when {
            params.isEmpty() -> jokeRepository.joke()
            else -> jokeRepository.joke(params)
        }
}