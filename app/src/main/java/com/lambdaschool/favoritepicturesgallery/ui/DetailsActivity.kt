package com.lambdaschool.favoritepicturesgallery.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lambdaschool.favoritepicturesgallery.R
import com.lambdaschool.favoritepicturesgallery.model.ImageData
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var data: ImageData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun onStart() {
        super.onStart()
        image.setImageURI(data?.fileUri)
        text_image_name.text = data?.name
        text_uri.text = data?.fileUri.toString()
    }
}