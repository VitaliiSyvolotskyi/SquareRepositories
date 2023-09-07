package com.andersenlab.poq.domain

import com.andersenlab.poq.presentation.repositories.Repository
import com.andersenlab.poq.presentation.state.State
import kotlinx.coroutines.flow.Flow

interface RepositoriesUseCase {
    suspend fun fetchRepositories(): Flow<State<List<Repository>>>
}