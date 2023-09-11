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

    private var _binding: FragmentRepositoriesBinding? = null
    private val binding: FragmentRepositoriesBinding get() = requireNotNull(_binding)

    private val repositoryAdapter by lazy {
        RepositoryAdapter()
    }
    private val viewModel: RepositoriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoriesBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()
        viewModel.fetchRepositoryItems()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initView() = with(binding) {
        repositoriesRV.adapter = repositoryAdapter

        swiperefresh.setOnRefreshListener {
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
        with(binding) {
            progressBar.show()
            repositoriesRV.hide()
        }
    }

    private fun showError(message: String) {
        with(binding) {
            progressBar.hide()
            swiperefresh.isRefreshing = false
            repositoriesRV.hide()
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
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