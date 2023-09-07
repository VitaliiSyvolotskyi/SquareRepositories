package com.andersenlab.poq.data.mapper

import com.andersenlab.poq.data.model.RepositoryResponse
import com.andersenlab.poq.presentation.repositories.Repository

fun RepositoryResponse.toRepositoryItem(): Repository {
    return Repository(
        id = this.id ?: 0,
        name = this.name ?: "",
        description = this.description ?: "",
    )
}