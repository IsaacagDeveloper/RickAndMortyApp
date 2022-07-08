package com.mango.android.presentation.viewmodels.failure

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mango.android.core.core.Failure

class FailureViewModelImpl : FailureViewModel {

    private val _failure: MutableLiveData<Failure> = MutableLiveData()
    override val failure: LiveData<Failure> = _failure

    override fun handleFailure(failure: Failure) {
        _failure.postValue(failure)
    }
}