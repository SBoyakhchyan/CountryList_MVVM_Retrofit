package com.example.countrylist_mvvm_retrofit.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrylist_mvvm_retrofit.R
import com.example.countrylist_mvvm_retrofit.adapter.CountryListAdapter
import com.example.countrylist_mvvm_retrofit.databinding.FragmentCountryListBinding
import com.example.countrylist_mvvm_retrofit.model.CountryListViewModel
import com.example.countrylist_mvvm_retrofit.model.CountryModel

class CountryListFragment : Fragment() {
    private lateinit var binding: FragmentCountryListBinding
    private lateinit var recyclerAdapter: CountryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView()
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_country_list, container, false)
        binding = FragmentCountryListBinding.bind(view)
        return binding.root
    }

    private fun initRecyclerView() {
        binding.countryListRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdapter = CountryListAdapter(ArrayList<CountryModel>())
        binding.countryListRecyclerview.adapter = recyclerAdapter

    }

    private fun initViewModel() {
        val viewModel: CountryListViewModel =
            ViewModelProvider(this)[CountryListViewModel::class.java]
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                recyclerAdapter.apply {
                    setCountryList(ArrayList())
                    notifyDataSetChanged()
                }
            } else {
                Toast.makeText(requireContext(), "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall()
    }
}