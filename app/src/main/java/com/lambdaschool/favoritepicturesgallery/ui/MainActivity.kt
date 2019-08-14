package com.lambdaschool.favoritepicturesgallery.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lambdaschool.favoritepicturesgallery.R
import com.lambdaschool.favoritepicturesgallery.model.ImageData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal var imageList: ArrayList<ImageData> = ArrayList()
    companion object {
        internal const val REQUEST_IMAGE_GET = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this
        imageList = ArrayList()


        button_add.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            if (intent.resolveActivity(packageManager) != null) {
                startActivityForResult(intent, REQUEST_IMAGE_GET)
            }
        }
    }

    fun createTextView(imageDetails: String, index: Int): TextView {
        val view = TextView(this)
        view.text = imageDetails
        view.textSize = 20f
        view.tag = index
        view.setOnClickListener {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("object", imageDetails)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            val fullPhotoUri = data!!.data
            if (fullPhotoUri != null) {
                imageList.add(ImageData(fullPhotoUri))
                var index = imageList.size-1
                layout_list.addView(createTextView("${imageList[imageList.size-1].fileUri}-${imageList[imageList.size - 1].name}", index))
            }
        }
    }
}