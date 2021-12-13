package org.sinou.android.sandbox.kotlin.basics

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}


class LoginViewModel(
    private val loginRepository: LoginRepository
): ViewModel() {

    fun login(username: String, token: String) {

//        // Create a new coroutine to move the execution off the UI thread
//        viewModelScope.launch(Dispatchers.IO) {
//            val jsonBody = "{ username: \"$username\", token: \"$token\"}"
//            loginRepository.makeLoginRequest(jsonBody)
//        }
    }
}