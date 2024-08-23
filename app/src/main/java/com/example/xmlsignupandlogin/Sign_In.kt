package com.example.xmlsignupandlogin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlsignupandlogin.databinding.SignInBinding
import com.google.firebase.auth.FirebaseAuth

class Sign_In : AppCompatActivity() {

        private lateinit var binding: SignInBinding
        lateinit var firebaseAuth: FirebaseAuth

         override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignInBinding.inflate(layoutInflater)

        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        binding.textView.setOnClickListener {
            startActivity(Intent(this , sign_up::class.java))
        }


        binding.button.setOnClickListener{
            val name = binding.NameEt.text.toString()
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if(name.isNotEmpty() && email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email , pass).addOnCompleteListener{
                    if (it.isSuccessful){
                      val bundle : Bundle = Bundle().apply {
                          putString("Name" , name)
                      }
                        val  intent : Intent = Intent(this , MainActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)

                    }
                    else{
                        Toast.makeText(this , it.toString() , Toast.LENGTH_SHORT).show()
                    }
                }

            }
            else{
                Toast.makeText(this , "Fill all the fields" , Toast.LENGTH_SHORT).show()
            }
        }




        }
    }
