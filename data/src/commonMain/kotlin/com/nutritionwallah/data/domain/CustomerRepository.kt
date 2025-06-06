package com.nutritionwallah.data.domain

import com.nutritionwallah.shared.util.RequestState
import dev.gitlive.firebase.auth.FirebaseUser

interface CustomerRepository {
    fun getCurrentUserId() : String?
    suspend fun createCustomer(
        user : FirebaseUser?,
        onSuccess :() -> Unit,
        onError: (String) -> Unit
    )
    suspend fun signOutUser() : RequestState<Unit>
}