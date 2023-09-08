package com.andersenlab.poq

import com.andersenlab.poq.core.test.BaseTest
import com.andersenlab.poq.data.api.RepositoriesApi
import com.andersenlab.poq.data.mapper.DataMapper
import com.andersenlab.poq.data.model.RepositoryResponse
import com.andersenlab.poq.domain.RepositoriesUseCase
import com.andersenlab.poq.domain.RepositoriesUseCaseImpl
import com.andersenlab.poq.domain.model.Repository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import com.andersenlab.poq.presentation.state.State
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Assert
import org.junit.Test

class RepositoryUseCaseTest : BaseTest() {


    lateinit var dataRepository: RepositoriesUseCase

    @MockK
    lateinit var api: RepositoriesApi

    override fun setUp() {
        super.setUp()
        dataRepository = RepositoriesUseCaseImpl(api)
    }

    @Test
    fun `fetchRepositories() returns list`() {
        runBlocking {
            val testResponse = listOf(
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
            val repositoryItems = testResponse.map { DataMapper().mapModel(it) }
            coEvery { api.getRepositories() } returns testResponse
            dataRepository.fetchRepositories().collectLatest {
                assert(it is State.Success)
                assertEquals(repositoryItems, (it as State.Success).data)
            }
            coVerify { api.getRepositories() }
        }
    }

    @Test
    fun `fetchRepositories() returns empty list`() {
        runBlocking {
            coEvery { api.getRepositories() } returns listOf()
            dataRepository.fetchRepositories().collectLatest {
                assert(it is State.Success)
                assertEquals(listOf<Repository>(), (it as State.Success).data)
            }
            coVerify { api.getRepositories() }
        }
    }

    @Test
    fun `fetchRepositories() throwing error`() {
        runBlocking {
            coEvery { api.getRepositories() } throws Error()
            Assert.assertThrows(Error::class.java) {
                runBlocking {
                    dataRepository.fetchRepositories().collectLatest {
                        assertTrue(it is State.Error)
                    }
                }
            }
            coVerify { api.getRepositories() }
        }
    }
}