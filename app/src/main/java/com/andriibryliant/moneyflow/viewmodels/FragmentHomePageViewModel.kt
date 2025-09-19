package com.andriibryliant.moneyflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andriibryliant.moneyflow.objects.TransactionListItem

class FragmentHomePageViewModel : ViewModel() {
    private val _selected = MutableLiveData<TransactionListItem>(TransactionListItem.ALL)
    val selectedMenuItem: LiveData<TransactionListItem> = _selected
    var previousSelected: TransactionListItem? = selectedMenuItem.value

    fun selectMenuItem(item: TransactionListItem){
        previousSelected = selectedMenuItem.value
        _selected.value = item
    }

}