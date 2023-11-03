package com.example.myapp_test7_8_9_10_11_12.ch17_Test.PreferenceTest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myapp_test7_8_9_10_11_12.databinding.ActivitySharedPreferTestBinding


class SharedPreferTestActivity : AppCompatActivity() {
    // 1번 화면
    // val test = 공유 프레퍼런스 파일에 데이터 담기
    // 공유 프레퍼런스 => 모든 액티비티 화면에서 접근 가능
    // Edit Text 뷰에서
    // 라디오 뷰에서
    // 각 뷰에서, 데이터를 가져와서 (like 회원가입)
    // test에 저장
    lateinit var binding: ActivitySharedPreferTestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 이벤트 처리 예정
        // 회원가입으로 사용했던 뷰를 재사용
        binding.buttonInsertSP.setOnClickListener{
            val email = binding.userEmail.text.toString()
            val password = binding.userPassword.text.toString()
            // 라디오 값 가져와서 사용.
            val pickRadio = getValue(binding.testRadioGroup)

            // 공유 프레퍼런스 값 넣기
            val pref = getSharedPreferences("userInfo", MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString("email", email)
            editor.putString("password", password)
            // 값 넣기.
            editor.putString("pickRadio",pickRadio)
            editor.commit()


            // 2번 화면으로 이동
            val intent = Intent(this,SharedPrefDetailTestActivity::class.java)
            startActivity(intent)


        }
    }
    // 출처 : https://blog.naver.com/PostView.naver?blogId=sherry911&logNo=220598357658
    // 함수의 매개변수에 : 라디오 그룹을 넣었음. 참고 ) 1)라디오 그룹 2) 라디오 요소 구성.
    fun getValue(v: View?): String? {
        val male = binding.radio1
        val female = binding.radio2
        var pickValue: String? = null

        if (male.isChecked) {
            pickValue = male.text.toString()
        } else if (female.isChecked) {
            pickValue = female.text.toString()
        }
        return pickValue

    }
}























