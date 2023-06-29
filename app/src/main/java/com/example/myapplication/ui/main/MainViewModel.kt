package com.example.myapplication.ui.main

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.util.MimeTypes

class MainViewModel : ViewModel() {
    private val videoURL =
        "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4"

    var currentWindow = 0
    var playbackPosition: Long = 0
    var player: ExoPlayer? = null
    fun getMediaItem() : MediaItem {
        // add one media item
        // val mediaItem: MediaItem = MediaItem.fromUri(getString(R.string.media_url_mp3))
        return MediaItem.Builder()
            .setUri(Uri.parse(videoURL))
            .setMimeType(MimeTypes.BASE_TYPE_VIDEO)
            .build()
    }
}