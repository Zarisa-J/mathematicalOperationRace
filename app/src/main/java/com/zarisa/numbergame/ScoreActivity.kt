package com.zarisa.numbergame

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zarisa.numbergame.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getScoreAndRecord()
        binding.buttonPlayAgain.setOnClickListener { playAgain() }
        binding.exit.setOnClickListener {
            State.level=0
            State.score=0
            State.GroupVisibility=false
            State.numberA=""
            State.numberB=""
            State.choiceButtonTrue=false
            State.choiceButtonFalse=false
            State.buttonList= mutableListOf("","","","")
            State.isAnswer=false
            State.randomIndex=0
            State.buttonIsWrong=0
            this.finishAffinity()
        }
    }
    @SuppressLint("SetTextI18n")
    fun getScoreAndRecord(){
        var score=intent.getIntExtra("score",0)
        binding.textViewShowUserScore.text= "Your score is $score"
        if (score>State.record)
            State.record=score
        binding.textViewRecord.text="Your record is ${State.record}"
    }
    fun playAgain(){
        State.level=0
        State.score=0
        State.GroupVisibility=false
        State.numberA=""
        State.numberB=""
        State.choiceButtonTrue=false
        State.choiceButtonFalse=false
        State.buttonList= mutableListOf("","","","")
        State.isAnswer=false
        State.randomIndex=0
        State.buttonIsWrong=0
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}