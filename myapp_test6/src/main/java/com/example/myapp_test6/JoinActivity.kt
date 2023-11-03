package com.example.myapp_test6

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.myapp_test6.databinding.ActivityJoinBinding
import com.example.myapp_test6.myapp_ljs231023.DatabaseHelper
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class JoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJoinBinding
    // 현재, 카메라에서만, 촬영후, 물리 이미지를 만들어서,
    // 각 앱의 내부 저장소에 저장하고, 저장된 위치를 담은 변수.
    //
    // 미구현
    // 갤러리에서 선택된 사진의 , Uri 주소를 가져와서 -> filePath 담기.
    lateinit var filePath: String

    var myDB: DatabaseHelper? = null

    var editTextName: EditText? = null
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var editTextPhone: EditText? = null
    var editTextAddress: EditText? = null

//    var editTextID: EditText? = null
    var buttonJoin: Button? = null
    var buttonView: Button? = null
//    var buttonUpdate: Button? = null
//    var buttonDelete: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //작업 구성 2가지.
        // 첫번째, 갤러리 앱을 호출 하는 작업
        // 두번째, 갤러리 앱에 데이터를 가져온 내용을 처리하는 작업.
        // 갤러리에서 선택 된 사진을 출력하는 뷰하나 생성.
        // 두번째, 갤러리 앱에 데이터를 가져온 내용을 처리하는 작업.
        val requestGalleryLauncher = registerForActivityResult(
            // 지금 정의하는 부분은 시스템에서 각 사용처 마다 정의가 다 되어 있고,
            // 골라서 사용할 예정. , 현재는 외부앱 에서 데이터 가져오는 역할부분을 이용할 예정.
            // StartActivityForResult -> 이부분 각각 다 정의가 되어 있다.
            ActivityResultContracts.StartActivityForResult()
        ) {
            // it 여기에, 갤러리에서 선택된 사진의 데이터가 들어 있음.
            // 위치가 들어 있다.

            //
            // 1) 불러온 사진을 적절히 크기를 조절해서, OOM(OutOf Memory) ,
            // 2) 바이트로 읽은 다음. ->
            // 3) 비트맵 타입으로 변환작업

            // try ~ catch 구문 사용할 예정. : IO(Input Output), 예외처리가 필요함.
            try {
                // 1) 불러온 사진을 적절히 크기를 조절해서, OOM(OutOf Memory) ,
                // 적당한 비율로 줄여주는 단위를 정하는 임의의 함수.
                // calculateInSampleSize 의 재료 , 인자로
                // 1) fileUri : Uri,
                // 2) reqWidth: Int,
                // 3) reqHeight: Int
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    // 가로 , 세로 : 150dp x 150dp
                    resources.getDimensionPixelSize(R.dimen.profile_img_width),
                    resources.getDimensionPixelSize(R.dimen.profile_img_height),
                )
                val options = BitmapFactory.Options()
                // 사진의 크기가, 원하는 크기에 거의 비슷하게 맞게 크기 조절이 됨.
                options.inSampleSize = calRatio

                // 사진을 읽은 바이트
                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                // 비트맵 타입으로 변환 -> 옵션에 크기 비율들어 있어서, ->
                // 비트맵은 크기가 거의 150 x 150 가까이 되어 있음.
                val bitmap = BitmapFactory.decodeStream(inputStream,null,options)
                inputStream!!.close()
                inputStream = null

                // 결과 사진을 원하는 뷰에 넣기.
                // 지금, 그냥 출력하지만, 비동기식으로 처리하는 glide 라는 라이브러리를 주로 이용할 예정.
                binding.resultUserImage.setImageBitmap(bitmap)

                Log.d("ljs","갤러리에서 선택된 사진의 크기 비율 calRatio : $calRatio")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("ljs", "사진 출력 실패")

            }

        }

        // 버튼클릭시 : 갤러리 앱 호출,
        binding.galleryBtn.setOnClickListener {
            //갤러리 앱 호출, 인텐트의 액션 문자열을 사용함. 시스템꺼를 사용해서 정해진 문자열.
            // Intent.ACTION_PICK : 갤러리 호출
            // MediaStore.Images.Media.EXTERNAL_CONTENT_URI : 외부 저장소(갤러리)의 사진의 위치
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            // data type 지정.
            intent.type = "image/*"
            // 후처리 함수 호출 .-> 데이터 가져오기 위한 로직.
            // requestGalleryLauncher: 아직 후처리 함수 정의 안했음.
            requestGalleryLauncher.launch(intent)
        }
        // 카메라 호출해서, 촬영된 사진 가져오기
        // 1) 카메라 호출하는 버튼, 액션 문자열로 카메라 외부 앱 연동
        // 2) 후처리하는 함수를 이용해서, 촬영된 사진을 결과 뷰에 출력하는 로직.

        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            // 원본 사진 크기 조절하는 비율 단위 (정수값)
            // 사진을 AVD 가상머신을 넣을 때 어느 전도 비율이 줄어들었음
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.profile_img_width),
                resources.getDimensionPixelSize(R.dimen.profile_img_height),
            )
            // 크기 옵션을 담을 인스턴스 생성.
            val options = BitmapFactory.Options()
            options.inSampleSize = calRatio
            // 촬영된 사진을 bitmap 타입으로 변환
            val bitmap = BitmapFactory.decodeFile(filePath,options)
            // 결과 뷰에, 비트맵 타입으로 변환된 사진을 출력하기
            binding.resultUserImage.setImageBitmap(bitmap)
        }

        binding.cameraBtn.setOnClickListener{
            // 사진이 촬영되고, 저장될때, 파일이름을 정하기
            // 중복 안되게 이름을 작성 UUID를 많이 씀
            // 일단, 날짜 기준으로 사진 파일명 구분짓기

            // 파일 이름 준비하기
            val timeStamp : String =
                SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

            // 촬영된 사진의 저장소 위치 정하기
            val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)


            // 위에서 만든 파일이름과 , 저장소 위치에 실제 물리 파일 생성하기
            // 빈 파일
            val file = File.createTempFile(
                "JPEG_${timeStamp}",
                ".jpg",
                storageDir
            )

            // 실제 사진 파일 저장소 위치 정의
            // 전역변수로 빼주기
            // 위에서 선언만 하고 ,실제 파일위치가 나올 이 때, 할당을 하는 구조.
            filePath = file.absolutePath
            Log.d("ljs", "file.absolutePath : $filePath")

            // 컨텐츠 프로바이더를 이용해서, 데이터를 가져와야 함
            val photoURI : Uri = FileProvider.getUriForFile(
                this,
                "com.example.myapp_test6.fileprovider",
                file
            )
            // 카메라 촬영하는 정해진 액션 문자열
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            // 인텐트 데이터를 담아서 전달
            // 키 : MediaStore.EXTRA_OUTPUT, 값 : photoURI
            intent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI)
            // 후처리 함수로 촬영된 사진을 처리하는 로직
            // 아직 정의 되지 않았음
            requestCameraFileLauncher
        }

        myDB = DatabaseHelper(this)

        // 자바 버전에 코드 -> 코틀린 변경.
        // findViewById ->  바인딩 기법으로 사용했음.
        editTextName = binding.editTextName
        editTextEmail = binding.editTextEmail
        editTextPassword = binding.editTextPassword
        editTextPhone = binding.editTextPhone
        editTextAddress = binding.editTextAddress
