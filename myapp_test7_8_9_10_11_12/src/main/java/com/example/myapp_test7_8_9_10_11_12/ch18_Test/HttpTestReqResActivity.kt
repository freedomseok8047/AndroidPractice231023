package com.example.myapp_test7_8_9_10_11_12.ch18_Test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test7_8_9_10_11_12.databinding.ActivityHttpTestReqResBinding

class HttpTestReqResActivity : AppCompatActivity() {
    lateinit var binding: ActivityHttpTestReqResBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpTestReqResBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // request.in : 외국에서 무로 REST 테스트 서버 제공해주는 곳
        // 데이터 받아서, 리사이클러 뷰에 출력해보기
        // 리사이클러 뷰 준비물 1)어댑터 2)뷰홀더 3)목록 요소 뷰 4)데이터

        // 준비작업 1) 모델 준비하기
//        기





    }
    // onCreate






    // http 통신
    // 1) Myapplication 설정 파일 2) 인터페이스 3) 모델 또는 모델이 담겨진 리스트 필요
}


































