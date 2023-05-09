package com.infos.assistant.ui.login_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.infos.assistant.MainActivity
import com.infos.assistant.databinding.ActivityGoogleSingInBinding
import com.infos.assistant.ui.login_screen.LoginFragment.Companion.EXTRA_NAME

class GoogleSingInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGoogleSingInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleSingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textDisplayName.text = intent.getStringExtra(EXTRA_NAME)
        binding.logout.setOnClickListener {
            Firebase.auth.signOut()
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}