package com.mango.android.rickandmorty_cache.core

import com.mango.android.core.core.FIRST_PAGE
import com.mango.android.rickandmorty_cache.models.CurrentPageDbModel

/**
 * DB Response and models
 */

val fakeCurrentPageDbModel by lazy {
    CurrentPageDbModel(
        FAKE_CHARACTERS_LIST_ID,
        FIRST_PAGE
    )
}

const val FAKE_CHARACTERS_LIST_ID = 1972