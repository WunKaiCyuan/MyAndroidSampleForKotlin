package com.cyuan.mysampleforkotlin.sample.instagram.model

import java.util.*

data class Post(
    var postUserName: String,
    var postUserPhoto: String,
    var isNewDynamic: Boolean,
    var contentList: List<String>,
    var contentText: String,
    var likeUserPhoto: List<String>,
    var likeUserName: List<String>,
    var isOtherLikeUser: Boolean,
    var messageUserName: String,
    var messageContent: String,
    var totalMessage: Int,
    var heart: Boolean,
    var bookmark: Boolean,
    var createPostTime: Date
)