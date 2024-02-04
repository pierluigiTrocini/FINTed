package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.models.PostDto
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostActivity(
    post: PostDto,
    sheetState: MutableState<Boolean>?
){
    Card(
        modifier = Modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = post.sellerUsername.toString(), style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.weight(1f))
            }
            Text(text = stringResource(id = R.string.publishDate) + SimpleDateFormat("dd/MM/yyy").format(post.publishedDate).toString(), style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = post.title.toString(), style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(modifier = Modifier.padding(vertical = 20.dp)) {
                Text(text = stringResource(id = R.string.postImage))
            }
            Text(text = stringResource(id = R.string.postStartingPrice) + post.startingPrice + "€")
            Row {
                Button(onClick = {
                    if (sheetState != null) {
                        sheetState.value = true
                    }
                }) {
                    Text(stringResource(id = R.string.makeOffer), color = Color.Black)
                }
            }
        }
    }
}