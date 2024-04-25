package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.ui.utility.AppIndex
import it.unical.demacs.enterprise.fintedapp.viewmodels.AuthValues
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun HomepageActivity(
    context: Context,
    appIndex: MutableState<AppIndex>,
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>,
    offerViewModel: MutableState<OfferViewModel>
) {
    val page = remember { mutableStateOf(0) }
    postViewModel.value.getAll(page.value)

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Row() {
            Text(text = stringResource(id = R.string.helloworld) + " " + AuthValues.username.value + "!")
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            if (postViewModel.value.postList.value.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Filled.Warning,
                            contentDescription = "Icona di avviso"
                        )
                        Text(stringResource(id = R.string.noPosts))
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(items = postViewModel.value.postList.value,
                        key = { post: PostDto -> post.id!! }) { post: PostDto ->
                        PostActivity(
                            post = post,
                            postViewModel = postViewModel,
                            offerViewModel = offerViewModel,
                            scope = PostActivityScope.HOMEPAGE
                        )
                    }
                }
            }
        }
    }

}
