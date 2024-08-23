package com.example.xmlsignupandlogin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.xmlsignupandlogin.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class sign_up : AppCompatActivity() {

   private lateinit var binding: ActivitySignUpBinding
    lateinit var firebaseAuth: FirebaseAuth


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

      setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this ,  Sign_In::class.java)
            startActivity(intent)
        }


        binding.button.setOnClickListener{
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty())
            { if(pass.equals(confirmPass)){

                firebaseAuth.createUserWithEmailAndPassword(email , pass)
                    .addOnCompleteListener{
                        if (it.isSuccessful){

                         val intent = Intent(this , Sign_In::class.java)
                            startActivity(intent)

                        }
                        else{

                            Toast.makeText(this , it.exception.toString() , Toast.LENGTH_SHORT).show()

                        }
                    }
            }
                else{
                    Toast.makeText(this , "Password doesn't match" , Toast.LENGTH_SHORT).show()
            }
            }
            else{
                Toast.makeText(this , "Fill all the fields" , Toast.LENGTH_SHORT).show()
            }
        }

    }
}