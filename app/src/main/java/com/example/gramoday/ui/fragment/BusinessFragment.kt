package com.example.gramoday.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gramoday.R
import com.example.gramoday.databinding.FragmentBusinessBinding
import com.example.gramoday.ui.adapter.ProductAdapter
import com.example.gramoday.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BusinessFragment : Fragment(), ProductAdapter.OnClickListener {

    private var _binding: FragmentBusinessBinding? = null

    private val binding: FragmentBusinessBinding
        get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    private lateinit var viewReference: View

    private lateinit var adapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBusinessBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewReference = view
        setUpRecyclerView()
        lifecycleScope.launch {
            viewModel.res.observe(viewLifecycleOwner) {
                if (it != null) {
                    setUserData()
                    adapter.submitList(it.products)
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        adapter = ProductAdapter(this)
        binding.rvProducts.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvProducts.adapter = adapter
    }

    private fun setUserData() {
        val data = viewModel.res.value
        binding.tvMarketName.text = data?.business?.marketStdName
        binding.tvFirmName.text = data?.business?.firmName
        binding.tvShopNo.text = data?.business?.mandiShopnum
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(position: Int) {

        Navigation.findNavController(viewReference)
            .navigate(R.id.action_userProfileFragment_to_detailScreenFragment)
    }

}