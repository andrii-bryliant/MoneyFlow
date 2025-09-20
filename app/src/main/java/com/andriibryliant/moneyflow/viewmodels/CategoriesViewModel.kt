package com.andriibryliant.moneyflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andriibryliant.moneyflow.R
import com.andriibryliant.moneyflow.objects.Category

class CategoriesViewModel : ViewModel() {
    private val _categoriesList = MutableLiveData<List<Category>>()
    var categoriesList: LiveData<List<Category>> = _categoriesList

    fun fetchCategories(){
        //TODO
    }

    fun loadDummyData(){
        _categoriesList.value = listOf(
            Category("shop", "Shop", R.drawable.ic_cart_shopping_solid),
            Category("food", "Food", R.drawable.ic_pizza_slice_solid),
            Category("travel", "Travel", R.drawable.ic_plane_solid)
        )
    }

}