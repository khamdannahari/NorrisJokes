package com.khamdannahari.norrisjokes.application.interactor

import com.khamdannahari.norrisjokes.application.repository.JokeRepository
import com.khamdannahari.norrisjokes.domain.Joke
import com.khamdannahari.norrisjokes.infrastructure.platform.SchedulersProvider
import io.reactivex.Single
import javax.inject.Inject

class SearchJokes @Inject constructor(
    private val jokeRepository: JokeRepository,
    schedulersProvider: SchedulersProvider
) : UseCaseSingle<List<Joke>, String>(schedulersProvider) {

    override fun buildUseCaseSingle(params: String): Single<List<Joke>> =
        jokeRepository.searchJokes(params)
}