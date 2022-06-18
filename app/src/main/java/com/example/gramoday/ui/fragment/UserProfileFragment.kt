package com.example.gramoday.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.gramoday.databinding.FragmentUserProfileBinding
import com.example.gramoday.ui.adapter.ViewPagerAdapter
import com.example.gramoday.ui.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private var _binding: FragmentUserProfileBinding? = null
    private val binding: FragmentUserProfileBinding
        get() = _binding!!
    private val homeFragmentTitle = arrayOf("Business", "Review")
    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        lifecycleScope.launch {
            viewModel.res.observe(viewLifecycleOwner) {
                setUserData()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setUserData() {
        val data = viewModel.res.value
        binding.tvName.text = data?.name
        if (data?.language.equals("en")) {
            binding.tvLanguage.text = "Speaks English"
        } else {
            binding.tvLanguage.text = "Speaks Hindi"
        }
        binding.tvAddress.text = data?.loclevel3Name + ", ${data?.loclevel2Name}"
    }

    private fun setUpViewPager() {
        val pagerAdapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = homeFragmentTitle[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}