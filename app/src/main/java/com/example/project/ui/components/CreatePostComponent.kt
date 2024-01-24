package com.example.project.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project.R
import com.example.project.ui.Posts.PostsFragmentViewModel

@Composable
@Preview
fun CreatePostComponentPreview(){
    return CreatePostComponent {}
}

@Composable
fun CreatePostComponent(onClick: (value: String) -> Unit){
    val text = remember {
        mutableStateOf("")
    }
    return Column {
        TextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
            ),
            modifier = Modifier
                .background(Color.White)
                .padding(8.dp, 0.dp, 8.dp, 0.dp)
                .fillMaxWidth(),
            value = text.value,
            onValueChange = { newText -> text.value = newText },
            label = { Text(text = "What's on your mind?") },
        )
        Row (
            modifier = Modifier
                .padding(0.dp, 0.dp, 8.dp, 0.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF4500)
                ),
                onClick = {
                    val value = text.value;
                    text.value = ""
                    onClick(value)
                }
            ) {
                Text("Post")
            }
        }
    }
}