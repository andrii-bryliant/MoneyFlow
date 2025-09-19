package com.andriibryliant.moneyflow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.andriibryliant.moneyflow.R

import com.andriibryliant.moneyflow.databinding.FragmentHomeBinding
import com.andriibryliant.moneyflow.objects.TransactionListItem
import com.andriibryliant.moneyflow.utils.Animations
import com.andriibryliant.moneyflow.viewmodels.MenuViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private var lastSelected: TransactionListItem? = null
    private val menuViewModel: MenuViewModel<TransactionListItem> by viewModels{ MenuViewModel.MenuViewModelFactory<TransactionListItem>(
        TransactionListItem.ALL) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.allText.setOnClickListener { menuViewModel.selectMenuItem(TransactionListItem.ALL) }
        binding.incomeText.setOnClickListener { menuViewModel.selectMenuItem(TransactionListItem.INCOME) }
        binding.expenseText.setOnClickListener { menuViewModel.selectMenuItem(TransactionListItem.EXPENSE) }

        menuViewModel.selectedMenuItem.observe(viewLifecycleOwner){ item ->
            updateMenuUI(item)
        }
    }

    private fun updateMenuUI(item: TransactionListItem){
        if(item != lastSelected){
            animateMenuItem(item)
            lastSelected = item
        }else{
            setMenuSelected(item)
        }
    }

    private fun animateMenuItem(item: TransactionListItem){
            val selectedView = when(item){
                TransactionListItem.ALL -> binding.allSelected
                TransactionListItem.INCOME -> binding.incomeSelected
                TransactionListItem.EXPENSE -> binding.expenseSelected
            }

            val showAnimation = Animations.buttonShowAnimation(400)

            listOf(binding.allSelected, binding.incomeSelected, binding.expenseSelected).forEach {
                it.clearAnimation()
                it.visibility = View.INVISIBLE
            }

            selectedView.visibility = View.VISIBLE
            selectedView.startAnimation(showAnimation)
    }

    private fun setMenuSelected(item: TransactionListItem){
        val selectedView = when(item){
            TransactionListItem.ALL -> binding.allSelected
            TransactionListItem.INCOME -> binding.incomeSelected
            TransactionListItem.EXPENSE -> binding.expenseSelected
        }

        listOf(binding.allSelected, binding.incomeSelected, binding.expenseSelected).forEach {
            it.clearAnimation()
            it.visibility = View.INVISIBLE
        }

        selectedView.visibility = View.VISIBLE
    }

}