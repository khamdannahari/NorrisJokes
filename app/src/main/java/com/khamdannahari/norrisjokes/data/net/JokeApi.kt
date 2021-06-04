package com.khamdannahari.norrisjokes.data.net

import com.khamdannahari.norrisjokes.data.entity.CategoriesEntity
import com.khamdannahari.norrisjokes.data.entity.JokeEntity
import com.khamdannahari.norrisjokes.data.entity.JokesEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface JokeApi {

    private companion object {

        const val CATEGORIES = "categories"
        const val RANDOM = "random"
        const val CATEGORY = "category"
        const val SEARCH = "search"
        const val QUERY = "query"
    }

    @GET(CATEGORIES)
    fun categories(): Single<CategoriesEntity>

    @GET(RANDOM)
    fun joke(): Single<JokeEntity>

    @GET(RANDOM)
    fun joke(@Query(CATEGORY) category: String): Single<JokeEntity>

    @GET(SEARCH)
    fun searchJokes(@Query(QUERY) query: String): Single<JokesEntity>
}