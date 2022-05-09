package com.admcdev.crypto.techtest.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.admcdev.crypto.techtest.R
import com.admcdev.crypto.techtest.data.CurrencyInfo
import com.admcdev.crypto.techtest.databinding.DemoActivityBinding
import com.admcdev.crypto.techtest.fragments.CurrenciesFragment
import com.admcdev.crypto.techtest.viewmodels.CurrenciesViewModel
import com.admcdev.crypto.techtest.viewmodels.DemoViewModel
import kotlinx.android.synthetic.main.demo_activity.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DemoActivity : AppCompatActivity(), CurrenciesFragment.OnItemClickListener {

    private val demoViewModel: DemoViewModel by viewModel()
    private lateinit var binding: DemoActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DemoActivityBinding.inflate(layoutInflater)
        binding.activity = this

        ViewTreeLifecycleOwner.set(window.decorView, this)

        setContentView(binding.root)

        demoViewModel.sortCurrenciesVisibility.observe(this) {
            binding.sortCurrenciesButton.visibility = it
        }

        demoViewModel.resetCurrenciesVisibility.observe(this) {
            binding.resetCurrenciesButton.visibility = it
        }

        demoViewModel.activeFragment.observe(this, Observer {
            when (it) {
                DemoViewModel.FragmentID.LANDING -> findNavController(R.id.navHostFragment).navigate(
                    R.id.landingFragment
                )
                DemoViewModel.FragmentID.CURRENCIES -> findNavController(R.id.navHostFragment).navigate(
                    R.id.currenciesFragment
                )
            }
        })
    }

    fun showCurrencyClick() {
        demoViewModel.showCurrencyClick()
    }

    fun resetCurrencies() {
        (navHostFragment.getFragment<NavHostFragment>().childFragmentManager.fragments[0] as CurrenciesFragment).sortClicked(
            CurrenciesViewModel.SortType.NONE
        )
    }

    fun sortCurrenciesClick() {
        (navHostFragment.getFragment<NavHostFragment>().childFragmentManager.fragments[0] as CurrenciesFragment).sortClicked(
            CurrenciesViewModel.SortType.ATOZ
        )
    }

    override fun onBackPressed() {

        //check if we wanna' handle the backstack ourselves
        if (!demoViewModel.backPressed()) {
            super.onBackPressed()
        }

    }

    override fun onItemClick(currencyInfo: CurrencyInfo) {
        Toast.makeText(this, "Item Click Callback Triggered: ${currencyInfo.name}", LENGTH_LONG)
            .show()
        //do whatever we'd like to do here with the information provided (currently just the position)
    }
}