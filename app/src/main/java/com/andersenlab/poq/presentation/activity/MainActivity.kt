package com.andersenlab.poq.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andersenlab.poq.R
import com.andersenlab.poq.databinding.ActivityMainBinding
import com.andersenlab.poq.presentation.repositories.RepositoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_content, RepositoryFragment())
                .commit()
        }

    }
}