package org.d3ifcool.a3food

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import org.d3ifcool.a3food.databinding.ActivityDetailBinding
import org.d3ifcool.a3food.network.PlaceApi

class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val bundle = intent.extras
        val toko = bundle!!.getString("toko")
        val alamat = bundle.getString("alamat")
        val rating = bundle.getString("rating")
        val img = bundle.getString("img")

        with(binding) {
            namaToko.text = toko
            alamatTextView.text = alamat
            ratingTextView.text = rating
            Glide.with(imageThumbnail.context)
                .load(img)
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageThumbnail)
        }
    }
}