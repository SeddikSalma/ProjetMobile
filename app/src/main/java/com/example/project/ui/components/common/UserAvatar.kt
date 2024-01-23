package com.example.project.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun UserAvatar(url: String){
    return AsyncImage(
        model = url,
        contentDescription = "User Avatar",
        modifier = Modifier
            .clip(shape = RoundedCornerShape(150.dp))
            .size(40.dp, 40.dp),
    )
}

@Composable
fun YCenteredAvatar(url: String, description: String = "") {
    return Column (
        modifier = Modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        UserAvatar(url)
    }
}