package com.example.prueba_parcial.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prueba_parcial.R
import com.example.prueba_parcial.adapters.ItemAdapter
import com.example.prueba_parcial.communication.ApiResponse
import com.example.prueba_parcial.db.AppDatabase
import com.example.prueba_parcial.models.Item
import com.example.prueba_parcial.network.MinecraftApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ItemActivity : AppCompatActivity(), ItemAdapter.OnItemClickListener {
    private lateinit var btLoad:Button
    private lateinit var etItemsCount:EditText
    private lateinit var rvItem:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item)

        setSupportActionBar(findViewById(R.id.toolbar))

        btLoad=findViewById(R.id.btLoad)
        etItemsCount=findViewById(R.id.etItemsCount)
        rvItem=findViewById(R.id.rvItem)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        btLoad.setOnClickListener{
            val count=etItemsCount.text.toString().toInt()
            loadItems(count){ item->
                rvItem.adapter=ItemAdapter(item,this)
                rvItem.layoutManager=LinearLayoutManager(this@ItemActivity)

            }
        }
    }

    private fun loadItems(results: Int, onComplete: (List<Item>) -> Unit) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://minecraft-api.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val itemService: MinecraftApiService = retrofit.create(MinecraftApiService::class.java)
        val request = itemService.getItems(results)

        request.enqueue(object: Callback<List<Item>> {
            override fun onResponse(call: Call<List<Item>>, response: Response<List<Item>>) {
                if (response.isSuccessful) {
                    response.body()?.let { items ->
                        onComplete(items)
                    }
                }
            }

            override fun onFailure(call: Call<List<Item>>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }



    override fun onItemClick(item: Item) {
        val dao=AppDatabase.getInstance(this).getDao()
        dao.insertOne(item)

        Toast.makeText(this, "The item"+item.name+ "was added to favorites", Toast.LENGTH_SHORT).show()
        val itemsList = dao.getAll()
        if (itemsList.isNotEmpty()) {
            println("Data saved correctly: ${itemsList[0]}")
        } else {
            println("There is no data in the database")
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