package com.example.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.io.IOException


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var yapilacaklarListe: ArrayList<Yapilacaklar>
    private lateinit var adapter:YapilacaklarAdapter
    private lateinit var vt: VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        veritabaniKopyala()

        toolbarMainActivity.title = "Yapılacaklar"
        setSupportActionBar(toolbarMainActivity)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this@MainActivity)

        vt = VeritabaniYardimcisi(this@MainActivity)

        tumTodoListiAl()

        fab.setOnClickListener{
            startActivity(Intent(this@MainActivity, KayitActivity::class.java))}
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        val item = menu.findItem(R.id.action_ara)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)

    }

    override fun onQueryTextSubmit(query: String): Boolean {
        aramaYap(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        aramaYap(newText)
        return true
    }


    //veritanbanı kopyala işlemi
    fun veritabaniKopyala(){
        val copyHelper = DatabaseCopyHelper(this@MainActivity)
        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()

        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    fun tumTodoListiAl(){
        yapilacaklarListe = Yapilacaklardao().tumTodoList(vt)
        adapter = YapilacaklarAdapter(this@MainActivity, yapilacaklarListe,vt)
        rv.adapter = adapter

    }

    fun aramaYap(aramaKelimesi:String){
        yapilacaklarListe = Yapilacaklardao().yapilacakAra(vt, aramaKelimesi)
        adapter = YapilacaklarAdapter(this@MainActivity, yapilacaklarListe,vt)
        rv.adapter = adapter

    }

}