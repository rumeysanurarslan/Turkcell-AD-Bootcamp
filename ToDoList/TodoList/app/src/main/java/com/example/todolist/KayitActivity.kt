package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_kayit.*

class KayitActivity : AppCompatActivity() {
    private lateinit var  vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kayit)

        toolbarYapilacakKayit.title = "Yapılacak Kayıt"
        toolbarYapilacakKayit.subtitle = "Yapılacaklar listenize yeni bir kayıt ekleyin.."
        setSupportActionBar(toolbarYapilacakKayit)

        vt = VeritabaniYardimcisi(this@KayitActivity)

        buttonEkle.setOnClickListener{
            val yapilacak_is = editTextYapilacak.text.toString()
            if(!TextUtils.isEmpty(yapilacak_is))
                kayit(yapilacak_is)
            else
                Toast.makeText(this@KayitActivity,"Yapilacak iş boş bırakılamaz.", Toast.LENGTH_SHORT).show()
        }

    }
    fun kayit(yapilacak_is:String){
        Yapilacaklardao().yapilacakEkle(vt,yapilacak_is)
        startActivity(Intent(this@KayitActivity, MainActivity::class.java))

    }
}