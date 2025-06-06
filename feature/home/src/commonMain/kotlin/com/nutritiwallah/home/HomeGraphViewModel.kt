package com.nutritiwallah.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nutritionwallah.data.domain.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeGraphViewModel(
    private val customerRepository: CustomerRepository,
) : ViewModel() {
    fun signOut(
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ){
        viewModelScope.launch {
            val state = withContext(Dispatchers.IO){
                customerRepository.signOutUser()
            }
            if(state.isSuccess()){
                onSuccess()
            }else{
                onError(state.getErrorMessage())
            }
        }
    }
}