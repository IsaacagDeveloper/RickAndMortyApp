package com.mango.android.presentation.viewmodels.failure

import androidx.lifecycle.LiveData
import com.mango.android.core.core.Failure

interface FailureViewModel {
    val failure: LiveData<Failure>
    fun handleFailure(failure: Failure)
}