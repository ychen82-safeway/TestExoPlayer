package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView


class MainFragment : Fragment(), Player.Listener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var playWhenReady = true
    private lateinit var viewModel: MainViewModel
    private lateinit var playerView: StyledPlayerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerView = view.findViewById(R.id.exo_player_view)
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }


    private fun initializePlayer() {
        viewModel.player = ExoPlayer.Builder(requireContext())
            .build()
        playerView.player = viewModel.player
        viewModel.player?.apply {
            setMediaItem(viewModel.getMediaItem())
            // prepare media list
            playWhenReady = playWhenReady
            seekTo(viewModel.currentWindow, viewModel.playbackPosition)
            addListener(this@MainFragment)
            prepare()
        }

    }

    private fun releasePlayer() {
        viewModel.player?.apply {
            this@MainFragment.playWhenReady = playWhenReady
            viewModel.playbackPosition = currentPosition
            viewModel.currentWindow = currentMediaItemIndex
            removeListener(this@MainFragment)
            release()
        }
        viewModel.player = null
    }

}