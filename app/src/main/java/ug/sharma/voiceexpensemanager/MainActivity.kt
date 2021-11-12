package ug.sharma.voiceexpensemanager

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.view.View
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import ug.sharma.voiceexpensemanager.Interface.Onitemclick
import ug.sharma.voiceexpensemanager.activity.FirstPage
import ug.sharma.voiceexpensemanager.adapter.TasksAdapter
import ug.sharma.voiceexpensemanager.databaseHandler.DatabaseHandler
import ug.sharma.voiceexpensemanager.model.Task
import ug.sharma.voiceexpensemanager.voice.Voice

class MainActivity : AppCompatActivity() {

    lateinit var taskAdapter: TasksAdapter
    private var tasksList = mutableListOf<Task>()

    val dbHandler = DatabaseHandler(this)
    val i =Intent()

    var voice = Voice()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        addToData()

        findViewById<FloatingActionButton>(R.id.voice).setOnClickListener{
            voice()
        }






    }

    fun addToData(){
        val btn= findViewById<FloatingActionButton>(R.id.Btns)
        btn.setOnClickListener {

            val i = Intent(this, FirstPage::class.java)


            i.putExtra(IncText.text.toString(), "IncText")


            i.putExtra(ExpText.text.toString(), "ExpText")

            dbHandler.insertTask(IncText.text.toString(), ExpText.text.toString())



            tasksList.addAll(dbHandler.getAllTasks())



            startActivity(i)

        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == voice.RQ && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            val string = result?.get(0).toString()
            val strings = string.split(" ")
            if (strings[0] == "income") {

                val k = strings[1]
                IncText.text = Editable.Factory.getInstance().newEditable(k.toString())
            } else if (strings[0] == "expense") {
                val k = strings[1]
                ExpText.text = Editable.Factory.getInstance().newEditable(k.toString())
            } else if (strings[0] == "total") {
                Toast.makeText(this, strings[0].toString(), Toast.LENGTH_SHORT).show()

            }

        }
    }




    fun voice() {
        voice.speak(this)
        startActivityForResult(voice.i, voice.RQ)
    }




}