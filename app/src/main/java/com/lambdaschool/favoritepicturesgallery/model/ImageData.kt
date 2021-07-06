package com.lambdaschool.favoritepicturesgallery.model

import android.net.Uri
import java.io.Serializable

class ImageData(fullPhotoUri: Uri) : Serializable {
    var name: String? = null
    val fileUriString: String

    val fileUri: Uri
        get() = Uri.parse(fileUriString)

    init {
        this.fileUriString = fullPhotoUri.toString()
        val path = fullPhotoUri.path!!.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        this.name = path[path.size - 1]
    }
}