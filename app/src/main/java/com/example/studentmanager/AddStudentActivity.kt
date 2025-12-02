package com.example.studentmanager

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtAge: EditText
    private lateinit var edtMajor: EditText
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        edtName = findViewById(R.id.edtName)
        edtAge = findViewById(R.id.edtAge)
        edtMajor = findViewById(R.id.edtMajor)
        btnSave = findViewById(R.id.btnSave)
        btnCancel = findViewById(R.id.btnCancel)

        btnSave.setOnClickListener {
            val result = intent
            result.putExtra("new_name", edtName.text.toString())
            result.putExtra("new_age", edtAge.text.toString().toInt())
            result.putExtra("new_major", edtMajor.text.toString())

            setResult(Activity.RESULT_OK, result)
            finish()
        }

        btnCancel.setOnClickListener { finish() }
    }
}
