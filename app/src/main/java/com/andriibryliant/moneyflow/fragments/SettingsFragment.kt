package com.andriibryliant.moneyflow.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.andriibryliant.moneyflow.R
import com.andriibryliant.moneyflow.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private lateinit var binding: FragmentSettingsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingsBinding.bind(view)
    }
}