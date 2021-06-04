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

class GetJokeTest {

    private lateinit var getJoke: GetJoke

    private lateinit var response: Single<Joke>

    @Mock
    private lateinit var jokeRepository: JokeRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        getJoke = GetJoke(jokeRepository, SchedulersProviderTest())
        response = Single.just(
            Joke(
                iconUrl = "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
                id = "tdttygk4rgygwgquno2t9a",
                url = "https://api.chucknorris.io/jokes/tdttygk4rgygwgquno2t9a",
                value = "Coroners refer to dead people as \"ABC's\". Already Been Chucked.",
                updatedAt = "2020-01-05 13:42:19.104863"
            )
        )

        whenever(jokeRepository.joke()).thenReturn(response)
        whenever(jokeRepository.joke(CATEGORY_EXPLICIT)).thenReturn(response)
    }

    @Test
    fun `build observable`() {
        val responseRandom = getJoke.buildUseCaseSingle("")
        val responseByCategory = getJoke.buildUseCaseSingle(CATEGORY_EXPLICIT)

        verify(jokeRepository, times(1)).joke()
        assertEquals(this.response, responseRandom)

        verify(jokeRepository, times(1)).joke(CATEGORY_EXPLICIT)
        assertEquals(this.response, responseByCategory)
    }

    private companion object {
        const val CATEGORY_EXPLICIT = "Explicit"
    }
}