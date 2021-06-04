package com.khamdannahari.norrisjokes.presentation.presenter

import com.khamdannahari.norrisjokes.application.interactor.GetCategories
import com.khamdannahari.norrisjokes.application.interactor.GetJoke
import com.khamdannahari.norrisjokes.application.interactor.UseCase.None
import com.khamdannahari.norrisjokes.application.navigation.Navigator
import com.khamdannahari.norrisjokes.application.service.ErrorMessageFormatter
import com.khamdannahari.norrisjokes.domain.Categories
import com.khamdannahari.norrisjokes.domain.Joke
import com.khamdannahari.norrisjokes.presentation.entity.CategoryViewEntity
import com.khamdannahari.norrisjokes.presentation.entity.JokeViewEntity
import com.khamdannahari.norrisjokes.presentation.view.fragment.MainFragment
import io.reactivex.observers.DisposableSingleObserver
import java.util.Locale
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private val getCategories: GetCategories,
    private val getJoke: GetJoke,
    private val errorFormatter: ErrorMessageFormatter
) {

    private lateinit var view: MainFragment

    fun init(view: MainFragment) {
        this.view = view
        getCategories()
        getJoke(RANDOM_CATEGORY)
    }

    private fun getCategories() {
        getCategories.execute(object : DisposableSingleObserver<Categories>() {
            override fun onSuccess(t: List<String>) = view.renderCategories(CategoryViewEntity.from(t))
            override fun onError(e: Throwable) {
                view.notify(errorFormatter.getErrorMessage(e))
                view.close()
            }
        }, None())
    }

    fun getJoke(category: String) {
        getJoke.execute(object : DisposableSingleObserver<Joke>() {
            override fun onSuccess(t: Joke) = view.renderJoke(JokeViewEntity.from(t))
            override fun onError(e: Throwable) {
                view.notify(errorFormatter.getErrorMessage(e))
                view.close()
            }
        }, category.takeIf { it.toLowerCase(Locale.ENGLISH) != RANDOM_TEXT } ?: RANDOM_CATEGORY)
    }

    companion object {

        const val RANDOM_CATEGORY = ""
        const val RANDOM_TEXT = "random"
    }
}