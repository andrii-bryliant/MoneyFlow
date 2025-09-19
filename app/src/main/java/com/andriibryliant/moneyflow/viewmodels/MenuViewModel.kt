package com.andriibryliant.moneyflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andriibryliant.moneyflow.objects.TransactionListItem

class MenuViewModel<T>(initialType: T) : ViewModel() {
    private val _selected = MutableLiveData<T>(initialType)
    val selectedMenuItem: LiveData<T> = _selected
    var previousSelected: T? = selectedMenuItem.value

    fun selectMenuItem(item: T){
        if(item != selectedMenuItem.value){
            _selected.value = item

        }
//        previousSelected = selectedMenuItem.value
    }

    class MenuViewModelFactory<I>(private val initialType: I) : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
                return MenuViewModel(initialType) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}