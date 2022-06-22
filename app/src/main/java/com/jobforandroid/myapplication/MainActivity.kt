package com.jobforandroid.myapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var calculator: CalculatorModel? = null
    private var text: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val numberIds = intArrayOf(
            R.id.zero,
            R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            R.id.six,
            R.id.seven,
            R.id.eight,
            R.id.nine
        )
        val actionsIds = intArrayOf(
            R.id.plus,
            R.id.minus,
            R.id.multiply,
            R.id.division,
            R.id.equals
        )
        text = findViewById(R.id.text)
        calculator = CalculatorModel()
        val numberButtonClickListener = View.OnClickListener { view ->
            calculator!!.onNumPressed(view.id)
            with(text) { this?.setText(calculator!!.text) }
        }
        val actionButtonOnclickListener = View.OnClickListener { view ->
            calculator!!.onActionPressed(view.id)
            with(text) { this?.setText(calculator!!.text) }
        }
        for (i in numberIds.indices) {
            findViewById<View>(numberIds[i]).setOnClickListener(numberButtonClickListener)
        }
        for (i in actionsIds.indices) {
            findViewById<View>(actionsIds[i]).setOnClickListener(actionButtonOnclickListener)
        }
        findViewById<View>(R.id.reset).setOnClickListener {
            calculator!!.reset()
            with(text) { this?.setText(calculator!!.text) }
        }
    }
}