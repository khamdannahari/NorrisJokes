package com.khamdannahari.norrisjokes.application.interactor

import com.khamdannahari.norrisjokes.application.repository.JokeRepository
import com.khamdannahari.norrisjokes.common.SchedulersProviderTest
import com.khamdannahari.norrisjokes.domain.Joke
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.*
import org.junit.Assert.*
import org.mockito.*
import org.mockito.Mockito.*

class SearchJokesTest {

    private lateinit var searchJokes: SearchJokes

    private lateinit var response: Single<List<Joke>>

    @Mock
    private lateinit var jokeRepository: JokeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        searchJokes = SearchJokes(jokeRepository, SchedulersProviderTest())
        response = Single.just(
            listOf(
                Joke(
                    iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
                    id = "tdttygk4rgygwgquno2t9a",
                    url = "https://api.chucknorris.io/jokes/tdttygk4rgygwgquno2t9a",
                    value = "Coroners refer to dead people as \"ABC's\". Already Been Chucked.",
                    updatedAt = "2020-01-05 13:42:19.104863"
                )
            )
        )

        whenever(jokeRepository.searchJokes(SEARCH_ABC)).thenReturn(response)
    }

    @Test
    fun `build observable`() {
        val response = searchJokes.buildUseCaseSingle(SEARCH_ABC)

        verify(jokeRepository, times(1)).searchJokes(SEARCH_ABC)
        assertEquals(this.response, response)
    }

    private companion object {
        const val SEARCH_ABC = "abc"
    }
}