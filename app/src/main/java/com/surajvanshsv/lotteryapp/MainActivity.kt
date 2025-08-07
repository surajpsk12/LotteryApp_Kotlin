package com.surajvanshsv.lotteryapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // declaring the widgets
    lateinit var editText: EditText
    lateinit var buttonresult : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //initialize the widgets
        editText=findViewById(R.id.editTextName)
        buttonresult=findViewById(R.id.generateBtn)

        // intent on button click
        buttonresult.setOnClickListener {
            var i = Intent(this, SecondActivity::class.java)
            // gettting edit text name from edit
            var name : String = editText.text.toString()
            // passing name in intent to other screen
            i.putExtra("username",name)
            startActivity(i)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}