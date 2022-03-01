package com.zarisa.numbergame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.zarisa.numbergame.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var listButtons = mutableListOf<Button>()
    var randomIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }
    private fun initView() {
        listButtons.add(binding.button1)
        listButtons.add(binding.button2)
        listButtons.add(binding.button3)
        listButtons.add(binding.button4)
        binding.buttonDice.setOnClickListener {dice()}
        listButtons.forEach {
            it.setOnClickListener {
                checkAnswer(listButtons.indexOf(it))
            }
        }
        binding.textViewScore.text = State.score.toString()
    }

    private fun dice() {
        if (State.level==4){
            val intent=Intent(this,ScoreActivity::class.java)
            intent.putExtra("score",State.score)
            startActivity(intent)
        }
        State.level++
        listButtons.forEach { it.isClickable=true}
        clearColor()
        listButtons.forEach { it.text = "" }
        var randomA = Random().nextInt(99) + 1
        binding.textViewNumberA.text = randomA.toString()
        var randomB = Random().nextInt(9) + 1
        binding.textViewNumberB.text = randomB.toString()
        randomIndex = Random().nextInt(listButtons.size - 1)
        var div = divide(randomA, randomB)
        listButtons[randomIndex].text = div.toString()
        var listTextCheck = mutableListOf<String>()
        listTextCheck.add(div.toString())
        listButtons.forEach {
            if (it.text == "") {
                while (it.text=="") {
                    var randAnswer =(Random().nextInt(9)+1).toString()
                    if (!listTextCheck.contains(randAnswer)) {
                        listTextCheck.add(randAnswer)
                        it.text = (randAnswer)
                    }
                }
            }
            binding.groupAnswers.visibility = View.VISIBLE
        }
    }

    fun checkAnswer(butIndex: Int) {
        if (butIndex == randomIndex) {
            State.score += 5
            listButtons[butIndex].setBackgroundColor(Color.GREEN)
        } else {
            State.score -= 2
            listButtons[butIndex].setBackgroundColor(Color.RED)
        }
        binding.textViewScore.text=State.score.toString()
        listButtons.forEach { it.isClickable=false }
    }
    fun divide(randomA: Int, randomB: Int): Int {
        return randomA % randomB
    }
    fun clearColor() {
        listButtons.forEach {
            it.setBackgroundColor(Color.BLUE)
        }
    }

}