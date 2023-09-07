package com.andersenlab.poq.ui.repositories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andersenlab.poq.domain.RepositoriesUseCase
import com.andersenlab.poq.ui.RepositoryItems
import com.andersenlab.poq.ui.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(private val repository: RepositoriesUseCase) :
    ViewModel() {

    private val _repositoryItems = MutableStateFlow<State<RepositoryItems>>(State.Loading)
    val repositoryItems: StateFlow<State<RepositoryItems>> get() = _repositoryItems

    init {
        fetchRepositoryItems()
    }

    fun fetchRepositoryItems() {
        viewModelScope.launch {
            repository.fetchRepositories()
                .onStart {
                    _repositoryItems.value = State.Loading
                }
                .catch {
                    _repositoryItems.value = State.Error(message = it.localizedMessage)
                }
                .collectLatest {
                    when (it) {
                        is State.Success -> _repositoryItems.value = State.Success(data = it.data)
                        is State.Error -> _repositoryItems.value = State.Error(it.message)
                        else -> _repositoryItems.value = State.Loading
                    }
                }
        }
    }
}