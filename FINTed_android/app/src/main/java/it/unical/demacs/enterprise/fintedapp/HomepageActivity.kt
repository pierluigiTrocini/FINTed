package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomepageActivity(
    context: Context,
    selectedIndex: MutableState<Index>,
    coroutineScope: CoroutineScope,
    sheetState: MutableState<Boolean>,
    postViewModel: PostViewModel = PostViewModel()
) {
    val page = remember { mutableStateOf(0) }
    postViewModel.getAll(page.value)

    if(postViewModel.postList.value.isEmpty()){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.noPosts))
        }
    }
    else{
        LazyColumn(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            items(
                items = postViewModel.postList.value,
                key = { post -> post.id!! }
            ){
                post -> PostActivity(post = post, sheetState = sheetState)
            }
        }
    }

    //TODO questo va messo in postActivity
    if(sheetState.value) {
        OfferActivity(
            sheetState = sheetState,
            coroutineScope = coroutineScope,
            post = null,
            context = context,
            selectedIndex = selectedIndex
        )
    }
}




