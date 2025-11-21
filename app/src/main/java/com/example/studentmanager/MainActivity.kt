package com.example.studentmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Student(var name: String, val id: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentApp() {
    var name by remember { mutableStateOf("") }
    var id by remember { mutableStateOf("") }

    val students = remember { mutableStateListOf<Student>() }

    var selectedIndex by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.padding(16.dp)) {

        // ------- INPUTS -------
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Họ tên") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("MSSV") },
            modifier = Modifier.fillMaxWidth(),
            enabled = selectedIndex == -1 // Khi chọn item thì khóa MSSV không cho sửa
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Button(
                onClick = {
                    if (name.isNotEmpty() && id.isNotEmpty()) {
                        students.add(Student(name, id))
                        name = ""
                        id = ""
                    }
                }
            ) {
                Text("Add")
            }

            Spacer(Modifier.width(12.dp))

            Button(
                onClick = {
                    if (selectedIndex != -1) {
                        students[selectedIndex].name = name
                        selectedIndex = -1
                        name = ""
                        id = ""
                    }
                }
            ) {
                Text("Update")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ------- LIST -------
        LazyColumn {
            items(students) { student ->
                val index = students.indexOf(student)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedIndex = index
                            name = student.name
                            id = student.id
                        }
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Họ tên: ${student.name}")
                        Text("MSSV: ${student.id}")
                    }

                    Button(onClick = { students.removeAt(index) }) {
                        Text("Xóa")
                    }
                }

                Divider()
            }
        }
    }
}
