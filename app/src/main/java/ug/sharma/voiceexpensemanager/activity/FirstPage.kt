package ug.sharma.voiceexpensemanager.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_first_page.*
import ug.sharma.voiceexpensemanager.Interface.Onitemclick
import ug.sharma.voiceexpensemanager.R
import ug.sharma.voiceexpensemanager.adapter.TasksAdapter
import ug.sharma.voiceexpensemanager.databaseHandler.DatabaseHandler
import ug.sharma.voiceexpensemanager.model.Task
import ug.sharma.voiceexpensemanager.voice.Voice

class FirstPage : AppCompatActivity(), Onitemclick {


    lateinit var taskAdapter: TasksAdapter

    private var tasksList = mutableListOf<Task>()

    val dbHandler= DatabaseHandler(this)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_first_page)


        tasksList.addAll(dbHandler.getAllTasks())



        taskAdapter= TasksAdapter(this,tasksList,this)
        recyclerview.layoutManager= LinearLayoutManager(this)
        recyclerview.adapter=taskAdapter





    }







    override fun onEditClicked(task: Task) {

        val newIncome=0
        val newExpense=0

        task.income=newIncome
        task.Exp=newExpense

        dbHandler.editTask(task)
        Toast.makeText(this,"After reset you cannot edit this we recommend to delete it",Toast.LENGTH_LONG).show()
        updateUI()


    }

    override fun onDeleteClicked(task: Task) {

        dbHandler.deleteTask(task)
        updateUI()

    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateUI(){
        val latestTasks=dbHandler.getAllTasks()
        tasksList.clear()
        tasksList.addAll(latestTasks)
        taskAdapter.notifyDataSetChanged()
    }

}