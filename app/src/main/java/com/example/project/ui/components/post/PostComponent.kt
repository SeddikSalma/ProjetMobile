package com.example.project.ui.components.post

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.project.dataclasses.Post
import com.example.project.dataclasses.User

@Preview
@Composable
fun PostComponentPreview(){
    return PostComponent(
        Post(
            "title",
            "dummy",
            User("_id", "name", "email", "https://picsum.photos/seed/picsum/200/200")
        )
    )
}

@Composable
fun PostComponent(postInfo: Post){
    return PostBox (
        header = {
            PostHeader(
                postInfo.postedBy,
                "A day ago"
            )
        },
        content = {
            PostBody(postInfo.content)
        }
    )
}

@Composable
fun PostBox(
    header: @Composable() () -> Unit,
    content: @Composable() () -> Unit,
){
    return OutlinedCard (
        modifier = Modifier
            .fillMaxWidth()
            //.height(150.dp)
            .padding(16.dp, 16.dp, 16.dp, 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),

        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
        ) {
            Column {
                header()
                content()
            }
        }
    }
}