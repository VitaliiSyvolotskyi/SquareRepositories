package com.andersenlab.poq

import com.andersenlab.poq.core.test.BaseTest
import com.andersenlab.poq.core.test.utils.isTheSame
import com.andersenlab.poq.data.mapper.DataMapper
import com.andersenlab.poq.data.model.RepositoryResponse
import com.andersenlab.poq.domain.model.Repository
import org.junit.Test

class MapperTest : BaseTest() {
    @Test
    fun mappingTest() {
        val responseModel = RepositoryResponse(
            123,
            "Name Repo 1",
            "Lorem ipsum"
        )

        val expectedResult = with(responseModel) {
            Repository(
                id = this.id ?: 0,
                name = this.name ?: "",
                description = this.description ?: ""
            )
        }

        val mapper = DataMapper()
        mapper.mapModel(responseModel) isTheSame expectedResult
    }
}