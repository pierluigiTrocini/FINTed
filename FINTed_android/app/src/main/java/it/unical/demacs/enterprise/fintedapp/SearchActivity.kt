package it.unical.demacs.enterprise.fintedapp

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchActivity(
    userViewModel: MutableState<UserViewModel>,
    postViewModel: MutableState<PostViewModel>,
    offerViewModel: MutableState<OfferViewModel>
) {
    val content = remember { mutableStateOf("") }
    val searchType = remember { mutableStateOf(SearchTypeList.NONE) }

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth()) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextField(value = content.value,
                            onValueChange = { v -> content.value = v },
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search, contentDescription = stringResource(
                                        id = R.string.search
                                    )
                                )
                            })
                        Button(
                            onClick = {
                                searchType.value = SearchTypeList.SEARCH_BY_USER
                                postViewModel.value.searchByUser(content = content.value)
                            }
                        ) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = stringResource(id = R.string.search_profiles)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = stringResource(
                                        id = R.string.search_profiles
                                    )
                                )
                            }
                        }
                        Button(onClick = {
                            searchType.value = SearchTypeList.SEARCH_BY_TITLE
                            postViewModel.value.searchByTitle(content = content.value)
                        }
                        ) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = stringResource(id = R.string.search_profiles)
                                )
                                Spacer(modifier = Modifier.width(2.dp))
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = stringResource(
                                        id = R.string.search_profiles
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }){
        Row {
            if (searchType.value != SearchTypeList.NONE) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    if (postViewModel.value.postList.value.isEmpty()) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                androidx.compose.material3.Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = ""
                                )
                                if(searchType.value == SearchTypeList.SEARCH_BY_TITLE){
                                    Text(stringResource(id = R.string.noPosts_title, content.value))
                                }
                                else if(searchType.value == SearchTypeList.SEARCH_BY_USER){
                                    Text(stringResource(id = R.string.noPosts_user, content.value))
                                }
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
                                    userViewModel = userViewModel,
                                    scope = PostActivityScope.HOMEPAGE
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}

enum class SearchTypeList {
    SEARCH_BY_TITLE, SEARCH_BY_USER, NONE
}