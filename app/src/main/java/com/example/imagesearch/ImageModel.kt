package com.example.imagesearch
import com.google.gson.annotations.SerializedName

data class ImageModel(
    @SerializedName("meta")
    val imgmeta: imgMeta,


    @SerializedName("documents")
    val imgdocument: ArrayList<imgDocument>
) {

    data class imgMeta(
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("pageable_count")
        val pageableCount: Int,
        @SerializedName("is_end")
        val isEnd: Boolean
    )

    data class imgDocument(
        val collection: String,
        @SerializedName("thumbnail_url")
        val thumbnailUrl: String,
        @SerializedName("image_url")
        val imageUrl: String,
        val width: Int,
        val height: Int,
        @SerializedName("display_sitename")
        val displaySiteName: String,
        @SerializedName("doc_url")
        val docUrl: String,
        val datetime: String
    )
}