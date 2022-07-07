package com.isaacag.core.core

sealed class Failure {
    object EmptyResponse : Failure()
    object ServerError : Failure()
}