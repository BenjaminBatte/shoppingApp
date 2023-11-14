package com.benjamin.shoppingapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val intent = intent
        val product = intent.getSerializableExtra("product") as Product

        val title = findViewById<TextView>(R.id.title)
        val itemId = findViewById<TextView>(R.id.itemid)
        val details = findViewById<TextView>(R.id.details)
        val image = findViewById<ImageView>(R.id.image)

        title.text = product.productName
        details.text = product.productDescription
        itemId.text = "$" + product.cost.toString()

        val imageUrl = intent.getStringExtra("image_url")
        if (imageUrl != null) {
            LoadImageTask(image, imageUrl).execute()
        }
    }

    private inner class LoadImageTask(
        private val imageView: ImageView,
        private val imageUrl: String
    ) : AsyncTask<Void, Void, Bitmap?>() {

        override fun doInBackground(vararg params: Void?): Bitmap? {
            return try {
                val url = URL(imageUrl)
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                connection.doInput = true
                connection.connect()
                val input = connection.inputStream
                BitmapFactory.decodeStream(input)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
        }

        override fun onPostExecute(result: Bitmap?) {
            if (result != null) {
                imageView.setImageBitmap(result)
            }
        }
    }
}
