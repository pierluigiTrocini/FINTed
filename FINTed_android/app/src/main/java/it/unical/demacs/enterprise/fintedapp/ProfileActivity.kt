package it.unical.demacs.enterprise.fintedapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import it.unical.demacs.enterprise.fintedapp.models.UserProfileDto
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
@Composable
fun ProfileActivity(context: Context, selectedIndex: MutableState<Index>, profile: UserProfileDto) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .clip(CardDefaults.shape),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row( modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = stringResource(id = R.string.profile),
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(2f)
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = profile?.username.toString(), style = MaterialTheme.typography.headlineSmall)
                        Text(text = profile?.firstName.toString() + "\t  " + profile?.lastName.toString(),
                            style = MaterialTheme.typography.bodyMedium)

                        Text(text = stringResource(id = R.string.registrationDate) + "\t" + profile?.registrationDate?.let {
                            SimpleDateFormat("dd/MM/yyy").format(
                                it
                            ).toString()
                        }, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
