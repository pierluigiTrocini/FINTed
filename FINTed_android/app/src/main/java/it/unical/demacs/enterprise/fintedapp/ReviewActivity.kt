package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.models.ReviewDto

@Composable
fun ReviewActivity(
    context: Context,
    selectedIndex: MutableState<Index>,
    profileIndex: MutableState<ProfileIndex>,
    reviews: List<ReviewDto>?,
    personalReviews: Boolean
){
    if(reviews != null){
        //todo implementare la lazy column
        LazyColumn{
            items(reviews) {review ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    elevation = CardDefaults.elevatedCardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "username",
                                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
                                )
                                Text(
                                    text = "data pubblicazione",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                        Text(
                            text = "content",
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top=4.dp)
                        )
                    }
                }
            }
        }
    }
    else{
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.padding(vertical = 20.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Filled.Email, contentDescription = null, modifier = Modifier.size(50.dp) )
                Text(text = stringResource(id = R.string.noReviews))

                if(!personalReviews){
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = stringResource(id = R.string.makeReview))
                    }
                }
            }
        }
    }
    
}