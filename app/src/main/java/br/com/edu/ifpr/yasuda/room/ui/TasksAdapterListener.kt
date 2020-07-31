package br.com.edu.ifpr.yasuda.room.ui

import br.com.edu.ifpr.yasuda.room.entities.Task

interface TasksAdapterListener {
    fun taskRemoved(task: Task)
    fun taskClicked(task: Task)
    fun taskUpdate(task: Task)
}