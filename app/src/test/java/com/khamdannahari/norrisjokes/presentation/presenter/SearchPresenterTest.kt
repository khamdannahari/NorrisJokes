package com.khamdannahari.norrisjokes.presentation.presenter

import com.khamdannahari.norrisjokes.application.exception.NetworkConnectionException
import com.khamdannahari.norrisjokes.application.interactor.GetCategories
import com.khamdannahari.norrisjokes.application.interactor.GetJoke
import com.khamdannahari.norrisjokes.application.interactor.SearchJokes
import com.khamdannahari.norrisjokes.application.service.ErrorMessageFormatter
import com.khamdannahari.norrisjokes.domain.Joke
import com.khamdannahari.norrisjokes.presentation.view.dialog.SearchDialog
import com.khamdannahari.norrisjokes.presentation.view.fragment.MainFragment
import com.nhaarman.mockitokotlin2.*
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class SearchPresenterTest {

    private lateinit var presenter: SearchPresenter

    @Mock private lateinit var searchJokes: SearchJokes
    @Mock private lateinit var errorFormatter: ErrorMessageFormatter
    @Mock private lateinit var view: SearchDialog

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchPresenter(searchJokes, errorFormatter)

        whenever(errorFormatter.getErrorMessage(any())).thenReturn("error")
        whenever(view.activity).thenReturn(mock())

        verifyZeroInteractions(searchJokes)
        verifyZeroInteractions(view)
        verifyZeroInteractions(errorFormatter)
    }

    @Test
    fun `render jokes`() {
        var response : DisposableSingleObserver<List<Joke>>? = null
        whenever(searchJokes.execute(any(), any())).thenAnswer {
            response = it.getArgument(0)
            Unit
        }

        presenter.init(view)
        presenter.searchJokes("")

        response!!.onSuccess(listOf())

        verify(view).render(any())

        verifyNoMoreInteractions(view)
    }

    @Test
    fun `show error`() {
        var response : DisposableSingleObserver<List<Joke>>? = null
        whenever(searchJokes.execute(any(), any())).thenAnswer {
            response = it.getArgument(0)
            Unit
        }

        presenter.init(view)
        presenter.searchJokes("")

        response!!.onError(NetworkConnectionException())

        verify(view).notify(anyString())
        verify(view).close()

        verifyNoMoreInteractions(view)
    }

}
