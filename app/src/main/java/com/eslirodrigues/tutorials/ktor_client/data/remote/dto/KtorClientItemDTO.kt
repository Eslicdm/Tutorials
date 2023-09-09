package com.eslirodrigues.tutorials.ktor_client.data.remote.dto


import androidx.annotation.Keep
import com.eslirodrigues.tutorials.ktor_client.data.local.model.KtorClientItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class KtorClientItemDTO(
    @SerialName("count")
    val count: Int = 0,
    @SerialName("results")
    val results: List<Result> = listOf()
) {
    @Keep
    @Serializable
    data class Result(
        @SerialName("author_avatar_url")
        val authorAvatarUrl: String = "",
        @SerialName("author_is_verified")
        val authorIsVerified: Int = 0,
        @SerialName("author_name")
        val authorName: String = "",
        @SerialName("author_rating")
        val authorRating: Int = 0,
        @SerialName("author_user_id")
        val authorUserId: Int = 0,
        @SerialName("author_username")
        val authorUsername: String = "",
        @SerialName("created_at")
        val createdAt: String? = null,
        @SerialName("is_flagged")
        val isFlagged: Boolean = false,
        @SerialName("recipe_id")
        val recipeId: Int = 0,
        @SerialName("status_id")
        val statusId: Int = 0,
        @SerialName("tip_body")
        val tipBody: String = "",
        @SerialName("tip_id")
        val tipId: Int = 0,
        @SerialName("tip_photo")
        val tipPhoto: TipPhoto? = null,
        @SerialName("updated_at")
        val updatedAt: Int = 0,
        @SerialName("upvotes_total")
        val upvotesTotal: Int = 0
    ) {
        @Keep
        @Serializable
        data class TipPhoto(
            @SerialName("height")
            val height: Int = 0,
            @SerialName("url")
            val url: String = "",
            @SerialName("width")
            val width: Int = 0
        )
    }

    fun toKtorClientItem() = KtorClientItem(
        authorName = this.results.first().authorName
    )
}