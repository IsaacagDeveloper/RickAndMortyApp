package com.mango.android.core

import com.mango.android.core.core.Either
import com.mango.android.core.core.fold
import com.mango.android.core.core.mapLeft
import com.mango.android.core.core.mapRight
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Test

class EitherTest {

    @Test
    fun `Either Right should return correct type`() {
        val result = Either.Right("rick and morty")

        result shouldBeInstanceOf Either::class.java
        result.isRight shouldBe true
        result.isLeft shouldBe false
        result.fold({},
            { right ->
                right shouldBeInstanceOf String::class.java
                right shouldBeEqualTo "rick and morty"
            })
    }

    @Test
    fun `Either Left should return correct type`() {
        val result = Either.Left(Exception("common error"))

        result shouldBeInstanceOf Either::class.java
        result.isLeft shouldBe true
        result.isRight shouldBe false
        result.fold(
            { left ->
                left shouldBeInstanceOf Exception::class.java
                left.message shouldBeEqualTo "common error"
            }, {})
    }

    @Test
    fun `test onSuccess extension function`() {
        val either: Either.Right<String> = Either.Right("success")

        either.mapRight {
            it shouldBeEqualTo "success"
        }
    }

    @Test
    fun `test onError extension function`() {
        val either: Either.Left<String> = Either.Left("error")

        either.mapLeft {
            it shouldBeEqualTo "error"
        }
    }

}