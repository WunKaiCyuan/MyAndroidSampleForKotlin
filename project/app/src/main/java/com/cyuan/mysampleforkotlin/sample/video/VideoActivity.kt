package com.cyuan.mysampleforkotlin.sample.video

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.SurfaceHolder
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cyuan.mysampleforkotlin.MyTimeUnit
import com.cyuan.mysampleforkotlin.R
import com.cyuan.mysampleforkotlin.databinding.ActivityVideoBinding
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity(), SurfaceHolder.Callback, SeekBar.OnSeekBarChangeListener,
    MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener,
    View.OnClickListener, MediaPlayer.OnVideoSizeChangedListener {

    val UPDATE_MEDIA_PLAYER_INFO = 1

    lateinit var viewModel: VideoViewModel
    lateinit var handler: Handler
    val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = VideoRepository()
        val factory = VideoViewModelFactory(application, repository)
        viewModel = ViewModelProvider(this, factory).get(VideoViewModel::class.java)

        val binding =
            DataBindingUtil.setContentView<ActivityVideoBinding>(this, R.layout.activity_video)
        binding.vm = viewModel
        binding.timeUnit = MyTimeUnit()
        binding.lifecycleOwner = this

        svVideo.holder.addCallback(this)
        sbCurrentPosition.setOnSeekBarChangeListener(this)
        mediaPlayer.setOnPreparedListener(this)
        mediaPlayer.setOnErrorListener(this)
        mediaPlayer.setOnCompletionListener(this)
        mediaPlayer.setOnVideoSizeChangedListener(this)
        ibPlay.setOnClickListener(this)
        ibNext.setOnClickListener(this)
        ibPrevious.setOnClickListener(this)

        viewModel.loadVideoList()

        handler = Handler {
            when (it.what) {
                UPDATE_MEDIA_PLAYER_INFO -> {
                    viewModel.updateMediaPlayerInfo(mediaPlayer.currentPosition)
                    handler.sendEmptyMessageDelayed(UPDATE_MEDIA_PLAYER_INFO, 33)
                }
            }
            true
        }

        viewModel.mediaCurrentPosition.observe(this, Observer {
            if (viewModel.isTouchProgressBar.value!!)
                mediaPlayer.seekTo(it)
        })

        viewModel.mediaPlayerStatus.observe(this, Observer {
            when (it) {
                MediaPlayerStatus.Init -> {

                }
                MediaPlayerStatus.NotFoundMedia -> {
                    mediaPlayer.reset()
                }
                MediaPlayerStatus.FoundMedia -> {
                    mediaPlayer.reset()
                    mediaPlayer.setDataSource(this, viewModel.mediaUri.value!!)
                    viewModel.callMediaPlayerPrepare()
                }
                MediaPlayerStatus.Prepare -> {
                    mediaPlayer.prepareAsync()
                }
                MediaPlayerStatus.Ready -> {
                    mediaPlayer.seekTo(viewModel.mediaCurrentPosition.value!!)
                    viewModel.setMediaInfo(mediaPlayer.duration)

                    // 自動撥放
                    viewModel.callMediaPlayerStart()
                }
                MediaPlayerStatus.Start -> {
                    if (!mediaPlayer.isPlaying) {
                        mediaPlayer.start()
                    }
                    handler.sendEmptyMessage(UPDATE_MEDIA_PLAYER_INFO)
                }
                MediaPlayerStatus.Pause -> {
                    if (mediaPlayer.isPlaying) {
                        mediaPlayer.pause()
                    }
                    handler.removeMessages(UPDATE_MEDIA_PLAYER_INFO)
                }
                MediaPlayerStatus.Stop -> {
                    mediaPlayer.stop()
                    handler.removeMessages(UPDATE_MEDIA_PLAYER_INFO)
                }
                MediaPlayerStatus.Complete -> {
                    handler.removeMessages(UPDATE_MEDIA_PLAYER_INFO)
                }
                MediaPlayerStatus.Error -> {

                }
            }
        })

        viewModel.displayViewStatus.observe(this, Observer {
            when (it) {
                DisplayViewStatus.Created -> mediaPlayer.setDisplay(svVideo.holder!!)
                DisplayViewStatus.Destroyed -> {
                }
            }
        })
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {

    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        viewModel.onDisplayDestroyed()
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        viewModel.onDisplayCreated()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        viewModel.onMediaProgressChange(p1)
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        viewModel.setTouchProgressBar(true)
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        viewModel.setTouchProgressBar(false)
    }

    override fun onPrepared(p0: MediaPlayer?) {
        viewModel.callMediaPlayerReady()
    }

    override fun onCompletion(p0: MediaPlayer?) {
        viewModel.onMediaPlayComplete()
    }

    override fun onError(p0: MediaPlayer?, p1: Int, p2: Int): Boolean {
        viewModel.onMediaPlayError(p1, p2)
        return true
    }

    override fun onClick(p0: View?) {
        when (p0) {
            ibPlay -> {
                viewModel.onMediaPlayerButton()
            }
            ibPrevious -> {
                viewModel.onPreviousButton()
            }
            ibNext -> {
                viewModel.onNextButton()
            }
        }
    }

    override fun onVideoSizeChanged(p0: MediaPlayer?, videoWidth: Int, videoHeight: Int) {

        val displayWidth = flVideoPanel.width
        val displayHeight = flVideoPanel.height

        var videoOrientation: Int = LinearLayout.VERTICAL
        if (videoHeight >= videoWidth) {
            videoOrientation = LinearLayout.HORIZONTAL
        }

        var displayOrientation: Int = LinearLayout.VERTICAL
        if (displayHeight >= displayWidth) {
            displayOrientation = LinearLayout.HORIZONTAL
        }

        val ratio: Float
        val newVideoWidth: Int
        val newVideoHeight: Int
        if (displayOrientation == videoOrientation) {
            if (displayOrientation == LinearLayout.VERTICAL) {
                ratio = displayWidth.toFloat() / videoWidth
                newVideoWidth = displayWidth
                newVideoHeight = (videoHeight * ratio).toInt()
            } else {
                ratio = displayHeight.toFloat() / videoHeight
                newVideoWidth = (videoWidth * ratio).toInt()
                newVideoHeight = displayHeight
            }
        } else {
            if (displayOrientation == LinearLayout.VERTICAL) {
                ratio = displayHeight.toFloat() / videoHeight
                newVideoWidth = (videoWidth * ratio).toInt()
                newVideoHeight = displayHeight
            } else {
                ratio = displayWidth.toFloat() / videoWidth
                newVideoWidth = displayWidth
                newVideoHeight = (videoHeight * ratio).toInt()
            }
        }

        svVideo.layoutParams.width = newVideoWidth
        svVideo.layoutParams.height = newVideoHeight
        svVideo.requestLayout()
    }
}