//        editTextID = binding.editTextID
        buttonJoin = binding.buttonJoin
        buttonView = binding.buttonView
//        buttonUpdate = binding.buttonUpdate
//        buttonDelete = findViewById(R.id.buttonDelete) -> 밑에 줄이랑 같은 기능
//        buttonDelete = binding.buttonDelete
        // 최초 1회 실행시, 직접 만든 함수를 호출하는 부분.
        // 현재, 화면만 테스트를 하니, 즉 다른 화면 이동 없어서,
        // 액티비티 생명주기에서 각 화면이 수행되는 구조를 이해하면
        // 좀 더 효육적인 자원 소모를 할 수 있음
        AddData()
//        viewAll()
//        UpdateData()
//        DeleteData()

        // 회원가입 버튼 클릭시, 회원 가입 하는 함수 호출
        binding.buttonJoin.setOnClickListener {
            AddData()
        }

        // 조회하는 뷰에 페이지 이동.
        binding.buttonView.setOnClickListener {
            val intent = Intent(this@JoinActivity, UserTableActivity::class.java)
            startActivity(intent)
        }

    }


        //데이터베이스 추가하기
        fun AddData() {
            buttonJoin!!.setOnClickListener {
                val isInserted = myDB!!.insertData(
                    editTextName!!.text.toString(),
                    editTextEmail!!.text.toString(),
                    editTextPassword!!.text.toString(),
                    editTextPhone!!.text.toString(),
                    editTextAddress!!.text.toString(),
                    filePath
                )
                if (isInserted == true)
                    Toast.makeText(this@JoinActivity, "데이터추가 성공", Toast.LENGTH_LONG).show()
                else Toast.makeText(this@JoinActivity, "데이터추가 실패", Toast.LENGTH_LONG).show()
            }
        }
    // 데이터베이스 읽어오기
    // 출력 됨. 기존 방법1
