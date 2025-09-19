package com.andriibryliant.moneyflow.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andriibryliant.moneyflow.objects.Transaction
import com.andriibryliant.moneyflow.objects.TransactionListItem
import com.andriibryliant.moneyflow.objects.TransactionType

class TransactionsViewModel : ViewModel() {
    private val _allList = MutableLiveData<List<Transaction>>()
    private val _filteredList = MutableLiveData<List<Transaction>>()
    val transactionList: LiveData<List<Transaction>> = _filteredList

    private var currentFilter = TransactionListItem.ALL

    fun loadDummyData() {
        _allList.value = listOf<Transaction>(
            Transaction("Groceries", "16/09/2025", -150, TransactionType.EXPENSE, "$", "shop"),
            Transaction("Mobile Payment", "13/09/2025", -10, TransactionType.EXPENSE, "$", "shop"),
            Transaction("Groceries", "09/09/2025", -150, TransactionType.EXPENSE, "$", "shop"),
            Transaction("Job Payment", "09/09/2025", 1500, TransactionType.INCOME, "$", "shop"),
            Transaction("Gym", "09/09/2025", -20, TransactionType.EXPENSE, "$", "shop"),
            Transaction("Zabka", "09/09/2025", -15, TransactionType.EXPENSE, "$", "shop"),
            Transaction("Bicycle Sold", "09/09/2025", 250, TransactionType.INCOME, "$", "shop"),
            Transaction("Clothes", "09/09/2025", -54, TransactionType.EXPENSE, "$", "shop"),
            Transaction("Job Payment", "09/08/2025", 1500, TransactionType.INCOME, "$", "shop")
        )
        applyFilter()
    }

    fun selectFilter(filter: TransactionListItem) {
        if (filter != currentFilter) {
            currentFilter = filter
            applyFilter()
        }
    }

    fun applyFilter() {
        val list = _allList.value ?: emptyList()
        _filteredList.value = when(currentFilter){
            TransactionListItem.ALL -> list
            TransactionListItem.INCOME -> list.filter { it.type == TransactionType.INCOME }
            TransactionListItem.EXPENSE -> list.filter { it.type == TransactionType.EXPENSE }
        }
    }

}