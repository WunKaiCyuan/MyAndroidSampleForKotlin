package com.cyuan.mysampleforkotlin.sample.instagram.repository

import com.cyuan.mysampleforkotlin.sample.instagram.model.DynamicPost
import com.cyuan.mysampleforkotlin.sample.instagram.model.Post
import io.reactivex.rxjava3.core.Single
import java.lang.Thread.sleep
import java.util.*

class HomeRepository {

    fun loadDynamicPostList(): Single<List<DynamicPost>> {

        return Single.fromCallable {
            val data = listOf(
                DynamicPost(
                    "nana",
                    "",
                    true
                ),
                DynamicPost(
                    "diane",
                    "",
                    true
                ),
                DynamicPost(
                    "david",
                    "",
                    true
                ),
                DynamicPost(
                    "simpson",
                    "",
                    true
                ),
                DynamicPost(
                    "kobe",
                    "",
                    true
                ),
                DynamicPost(
                    "james",
                    "",
                    true
                ),
                DynamicPost(
                    "lee",
                    "",
                    false
                ),
                DynamicPost(
                    "thomas",
                    "",
                    false
                ),
                DynamicPost(
                    "tommy",
                    "",
                    false
                )
            )

            sleep(1 * 1000)
            data
        }
    }

    fun loadPostList(): Single<List<Post>> {

        return Single.fromCallable {
            val data = listOf(
                Post(
                    "cyuan",
                    "",
                    false,
                    listOf("", "", ""),
                    "Android 開發，1天1個Sample view",
                    listOf(),
                    listOf(),
                    false,
                    "diane",
                    "敢不敢1天做2個",
                    1,
                    false,
                    false,
                    Date(2020 - 1900, 7 - 1, 28, 9, 0, 0)
                ),
                Post(
                    "diane",
                    "",
                    false,
                    listOf("", "", ""),
                    "我要成為好的PM",
                    listOf("", "", ""),
                    listOf("cyuan", "david"),
                    true,
                    "cyuan",
                    "我要成為好的RD",
                    33,
                    true,
                    false,
                    Date(2020 - 1900, 7 - 1, 28, 9, 0, 0)
                ),
                Post(
                    "david",
                    "",
                    true,
                    listOf("", "", ""),
                    "尋找Asp.net core後端工程師",
                    listOf("", "", ""),
                    listOf("diane", "david"),
                    true,
                    "kobe",
                    "私",
                    100,
                    false,
                    true,
                    Date(2020 - 1900, 7 - 1, 25, 9, 0, 0)
                )
            )

            sleep(1 * 1000)
            data
        }
    }
}