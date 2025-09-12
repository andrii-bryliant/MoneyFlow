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
import com.andriibryliant.moneyflow.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private val currentMenuItem: MainMenuItem? = null

    private val homeFragment = HomeFragment()
    private val statisticsFragment = StatisticsFragment()
    private val savingsFragment = SavingsFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeIcon.setOnClickListener { mainActivityViewModel.selectMenuItem(MainMenuItem.HOME) }
        binding.statisticsIcon.setOnClickListener { mainActivityViewModel.selectMenuItem(MainMenuItem.STATISTICS) }
        binding.savingsIcon.setOnClickListener { mainActivityViewModel.selectMenuItem(MainMenuItem.SAVINGS) }
        binding.settingsIcon.setOnClickListener { mainActivityViewModel.selectMenuItem(MainMenuItem.SETTINGS) }

        mainActivityViewModel.selectedMenuItem.observe(this) { item ->
            animateMenuItem(item)
            selectFragment(item)
        }

        if (savedInstanceState == null) {
            mainActivityViewModel.selectMenuItem(MainMenuItem.HOME)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun animateMenuItem(item: MainMenuItem){
        if(mainActivityViewModel.selectedMenuItem.value != mainActivityViewModel.previousSelected){
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
    }

    private fun selectFragment(item: MainMenuItem){
        if(mainActivityViewModel.selectedMenuItem.value != mainActivityViewModel.previousSelected){
            val selectedFragment = when(item){
                MainMenuItem.HOME -> homeFragment
                MainMenuItem.STATISTICS -> statisticsFragment
                MainMenuItem.SAVINGS -> savingsFragment
                MainMenuItem.SETTINGS -> settingsFragment
            }

            supportFragmentManager.beginTransaction().replace(binding.fragmentContainer.id, selectedFragment).commit()
        }
    }

}