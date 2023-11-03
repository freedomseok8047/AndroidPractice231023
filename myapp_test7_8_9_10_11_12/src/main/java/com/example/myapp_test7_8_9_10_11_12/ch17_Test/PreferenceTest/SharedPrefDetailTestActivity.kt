package com.example.myapp_test7_8_9_10_11_12.ch17_Test.PreferenceTest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test7_8_9_10_11_12.databinding.ActivitySharedPrefDetailTestBinding


class SharedPrefDetailTestActivity : AppCompatActivity() {
    // 2번 화면
    // 공유 프레퍼런스 파일에서 데이터 가져오기

    lateinit var binding: ActivitySharedPrefDetailTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPrefDetailTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 조회 버튼 누르면 공유된 프레퍼런스 파일에 저장된 값 가져오기
        binding.getSharedPreferBtnTest.setOnClickListener{
            // 값 가져오기
            // 저장된 파일명 : userInfo
            // key, value 형태로 저장
            // key 로 해당 값을 불러오기.동
            val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
            val email = pref.getString("email", "Default Email")
            val password = pref.getString("password", "Default Password")
            // 라디오 값 가져오기.
            val pickRadio = pref.getString("pickRadio", "Default pickRadio")


            // 결과 뷰에 데이터 넣기.
            binding.resultEmailSP.text = email
            binding.resultPasswordSP.text = password
            // 라디오 값 결과 뷰에 넣기.
            binding.resultRadioSP.text = pickRadio

            // 삭제 테스트1 - 각 요소 부분 삭제
            binding.deleteSharedPreferBtnTest.setOnClickListener{
                val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
                val editor = pref.edit()
                editor.remove("email")
                editor.remove("password")
                editor.commit()
            }

            // 삭제 테스트2 - 파일 내용 전체 삭제
            binding.deleteFileSharedPreferBtnTest.setOnClickListener{
                val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
                val editor = pref.edit()
                editor.clear()
                editor.commit()
            }
        }
    }
}




























