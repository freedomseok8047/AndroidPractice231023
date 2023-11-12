package com.example.myapp_test7_8_9_10_11_12.Public

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test7_8_9_10_11_12.Public.adapter.MyAdapterRetrofit4
import com.example.myapp_test7_8_9_10_11_12.Public.model.ItemListModel
import com.example.myapp_test7_8_9_10_11_12.Public.retrofit.MyApplication4
import com.example.myapp_test7_8_9_10_11_12.databinding.ActivityPublicDataPracticeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class publicDataPracticeActivity : AppCompatActivity() {
    lateinit var binding: ActivityPublicDataPracticeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPublicDataPracticeBinding.inflate(layoutInflater)
        setContentView(binding.root)
// 레스트 서버에서 (뉴스 API) 데이터 받아오고

        // 리사이클러 뷰 붙이기

        // 변경1
        val networkService = (applicationContext as MyApplication4).networkService

        // 2) 호출하는 함수 콜 만들기. 우리가 만든 인터페이스에 등록된
        // 추상 함수를 이용함. 인자값은 페이지 번호 정의를 문자열 타입으로 했음.

        // 변경2
//       @GET("WalkingService/getWalkingKr")
//        fun getList2(
//        // 파라미터 값 설정
//        // 1)q 2)from 3)sortBy 4)apiKey
//        @Query("serviceKey") serviceKey: String?,
//        @Query("pageNo") pageNo: Int?,
//        @Query("numOfRows") numOfRows: Int?,
//        @Query("resultType") resultType: String?,

        //https://apis.data.go.kr/6260000/WalkingService/getWalkingKr?
        // serviceKey=ALRX9GpugtvHxcIO%2FiPg1vXIQKi0E6Kk1ns4imt8BLTgdvSlH%2FAKv%2BA1GcGUQgzuzqM3Uv1ZGgpG5erOTDcYRQ%3D%3D
        // &pageNo=1
        // &numOfRows=100
        // &resultType=json

        val serviceKey = "ALRX9GpugtvHxcIO/iPg1vXIQKi0E6Kk1ns4imt8BLTgdvSlH/AKv+A1GcGUQgzuzqM3Uv1ZGgpG5erOTDcYRQ=="
        val resultType = "json"
        val userListCall = networkService.getList2(serviceKey,1,10,resultType)

        //변경3
        // 실제 통신이 시작이 되는 부분, 이 함수를 통해서 데이터를 받아옴.
        userListCall.enqueue(object : Callback<ItemListModel> {
            //익명 클래스가, Callback , 레트로핏2에서 제공하는 인터페이스를 구현했고,
            // 반드시 재정의해야하는 함수들이 있음.
            // 변경4
            override fun onResponse(call: Call<ItemListModel>, response: Response<ItemListModel>) {
                // 데이터를 성공적으로 받았을 때 수행되는 함수
                val userList = response.body()
                // 변경8
                Log.d("lsy","userList의 값 : ${userList?.getWalkingKr}")

                // 데이터를 성공적으로 받았다면, 여기서 리사이클러 뷰 어댑터에 연결하면 됨.
                // 리사이클러뷰 의 레이아웃 정하는 부분, 기본인 LinearLayoutManager 이용했고,

                //변경6
                val layoutManager = LinearLayoutManager(
                    this@publicDataPracticeActivity)
                // 리사이클러뷰에 어댑터 연결
                // 인자값은 : 현재 context : this@HttpTestReqResActivity
                // 2번째 인자값은 : 데이터 , 네트워크 ,레트로핏2 통신으로 받아온 데이터 리스트

                //변경7
                binding.retrofitRecyclerView3.layoutManager = layoutManager
                // 변경9, 주의사항 객체안에 배열이 있다. 가져오는 데이터의 구조 따라 달라짐
                binding.retrofitRecyclerView3.adapter =
                    MyAdapterRetrofit4(this@publicDataPracticeActivity,userList?.getWalkingKr?.item)

            }

            //변경5
            override fun onFailure(call: Call<ItemListModel>, t: Throwable) {

                // 데이터를 못 받았을 때 수행되는 함수
                call.cancel()
            }

        })
    }
}