//    fun viewAll() {
//        // SAM (Single Abstract Method) 함수형 인터페이스,
//        buttonView!!.setOnClickListener(View.OnClickListener {
//            // res에 조회된 , 테이블의 내용이 들어가 있다. select 의 조회의 결괏값 있다.
//            val res = myDB!!.allData
//            // 결과가 없을 때
//            if (res.count == 0) {
//                ShowMessage("실패", "데이터를 찾을 수 없습니다.")
//                return@OnClickListener
//            }
//            //결과가 있다면.
//            // 자바에서, String 단점, 새로운 문자열이 있다면, 매번 새로 주소를 생성.
//            // StringBuffer 하나의 객체에 해당 문자열을 추가만 하는 형태라서, 주소를 새로 생성안함.
//
//            val buffer = StringBuffer()
//            // res 형 ->Cursor , 쉽게 엑셀 마치 테이블 , 0행부터 시작한다.
//            // res.moveToNext() -> 1행을 의미.
//            while (res.moveToNext()) {
//                buffer.append(
//                    //코틀린 3중 따옴표, 멀티 라인.
//                    // 1행의 첫번째 컬럼을 가져오기.
//                    """
//    ID: ${res.getString(0)}
//
//    """.trimIndent()
//                )
//                buffer.append(
//                    """
//    이름: ${res.getString(1)}
//
//    """.trimIndent()
//                )
//                buffer.append(
//                    """
//    이메일 : ${res.getString(2)}
//
//    """.trimIndent()
//                )
//                buffer.append(
//                    """
//    패스워드 : ${res.getString(3)}
//
//    """.trimIndent()
//                )
//                buffer.append(
//                    """
//    전화번호: ${res.getString(4)}
//
//    """.trimIndent()
//                )
//                buffer.append(
//                    """
//    주소: ${res.getString(5)}
//
//
//    """.trimIndent()
//                )
//            }
//            ShowMessage("데이터", buffer.toString())
//        })
//    }


    //데이터베이스 수정하기
//    fun UpdateData() {
//        buttonUpdate!!.setOnClickListener {
//            val isUpdated = myDB!!.updateData(
//                editTextID!!.text.toString(),
//                editTextName!!.text.toString(),
//                editTextPhone!!.text.toString(),
//                editTextAddress!!.text.toString()
//            )
//            if (isUpdated == true)
//                Toast.makeText(this@DbTestActivity, "데이터 수정 성공", Toast.LENGTH_LONG)
//                    .show()
//            else Toast.makeText(this@DbTestActivity, "데이터 수정 실패", Toast.LENGTH_LONG)
//                .show()
//        }
//    }

