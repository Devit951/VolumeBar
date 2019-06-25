package com.github.devit951.volumebar

import android.content.Context
import android.media.AudioManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var audioManager: AudioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        vv_bar.calibrate(
                maxVolumeCount = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC),
                currentVolumeCount = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC))
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_VOLUME_UP || event?.keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            vv_bar.setCurrentVolume(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC))
        }
        return super.dispatchKeyEvent(event)
    }
}
