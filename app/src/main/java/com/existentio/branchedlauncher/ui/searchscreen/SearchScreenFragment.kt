package com.existentio.branchedlauncher.ui.searchscreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.existentio.branchedlauncher.R
import com.existentio.branchedlauncher.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchScreenFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchScreenViewModel by viewModels()
    private lateinit var adapter: SearchAppsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.rvAppsContainer.layoutManager = LinearLayoutManager(requireContext())
        adapter = SearchAppsAdapter(requireContext(), viewModel)
        binding.rvAppsContainer.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performNavigation()
        performSearch()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun performNavigation() {
        binding.btnCancel.setOnClickListener {
            findNavController().navigate(R.id.action_SearchFragment_to_LeadFragment)
        }
    }

    private fun performSearch() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query)
                Log.d("searchSubmit", query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search(newText)
                Log.d("searchChange", newText.toString())
                return true
            }
        })
    }

    private fun search(textQuery: String?) {
        adapter.filteredApps.clear()
        adapter.filteredApps =
            viewModel.filterApps(viewModel.loadApps(), textQuery as CharSequence)
        Log.d("filteredApps.size", adapter.filteredApps.size.toString())

        if (adapter.filteredApps.isEmpty()) binding.rvAppsContainer.visibility = INVISIBLE
        else binding.rvAppsContainer.visibility = VISIBLE

        adapter.notifyDataSetChanged()
    }

}