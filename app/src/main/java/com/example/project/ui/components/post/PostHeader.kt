package com.example.project.ui.components.post

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project.dataclasses.User
import com.example.project.ui.components.common.YCenteredAvatar

@Composable
@Preview
fun PostHeaderPreview(){
    return PostHeader(
        user = User(
            "_id",
            "Kater Kat",
            "email@gmail.com",
            "https://picsum.photos/seed/picsum/200/200"
        ),
        "A day ago"
    )
}
// "https://i.imgur.com/tec6XdD.png"
// "A day ago"
@Composable
fun PostHeader(
    user: User,
    postDate: String,
){
    return Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        YCenteredAvatar(url = user.avatar)
        Column (
            modifier = Modifier
                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            PostUsername(name = user.fullName)
            PostPublishDate(date = postDate)
        }
    }
}

@Composable
fun PostUsername(name: String) {
    return Text(
        fontWeight = FontWeight.ExtraBold,
        text = name,
    )
}

@Composable
fun PostPublishDate(date: String) {
    return Text(
        color = Color.Gray,
        modifier = Modifier
            .padding(8.dp, 0.dp, 0.dp, 0.dp),
        text = date,
    )
}
