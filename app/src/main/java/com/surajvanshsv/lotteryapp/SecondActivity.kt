package com.surajvanshsv.lotteryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    // declaring the view
    lateinit var textview1 : TextView
    lateinit var resulttextview1 : TextView
    lateinit var shareButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        // initialize the widgets
        textview1=findViewById(R.id.textView2)
        resulttextview1=findViewById(R.id.resultTextView)
        shareButton=findViewById(R.id.shareBtn)
        // generate the random number then set text to result view and then share with button with implicit intent
        // Generate 6 random numbers and store them in a string
        val randomNumbers = generateRandomNumbers(6)
        resulttextview1.text = randomNumbers

        // Getting the Username from the main Activity
        var username = receiveUserName()

        // Sharing the username & generated Numbers with Email App
        shareButton.setOnClickListener{
            shareResult(username,randomNumbers)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun generateRandomNumbers(count:Int):String{
        // Create a list of random numbers
        var randomNumbers = List(count){
            // lambda expression to generate random numbers
            (1..42).random()
        }

        // Convert the list of numbers to a string
        return randomNumbers.joinToString(" ")

    }

    fun receiveUserName():String{
        // retrieve the extras that were added to an INTENT
        // ? --> indicates that the variable can be NULL
        var bundle:Bundle? = intent.extras

        // Give me the key, I'll give you the value
        var username = bundle?.getString("username").toString()

        return username
    }
    fun shareResult(username:String, generatedNums:String){

        // Implicit Intents: specify an action to be performed
        // and the system determines the appropriate component
        // to handle that action based on the available registered
        // component and their declared capabilities

        // Sending data to another application
        var i = Intent(Intent.ACTION_SEND)

        i.setType("text/plain")
        i.putExtra(Intent.EXTRA_SUBJECT,"$username generates these numbers")
        i.putExtra(Intent.EXTRA_TEXT, "The Lottery Numbers are: $generatedNums")
        startActivity(i)
    }
}