package com.example.prueba_parcial.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.prueba_parcial.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btItems=findViewById<Button>(R.id.btItems)
        val btFavorites=findViewById<Button>(R.id.btFavorites)

        btItems.setOnClickListener{
            val intent=Intent(this, ItemActivity::class.java)
            startActivity(intent)
        }

        btFavorites.setOnClickListener{
            val intent=Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }
}