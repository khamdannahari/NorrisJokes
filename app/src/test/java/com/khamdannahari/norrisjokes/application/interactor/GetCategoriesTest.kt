package com.khamdannahari.norrisjokes.application.interactor

import com.khamdannahari.norrisjokes.application.interactor.UseCase.None
import com.khamdannahari.norrisjokes.application.repository.JokeRepository
import com.khamdannahari.norrisjokes.common.SchedulersProviderTest
import com.khamdannahari.norrisjokes.domain.Categories
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.*
import org.junit.Assert.*
import org.mockito.*
import org.mockito.Mockito.*

class GetCategoriesTest {

    private lateinit var getCategories: GetCategories

    private lateinit var response: Single<Categories>

    @Mock
    private lateinit var jokeRepository: JokeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        getCategories = GetCategories(jokeRepository, SchedulersProviderTest())
        response = Single.just(listOf("A","B"))

        whenever(jokeRepository.categories()).thenReturn(response)
    }

    @Test
    fun `build observable`() {
        val response = getCategories.buildUseCaseSingle(None())

        verify(jokeRepository, times(1)).categories()

        assertEquals(this.response, response)
    }
}