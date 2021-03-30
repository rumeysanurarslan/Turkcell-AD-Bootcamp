package com.example.todolist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class YapilacaklarAdapter(var mContext: Context, var yapilacaklarListe: ArrayList<Yapilacaklar>, var vt:VeritabaniYardimcisi)
    : RecyclerView.Adapter<YapilacaklarAdapter.CardTasarimTutucu>()
{

    inner class CardTasarimTutucu(tasarim: View):RecyclerView.ViewHolder(tasarim)
    {
        var satir_card: CardView
        var satir_yazi: TextView
        var satir_resim: ImageView

        init {
            satir_card = tasarim.findViewById(R.id.satir_card)
            satir_yazi = tasarim.findViewById(R.id.satir_yazi)
            satir_resim = tasarim.findViewById(R.id.satir_resim)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim =  LayoutInflater.from(mContext).inflate(R.layout.satir_tasarim,parent, false)
        return CardTasarimTutucu(tasarim)


    }

    override fun getItemCount(): Int {
        return yapilacaklarListe.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        var yapilacak = yapilacaklarListe.get(position)
        holder.satir_yazi.text = "${yapilacak.yapilacak_is}"
        holder.satir_resim.setOnClickListener{
            Toast.makeText(mContext,"${yapilacak.yapilacak_is} Silindi.", Toast.LENGTH_SHORT).show()
            Yapilacaklardao().yapilacakSil(vt, yapilacak.yapilacak_id)

            yapilacaklarListe = Yapilacaklardao().tumTodoList(vt)
            notifyDataSetChanged()

        }

        holder.satir_card.setOnClickListener {

            val intent = Intent(mContext, DetayActivity::class.java)
            intent.putExtra("nesne", yapilacak)
            mContext.startActivity(intent)
        }
    }

}