package com.lambdaschool.favoritepicturesgallery.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lambdaschool.favoritepicturesgallery.R
import com.lambdaschool.favoritepicturesgallery.model.ImageData
import com.lambdaschool.favoritepicturesgallery.ui.DetailsActivity

class ImageListAdapter
    (private val imageList: ArrayList<ImageData>, private val activity: Activity) : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
        private var context: Context? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.image_thumbnail)
        var name: TextView = view.findViewById(R.id.text_image_name)
        var mainLayout: LinearLayout = view.findViewById(R.id.main_layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListAdapter.ViewHolder {
        context = parent.context
        val imageView = LayoutInflater.from(parent.context).inflate(R.layout.image_item_layout, parent, false)
        return ViewHolder(imageView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    companion object {
        internal val EDIT_IMAGE_REQUEST = 2
    }

    override fun onBindViewHolder(holder: ImageListAdapter.ViewHolder, position: Int) {
        val data = imageList[position]
        holder.image.setImageURI(data.fileUri)
        holder.name.text = data.name
        holder.mainLayout.tag = position
        holder.mainLayout.setOnClickListener { view ->
            val intent = Intent(view.context, DetailsActivity::class.java)
            intent.putExtra("object", imageList[Integer.parseInt(view.tag.toString())])
            (view.context as Activity).startActivityForResult(intent, EDIT_IMAGE_REQUEST)
        }
    }
}