package com.example.studentmanager

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var txtName: TextView
    private lateinit var txtAge: TextView
    private lateinit var txtMajor: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        txtName = findViewById(R.id.txtNameDetail)
        txtAge = findViewById(R.id.txtAgeDetail)
        txtMajor = findViewById(R.id.txtMajorDetail)

        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0)
        val major = intent.getStringExtra("major")

        txtName.text = name
        txtAge.text = "Tuổi: $age"
        txtMajor.text = "Ngành: $major"
    }
}
