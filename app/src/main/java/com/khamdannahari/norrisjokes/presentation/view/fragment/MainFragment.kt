package com.khamdannahari.norrisjokes.presentation.view.fragment

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.khamdannahari.norrisjokes.R
import com.khamdannahari.norrisjokes.infrastructure.extensions.loadFromUrl
import com.khamdannahari.norrisjokes.infrastructure.platform.BaseFragment
import com.khamdannahari.norrisjokes.presentation.entity.CategoriesViewEntity
import com.khamdannahari.norrisjokes.presentation.entity.JokeViewEntity
import com.khamdannahari.norrisjokes.presentation.presenter.MainPresenter
import com.khamdannahari.norrisjokes.presentation.view.adapter.CategoryAdapter
import com.khamdannahari.norrisjokes.presentation.view.dialog.SearchDialog
import kotlinx.android.synthetic.main.fragment_main.fab_shuffle
import kotlinx.android.synthetic.main.fragment_main.iv_icon
import kotlinx.android.synthetic.main.fragment_main.rv_category
import kotlinx.android.synthetic.main.fragment_main.tv_joke
import javax.inject.Inject

class MainFragment : BaseFragment() {

    @Inject
    lateinit var presenter: MainPresenter

    override fun layoutId(): Int = R.layout.fragment_main

    private val adapter by lazy {
        CategoryAdapter { presenter.getJoke(it.name) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_search -> {
                showSearchDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSearchDialog() {
        fragmentManager?.let {
            SearchDialog(onSearchItemSelected).show(it, SearchDialog::class.java.simpleName)
        }
    }

    private val onSearchItemSelected = { jokeViewEntity: JokeViewEntity ->
        adapter.setRandomSelected()
        renderJoke(jokeViewEntity)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init(this)
        setupFabShuffle()
    }

    private fun setupFabShuffle() {
        fab_shuffle.setOnClickListener {
            presenter.getJoke(adapter.getSelectedItem().name)
        }
    }

    fun renderCategories(categories: CategoriesViewEntity) {
        rv_category.adapter = adapter
        adapter.updateItems(categories)
    }

    fun renderJoke(joke: JokeViewEntity) {
        with(joke) {
            iv_icon.loadFromUrl(iconUrl)
            tv_joke.text = value
        }
    }
}