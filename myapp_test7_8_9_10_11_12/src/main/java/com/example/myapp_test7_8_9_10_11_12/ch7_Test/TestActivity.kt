package com.example.myapp_test7_8_9_10_11_12.ch7_Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapp_test7_8_9_10_11_12.R
import com.example.myapp_test7_8_9_10_11_12.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {
    lateinit var activityTestBinding: ActivityTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        activityTestBinding= ActivityTestBinding.inflate(layoutInflater)
        setContentView(activityTestBinding.root)

        activityTestBinding.frameBtn.setOnClickListener {
            activityTestBinding.frameBtn.visibility = View.INVISIBLE
            activityTestBinding.img1.visibility = View.VISIBLE
        }

        activityTestBinding.img1.setOnClickListener {
            activityTestBinding.frameBtn.visibility = View.VISIBLE
            activityTestBinding.img1.visibility = View.INVISIBLE
        }

    }
}