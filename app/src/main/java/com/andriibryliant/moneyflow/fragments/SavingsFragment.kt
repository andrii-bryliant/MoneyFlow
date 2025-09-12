package com.andriibryliant.moneyflow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.andriibryliant.moneyflow.R
import com.andriibryliant.moneyflow.databinding.FragmentSavingsBinding

class SavingsFragment : Fragment(R.layout.fragment_savings) {
    private lateinit var binding: FragmentSavingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSavingsBinding.bind(view)
    }
}