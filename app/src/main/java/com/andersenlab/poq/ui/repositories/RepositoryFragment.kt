package com.andersenlab.poq.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.andersenlab.poq.databinding.FragmentRepositoriesBinding
import com.andersenlab.poq.ui.RepositoryAdapter
import com.andersenlab.poq.ui.State
import com.andersenlab.poq.utils.hide
import com.andersenlab.poq.utils.show
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RepositoryFragment : Fragment() {

    private val binding by lazy {
        FragmentRepositoriesBinding.inflate(LayoutInflater.from(context))
    }
    private val repositoryAdapter by lazy {
        RepositoryAdapter()
    }
    private val viewModel: RepositoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()
    }

    private fun initView() = with(binding) {
        binding.rvRepositories.adapter = repositoryAdapter

        binding.ivRefresh.setOnClickListener {
            viewModel.fetchRepositoryItems()
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.repositoryItems.collectLatest {
                when (it) {
                    is State.Loading -> {
                        binding.pbLoading.show()
                        binding.rvRepositories.hide()
                    }

                    is State.Success -> {
                        binding.pbLoading.hide()
                        binding.rvRepositories.show()
                        repositoryAdapter.submitList(it.data)
                    }

                    is State.Error -> {
                        binding.pbLoading.hide()
                        binding.rvRepositories.hide()
                        Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}