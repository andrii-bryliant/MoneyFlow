package com.andriibryliant.moneyflow.objects

data class Transaction(
    val name: String,
    val date: String,
    val amount: Int,
    val type: TransactionType,
    val currency: String,
    val categoryID: String
)

enum class TransactionType{
    INCOME,
    EXPENSE
}