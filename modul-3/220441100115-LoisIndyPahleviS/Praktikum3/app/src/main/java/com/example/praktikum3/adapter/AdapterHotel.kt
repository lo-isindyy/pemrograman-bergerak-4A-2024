package com.example.praktikum3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikum3.R
import com.example.praktikum3.data.HotelData
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

// Kelas adapter untuk RecyclerView dengan tampilan LinearLayoutManager
class AdapterHotel(private val playerList: List<HotelData>) : RecyclerView.Adapter<AdapterHotel.PlayerViewHolder>() {

    // Deklarasi variabel untuk callback ketika item diklik
    private lateinit var onItemClickCallback: OnItemClickCallback

    // Fungsi untuk mengatur callback ketika item diklik
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    // Interface untuk callback ketika item diklik
    interface OnItemClickCallback {
        fun onItemClicked(data: HotelData)
    }

    // Kelas ViewHolder untuk menyimpan referensi view yang digunakan dalam RecyclerView
    class PlayerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName: MaterialTextView = itemView.findViewById(R.id.player_name)
        val playerImage: ShapeableImageView = itemView.findViewById(R.id.player_picture)
    }

    // Fungsi untuk membuat ViewHolder (Melakukan setting untuk XML yang akan kita gunakan untuk menampilkan data)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayerViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.layout_linier_horizontal, parent, false)
        return PlayerViewHolder(view)
    }

    // Fungsi untuk mengikat data dengan ViewHolder (memasukkan data yang kita miliki ke dalam XML ViewHolder)
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val data = playerList[position]

        holder.playerName.text = data.name
        holder.playerImage.setImageResource(data.image)

        // Mengatur aksi ketika item diklik
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(playerList[holder.adapterPosition]) }
    }

    // Fungsi untuk mendapatkan jumlah item
    override fun getItemCount(): Int = playerList.size

    // Fungsi untuk memendekkan teks jika melebihi panjang maksimum
    private fun String.shorten(maxLength: Int): String {
        return if (this.length <= maxLength) this else "${this.substring(0, maxLength)}..."
    }
}