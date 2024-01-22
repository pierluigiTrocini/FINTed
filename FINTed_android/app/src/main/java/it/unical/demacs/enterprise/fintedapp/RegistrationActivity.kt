package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import kotlinx.coroutines.CoroutineScope


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationActivity(
    context: Context,
    selectedIndex: MutableState<Index>,
    profile: UserPersonalProfileDto?,
    accountState: MutableState<AccountState>,
    coroutineScope: CoroutineScope,
    bottomSheetChoice: MutableState<ProfileBottomSheet>
) {
    val firstName = remember { mutableStateOf("") }
    val lastName  = remember { mutableStateOf("") }
    val userName  = remember { mutableStateOf("") }
    var credentialsEmail  = remember { mutableStateOf("") }
    val credentialsPassword  = remember { mutableStateOf("") }
    val addressRoute  = remember { mutableStateOf("") }
    val addressNumber  = remember { mutableStateOf("") }
    val addressCity = remember { mutableStateOf("") }

    Dialog(onDismissRequest = { bottomSheetChoice.value = ProfileBottomSheet.NONE }) {
        Card(modifier = Modifier.padding(5.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.registrationTitle), style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                 horizontalArrangement = Arrangement.SpaceEvenly){
                    OutlinedTextField(
                        value = firstName.value,
                        onValueChange = { v -> firstName.value = v },
                        singleLine = true,
                        label = { Text(text = stringResource(id = R.string.registrationFName)) },
                        modifier = Modifier.weight(1f),
                    )
                    OutlinedTextField(
                        value = lastName.value,
                        onValueChange = { v -> lastName.value = v },
                        singleLine = true,
                        label = { Text(text = stringResource(id = R.string.registrationLName)) },
                        modifier = Modifier.weight(1f))
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                    OutlinedTextField(
                        value = userName.value,
                        onValueChange = { v -> userName.value = v },
                        singleLine = true,
                        label = { Text(text = stringResource(id = R.string.registrationUsername)) },
                        modifier = Modifier.weight(1f))
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                    OutlinedTextField(
                        value = credentialsEmail.value,
                        onValueChange = { v -> credentialsEmail.value = v },
                        singleLine = true,
                        label = { Text(text = stringResource(id = R.string.registrationEmail)) },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text(text = stringResource(id = R.string.registrationEmailSample)) }
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                    OutlinedTextField(
                        value = credentialsPassword.value,
                        onValueChange = { v -> credentialsPassword.value = v },
                        label = { Text(stringResource(id = R.string.registrationPassword)) },
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                    OutlinedTextField(
                        value = addressRoute.value,
                        onValueChange = { v -> addressRoute.value = v },
                        label = { Text(stringResource(R.string.addressRoute)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly){
                    OutlinedTextField(
                        value = addressNumber.value,
                        onValueChange = {
                                        v ->
                            if(v.all{ it.isDigit() }){
                                addressNumber.value = v
                            }
                        },
                        label = { Text(stringResource(R.string.addressNumber)) },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = addressCity.value,
                        onValueChange = { v -> addressCity.value = v },
                        label = { Text(stringResource(R.string.addressCity)) },
                        singleLine = true,
                        modifier = Modifier.weight(2f)
                    )
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.registration))
                }

            }
        }
    }
}


private fun makeToast(context: Context, string: String) {
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}