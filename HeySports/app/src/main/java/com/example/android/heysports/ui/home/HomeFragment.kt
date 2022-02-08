package com.example.android.heysports.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.android.heysports.R
import com.example.android.heysports.databinding.FragmentHomeBinding
import com.example.android.heysports.util.extension.setPlayer
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Jihye Noh
 * Date: 2022-02-05
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate<FragmentHomeBinding?>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        ).apply {
            lifecycleOwner = this@HomeFragment
            model = viewModel
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding.pvIntroduction.setPlayer(context)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        binding.pvIntroduction.player = null
    }
}