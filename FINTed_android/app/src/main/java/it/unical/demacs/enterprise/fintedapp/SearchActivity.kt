package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel


@Composable
fun SearchActivity(
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>
) {
    val content = remember { mutableStateOf("") }

    TopAppBar(modifier = Modifier.fillMaxWidth()) {
        
    }
}