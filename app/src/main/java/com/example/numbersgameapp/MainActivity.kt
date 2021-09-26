package com.example.numbersgameapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random
var randomnumber: Int = 0
var chancescount: Int = 0
var gamemessage: String = ""
lateinit var result: TextView
lateinit var userchances: TextView
lateinit var usernamber: TextInputEditText
lateinit var buttonplay: Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        result = findViewById(R.id.result)
        userchances = findViewById(R.id.userchances)
        usernamber = findViewById(R.id.usernamber)
        buttonplay = findViewById(R.id.buttonplay)
        chancescount = 3
        randomnumber = Random.nextInt(20)
        gamemessage = ""
        buttonplay.setOnClickListener { game() }
    }

    fun game() {
        if (chancescount > 1) {

                result.text = "You guessed ${usernamber.text}"
                if (usernamber.text.toString().toInt() == randomnumber) {
                    gamemessage = "You won!"
                    customAlert()
                } else {
                    chancescount--
                    if (chancescount == 1) {
                        userchances.text = "You have $chancescount guess left!"
                        Log.d("MainActivityGame", "answer is $randomnumber")
                    } else {
                        userchances.text = "You have $chancescount guesses left!"
                    }
                }


        } else {
            userchances.text = "You have $chancescount guess left!"
            gamemessage = "You lost the game!"
            customAlert()
        }
    }

    private fun customAlert(){

        val dialogBuilder = AlertDialog.Builder(this)


        dialogBuilder.setMessage("Do you want to play again?:")

            .setPositiveButton("yes", DialogInterface.OnClickListener {
                    dialog, id -> this.recreate()
            })

            .setNegativeButton("No", DialogInterface.OnClickListener {
                    dialog, id -> finish()
            })

        val alert = dialogBuilder.create()

        alert.setTitle("$gamemessage")


        alert.show()
    }
}