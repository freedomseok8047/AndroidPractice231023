package com.example.myapp_test7_8_9_10_11_12.ch11_12_Test.viewPagerAndRecycler

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_test7_8_9_10_11_12.R
import com.example.myapp_test7_8_9_10_11_12.ch11_12_Test.viewPagerAndRecycler.adapter.RecyclerViewTest
import com.example.myapp_test7_8_9_10_11_12.ch11_12_Test.viewPagerAndRecycler.adapter.ViewPageAdapterTest
import com.example.myapp_test7_8_9_10_11_12.databinding.ActivityTestPageRecyclerBinding

class TestPageRecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityTestPageRecyclerBinding
    var newDataNumber = 11
    // 액션 버튼 토글(스위치), 서랍 화면 토글
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestPageRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1)툴바 , 2)드로워 , 3)드로워 네비게이션
        // 4)플로팅 액션 버튼 , 5)앱바 , 6)탭 레이아웃

        // 1) 툴바 붙이 Test11_ToolBarActivity 참고
        setSupportActionBar(binding.toolbar)

        //1)-2 툴바 오버플로우 메뉴 붙이기
        // 재료 , res -> 메뉴 , toolbar_menu 참고 재사용

        // 3) 드로워 네비 뷰 축 설정 -> 뷰에서 작업
        // 재료 : 1) 네비 헤더 , 2) 본문: res -> 메뉴

        // 3) 이벤트 핸들러 추가하기.
        // 각 아이템 요소 클릭 이벤트 추가. 각 뷰마다 이벤트 헨들러가 다름
        binding.mainDrawerView.setNavigationItemSelectedListener {
            it ->
            if (it.title == "로그인"){
                Toast.makeText(this@TestPageRecyclerActivity, "로그인 화면 이동", Toast.LENGTH_SHORT).show()
            }else if (it.title == "로그아웃"){
                Toast.makeText(this@TestPageRecyclerActivity, "로그아웃 화면 이동", Toast.LENGTH_SHORT).show()
            }else if (it.title == "메인가기"){
                Toast.makeText(this@TestPageRecyclerActivity, "메인가기 화면 이동", Toast.LENGTH_SHORT).show()
            }
            true
        }

        //드로워 화면에 액션 버튼 클릭시 -> 드로워 화면 나오게
        toggle = ActionBarDrawerToggle(this@TestPageRecyclerActivity,binding.drawer,
            R.string.open ,R.string.close)

        // 화면에 붙이는 작업
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // 버튼 클릭시,
        toggle.syncState()

        // 4)floating action button
        binding.floatingActionButton.setOnClickListener{
            when(binding.floatingActionButton.isExtended){
                true -> binding.floatingActionButton.shrink()
                false -> binding.floatingActionButton.extend()
            }
            Toast.makeText(this@TestPageRecyclerActivity,"floatingActionButton 클릭됨", Toast.LENGTH_SHORT).show()
        }




        // 뷰 페이져2 프래그먼트 어댑터 이용해서 출력해보기
        // 현재 액티비티는 기본 도화지 , 캔버스, 기본화면
        // 여기에 뷰페이저와 리사이클러뷰 2개를 붙일 예정

        //뷰 페이저2
        // 준비물1) 프래그먼트 어댑터
        // 준비물2) 목록요소 프래그먼트 3개 필요
        // 준비물3) 더미 데이터 (=자기자신)
        binding.viewPager1.adapter = ViewPageAdapterTest(this)

        // 리사이클러 뷰 붙이기
        // 준비물1) 리사이클러뷰 어댑터
        // 준비물2) 목록요소 아이템 뷰 -> item_recycler2
        // 준비물3) 더미 데이터

        val datas = mutableListOf<String>()
        for (i in 1..10) {
            datas.add("더미 데이터 추가 번호 $i")
        }
        // 출력 방향, 리니어 나란히 수직으로
        val layoutManager = LinearLayoutManager(this)
        // 리사이클러 뷰 석성 옵션에 출력 옵션 붙이기
        binding.recyclerView.layoutManager = layoutManager
        // 리사이클러 뷰 속성 옵션에 데이터를 붙이기, 어댑터 연결
        val customAdapter = RecyclerViewTest(datas)
        binding.recyclerView.adapter = RecyclerViewTest(datas)

        binding.addBtn.setOnClickListener {
            datas.add("NEW DATA " + newDataNumber++)
            customAdapter.notifyItemInserted(datas.size)
        }

            binding.delBtn.setOnClickListener {
                datas.removeAt(datas.size - 1)
                customAdapter.notifyDataSetChanged() // 만능, 되도록 사용x
            }

        // OnCreate 끝나는 부분.
    }
    // 오버플로우 메뉴 이벤트 핸들러 추가하기.
    // 만약, 메뉴 교체 하면, 해당 아이디 다시 재정의하기.

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        // 버튼을 열때 이용되는 이벤트 핸들러 부분.
        if(toggle.onOptionsItemSelected(item)){
            return true
            // 오버 플로우 메뉴의 클릭시 이벤트를 ,
        } else if ( R.id.menu_toolbar1 == item.itemId) {
            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴1 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar2 == item.itemId) {
            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴2 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        else if ( R.id.menu_toolbar3 == item.itemId) {
            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴3 클릭됨", Toast.LENGTH_SHORT).show()
            true
        }
        return super.onOptionsItemSelected(item)
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
//
//        R.id.menu_toolbar1 -> {
//            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴1 클릭됨", Toast.LENGTH_SHORT).show()
//            true
//        }
//
//        R.id.menu_toolbar2 -> {
//            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴2 클릭됨", Toast.LENGTH_SHORT).show()
//            true
//        }
//
//        R.id.menu_toolbar3 -> {
//            Toast.makeText(this@TestPageRecyclerActivity,"툴바메뉴3 클릭됨", Toast.LENGTH_SHORT).show()
//            true
//        }
//
//        /* R.id.menu_main4 -> {
//             Toast.makeText(this@Test11_ToolBarActivity,"메뉴4 클릭됨", Toast.LENGTH_SHORT).show()
//             true
//         }*/
//        // 람다식에서 return 사용 못함.
//        else -> super.onOptionsItemSelected(item)
//    }

    // 검색 이벤트 핸들러 추가하는 부분
    // 액션바에 오버플로우 메뉴 붙이기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        // 검색 뷰에, 이벤트 추가하기.
        val menuItem = menu?.findItem(R.id.menu_toolbar_search)
        // menuItem 의 형을 SearchView 타입으로 변환, 형변환
        val searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                //검색어가 변경시 마다, 실행될 로직을 추가.
                Log.d("lsy","텍스트 변경시 마다 호출 : ${newText} ")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                // 검색어가 제출이 되었을 경우, 연결할 로직.
                // 사용자 디비, 검색을하고, 그 결과 뷰를 출력하는 형태.
                Toast.makeText(this@TestPageRecyclerActivity,"검색어가 전송됨 : ${query}", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
















