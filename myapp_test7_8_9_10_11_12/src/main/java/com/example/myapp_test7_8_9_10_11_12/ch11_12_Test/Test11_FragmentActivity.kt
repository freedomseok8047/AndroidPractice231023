package com.example.myapp_test7_8_9_10_11_12.ch11_12_Test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapp_test7_8_9_10_11_12.R
import com.example.myapp_test7_8_9_10_11_12.databinding.ActivityTest11FragmentBinding

class Test11_FragmentActivity : AppCompatActivity() {

    //프레그 먼트를 출력하는
    lateinit var binding: ActivityTest11FragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTest11FragmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //프레그먼트 출력하기 . 연결(바인딩)
        val fragmentManager : FragmentManager = supportFragmentManager
        val transaction : FragmentTransaction = fragmentManager.beginTransaction()

        //첫 번째, 프래그먼트
        val oneFragment = OneFragment()
        //두 번째, 프래그먼트
        val twoFragment = TwoFragment()
        //세 번째, 프래그먼트
        val threeFragment = ThreeFragment()


        //첫 번째, 프래그먼트 붙이기
        transaction.add(R.id.fragment1,oneFragment)

        //두 번째, 프래그먼트 붙이기
        transaction.add(R.id.fragment2,twoFragment)

        //세 번째, 프래그먼트 붙이기
        transaction.add(R.id.fragment3,threeFragment)

        // 백스택, 화면을 출력시, Task라는 공간을 사용해서, (메모리 사용을 함)
        // 출력하고, 화면 전환이 발생할 경우, 매번 프래그먼트를 소멸시키고, 또 생성하고
        // 이 작업이 반복이 되면, 자원이 비효울적.
        // 그래서, 잠시 keep, 가지고 있다가, 다시 그려주기 (소멸 ㄴㄴ)
        // 액티비티 에서도, 기존의 액디비티를 최대한 활용하는 방안으로 singleTop
        // 뷰 페이져 등을 이용해서 , 프래그먼트 전환할 때 ,다시 확인하기
        transaction.addToBackStack(null)

        //화면에 출력하기
        transaction.commit()


    }
}

























