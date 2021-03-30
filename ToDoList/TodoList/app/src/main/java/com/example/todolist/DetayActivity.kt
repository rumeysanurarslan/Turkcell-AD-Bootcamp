package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import kotlinx.android.synthetic.main.activity_detay.*

class DetayActivity : AppCompatActivity() {
    private lateinit var  yapilacak: Yapilacaklar
    private lateinit var vt: VeritabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detay)

        toolbarYapilacakDetay.title = "Yapılacak Detay"
        toolbarYapilacakDetay.subtitle = "Yapılacaklar listenizden bir işi görüntüleyin.."
        setSupportActionBar(toolbarYapilacakDetay)

        vt = VeritabaniYardimcisi(this@DetayActivity)

        yapilacak = intent.getSerializableExtra("nesne") as Yapilacaklar

        editTextYapilacak.setText(yapilacak.yapilacak_is)

        buttonGuncelle.setOnClickListener {
            val yapilacak_is = editTextYapilacak.text.toString()
            guncelle(yapilacak.yapilacak_id,yapilacak_is)
        }
    }

    fun guncelle(yapilacak_id: Int, yapilacak_is: String){
        Yapilacaklardao().yapilacakGuncelle(vt, yapilacak_id, yapilacak_is)
        startActivity(Intent(this@DetayActivity, MainActivity::class.java))
    }
}