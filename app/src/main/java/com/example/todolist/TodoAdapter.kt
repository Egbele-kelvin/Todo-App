package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list.view.*

class TodoAdapter(
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder (LayoutInflater.from(parent.context).inflate(
            R.layout.item_list,
            parent,
            false)
        )
    }

    private fun strikeText(tvTodoTitle: TextView, isChecked: Boolean){
        if (isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteTodo(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            itemList_textview.text = curTodo.title
            checkbox.isChecked = curTodo.isChecked
           strikeText(itemList_textview,curTodo.isChecked)
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                strikeText(itemList_textview,isChecked)
                curTodo.isChecked = !curTodo.isChecked
            }

        }
    }

    override fun getItemCount(): Int {
     return todos.size
    }

}