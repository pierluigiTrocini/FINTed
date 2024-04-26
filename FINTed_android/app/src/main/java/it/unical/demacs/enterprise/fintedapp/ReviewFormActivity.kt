package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import it.unical.demacs.enterprise.fintedapp.viewmodels.ReviewViewModel
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.viewmodels.AuthValues

@Composable
fun ReviewFormActivity(
    showReviewForm: MutableState<Boolean>,
    reviewViewModel: MutableState<ReviewViewModel>,
    targetUsername: String
){
    val reviewText = remember { mutableStateOf("") }

    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.makeReview),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = reviewText.value,
            onValueChange = { v -> reviewText.value = v },
            label = { Text(stringResource(id = R.string.makeOffer)) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            showReviewForm.value = false
            reviewViewModel.value.save(
                targetUsername = targetUsername,
                content = reviewText.value
            )
        }) {
            Text(text = stringResource(id = R.string.publish))
        }
    }
}