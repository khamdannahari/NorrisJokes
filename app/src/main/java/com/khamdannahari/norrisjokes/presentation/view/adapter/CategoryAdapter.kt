package com.khamdannahari.norrisjokes.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.khamdannahari.norrisjokes.R
import com.khamdannahari.norrisjokes.presentation.entity.CategoriesViewEntity
import com.khamdannahari.norrisjokes.presentation.entity.CategoryViewEntity
import kotlinx.android.synthetic.main.item_category.view.cv_category
import kotlinx.android.synthetic.main.item_category.view.tv_name
import javax.inject.Inject

class CategoryAdapter @Inject constructor(
    private val clickListener: (CategoryViewEntity) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private lateinit var items: CategoriesViewEntity

    fun getSelectedItem(): CategoryViewEntity =
        items.find { it.isSelected } ?: items[0]

    fun setRandomSelected() {
        items = items.mapIndexed { index, categoryViewEntity ->
            val isSelected = index == 0
            CategoryViewEntity(categoryViewEntity.name, isSelected)
        }
        notifyDataSetChanged()
    }

    fun updateItems(items: CategoriesViewEntity) {
        this.items = items
        notifyDataSetChanged()
    }

    fun updateItems(itemSelected: CategoryViewEntity) {
        this.items = items.map {
            val isSelected = itemSelected.name == it.name
            CategoryViewEntity(it.name, isSelected)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_category, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(category: CategoryViewEntity) {
            with(itemView) {

                val categoryColor =
                    if (category.isSelected) context.getColor(R.color.selectedItem)
                    else context.getColor(R.color.unSelectedItem)

                cv_category.setCardBackgroundColor(categoryColor)

                tv_name.text = category.name

                setOnClickListener {
                    updateItems(category)
                    clickListener(category)
                }
            }
        }
    }
}
