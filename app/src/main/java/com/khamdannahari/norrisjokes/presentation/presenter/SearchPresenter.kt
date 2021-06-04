package com.khamdannahari.norrisjokes.presentation.presenter

import com.khamdannahari.norrisjokes.application.interactor.SearchJokes
import com.khamdannahari.norrisjokes.application.service.ErrorMessageFormatter
import com.khamdannahari.norrisjokes.domain.Joke
import com.khamdannahari.norrisjokes.presentation.entity.JokeViewEntity
import com.khamdannahari.norrisjokes.presentation.view.dialog.SearchDialog
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val searchJokes: SearchJokes,
    private val errorFormatter: ErrorMessageFormatter
) {

    private lateinit var mView: SearchDialog

    fun init(view: SearchDialog) {
        this.mView = view
    }

    fun searchJokes(query: String) {
        searchJokes.execute(object : DisposableSingleObserver<List<Joke>>() {
            override fun onSuccess(t: List<Joke>) = mView.render(t.map { JokeViewEntity.from(it) })
            override fun onError(e: Throwable) {
                mView.notify(errorFormatter.getErrorMessage(e))
                mView.close()
            }
        }, query)
    }
}