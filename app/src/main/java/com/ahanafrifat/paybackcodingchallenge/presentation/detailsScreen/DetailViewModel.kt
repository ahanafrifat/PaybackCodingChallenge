package com.ahanafrifat.paybackcodingchallenge.presentation.detailsScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.domain.use_case.UseCases
import com.ahanafrifat.paybackcodingchallenge.utils.Constants.DETAILS_HIT_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCases: UseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedHit:MutableStateFlow<Hit?> = MutableStateFlow(null)
    val selectedHit = _selectedHit

    init {
        viewModelScope.launch (Dispatchers.IO){
            val hitId = savedStateHandle.get<Int>(DETAILS_HIT_ID)
            _selectedHit.value = hitId?.let {
                useCases.getSelectedHitUseCases(hitId = it)
            }
        }
    }
}