package com.andriibryliant.moneyflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andriibryliant.moneyflow.objects.MainMenuItem

class MainActivityViewModel : ViewModel() {
    private val _selected = MutableLiveData<MainMenuItem>(MainMenuItem.HOME)
    val selectedMenuItem: LiveData<MainMenuItem> = _selected
    var previousSelected: MainMenuItem? = selectedMenuItem.value

    fun selectMenuItem(item: MainMenuItem){
        previousSelected = selectedMenuItem.value
        _selected.value = item
    }

}