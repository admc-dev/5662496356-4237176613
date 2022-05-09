package com.admcdev.crypto.techtest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.admcdev.crypto.techtest.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class LandingFragment : Fragment() {

    companion object {
        fun newInstance() = LandingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.landing_fragment, container, false)
    }

}