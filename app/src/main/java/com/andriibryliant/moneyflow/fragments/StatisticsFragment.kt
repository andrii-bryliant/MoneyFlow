package com.andriibryliant.moneyflow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.andriibryliant.moneyflow.R
import com.andriibryliant.moneyflow.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment(R.layout.fragment_statistics) {
    private lateinit var binding: FragmentStatisticsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStatisticsBinding.bind(view)
    }
}