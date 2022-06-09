package com.example.branchedlauncher.ui.searchscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.branchedlauncher.R
import com.example.branchedlauncher.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchScreenFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentSearchBinding? = null
    private val viewModel: SearchScreenViewModel by viewModels()

    private val binding get() = _binding!!
    private var adapter = binding.rvAppsContainer.adapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        val textQuery = binding.searchBar.query

        binding.rvAppsContainer.layoutManager = LinearLayoutManager(requireContext())

        adapter = SearchAppsAdapter(
            requireContext(),
            viewModel,
            textQuery
        )
//        binding.searchBar.setOnQueryTextListener(SearchView.OnQueryTextListener)) {
//
//        }


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_SearchFragment_to_LeadFragment)
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        adapter
        return false
    }
}