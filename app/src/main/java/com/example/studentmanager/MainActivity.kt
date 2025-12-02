package com.example.studentmanager

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

data class Student(
    val id: Int,
    val name: String,
    val age: Int,
    val major: String
)

class MainActivity : AppCompatActivity() {

    private val students = mutableListOf<Student>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listViewStudents)
        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)

        // Khởi tạo adapter với danh sách rỗng ban đầu
        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            students.map { it.name }
        )
        listView.adapter = adapter

        // Mở màn hình AddStudentActivity khi nhấn FAB
        fab.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivityForResult(intent, 100)
        }

        // Xem chi tiết khi bấm vào item
        listView.setOnItemClickListener { _, _, position, _ ->
            val st = students[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", st.id)
            intent.putExtra("name", st.name)
            intent.putExtra("age", st.age)
            intent.putExtra("major", st.major)
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK && data != null) {
            val id = students.size + 1
            val name = data.getStringExtra("name") ?: ""
            val age = data.getIntExtra("age", 0)
            val major = data.getStringExtra("major") ?: ""

            students.add(Student(id, name, age, major))

            // Cập nhật adapter
            adapter.clear()
            adapter.addAll(students.map { it.name })
            adapter.notifyDataSetChanged()
        }
    }
}
