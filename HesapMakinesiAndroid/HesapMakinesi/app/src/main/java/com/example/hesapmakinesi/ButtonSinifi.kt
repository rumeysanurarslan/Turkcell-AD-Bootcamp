package com.example.hesapmakinesi

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_main.*

open class ButtonSinifi() {

    var operatorSecildiMi: Boolean = false
    private var secilenOperator: String = ""

    fun tiklananSayiyiBul(tiklananButon : Button) : String {
       when (tiklananButon.id) {
           R.id.btn0 -> {
               return "0"
           }
           R.id.btn1 -> {
               return "1"
           }
           R.id.btn2 -> {
              return "2"
           }
           R.id.btn3 -> {
               return "3"
           }
           R.id.btn4 -> {
                return "4"
           }
           R.id.btn5 -> {
               return "5"
           }
           R.id.btn6 -> {
               return "6"
           }
           R.id.btn7 -> {
               return "7"
           }
           R.id.btn8 -> {
               return "8"
           }
           R.id.btn9 -> {
               return "9"
           }
           else -> return ""
       }

   }

    fun tiklananOperatoruBul(tiklananButon : ImageButton) : String{
        operatorSecildiMi = true
        when(tiklananButon.id){
            R.id.btnBolu ->{
                secilenOperator =  "/"
            }
            R.id.btnCarpi ->{
                secilenOperator =  "x"
            }
            R.id.btnEksi ->{
                secilenOperator = "-"
            }
            R.id.btnToplam ->{
                secilenOperator = "+"
            }
            else -> {
                operatorSecildiMi = false
                secilenOperator = ""
            }
        }
        return secilenOperator
    }

    fun sonucHesapla(oncekiDeger : String, secilenSayi : String) : Double {
        operatorSecildiMi = false
        when(secilenOperator){
            "/" -> {
                return oncekiDeger.toDouble() / secilenSayi.toDouble()
            }
            "x" -> {
                return oncekiDeger.toDouble() * secilenSayi.toDouble()
            }
            "-" -> {
                return oncekiDeger.toDouble() - secilenSayi.toDouble()
            }
            "+" -> { 
                return oncekiDeger.toDouble() + secilenSayi.toDouble()
            }
            else -> {
                return 0.0
            }
        }
    }

}