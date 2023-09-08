package com.andersenlab.poq.presentation.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.andersenlab.poq.databinding.FragmentRepositoriesBinding
import com.andersenlab.poq.domain.model.Repository
import com.andersenlab.poq.presentation.state.State
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
        viewModel.fetchRepositoryItems()
    }

    private fun initView() = with(binding) {
        binding.repositoriesRV.adapter = repositoryAdapter

        binding.swiperefresh.setOnRefreshListener {
            viewModel.fetchRepositoryItems()
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.repositoryItems.collectLatest {
                when (it) {
                    is State.Loading -> showLoading()
                    is State.Success -> showRepositories(it.data as List<Repository>)
                    is State.Error -> showError(it.message as String)
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.show()
        binding.repositoriesRV.hide()
    }

    private fun showError(message: String) {
        binding.progressBar.hide()
        binding.swiperefresh.isRefreshing = false
        binding.repositoriesRV.hide()
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun showRepositories(it: List<Repository>) {
        with(binding) {
            progressBar.hide()
            swiperefresh.isRefreshing = false
            repositoriesRV.show()
            repositoryAdapter.submitList(it)
        }
    }
}