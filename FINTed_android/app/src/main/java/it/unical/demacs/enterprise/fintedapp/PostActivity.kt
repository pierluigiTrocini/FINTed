package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.`OfferViewModel.kt`
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

@Composable
fun PostActivity(
    post: PostDto,
    coroutineScope: CoroutineScope,
    context: Context,
    selectedIndex: MutableState<Index>,
    postType: PostType,
    userViewModel: UserViewModel,
    `anOfferViewModel.kt`: `OfferViewModel.kt`,
    postSheetStates: SnapshotStateMap<Long, Boolean>?
){
    Card(
        modifier = Modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {        
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.itemId) + "\t #" + post?.id.toString(), style = MaterialTheme.typography.bodySmall)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = post.sellerUsername.toString(), style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
            }
            Text(text = stringResource(id = R.string.publishDate) + post.publishedDate?.let {
                SimpleDateFormat("dd/MM/yyy").format(
                    it
                ).toString()
            }, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.title.toString(), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(vertical = 20.dp)) {
                Text(text = stringResource(id = R.string.postImage))
            }
            if(postType == PostType.FOR_HOMEPAGE){
                Text(text = stringResource(id = R.string.postStartingPrice) + post.startingPrice + "€")

                Row {
                    Button(onClick = {
                        postSheetStates?.set(post.id!!, true)
                    }) {
                        Text(stringResource(id = R.string.makeOffer), color = Color.Black)
                    }
                }
            }
        }
        if (postSheetStates?.get(post.id!!) != null) {
            if(postSheetStates[post.id!!] == true) {
                val offerPrice = remember { mutableStateOf("") }

                Dialog(
                    onDismissRequest = { postSheetStates[post.id!!] = false },
                ) {
                    Column(modifier = Modifier
                        .padding(30.dp)
                        .background(MaterialTheme.colorScheme.background)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){
                            androidx.wear.compose.material.Text(text = stringResource(id = R.string.itemId) + "\t " + post.id)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                            Text(
                                text = stringResource(id = R.string.offerTitle),
                                style = MaterialTheme.typography.titleLarge,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                            OutlinedTextField(
                                value = offerPrice.value,
                                onValueChange = {
                                        v ->
                                    if(v.all{ it.isDigit() }){
                                        offerPrice.value = v
                                    }
                                },
                                label = { androidx.wear.compose.material.Text(stringResource(id = R.string.makeOfferLabel)) },
                                maxLines = 1,
                                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                            Button(onClick = {
                                selectedIndex.value = Index.HOMEPAGE

                                if (post.id != null) {
                                    post?.id.let {
                                        userViewModel.personalProfile.value.id?.let { it1 ->
                                            `anOfferViewModel.kt`.save(
                                                postId = it,
                                                userId = it1,
                                                offer = offerPrice.value
                                            )
                                        }
                                    }
                                }

                                coroutineScope.launch {
                                    postSheetStates[post.id!!] = false
                                    makeToast(context, context.resources.getString(R.string.offerPublishedToast))
                                    userViewModel.personalProfile.value.id?.let {
                                        `anOfferViewModel.kt`.save(post?.id,
                                            it, offerPrice.value)
                                    }
                                }
                            }) {
                                androidx.wear.compose.material.Text(stringResource(id = R.string.publish))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

private fun makeToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}