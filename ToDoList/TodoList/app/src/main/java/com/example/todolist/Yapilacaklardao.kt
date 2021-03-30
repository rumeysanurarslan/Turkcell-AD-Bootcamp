package com.example.todolist

import android.content.ContentValues

class Yapilacaklardao {

    fun tumTodoList(vt:VeritabaniYardimcisi) : ArrayList<Yapilacaklar> {
        val db  = vt.writableDatabase

        val YapilacaklarListe = ArrayList<Yapilacaklar>()
        val cursor = db.rawQuery("SELECT * FROM yapilacaklar", null)


        while (cursor.moveToNext()){

            val yapilacak = Yapilacaklar(cursor.getInt(cursor.getColumnIndex("yapilacak_id")),
                cursor.getString(cursor.getColumnIndex("yapilacak_is"))
            )
            YapilacaklarListe.add(yapilacak)

        }
        return YapilacaklarListe

    }


    fun yapilacakAra(vt:VeritabaniYardimcisi, aramaKelimesi: String) : ArrayList<Yapilacaklar> {
        val db  = vt.writableDatabase

        val YapilacaklarListe = ArrayList<Yapilacaklar>()
        val cursor = db.rawQuery("SELECT * FROM yapilacaklar WHERE yapilacak_is like '%$aramaKelimesi%'", null)


        while (cursor.moveToNext()){

            val yapilacak = Yapilacaklar(cursor.getInt(cursor.getColumnIndex("yapilacak_id")),
                cursor.getString(cursor.getColumnIndex("yapilacak_is"))
            )
            YapilacaklarListe.add(yapilacak)

        }
        return YapilacaklarListe

    }

    fun yapilacakEkle(vt: VeritabaniYardimcisi, yapilacak_is: String){
        val db  = vt.writableDatabase
        val values = ContentValues()
        values.put("yapilacak_is", yapilacak_is)

        db.insertOrThrow("yapilacaklar", null, values)
        db.close()

    }

    fun yapilacakSil(vt: VeritabaniYardimcisi, yapilacak_id: Int){
        val db  = vt.writableDatabase
        db.delete("yapilacaklar", "yapilacak_id=?", arrayOf(yapilacak_id.toString()))
        db.close()
    }


    fun yapilacakGuncelle(vt: VeritabaniYardimcisi, yapilacak_id: Int, yapilacak_is: String){
        val db  = vt.writableDatabase

        val values = ContentValues()
        values.put("yapilacak_is", yapilacak_is)

        db.update("yapilacaklar", values,"yapilacak_id=?", arrayOf(yapilacak_id.toString()))
        db.close()

    }
}