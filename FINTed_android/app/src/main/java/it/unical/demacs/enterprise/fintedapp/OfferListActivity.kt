package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text

@Composable
fun OfferListActivity(context: Context, selectedIndex: MutableState<Index>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.postLabel),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = stringResource(id = R.string.usernameLabel) + "\toffre:\t" + stringResource(id = R.string.offerLabel),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top=4.dp)
            )

            Button(
                onClick = {
                    makeToast(context, context.resources.getString(R.string.acceptedOffer))
                },
                modifier=Modifier.padding(top=12.dp),
                colors=buttonColors(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(stringResource(id = R.string.acceptOffer))
            }
        }
    }
}

private fun makeToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}
