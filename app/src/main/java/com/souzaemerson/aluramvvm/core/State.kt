package com.souzaemerson.aluramvvm.core

data class State<out T>(
    val status: Status,
    val loading: Boolean?,
    val data: T?,
    val error: Throwable?
) {
    companion object{
        fun <T> success(data: T?): State<T>{
            return State(Status.SUCCESS, loading = false, data, error = null)
        }
       fun <T> error(error: Throwable?): State<T>{
           return State(Status.ERROR, loading = false, null, error)
       }
        fun <T> loading(loading: Boolean?): State<T>{
            return State(Status.LOADING, loading, null,null)
        }
    }
}