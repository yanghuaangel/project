package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import android.view.TextureView
import com.lmy.codec.presenter.impl.VideoRecorderImpl
import com.lmy.codec.texture.impl.filter.BaseFilter
import com.lmy.codec.texture.impl.filter.BeautyV4Filter
import com.lmy.codec.texture.impl.filter.GroupFilter
import com.lmy.codec.texture.impl.sticker.ImageSticker
import com.lmy.codec.texture.impl.sticker.TextSticker

class Hwvideo : AppCompatActivity() {
    private lateinit var mRecorder: VideoRecorderImpl
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mTextureView = TextureView(this)
        setContentView(mTextureView)
        mRecorder = VideoRecorderImpl(this).apply {
            reset()
            setOutputUri("$filesDir/test.mp4")
            setOutputSize(720, 1280)//Default 720x1280
            setFps(30)
            setFilter(getDefaultFilter())
            setPreviewDisplay(mTextureView)
        }
        mRecorder.prepare()
        //For recording control
        mTextureView.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (mRecorder.prepared())
                        mRecorder.start()
                }
                MotionEvent.ACTION_UP -> {
                    if (mRecorder.started())
                        mRecorder.pause()
                }
            }
            true
        }
    }

    private fun getDefaultFilter(): BaseFilter = GroupFilter.create(BeautyV4Filter())
            .addSticker(TextSticker().apply {
                setText(TextSticker.Text("HWVC", 56f).apply {
                    x = 0.8f
                    y = 0.03f
                })
            })
            .addSticker(ImageSticker().apply {
                setImage(ImageSticker.Image().apply {
                    x = 0.03f
                    y = 0.03f
                    scale = 1.6f
                    bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_logo_hwvc)
                })
            })

    override fun onDestroy() {
        super.onDestroy()
        mRecorder.release()
    }
}