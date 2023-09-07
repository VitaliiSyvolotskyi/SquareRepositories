package com.andersenlab.poq.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.andersenlab.poq.R
import com.andersenlab.poq.ui.repositories.RepositoryFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame_content, RepositoryFragment())
                .commit()
        }

    }
}