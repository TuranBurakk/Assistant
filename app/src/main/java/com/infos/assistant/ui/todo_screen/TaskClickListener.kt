package com.infos.assistant.ui.todo_screen

import com.infos.assistant.data.TodoData

interface TaskClickListener
{
    fun completed(todo: TodoData)
    fun delete(todo: TodoData)
}