package com.andersenlab.poq.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null
)