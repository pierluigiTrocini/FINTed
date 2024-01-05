package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomepageActivity(context: Context, selectedIndex: MutableState<Index>) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = remember { mutableStateOf(false) }
    var offerPrice = remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier.padding(16.dp),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "username", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.weight(1f))
                }
                Text(text = stringResource(id = R.string.publishDate) + "../../..", style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Titolo", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.padding(vertical = 20.dp)) {
                    Text(text = stringResource(id = R.string.postImage))
                }
                Row {
                    Button(onClick = {
                        sheetState.value = true
                    }) {
                        Text(stringResource(id = R.string.makeOffer), color = Color.Black)
                    }
                }
            }
        }
    }

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




