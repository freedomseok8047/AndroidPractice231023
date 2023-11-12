package com.example.myapp_test7_8_9_10_11_12.Public.retrofit

import com.example.myapp_test7_8_9_10_11_12.Public.model.ItemListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

// 통신 라이브러리 : retrofit2 이용해서
// 인터페이스, 추상 메서드를 만들어서
// 레트로핏 한테 전달 : 인터페이스 통해서 전달하면,
// 여기에 정의 된 함수를 이용해서 통신을 함, CRUD 개념

// 통신 라이브러리 : retrofit2 이용해서
// 인터페이스, 추상 메서드를 만들어서
// 레트로핏 한테 전달 : 인터페이스 통해서 전달하면,
// 여기에 정의 된 함수를 이용해서 통신을 함, CRUD 개념


interface INetworkService4 {
    // https://newsapi.org/v2/everything?q=Apple&from=2023-10-10&sortBy=popularity&apiKey=API_KEY
    // base url : https://newsapi.org
    // base url 주소 기본값에 이어서 뒤에 추가 주소 설정 : v2/everything
    // 쿼리 설정으로 파라미터 값 설정 : ?q=Apple&from=2023-10-10&sortBy=popularity&apiKey=API_KEY

    //https://apis.data.go.kr/6260000/WalkingService/getWalkingKr?
    // serviceKey=ALRX9GpugtvHxcIO%2FiPg1vXIQKi0E6Kk1ns4imt8BLTgdvSlH%2FAKv%2BA1GcGUQgzuzqM3Uv1ZGgpG5erOTDcYRQ%3D%3D
    // &pageNo=1&numOfRows=100&resultType=json
    @GET("WalkingService/getWalkingKr")
    fun getList2(
        // 파라미터 값 설정
        // 1)q 2)from 3)sortBy 4)apiKey
        @Query("serviceKey") serviceKey: String?,
        @Query("pageNo") pageNo: Int?,
        @Query("numOfRows") numOfRows: Int?,
        @Query("resultType") resultType: String?,
    ) : Call<ItemListModel>

}




//interface INetworkService4 {
//    // https://newsapi.org/v2/everything?q=Apple&from=2023-10-10&sortBy=popularity&apiKey=87af28a1123a4fcc9c869c0b81bd243c
//    // baseurl :https://newsapi.org
//    // 주소 기본값에 이어서 뒤에 추가 주소 설정. -> v2/everything
//    // 쿼리 설정으로 파라미터값 설정.  ->?q=Apple&from=2023-10-10&sortBy=popularity&apiKey=87af28a1123a4fcc9c869c0b81bd243c
//
//    //http://apis.data.go.kr/6260000/WalkingService/getWalkingKr?serviceKey=인증키&numOfRows=10&pageNo=1
//    //예시
//    //https://apis.data.go.kr/6260000/WalkingService/getWalkingKr?serviceKey=ALRX9GpugtvHxcIO%2FiPg1vXIQKi0E6Kk1ns4imt8BLTgdvSlH%2FAKv%2BA1GcGUQgzuzqM3Uv1ZGgpG5erOTDcYRQ%3D%3D&pageNo=1&numOfRows=100&resultType=json
//    @GET("WalkingService/getWalkingKr")
//    fun getList2(
//        @Query("serviceKey") serviceKey: String?,
//        @Query("pageNo") pageNo: Int,
//        @Query("numOfRows") numOfRows: Int,
//        @Query("resultType") resultType : String
//    ): retrofit2.Call<ItemListModel2>
//
//}