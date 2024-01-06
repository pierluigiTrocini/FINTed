package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Icon
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import kotlinx.coroutines.CoroutineScope

@Composable
fun PersonalProfileActivity(
    context: Context,
    selectedIndex: MutableState<Index>,
    profile: UserPersonalProfileDto?,
    accountState: MutableState<AccountState>,
    coroutineScope: CoroutineScope,
) {
    var debugListValue = remember { mutableStateOf(ProfileIndex.POSTS) }

    var bottomSheetChoice = remember { mutableStateOf(ProfileBottomSheet.NONE) }

    if (accountState.value == AccountState.LOGGED) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Card(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .clip(CardDefaults.shape),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primaryContainer),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = stringResource(id = R.string.profile),
                                modifier = Modifier.size(70.dp)
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 12.dp, vertical = 4.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = profile?.username.toString(),
                                style = MaterialTheme.typography.headlineMedium
                            )
                            Text(
                                text = profile?.firstName.toString() + "\t" + profile?.lastName.toString(),
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Text(
                                text = stringResource(id = R.string.emailLabel) + profile?.credentialsEmail.toString(),
                                modifier = Modifier.padding(vertical = 10.dp),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                        Column {
                            Text(text = stringResource(id = R.string.balance) + stringResource(id = R.string.currency))
                        }
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                        Button(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = stringResource(id = R.string.edit),
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                        }
                    }
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { debugListValue.value = ProfileIndex.POSTS },
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.postList),
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                }

                Button(
                    onClick = { debugListValue.value = ProfileIndex.REVIEWS },
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.reviewList),
                        color = MaterialTheme.colorScheme.inversePrimary
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                if (debugListValue.value == ProfileIndex.REVIEWS) {
                    ReviewActivity(
                        context = context,
                        selectedIndex = selectedIndex,
                        profileIndex = debugListValue,
                        reviews = null,
                        personalReviews = true
                    )
                }
            }
        }
    } else {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(text = stringResource(id = R.string.noProfileTitle),
                    style = MaterialTheme.typography.headlineMedium)
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { bottomSheetChoice.value = ProfileBottomSheet.LOGIN },
                    modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(text = stringResource(id = R.string.loginLabel))
                }
                Button(onClick = { bottomSheetChoice.value = ProfileBottomSheet.REGISTRATION },
                    modifier = Modifier.padding(horizontal = 20.dp)) {
                    Text(text = stringResource(id = R.string.registrationLabel))
                }
            }
        }
    }

    if(bottomSheetChoice.value != ProfileBottomSheet.NONE){
        if(bottomSheetChoice.value == ProfileBottomSheet.LOGIN){
            LoginActivity(
                context,
                selectedIndex,
                profile,
                accountState,
                coroutineScope,
                bottomSheetChoice
            )
        }
        else if (bottomSheetChoice.value == ProfileBottomSheet.REGISTRATION){
            RegistrationActivity(
                context,
                selectedIndex,
                profile,
                accountState,
                coroutineScope,
                bottomSheetChoice
            )
        }
    }
}
