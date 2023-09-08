package com.andersenlab.poq.data.mapper

import com.andersenlab.poq.data.model.RepositoryResponse
import com.andersenlab.poq.domain.model.Repository

class DataMapper : Mapper<RepositoryResponse, Repository> {
    override fun mapModel(model: RepositoryResponse): Repository {
        return Repository(
            id = model.id ?: 0,
            name = model.name ?: "",
            description = model.description ?: ""
        )
    }
}