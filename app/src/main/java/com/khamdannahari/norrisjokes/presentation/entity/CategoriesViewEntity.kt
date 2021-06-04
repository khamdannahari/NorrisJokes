package com.khamdannahari.norrisjokes.presentation.entity

import com.khamdannahari.norrisjokes.domain.Categories
import java.util.Locale

typealias CategoriesViewEntity = List<CategoryViewEntity>

data class CategoryViewEntity(
    val name: String,
    var isSelected: Boolean = false
) {

    companion object {

        private val randomCategory = CategoryViewEntity("Random", true)

        fun from(categories: Categories) = listOf(randomCategory) + categories.map {
            CategoryViewEntity(it.capitalize(Locale.ENGLISH))
        }
    }
}