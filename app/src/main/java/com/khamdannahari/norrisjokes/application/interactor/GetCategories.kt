package com.khamdannahari.norrisjokes.application.interactor

import com.khamdannahari.norrisjokes.application.interactor.UseCase.None
import com.khamdannahari.norrisjokes.application.repository.JokeRepository
import com.khamdannahari.norrisjokes.domain.Categories
import com.khamdannahari.norrisjokes.infrastructure.platform.SchedulersProvider
import io.reactivex.Single
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val jokeRepository: JokeRepository,
    schedulersProvider: SchedulersProvider
) : UseCaseSingle<Categories, None>(schedulersProvider) {

    override fun buildUseCaseSingle(params: None): Single<Categories> =
        jokeRepository.categories()
}