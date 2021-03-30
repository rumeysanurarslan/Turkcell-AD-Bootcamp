package com.example.hesapmakinesi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewStructure
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var toplamIslemler: String = ""
    val buttonSinifi = ButtonSinifi()
    var degerTutucu: String = ""
    var oncekiDeger: String = ""

    fun buttonNumberClick(view : View){
        var tiklananButon = view as Button

        if(buttonSinifi.operatorSecildiMi)
        {
            sonucGoster.setText("")
            degerTutucu = ""
        }

        var tiklananDeger : String =  buttonSinifi.tiklananSayiyiBul(tiklananButon)
        degerTutucu += tiklananDeger
        toplamIslemler += tiklananDeger
        sonucTotalGoster.setText(toplamIslemler)
        sonucGoster.setText(degerTutucu)
    }


    fun buttonOperatorClick(view: View){
        var operator = view as ImageButton
        oncekiDeger =  sonucGoster.text.toString()
        if(!buttonSinifi.operatorSecildiMi)
            toplamIslemler += buttonSinifi.tiklananOperatoruBul(operator)
        sonucTotalGoster.setText(toplamIslemler)
    }

    fun buttonEsittirClick(view : View){
        var sayi2 : String = sonucGoster.text.toString()

        var sonuc = buttonSinifi.sonucHesapla(oncekiDeger, sayi2)

        sonucGoster.setText(sonuc.toString())
    }

    fun buttonACClick(view: View){
        sonucGoster.setText("")
        sonucTotalGoster.setText("")
        toplamIslemler = ""
        degerTutucu = ""
        buttonSinifi.operatorSecildiMi = false
    }
}