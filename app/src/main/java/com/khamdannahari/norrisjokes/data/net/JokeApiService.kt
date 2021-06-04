package com.khamdannahari.norrisjokes.data.net

import com.fasterxml.jackson.databind.JsonMappingException
import com.khamdannahari.norrisjokes.application.exception.ServerErrorException
import com.khamdannahari.norrisjokes.data.entity.JokeEntity
import com.khamdannahari.norrisjokes.data.entity.JokesEntity
import io.reactivex.Single
import retrofit2.HttpException
import retrofit2.Retrofit
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JokeApiService @Inject constructor(retrofit: Retrofit) : JokeApi {

    private val jokeApi by lazy { retrofit.create(JokeApi::class.java) }

    override fun categories(): Single<List<String>> =
        jokeApi
            .categories()
            .onErrorResumeNext { error: Throwable -> Single.error(translate(error)) }

    override fun joke(): Single<JokeEntity> =
        jokeApi
            .joke()
            .onErrorResumeNext { error: Throwable -> Single.error(translate(error)) }

    override fun joke(category: String): Single<JokeEntity> =
        jokeApi
            .joke(category.toLowerCase(Locale.ENGLISH))
            .onErrorResumeNext { error: Throwable -> Single.error(translate(error)) }

    override fun searchJokes(query: String): Single<JokesEntity> =
        jokeApi
            .searchJokes(query)
            .onErrorResumeNext { error: Throwable -> Single.error(translate(error)) }

    private fun translate(throwable: Throwable): Throwable =
        when (throwable) {
            is JsonMappingException -> ServerErrorException(throwable)
            is HttpException -> ServerErrorException(throwable)
            else -> throwable
        }
}