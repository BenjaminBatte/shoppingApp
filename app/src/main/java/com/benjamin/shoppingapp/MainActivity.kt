package com.benjamin.shoppingapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_details.image
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.shopping_item.productCost
import kotlinx.android.synthetic.main.shopping_item.productDescription
import kotlinx.android.synthetic.main.shopping_item.productName
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val products = ArrayList<Product>()
        products.add(Product("iPad", "iPad Pro 11-inch", 400.0).apply { position = 0 })
        products.add(Product("MacBook M3 Pro", "12-core CPU\n18-core GPU", 2500.00).apply { position = 1 })
        products.add(Product("Dell Inspiron", "13th Gen Intel® Core™ i7", 1499.00).apply { position = 2 })
        products.add(Product("Logitech Keyboard", "Logitech - PRO X\nTKL LIGHTSPEED Wireless", 199.00).apply { position = 3})
        products.add(Product("MacBook M3 Max", "14-core CPU\n30-core GPU", 3499.00).apply { position = 4})

        val imageList = listOf(
            getDrawable(R.drawable.ipad),
            getDrawable(R.drawable.macbook),
            getDrawable(R.drawable.delloinspiron),
            getDrawable(R.drawable.logiteckeyboard),
            getDrawable(R.drawable.macbook)
        )

        val logList = listOf(
            getDrawable(R.drawable.applelog),
            getDrawable(R.drawable.applelog),
            getDrawable(R.drawable.delolog),
            getDrawable(R.drawable.logiteclog),
            getDrawable(R.drawable.applelog)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MyAdapter(products, imageList, logList)
        var addBtn = findViewById<Button>(R.id.addToCard)

        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

        addBtn.setOnClickListener{

        }

        adapter.onItemClick = { clickedProduct ->
            val intent = Intent(this, DetailsActivity::class.java)

            val bitmap = (imageList[clickedProduct.position] as BitmapDrawable).bitmap


            val file = File(applicationContext.cacheDir, "image.jpg")
            val fileOutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.close()

            intent.putExtra("product", clickedProduct)
            intent.putExtra("image1",file.toURI().toString())

            startActivity(intent)
        }
        }
    }
