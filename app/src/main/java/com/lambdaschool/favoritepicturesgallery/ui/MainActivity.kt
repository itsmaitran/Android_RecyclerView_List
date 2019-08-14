package com.lambdaschool.favoritepicturesgallery.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.lambdaschool.favoritepicturesgallery.R
import com.lambdaschool.favoritepicturesgallery.adapter.ImageListAdapter
import com.lambdaschool.favoritepicturesgallery.model.ImageData
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal var imageList: ArrayList<ImageData> = ArrayList()
    private var layoutManager: LinearLayoutManager? = null
    private var listAdapter: ImageListAdapter? = null
    companion object {
        internal const val REQUEST_IMAGE_GET = 1
        internal const val EDIT_IMAGE_REQUEST = 2
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

        layout_list.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        layout_list.layoutManager = layoutManager
        listAdapter = ImageListAdapter(imageList, this)
        layout_list.adapter = listAdapter
    }

    //private fun refreshListView() {
    //    listAdapter!!.notifyDataSetChanged()
    //}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            val fullPhotoUri = data!!.data
            if (fullPhotoUri != null) {
                imageList.add(ImageData(fullPhotoUri))
                listAdapter!!.notifyItemInserted(imageList.size - 1)
            }
        } else if (requestCode == EDIT_IMAGE_REQUEST && resultCode == RESULT_OK) {
            val returnedData = data!!.getSerializableExtra("object") as ImageData
            for (i in imageList.indices) {
                if (imageList[i].fileUriString == returnedData.fileUriString) {
                    imageList[i] = returnedData
                }
            }
        }
        //refreshListView()
    }
}