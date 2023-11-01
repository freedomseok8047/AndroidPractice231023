package com.example.myapp_test7_8_9_10_11_12.ch11_12_Test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapp_test7_8_9_10_11_12.databinding.FragmentOneBinding

class OneFragment : Fragment() {
    //기본1
    lateinit var binding: FragmentOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ljs","생명주기 ,onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("ljs","생명주기 ,onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("ljs","생명주기 ,onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("ljs","생명주기 ,onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("ljs","생명주기 ,onStop ")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("ljs","생명주기 ,onDestroy")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //기본2
        binding = FragmentOneBinding.inflate(layoutInflater,container,false)
        //기본3
        return binding.root
    }
}