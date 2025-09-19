package com.andriibryliant.moneyflow.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.andriibryliant.moneyflow.R
import com.andriibryliant.moneyflow.databinding.ActivityMainBinding
import com.andriibryliant.moneyflow.fragments.HomeFragment
import com.andriibryliant.moneyflow.fragments.SavingsFragment
import com.andriibryliant.moneyflow.fragments.SettingsFragment
import com.andriibryliant.moneyflow.fragments.StatisticsFragment
import com.andriibryliant.moneyflow.objects.MainMenuItem
import com.andriibryliant.moneyflow.utils.Animations
import com.andriibryliant.moneyflow.viewmodels.MenuViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var lastSelected: MainMenuItem? = null
    private val menuViewModel: MenuViewModel<MainMenuItem> by viewModels { MenuViewModel.MenuViewModelFactory<MainMenuItem>(
        MainMenuItem.HOME) }

    private val homeFragment = HomeFragment()
    private val statisticsFragment = StatisticsFragment()
    private val savingsFragment = SavingsFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeIcon.setOnClickListener { menuViewModel.selectMenuItem(MainMenuItem.HOME) }
        binding.statisticsIcon.setOnClickListener { menuViewModel.selectMenuItem(MainMenuItem.STATISTICS) }
        binding.savingsIcon.setOnClickListener { menuViewModel.selectMenuItem(MainMenuItem.SAVINGS) }
        binding.settingsIcon.setOnClickListener { menuViewModel.selectMenuItem(MainMenuItem.SETTINGS) }

        menuViewModel.selectedMenuItem.observe(this) { item ->
            updateMenuUI(item)
        }

        if (savedInstanceState == null) {
            menuViewModel.selectMenuItem(MainMenuItem.HOME)
            supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, homeFragment).commit()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun updateMenuUI(item: MainMenuItem){
        if(item != lastSelected){
            animateMenuItem(item)
            selectFragment(item)
            lastSelected = item
        }
    }

    private fun animateMenuItem(item: MainMenuItem){
            val selectedView = when(item){
                MainMenuItem.HOME -> binding.homeSelected
                MainMenuItem.STATISTICS -> binding.statisticsSelected
                MainMenuItem.SAVINGS -> binding.savingsSelected
                MainMenuItem.SETTINGS -> binding.settingsSelected
            }

            val showAnimation = Animations.buttonShowAnimation(400)

            listOf(binding.homeSelected, binding.statisticsSelected, binding.savingsSelected, binding.settingsSelected).forEach {
                it.clearAnimation()
                it.visibility = View.INVISIBLE
            }

            selectedView.visibility = View.VISIBLE
            selectedView.startAnimation(showAnimation)
    }

    private fun selectFragment(item: MainMenuItem){
            val selectedFragment = when(item){
                MainMenuItem.HOME -> homeFragment
                MainMenuItem.STATISTICS -> statisticsFragment
                MainMenuItem.SAVINGS -> savingsFragment
                MainMenuItem.SETTINGS -> settingsFragment
            }

            supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, selectedFragment).commit()
    }
}