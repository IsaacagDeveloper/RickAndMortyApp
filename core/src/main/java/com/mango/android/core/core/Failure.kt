package com.mango.android.core.core

sealed class Failure {
    object EmptyResponse : Failure()
    object ServerError : Failure()
}