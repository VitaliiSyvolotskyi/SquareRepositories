package com.andersenlab.poq.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryResponse(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("description") var description: String? = null
)