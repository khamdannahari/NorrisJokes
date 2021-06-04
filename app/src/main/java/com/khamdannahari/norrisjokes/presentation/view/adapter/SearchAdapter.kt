package com.khamdannahari.norrisjokes.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khamdannahari.norrisjokes.R
import com.khamdannahari.norrisjokes.infrastructure.extensions.loadFromUrl
import com.khamdannahari.norrisjokes.presentation.entity.JokeViewEntity
import kotlinx.android.synthetic.main.item_search.view.iv_joke
import kotlinx.android.synthetic.main.item_search.view.tv_updated_at
import kotlinx.android.synthetic.main.item_search.view.tv_value
import javax.inject.Inject

class SearchAdapter @Inject constructor(
    private val clickListener: (JokeViewEntity) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    private var items: List<JokeViewEntity> = listOf()

    fun updateItems(items: List<JokeViewEntity>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_search, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(joke: JokeViewEntity) {
            with(itemView) {
                iv_joke.loadFromUrl(joke.iconUrl)
                tv_value.text = joke.value
                tv_updated_at.text = String.format(
                    context.getString(R.string.format_updated_at),
                    joke.updatedAt.take(10).replace("-", "/")
                )
                setOnClickListener {
                    clickListener(joke)
                }
            }
        }
    }
}
