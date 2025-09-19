package com.andriibryliant.moneyflow.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andriibryliant.moneyflow.R
import com.andriibryliant.moneyflow.databinding.ItemTransactionBinding
import com.andriibryliant.moneyflow.objects.Category
import com.andriibryliant.moneyflow.objects.Transaction
import com.andriibryliant.moneyflow.objects.TransactionType

class TransactionsRecyclerViewAdapter :
    RecyclerView.Adapter<TransactionsRecyclerViewAdapter.Holder>() {
    private var items = emptyList<Transaction>()
    private var categories = emptyList<Category>()

    fun setTransactions(list: List<Transaction>){
        items = list
        notifyDataSetChanged()
    }

    fun setCategories(list: List<Category>){
        categories = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        return Holder(ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(
        holder: Holder,
        position: Int
    ) {
        holder.bind(items[position], categories)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class Holder(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(transaction: Transaction, categories: List<Category>){
            val category = categories.find { it.id == transaction.categoryID }

            when{
                category?.iconRes != null -> binding.transactionIcon.setImageResource(category.iconRes)
                else -> binding.transactionIcon.setImageResource(R.drawable.ic_money_bill_transfer_solid_full)
            }

            binding.transactionName.text = transaction.name
            binding.transactionDate.text = transaction.date
            var transactionAmountText: String

            if (transaction.type == TransactionType.INCOME){
                binding.transactionAmount.setTextColor(binding.transactionAmount.resources.getColor(R.color.greenText))
                transactionAmountText = "+" + transaction.amount.toString() + transaction.currency
            }else{
                binding.transactionAmount.setTextColor(binding.transactionAmount.resources.getColor(R.color.redText))
                transactionAmountText = transaction.amount.toString() + transaction.currency
            }

            binding.transactionAmount.text = transactionAmountText
        }
    }


}