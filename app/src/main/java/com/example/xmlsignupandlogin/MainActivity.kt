package com.example.xmlsignupandlogin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlsignupandlogin.databinding.ActivityMainBinding

import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val bundle = intent.extras
        val currentUser = auth.currentUser


        val userName: TextView = binding.userNameTextView
        val userEmail: TextView = binding.userEmailTextView
        val logoutButton: Button = binding.button


        if (currentUser != null) {
            val name = bundle?.getString("Name")
            val email = currentUser.email


            if (!name.isNullOrEmpty() && !email.isNullOrEmpty()) {
                userName.text = "Hello, $name!"
                userEmail.text = "Current email: $email"
            }


            else {
                userName.text = "Hello, User!"
                userEmail.text = "No current email"
            }


            logoutButton.setOnClickListener {
                auth.signOut()

                val intent = Intent(this,   Sign_In::class.java)
                startActivity(intent)
                finish()
            }
        }

        else {
            userName.text = "Hello, Guest!"
            userEmail.text = "No email found."
        }
    }
}
