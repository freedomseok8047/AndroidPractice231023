package com.example.myapp_test7_8_9_10_11_12.ch18_Test.model

// data 값의 배열의 요소를 모델링 :UserModel
// UserModel 를 요소로 갖는 리스트 만들기 -> 모델화
data class UserListModel(
    val page : String,
    val perPage : String,
    val total : String,
    val totalPages : String,
    // UserModel을 요소로하는 리스트
    // 이름 : data ,똑같이 하기
    // 공공데이터 모델링 할때, 주의 사항!!!
    val data : List<UserModel>
)
