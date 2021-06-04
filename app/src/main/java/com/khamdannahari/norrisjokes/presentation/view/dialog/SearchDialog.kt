package com.khamdannahari.norrisjokes.presentation.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContextCompat.getSystemService
import com.khamdannahari.norrisjokes.R
import com.khamdannahari.norrisjokes.infrastructure.platform.BaseDialog
import com.khamdannahari.norrisjokes.presentation.entity.JokeViewEntity
import com.khamdannahari.norrisjokes.presentation.presenter.SearchPresenter
import com.khamdannahari.norrisjokes.presentation.view.adapter.SearchAdapter
import kotlinx.android.synthetic.main.fragment_search.rv_search
import kotlinx.android.synthetic.main.fragment_search.search_jokes
import javax.inject.Inject

class SearchDialog(private val callback: (JokeViewEntity) -> Unit) : BaseDialog() {

    @Inject
    lateinit var presenter: SearchPresenter

    private val adapter by lazy {
        SearchAdapter {
            callback(it)
            dismiss()
        }
    }

    override fun layoutId(): Int = R.layout.fragment_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init(this)
        setupSearchJokes()
        setupRvSearch()
    }

    private fun setupSearchJokes() {
        with(search_jokes) {
            isFocusable = true
            isIconified = false
            setIconifiedByDefault(false)
            requestFocus()
            requestFocusFromTouch()

            setOnQueryTextListener(
                object : OnQueryTextListener {
                    override fun onQueryTextChange(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        if (query != null) {
                            presenter.searchJokes(query)
                        }
                        return true
                    }
                })
        }
    }

    private fun setupRvSearch() {
        rv_search.adapter = adapter
    }

    fun render(jokes: List<JokeViewEntity>) {
        adapter.updateItems(jokes)
    }
}