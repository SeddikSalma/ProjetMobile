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

@Preview
@Composable
fun PostComponentPreview(){
    return PostComponent("dummy")
}

@Composable
fun PostComponent(postInfo: String){
    return PostBox (
        header = {
            PostHeader(
                name = "Kater Kat",
                image = "https://i.imgur.com/tec6XdD.png",
                postDate = "A day ago"
            )
        },
        content = {
            PostBody("Hello World\nLorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
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
            .height(150.dp)
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