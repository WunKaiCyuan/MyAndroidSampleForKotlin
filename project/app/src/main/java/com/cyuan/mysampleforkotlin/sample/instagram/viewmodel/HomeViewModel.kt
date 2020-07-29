package com.cyuan.mysampleforkotlin.sample.instagram.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cyuan.mysampleforkotlin.sample.instagram.model.DynamicPost
import com.cyuan.mysampleforkotlin.sample.instagram.repository.HomeRepository
import com.cyuan.mysampleforkotlin.sample.instagram.model.Post
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import java.util.concurrent.TimeUnit

class HomeViewModel(val repository: HomeRepository) : ViewModel() {
    val dynamicPostList = MutableLiveData<List<DynamicPost>>()
    val postList = MutableLiveData<List<Post>>()
    val postUpdateIndex = MutableLiveData<Int>()

    init {
        dynamicPostList.value = listOf()
        postList.value = listOf()
    }

    fun loadDynamicPostList() {
        repository.loadDynamicPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                dynamicPostList.value = it
            }
    }

    fun loadPostList() {
        repository.loadPostList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy {
                postList.value = it
            }
    }

    fun onDynamicUserClick(index: Int) {}

    fun onNewDynamicClick() {}

    fun onPostUserClick(index: Int) {}

    fun onPostMoreClick(index: Int) {}

    fun onPostHeartClick(index: Int) {
        val value = postList.value!!.get(index).heart
        postList.value!!.get(index).heart = !value
        postUpdateIndex.value = index
    }

    fun onPostCommentClick(index: Int) {}

    fun onPostSendCommentClick(index: Int) {}

    fun onPostBookmarkClick(index: Int) {
        val value = postList.value!!.get(index).bookmark
        postList.value!!.get(index).bookmark = !value
        postUpdateIndex.value = index
    }

    fun onOtherLikeUserClick(index: Int) {}

    fun onPostContentMoreContentClick(index: Int) {}

    fun totalMessageContent(index: Int): String {
        return when {
            postList.value!!.get(index).totalMessage == 1 -> "查看1則留言"
            postList.value!!.get(index).totalMessage > 1 -> "查看全部" + postList.value!!.get(index).totalMessage + "則留言"
            else -> ""
        }
    }

    fun howLongContent(index: Int): String {
        val diff =
            System.currentTimeMillis() - postList.value!!.get(index).createPostTime.time
        postList.value!!.get(index).createPostTime
        return when {
            diff >= 24 * 60 * 60 * 1000 -> "${TimeUnit.MILLISECONDS.toDays(diff)}天前"
            diff >= 60 * 60 * 1000 -> "${TimeUnit.MILLISECONDS.toHours(diff)}小時前"
            diff >= 60 * 1000 -> "${TimeUnit.MILLISECONDS.toMinutes(diff)}分鐘前"
            else -> "幾秒之前"
        }
    }

    fun likeNameContent(index: Int): String {
        return postList.value!!.get(index).likeUserName.joinToString("、")
    }
}