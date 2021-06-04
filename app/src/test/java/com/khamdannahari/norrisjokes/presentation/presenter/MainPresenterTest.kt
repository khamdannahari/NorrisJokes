package com.khamdannahari.norrisjokes.presentation.presenter

import com.khamdannahari.norrisjokes.application.exception.NetworkConnectionException
import com.khamdannahari.norrisjokes.application.interactor.GetCategories
import com.khamdannahari.norrisjokes.application.interactor.GetJoke
import com.khamdannahari.norrisjokes.application.service.ErrorMessageFormatter
import com.khamdannahari.norrisjokes.domain.Joke
import com.khamdannahari.norrisjokes.presentation.entity.JokeViewEntity
import com.khamdannahari.norrisjokes.presentation.view.fragment.MainFragment
import com.nhaarman.mockitokotlin2.*
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainPresenterTest {

    private lateinit var presenter: MainPresenter

    @Mock private lateinit var getCategories: GetCategories
    @Mock private lateinit var getJoke: GetJoke
    @Mock private lateinit var errorFormatter: ErrorMessageFormatter
    @Mock private lateinit var view: MainFragment

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(getCategories, getJoke, errorFormatter)

        whenever(errorFormatter.getErrorMessage(any())).thenReturn("error")
        whenever(view.activity).thenReturn(mock())

        verifyZeroInteractions(getCategories)
        verifyZeroInteractions(view)
        verifyZeroInteractions(errorFormatter)
    }

    @Test
    fun `render categories`() {
        var response : DisposableSingleObserver<List<String>>? = null
        whenever(getCategories.execute(any(), any())).thenAnswer {
            response = it.getArgument(0)
            Unit
        }

        presenter.init(view)

        response!!.onSuccess(listOf())

        verify(view).renderCategories(any())

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `render joke`() {
        var response : DisposableSingleObserver<Joke>? = null
        whenever(getJoke.execute(any(), any())).thenAnswer {
            response = it.getArgument(0)
            Unit
        }

        presenter.init(view)

        response!!.onSuccess(Joke("","","","",""))

        verify(view).renderJoke(any())

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `show error`() {
        var response : DisposableSingleObserver<List<String>>? = null
        whenever(getCategories.execute(any(), any())).thenAnswer {
            response = it.getArgument(0)
            Unit
        }

        presenter.init(view)

        response!!.onError(NetworkConnectionException())

        verify(view).notify(anyString())
        verify(view).close()

        verifyNoMoreInteractions(view)
    }

}
