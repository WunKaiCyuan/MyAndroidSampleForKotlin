package com.cyuan.mysampleforkotlin.sample.video

import android.app.Application
import android.net.Uri
import android.util.Log
import android.view.SurfaceHolder
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class VideoViewModel(application: Application, private val repository: VideoRepository) :
    AndroidViewModel(application) {

    val TAG = "VideoViewModel"

    var mediaIndex = MutableLiveData<Int>()
    val mediaList = MutableLiveData<ArrayList<VideoInfo>>()

    val mediaPlayerStatus = MutableLiveData<MediaPlayerStatus>()
    val mediaName = MutableLiveData<String>()
    val mediaInformation = MutableLiveData<String>()
    val mediaTotalLength = MutableLiveData<Int>()
    val mediaCurrentPosition = MutableLiveData<Int>()
    val mediaUri = MutableLiveData<Uri>()

    val displayViewStatus = MutableLiveData<DisplayViewStatus>()

    val isTouchProgressBar = MutableLiveData<Boolean>()

    init {
        mediaIndex.value = 0
        mediaList.value = ArrayList()

        mediaPlayerStatus.value = MediaPlayerStatus.Init
        mediaName.value = "尚未選擇播放影片"
        mediaInformation.value = "尚未選擇播放影片"
        mediaTotalLength.value = 0
        mediaCurrentPosition.value = 0
        mediaUri.value = Uri.EMPTY

        isTouchProgressBar.value = false
    }

    private fun selectedMedia() {
        // 設定媒體位置，修改媒體撥放器狀態
        mediaUri.value = mediaList.value!!.get(mediaIndex.value!!).videoUri
        mediaPlayerStatus.value = MediaPlayerStatus.FoundMedia

        // 載入預設值
        mediaName.value = "請稍後媒體載入中..."
        mediaInformation.value = "請稍後媒體載入中..."
        mediaCurrentPosition.value = 0
        mediaTotalLength.value = 0
    }

    fun loadVideoList() {
        val data = repository.loadVideoList()
        mediaList.value!!.addAll(data)
        if (!mediaList.value.isNullOrEmpty()) {
            mediaIndex.value = 0
            selectedMedia()
        } else {
            mediaPlayerStatus.value = MediaPlayerStatus.NotFoundMedia
        }
    }

    fun setTouchProgressBar(isTouch: Boolean) {
        isTouchProgressBar.value = isTouch

        if (mediaPlayerStatus.value == MediaPlayerStatus.Start && isTouch) {
            mediaPlayerStatus.value = MediaPlayerStatus.Pause
        } else if (mediaPlayerStatus.value == MediaPlayerStatus.Pause && !isTouch) {
            mediaPlayerStatus.value = MediaPlayerStatus.Start
        }
    }

    fun onMediaProgressChange(progress: Int) {
        mediaCurrentPosition.value = progress
    }

    fun onDisplayCreated() {
        displayViewStatus.value = DisplayViewStatus.Created
        if (mediaPlayerStatus.value == MediaPlayerStatus.Stop) {
            mediaPlayerStatus.value = MediaPlayerStatus.Prepare
        }
    }

    fun onDisplayDestroyed() {
        displayViewStatus.value = DisplayViewStatus.Destroyed
        mediaPlayerStatus.value = MediaPlayerStatus.Stop
    }

    fun onMediaPlayerButton() {
        when (mediaPlayerStatus.value) {
            MediaPlayerStatus.Ready, MediaPlayerStatus.Pause, MediaPlayerStatus.Complete -> mediaPlayerStatus.value =
                MediaPlayerStatus.Start
            MediaPlayerStatus.Start -> mediaPlayerStatus.value = MediaPlayerStatus.Pause
            else -> {

            }
        }
    }

    fun onPreviousButton() {
        if (mediaIndex.value!! > 0 && mediaList.value!!.size > mediaIndex.value!! - 1) {
            mediaIndex.value = mediaIndex.value!! - 1
            selectedMedia()
        }
    }

    fun onNextButton() {
        if (mediaList.value!!.size > mediaIndex.value!! + 1) {
            mediaIndex.value = mediaIndex.value!! + 1
            selectedMedia()
        }
    }

    fun onMediaPlayComplete() {
        mediaPlayerStatus.value = MediaPlayerStatus.Complete
    }

    fun callMediaPlayerReady() {
        // 設定媒體資訊
        mediaName.value = mediaList.value!!.get(mediaIndex.value!!).videoName
        mediaInformation.value = mediaList.value!!.get(mediaIndex.value!!).videoInfomation
        //mediaTotalLength.value = mediaPlayer.duration

        mediaPlayerStatus.value = MediaPlayerStatus.Ready
    }

    fun onMediaPlayError(p1: Int, p2: Int) {
        mediaPlayerStatus.value = MediaPlayerStatus.Error
    }

    fun updateMediaPlayerInfo(currentPosition: Int) {
        if (checkReadyMedia()) {
            mediaCurrentPosition.value = currentPosition
        }
    }

    fun callMediaPlayerPrepare() {
        mediaPlayerStatus.value = MediaPlayerStatus.Prepare
    }

    fun callMediaPlayerStart() {
        if (checkStartMedia())
            mediaPlayerStatus.value = MediaPlayerStatus.Start
    }

    private fun checkStartMedia(): Boolean {
        return when (mediaPlayerStatus.value) {
            MediaPlayerStatus.Ready, MediaPlayerStatus.Pause, MediaPlayerStatus.Complete -> true
            else -> false
        }
    }

    private fun checkReadyMedia(): Boolean {
        return when (mediaPlayerStatus.value) {
            MediaPlayerStatus.Ready, MediaPlayerStatus.Start, MediaPlayerStatus.Pause, MediaPlayerStatus.Complete -> true
            else -> false
        }
    }

    fun setMediaInfo(totalLength: Int) {
        mediaTotalLength.value = totalLength
    }
}