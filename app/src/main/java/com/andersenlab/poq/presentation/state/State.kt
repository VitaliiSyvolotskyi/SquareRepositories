package com.andersenlab.poq.presentation.state

sealed class State<out R> {
    data class Success<out T>(
        val data: T?
    ) : State<T>()

    data class Error(
        val message: String? = null
    ) : State<Nothing>()

    object Loading : State<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[message=$message]"
            Loading -> "Loading"
        }
    }
}