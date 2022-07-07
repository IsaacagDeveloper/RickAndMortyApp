package com.isaacag.core

//Either: https://www.adictosaltrabajo.com/2019/07/04/either-en-kotlin/
sealed class Either<out L, out R> {
    data class Left<T>(val value: T) : Either<T, Nothing>()
    data class Right<T>(val value: T) : Either<Nothing, T>()

    val isRight: Boolean get() = this is Right
    val isLeft: Boolean get() = this is Left
}

inline fun <A, B, C> Either<A, B>.fold(left: (A) -> C, right: (B) -> C): C = when(this) {
    is Either.Left -> left(this.value)
    is Either.Right -> right(this.value)
}

inline infix fun <A, B, C> Either<A, B>.mapRight(f: (B) -> C): Either<A, C> = when(this) {
    is Either.Left -> this
    is Either.Right -> Either.Right(f(this.value))
}

inline infix fun <A, B, C> Either<A, C>.mapLeft(f: (A) -> B): Either<B, C> = when(this) {
    is Either.Left -> Either.Left(f(value))
    is Either.Right -> this
}