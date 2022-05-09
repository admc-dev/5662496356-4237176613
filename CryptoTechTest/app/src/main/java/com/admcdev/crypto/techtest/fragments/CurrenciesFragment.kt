package com.admcdev.crypto.techtest.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.admcdev.crypto.techtest.utils.CurrencyAdapter
import com.admcdev.crypto.techtest.data.CurrencyInfo
import com.admcdev.crypto.techtest.databinding.CurrenciesFragmentBinding
import com.admcdev.crypto.techtest.viewmodels.CurrenciesViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.ClassCastException
import io.reactivex.disposables.CompositeDisposable


class CurrenciesFragment : Fragment(), CurrencyAdapter.OnItemClickListener {

    interface OnItemClickListener {
        fun onItemClick(currencyInfo: CurrencyInfo)
    }

    private val currenciesViewModel: CurrenciesViewModel by viewModel()
    private val disposables = CompositeDisposable()

    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var binding: CurrenciesFragmentBinding
    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CurrenciesFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currencyAdapter = CurrencyAdapter(this)

        Log.d("HIT HERE", "NOW")

        disposables.add(
            currenciesViewModel.currencyList.observeOn(AndroidSchedulers.mainThread()).subscribe {
                Log.d("HIT HERE", "NOW NOW")
                currencyAdapter.setCurrencies(it)
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.currencyRecyclerView.adapter = currencyAdapter
        binding.currencyRecyclerView.setLayoutManager(LinearLayoutManager(context));
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            onItemClickListener = context as OnItemClickListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                context.toString()
            )
        }
    }

    override fun itemClick(currencyInfo: CurrencyInfo) {
        onItemClickListener.onItemClick(currencyInfo)
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    fun sortClicked(sortType: CurrenciesViewModel.SortType) {
        currenciesViewModel.sortClicked(sortType)
    }
}