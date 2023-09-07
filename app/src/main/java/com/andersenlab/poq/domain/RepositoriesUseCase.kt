package com.andersenlab.poq.domain

import com.andersenlab.poq.ui.RepositoryItems
import com.andersenlab.poq.ui.State
import kotlinx.coroutines.flow.Flow

interface RepositoriesUseCase {
    suspend fun fetchRepositories(): Flow<State<RepositoryItems>>
}