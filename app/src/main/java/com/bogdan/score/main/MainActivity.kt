package com.bogdan.score.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bogdan.score.R
import com.bogdan.score.ui.MainFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, MainFragment(), MainFragment.FRAGMENT_TAG)
            .commit()
    }
}