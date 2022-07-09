package com.mango.android.data.mappers

import com.mango.android.core.core.FIRST_PAGE
import com.mango.android.rickandmorty_cache.models.CurrentPageDbModel

class CurrentPageMapper {

    fun fromListIDAndPageDomainModelToCurrentPageDbModel(listID: Int, page: Int) =
        CurrentPageDbModel(
            listID = listID,
            currentPage = page
        )

    fun fromCurrentPageDbModelToPage(currentPageDbModel: CurrentPageDbModel?) =
        currentPageDbModel?.currentPage ?: FIRST_PAGE

}