package com.existentio.branchedlauncher.ui.commandline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.existentio.branchedlauncher.R
import com.existentio.branchedlauncher.databinding.CommandLineFragmentBinding

class CommandLineFragment : Fragment() {

    private var _binding: CommandLineFragmentBinding? = null
    private val viewModel: CommandLineViewModel by viewModels()
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()

        _binding = CommandLineFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        performNavigation()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun performNavigation() {
        binding.tvCancel.setOnClickListener {
            navController.navigate(R.id.action_CommandLineFragment_to_LeadFragment)
        }
    }


}