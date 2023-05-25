package com.example.uno.presentation.toasts

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import com.example.uno.R

class ToastFinishGame {

    companion object{

        private var isPlaying = false
        private val handler = Handler()
        private const val DELAY_IN_MILLIS: Long = 8000

        var song: MediaPlayer? = null

        fun toastFinishGame(context: Context){
            if (!isPlaying) {
                isPlaying = true
                song = MediaPlayer.create(context, R.raw.song_finish)
                song?.start()
                val toast = Toast(context)
                val view = LayoutInflater.from(context).inflate(
                    R.layout.toast_finish_game,
                    null,
                    false
                )
                toast.setView(view)
                toast.duration = Toast.LENGTH_LONG
                toast.setGravity(Gravity.CENTER_VERTICAL,0,0)
                toast.show()
                handler.postDelayed({
                    onMelodyCompletion()
                }, DELAY_IN_MILLIS)
            }
        }

        private fun onMelodyCompletion() {
            isPlaying = false
        }

    }

}