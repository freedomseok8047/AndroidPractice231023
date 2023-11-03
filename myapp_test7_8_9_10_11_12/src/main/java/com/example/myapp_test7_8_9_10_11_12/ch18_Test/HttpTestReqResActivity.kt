package com.example.myapp_test7_8_9_10_11_12.ch18_Test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test7_8_9_10_11_12.ch18_Test.adapter.MyAdapterRetrofit
import com.example.myapp_test7_8_9_10_11_12.ch18_Test.model.UserListModel
import com.example.myapp_test7_8_9_10_11_12.ch18_Test.retrofit.MyApplication
import com.example.myapp_test7_8_9_10_11_12.databinding.ActivityHttpTestReqResBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HttpTestReqResActivity : AppCompatActivity() {
    lateinit var binding: ActivityHttpTestReqResBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHttpTestReqResBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // request.in : 외국에서 무로 REST 테스트 서버 제공해주는 곳
        // 데이터 받아서, 리사이클러 뷰에 출력해보기
        // 리사이클러 뷰 준비물 1)어댑터 2)뷰홀더 3)목록 요소 뷰 4)데이터

        // 작업순서1) 모델 준비하기


        // 작업순서2) 네트워크 인터페이스 정의하기 -> INetworkService

        // 작업순서3) MyApplication, baseUrl 등록, 우리가 만든 인터페이스 연결
        // 매니페스트에 등록해서, 앱이 실행ㅅ, 선언 및 초기화 자동으로 되고,
        // 바로 사용 가능

        // 작업순서4) 일단, retrofit 통신 이용해서 데이터 전달 유부 확인
        // 데이터 가져오기
        // 1)
        // applicationContext 안에 우리가 등록한 설정이 있고,
        // as MyApplication -> 형 변환
        // 형 변환된 인스턴스 내부에 networkService를 사용하기
        val networkService = (applicationContext as MyApplication).networkService

        //2) 호출하는 함수 콜 만들기
        // 추상 함수를 이용함. 인자값은 페이지 번호 정의를 문자열 타입으로 했음
        val userListCall = networkService.doGetUserList("2")

        // 실제 통신이 시작이 되는 부분, 이 함수를 통해서 데이터를 받아옴.
        userListCall.enqueue(object : Callback<UserListModel> {
            //익명 클래스가, Callback , 레트로핏2에서 제공하는 인터페이스를 구현했고,
            // 반드시 재정의해야하는 함수들이 있음.
            override fun onResponse(call: Call<UserListModel>, response: Response<UserListModel>) {
                // 데이터를 성공적으로 받았을 때 수행되는 함수
                val userList = response.body()
                Log.d("lsy","userList의 값 : ${userList?.data}")

                // 데이터를 성공적으로 받았다면, 여기서 리사이클러 뷰 어댑터에 연결하면 됨.
                // 리사이클러 뷰의 레아아웃 정하는 부분, 기본인 LinearLayoutManager 이용했고,
                val layoutManager = LinearLayoutManager(this@HttpTestReqResActivity)

                // 리사이클러 뷰에 어댑터 연결
                // 인자값은 : 현재 context : this@HttpTestReqResActivity
                // 2번째 인자값은 :데이터 ,네트워크, 레트로핏2 통신으로 받아온 데이터 리스트
                binding.retrofitRecyclerView.layoutManager = layoutManager
                binding.retrofitRecyclerView.adapter =
                    MyAdapterRetrofit(this@HttpTestReqResActivity,userList?.data)

            }

            override fun onFailure(call: Call<UserListModel>, t: Throwable) {

                // 데이터를 못 받았을 때 수행되는 함수
                call.cancel()
            }

        })


        // 작업순서5) 리사이클러 뷰에 넣는 작업, 재사용








    }
    // onCreate






    // http 통신
    // 1) Myapplication 설정 파일 2) 인터페이스 3) 모델 또는 모델이 담겨진 리스트 필요
}


































