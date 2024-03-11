package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.Text
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun UpdateFormActivity(
    userViewModel: MutableState<UserViewModel>,
    showUpdateForm: MutableState<Boolean>
) {
    var credentialsEmail = remember { mutableStateOf("") }
    val addressRoute = remember { mutableStateOf("") }
    val addressNumber = remember { mutableStateOf("") }
    val addressCity = remember { mutableStateOf("") }

    Dialog(onDismissRequest = { showUpdateForm.value = false }) {
        Card(modifier = Modifier.padding(10.dp)) {
            Column() {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = credentialsEmail.value,
                        onValueChange = { v -> credentialsEmail.value = v },
                        singleLine = true,
                        label = {
                            Text(
                                text = stringResource(
                                    id = R.string.registrationEmail
                                )
                            )
                        },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text(
                                text = stringResource(
                                    id = R.string.registrationEmailSample
                                )
                            )
                        }
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = addressRoute.value,
                        onValueChange = { v -> addressRoute.value = v },
                        label = { Text(stringResource(R.string.addressRoute)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = addressNumber.value,
                        onValueChange = { v ->
                            if (v.all { it.isDigit() }) {
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
                Button(onClick = {
                    userViewModel.value.updatePersonalProfile(
                        UserPersonalProfileDto(
                            firstName = "",
                            lastName = "",
                            username = userViewModel.value.personalProfile.value.username,
                            credentialsEmail = credentialsEmail.value,
                            addressCity = addressCity.value,
                            addressNumber = addressNumber.value,
                            addressRoute = addressRoute.value
                        )
                    )
                    showUpdateForm.value = false
                }) {
                    Text(text = stringResource(id = R.string.update))
                }
            }
        }

    }

}