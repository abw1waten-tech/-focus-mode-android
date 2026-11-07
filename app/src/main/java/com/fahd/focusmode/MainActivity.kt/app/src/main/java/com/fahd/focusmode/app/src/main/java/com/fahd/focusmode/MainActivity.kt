
package com.fahd.focusmode

import android.app.Activity
import android.os.Bundle
import android.os.CountDownTimer
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private var minutes = 30
    private var timer: CountDownTimer? = null
    private lateinit var display: TextView
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(30, 30, 30, 30)
            setBackgroundColor(Color.rgb(18, 25, 38))
        }

        val title = TextView(this).apply {
            text = "وضع التركيز"
            textSize = 30f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        display = TextView(this).apply {
            text = "30:00"
            textSize = 55f
            setTextColor(Color.GREEN)
            gravity = Gravity.CENTER
            setPadding(0, 50, 0, 50)
        }

        val choices = LinearLayout(this).apply {
            gravity = Gravity.CENTER
        }

        listOf(15, 30, 60, 90).forEach { value ->
            val button = Button(this).apply {
                text = "$value"
                setOnClickListener {
                    if (timer == null) {
                        minutes = value
                        display.text = "%02d:00".format(minutes)
                    }
                }
            }
            choices.addView(button)
        }

        startButton = Button(this).apply {
            text = "ابدأ التركيز"
            textSize = 20f
            setOnClickListener { startFocus() }
        }

        layout.addView(title)
        layout.addView(display)
        layout.addView(choices)
        layout.addView(startButton)

        setContentView(layout)
    }

    private fun startFocus() {
        startButton.isEnabled = false

        timer = object : CountDownTimer(
            minutes * 60 * 1000L, 1000
        ) {
            override fun onTick(millis: Long) {
                val total = millis / 1000
                display.text = "%02d:%02d".format(
                    total / 60, total % 60
                )
            }

            override fun onFinish() {
                display.text = "انتهى وقت التركيز"
                startButton.isEnabled = true
                timer = null
            }
        }.start()
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }
}
