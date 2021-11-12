package ug.sharma.voiceexpensemanager.Interface

import ug.sharma.voiceexpensemanager.model.Task

interface Onitemclick {

    fun onEditClicked(task: Task)

    fun onDeleteClicked(task: Task)


}