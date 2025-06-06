package com.nutritionwallah.data

import com.nutritionwallah.data.domain.CustomerRepository
import com.nutritionwallah.shared.domain.Customer
import com.nutritionwallah.shared.util.RequestState
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore

class CustomerRepositoryImpl: CustomerRepository {
    override fun getCurrentUserId(): String? {
        return Firebase.auth.currentUser?.uid
    }

    override suspend fun createCustomer(
        user: FirebaseUser?,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            if(user!=null){
                val customerCollection = Firebase.firestore.collection("customers")
                val customer = Customer(
                    id = user.uid,
                    firstName = user.displayName?.split(" ")?.firstOrNull() ?: "Unknown",
                    lastName = user.displayName?.split(" ")?.lastOrNull() ?: "Unknown",
                    email = user.email ?: ""
                )
                val customerExist = customerCollection.document(user.uid).get().exists
                if(customerExist){
                    onSuccess()
                }else{
                    customerCollection.document(user.uid).set(customer)
                    onSuccess()
                }
            }else{
                onError("User Not Available")
            }
        }catch (e:Exception){
            onError("Error While Creating a customer : ${e.message.toString()}")
        }
    }

    override suspend fun signOutUser(): RequestState<Unit> {
        return try {
            Firebase.auth.signOut()
            RequestState.Success(Unit)
        }catch (e:Exception){
            RequestState.Error(e.message.toString())
        }
    }

}