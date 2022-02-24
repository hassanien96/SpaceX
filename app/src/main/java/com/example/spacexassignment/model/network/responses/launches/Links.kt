package com.example.spacexassignment.model.network.responses.launches

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import java.io.Serializable

data class Links(
    @SerializedName("article_link")
    val articleLink: String?=null,
    @SerializedName("flickr_images")
    val flickrImages: List<String>?=null,
    @SerializedName("mission_patch")
    val missionPatch: String?=null,
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String?=null,
    @SerializedName("presskit")
    val presskit: String?=null,
    @SerializedName("reddit_campaign")
    val redditCampaign: String?=null,
    @SerializedName("reddit_launch")
    val redditLaunch: String?=null,
    @SerializedName("reddit_media")
    val redditMedia: String?=null,
    @SerializedName("reddit_recovery")
    val redditRecovery: String?=null,
    @SerializedName("video_link")
    val videoLink: String?=null,
    @SerializedName("wikipedia")
    val wikipedia: String?=null,
    @SerializedName("youtube_id")
    val youtubeId: String?=null
):Serializable{
    fun isLinksAvailable():Boolean{
        return !(articleLink==null && wikipedia==null && videoLink==null)
    }
}