package com.andersenlab.poq.data.mapper

import com.andersenlab.poq.data.model.RepositoryResponse
import com.andersenlab.poq.domain.model.Repository

fun RepositoryResponse.toRepositoryItem(): Repository {
    return Repository(
        id = this.id ?: 0,
        name = this.name ?: "",
        description = this.description ?: ""
    )
}