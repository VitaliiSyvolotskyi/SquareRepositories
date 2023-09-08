package com.andersenlab.poq

import com.andersenlab.poq.data.api.RepositoriesApi
import com.andersenlab.poq.data.mapper.toRepositoryItem
import com.andersenlab.poq.data.model.RepositoryResponse
import com.andersenlab.poq.domain.RepositoriesUseCase
import com.andersenlab.poq.domain.RepositoriesUseCaseImpl
import com.andersenlab.poq.domain.model.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import com.andersenlab.poq.presentation.state.State
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class RepositoryUseCaseTest {

    lateinit var dataRepository: RepositoriesUseCase

    @Mock
    lateinit var api: RepositoriesApi

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dataRepository = RepositoriesUseCaseImpl(api)
    }

    @Test
    fun list() {
        runBlocking {
            val response = listOf(
                RepositoryResponse(
                    123,
                    "Name Repo 1",
                    "Lorem ipsum"
                ),
                RepositoryResponse(
                    124,
                    "Name Repo 2",
                    "Lorem ipsum"
                ),
            )
            val repositoryItems = response.map { it.toRepositoryItem() }
            Mockito.`when`(api.getRepositories()).thenReturn(response)
            dataRepository.fetchRepositories().collectLatest {
                if (it is State.Success) {
                    assertEquals(repositoryItems, it.data)
                }
            }
        }
    }

    @Test
    fun empty() {
        runBlocking {
            Mockito.`when`(api.getRepositories()).thenReturn(listOf())
            dataRepository.fetchRepositories().collectLatest {
                if (it is State.Success) {
                    assertEquals(listOf<Repository>(), it.data)
                }
            }
        }
    }
}