package com.andersenlab.poq.domain

import com.andersenlab.poq.data.api.RepositoriesApi
import com.andersenlab.poq.data.mapper.toRepositoryItem
import com.andersenlab.poq.domain.model.Repository
import com.andersenlab.poq.presentation.state.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RepositoriesUseCaseImpl @Inject constructor(private val repositoriesApi: RepositoriesApi) :
    RepositoriesUseCase {

    override suspend fun fetchRepositories(): Flow<State<List<Repository>>> {
        return flow {
            val repositories = repositoriesApi.getRepositories().body()?.map {
                it.toRepositoryItem()
            }
            emit(State.Success(repositories))
        }.flowOn(Dispatchers.IO)
    }
}