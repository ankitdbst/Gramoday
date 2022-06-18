package com.example.gramoday.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gramoday.databinding.FragmentDetailScreenBinding
import com.example.gramoday.ui.adapter.PriceAdapter
import com.example.gramoday.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailScreenFragment : Fragment() {
    private var _binding: FragmentDetailScreenBinding? = null

    private val binding: FragmentDetailScreenBinding
        get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private lateinit var adapter: PriceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailScreenBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        lifecycleScope.launch {
            viewModel.res.observe(viewLifecycleOwner) {
                if (it != null) {
                    adapter.submitList(it.products[0].posts[0].priceDetails)
                    setUserData()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        adapter = PriceAdapter()
        binding.rvRates.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvRates.adapter = adapter
    }

    @SuppressLint("SetTextI18n")
    private fun setUserData() {
        val data = viewModel.res.value
        binding.tvName.text = data?.name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}