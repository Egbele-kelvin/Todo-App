package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_list.*

class MainActivity : AppCompatActivity() {
    private lateinit var todoAdapter: TodoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        todoAdapter = TodoAdapter(mutableListOf())

        rv_todoItems.adapter = todoAdapter
        rv_todoItems.layoutManager =LinearLayoutManager(this)

        button_add.setOnClickListener {
            val todoTitle = todo_input.text.toString()
            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                todo_input.text.clear() // this will clear the text once its added to the list
            }else {
                val toast = Toast.makeText(applicationContext,"Please Enter a Todo", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        button_delete.setOnClickListener {
            todoAdapter.deleteTodo()
        }
    }
}