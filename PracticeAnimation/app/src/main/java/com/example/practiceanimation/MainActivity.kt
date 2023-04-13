package com.example.practiceanimation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.room.Room
import com.example.practiceanimation.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = "testGlobal"
    private lateinit var binding: ActivityMainBinding
    lateinit var db: UserDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = Room.databaseBuilder(
            this, UserDB::class.java, "UserDB")
            .allowMainThreadQueries()
            .build()


        binding.insertBtn.setOnClickListener {

        }

    }

    fun joinUser() {
        val pkId = db.getDao().getEmail().size + 1
        db.getDao().insertUser(UserInfo(15, "sss@sss", "qpwijdcn"))
    }


    fun checkValidUser(): Boolean {
        return db.getDao().getEmail().isNotEmpty()
    }

    fun checkValidPw(email: String): String {
        return db.getDao().getPwByEmail(email)
    }



    override fun onClick(v: View?) {
        when (v) {
            btnLogin -> {
                if (etMail.text.isNotEmpty() && etPw.text.isNotEmpty()) {
                    if (checkValidUser()) {
                        if (checkValidPw(etMail.text.toString()) == etPw.text.toString()) {
                            Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                        } else
                            Toast.makeText(this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT)
                                .show()
                    } else {
                        Toast.makeText(this, "가입된 계정이 없습니다.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "아이디와 비밀번호는 필수 입력 사항입니다.", Toast.LENGTH_SHORT).show()
                }
            }

            btnRegister -> {
                if (etMail.text.isNotEmpty() && etPw.text.isNotEmpty()) {
                    joinUser()
                } else {
                    Toast.makeText(this, "아이디와 비밀번호는 필수 입력 사항입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}