//    // 데이터베이스 삭제하기
//    fun DeleteData() {
//        buttonDelete!!.setOnClickListener {
//            val deleteRows = myDB!!.deleteData(editTextID!!.text.toString())
//            if (deleteRows > 0)
//                Toast.makeText(this@DbTestActivity, "데이터 삭제 성공", Toast.LENGTH_LONG)
//                    .show()
//            else Toast.makeText(this@DbTestActivity, "데이터 삭제 실패", Toast.LENGTH_LONG)
//                .show()
//        }
//    }
    // 사용자 정의 다이얼 로그창을 자주 이용할 때 사용하는 기본 샘플 코드
    fun ShowMessage(title: String?, Message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }




    // onCreate

    //크기를 조절해주는 임의의 함수 만들기.
    // 자주 쓰는 기능은 따로 클래스를 만들어서 재사용하면됨. 일단,
    // 원본의 가로 세로 크기를, 적당히 반으로 접어서, 원하는 가로 세로 크기 만큼의 비율로 계산을 해줌.
    // 비율 1, 원본, 크기 4 -> 1/4
    private fun calculateInSampleSize (fileUri : Uri, reqWidth: Int, reqHeight: Int) :Int {
        // 매개변수 1: 사진의 위치 , 2: 요구하는 가로크기 , 3: 요구하는 세로크기
        // 함수의 반환값 : 적절한 비율의 단위
        // 비율은 크기가 클수록, 사이즈를 더 작게 만들어줌. 마치, 썸네일 이미지와 비슷함.
        // 이 함수의 내용은 크게 몰라도 됨.
        // 적당히, 원본사진의 비율을 줄여 나가면서, 적당한 크기의 상수를 구하는 로직.
        // 로직을 이해하면, 이 함수를 통째로 사용할 것임.
        //
        // 안드로이드에서 이미지의 타입을 비트맵으로 정하는데, 거기에 옵션을 넣는 인스턴스.
        val options = BitmapFactory.Options()
        // 사진의 영향을 안주고, 옵션만 제공을 하겠다.
        options.inJustDecodeBounds = true

        // 계산을 하기 위해서, 원본 사진을 읽는 과정이 있어서, 여기에서도 , IO 발생
        // 그래서, try ~ catch 구문이 필요함,.
        try {
            // fileUri : 사진이 들어있는 위치, 파일을 바이트로 읽었음.
            // inputStream : 사진의 읽은 값이 바이트 단위로 들어 있음.
            var inputStream = contentResolver.openInputStream(fileUri)

            // 변환 작업.
            BitmapFactory.decodeStream(inputStream,null,options)
            inputStream!!.close()
            inputStream = null
        } catch (e:Exception) {
            e.printStackTrace()
            Log.d("ljs", "사진 크기 비율 계산 실패 ")
        }
        // 비율 계산
        val (height : Int, width: Int) = options.run { outHeight to outWidth }
        // 비율 1, 원본 사이즈 크기
        var inSampleSize = 1
        // 원본 사진의 크기 height x width 예를 3000 x 2000 px
        // reqHeight : 150 , reqWidth : 150
        if (height > reqHeight || width > reqWidth) {

            // 반으로 접기
            val halfHeight : Int = height / 2
            val halfWidth : Int = width / 2

            while ( halfHeight / inSampleSize >= reqHeight &&
                halfWidth / inSampleSize >= reqWidth ) {
                // 비율 2배씩 증가, 값이 증가하면, 사이즈의 크기는 줄어듬.
                inSampleSize *= 2
            }
        }

        return  inSampleSize

    }
}