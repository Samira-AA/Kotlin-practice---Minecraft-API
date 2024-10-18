package com.example.prueba_parcial.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_parcial.R
import com.example.prueba_parcial.adapters.ItemAdapter
import com.example.prueba_parcial.db.AppDatabase
import com.example.prueba_parcial.models.Item

class FavoriteActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {

    private lateinit var rvFavoriteItem:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.activity_favorite)

        setSupportActionBar(findViewById(R.id.toolbar2))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        rvFavoriteItem = findViewById(R.id.rvFavoriteItem)

    }

    override fun onResume() {
        super.onResume()

        loadFavoriteItems{ item->
            rvFavoriteItem.adapter=ItemAdapter(item,this)
            rvFavoriteItem.layoutManager= LinearLayoutManager(this@FavoriteActivity)

        }
    }

    private fun loadFavoriteItems(onComplete: (List<Item>)-> Unit){
        val dao=AppDatabase.getInstance(this).getDao()
        onComplete(dao.getAll())
    }

    override fun onItemClick(item: Item) {
        val dao = AppDatabase.getInstance(this).getDao()

        dao.delete(item)

        Toast.makeText(this, "Item " +item.name + " was deleted from favorites", Toast.LENGTH_SHORT).show()

        loadFavoriteItems { people ->
            rvFavoriteItem.adapter = ItemAdapter(people, this)
            rvFavoriteItem.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}