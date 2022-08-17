package com.example.trent.view

import android.os.Bundle
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.trent.R
import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity(){
    private lateinit var imageSlider: ImageSlider
    private lateinit var titleBar: TextView
    private lateinit var backBTN: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        imageSlider = findViewById(R.id.imageSlider)
        titleBar = findViewById(R.id.titleBar)
        backBTN = findViewById(R.id.backBTN)

        val bundle : Bundle?= intent.extras
        val title = bundle?.getString("title")
        val images = bundle?.getStringArrayList("images")
        val price = bundle?.getInt("price")
        val description = bundle?.getString("description")
        val category = bundle?.getString("category")
        val stock = bundle?.getInt("stock")
        val brand = bundle?.getString("brand")
        val rating = bundle?.getDouble("rating")

//        nameText.text = title
        titleBar.text = title
        imageSlider.adapter = images?.let {
            SliderAdapter(
                this,
                PicassoImageLoaderFactory(),
                imageUrls = it,

            )
        }
        priceTXT.text = "Price: $price"
        descTXT.text = (Html.fromHtml("<font><b>" + "Description: " + "</b></font>"  + description));
        categoryTXT.text = (Html.fromHtml("<font><b>" + "Category: " + "</b></font>"  + category));
        stockTXT.text = (Html.fromHtml("<font><b>" + "Stock: " + "</b></font>"  + stock));
        brandTXT.text = (Html.fromHtml("<font><b>" + "Brand: " + "</b></font>"  + brand));
        ratingTXT.text = (Html.fromHtml("<font><b>" + "Rating: " + "</b></font>"  + rating));


        backBTN.setOnClickListener {
           finish()
        }
    }


}