package com.ahanafrifat.paybackcodingchallenge.presentation.homeScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ahanafrifat.paybackcodingchallenge.domain.model.Hit
import com.ahanafrifat.paybackcodingchallenge.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

//    val getAllHits = useCases.getAllHitsUseCase("")

    private val _searchQuery = mutableStateOf("")
    val searchQuery =_searchQuery

    private val _searchHits = MutableStateFlow<PagingData<Hit>>(PagingData.empty())
    val searchHits =_searchHits

    private val _showDialog = MutableSharedFlow<Boolean>()
    val showDialog = _showDialog.asSharedFlow()

    init {
        searchHits("")
    }

    fun updateQuery(query:String){
        _searchQuery.value = query
    }

    fun searchHits(query:String){
        viewModelScope.launch (Dispatchers.IO){
            useCases.getAllHitsUseCase(query = query).cachedIn(viewModelScope).collect(){
                _searchHits.value = it
            }
        }
    }

    fun onOpenDialogClicked() {
//        viewModelScope.
//        _showDialog.value = true
    }

    fun onDialogConfirm() {
//        _showDialog.value = false
    }

    fun onDialogDismiss() {
//        _showDialog.value = false
    }

}