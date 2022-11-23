package com.eslirodrigues.tutorials.retrofit_api.data.model.dto


import com.squareup.moshi.Json
import androidx.annotation.Keep
import com.eslirodrigues.tutorials.retrofit_api.data.model.RetrofitApiItem

@Keep
data class RetrofitApiItemDTO(
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "results")
    val results: List<Result> = listOf()
) {
    @Keep
    data class Result(
        @Json(name = "author_avatar_url")
        val authorAvatarUrl: String = "",
        @Json(name = "author_is_verified")
        val authorIsVerified: Int = 0,
        @Json(name = "author_name")
        val authorName: String = "",
        @Json(name = "author_rating")
        val authorRating: Int = 0,
        @Json(name = "author_user_id")
        val authorUserId: Int = 0,
        @Json(name = "author_username")
        val authorUsername: String = "",
        @Json(name = "created_at")
        val createdAt: Any? = null,
        @Json(name = "is_flagged")
        val isFlagged: Boolean = false,
        @Json(name = "recipe_id")
        val recipeId: Int = 0,
        @Json(name = "status_id")
        val statusId: Int = 0,
        @Json(name = "tip_body")
        val tipBody: String = "",
        @Json(name = "tip_id")
        val tipId: Int = 0,
        @Json(name = "tip_photo")
        val tipPhoto: TipPhoto? = null,
        @Json(name = "updated_at")
        val updatedAt: Int = 0,
        @Json(name = "upvotes_total")
        val upvotesTotal: Int = 0
    ) {
        @Keep
        data class TipPhoto(
            @Json(name = "height")
            val height: Int = 0,
            @Json(name = "url")
            val url: String = "",
            @Json(name = "width")
            val width: Int = 0
        )
    }

    fun toRetrofitApiItem() = RetrofitApiItem(
        authorName = this.results.first().authorName
    )
}