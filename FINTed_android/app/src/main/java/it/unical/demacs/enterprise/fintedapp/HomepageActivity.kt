package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun HomepageActivity(
    context: Context,
    selectedIndex: MutableState<Index>,
    coroutineScope: CoroutineScope,
    postViewModel: PostViewModel,
    userViewModel: UserViewModel,
    offerViewModel: OfferViewModel,
    postSheetStates: SnapshotStateMap<Long, Boolean>,
) {
    val page = remember { mutableStateOf(0) }
    userViewModel.personalProfile.value.id?.let { postViewModel.getHomepage(page.value, it) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row() {
            Text(text = stringResource(id = R.string.helloworld) + userViewModel.personalProfile.value.firstName + "!",
                style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(vertical = 10.dp))
        }
        Row(){
            if (postViewModel.postList.value.isEmpty()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.noPosts))
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    items(items = postViewModel.postList.value, key = { post -> post.id!! }) { post ->

                        postSheetStates[post.id!!] = false

                        PostActivity(
                            post = post,
                            postSheetStates = postSheetStates,
                            coroutineScope = coroutineScope,
                            context = context,
                            selectedIndex = selectedIndex,
                            postType = PostType.FOR_HOMEPAGE,

                            userViewModel = userViewModel,
                            offerViewModel = offerViewModel
                        )

                    }
                }
            }
        }
    }
}




