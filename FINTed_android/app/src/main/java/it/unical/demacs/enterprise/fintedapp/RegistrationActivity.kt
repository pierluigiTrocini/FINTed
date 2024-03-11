package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel


@Composable
fun RegistrationActivity(
    userViewModel: MutableState<UserViewModel>
) {
    val firstName = remember { mutableStateOf("") }
    val lastName = remember { mutableStateOf("") }
    val userName = remember { mutableStateOf("") }
    var credentialsEmail = remember { mutableStateOf("") }
    val credentialsPassword = remember { mutableStateOf("") }
    val addressRoute = remember { mutableStateOf("") }
    val addressNumber = remember { mutableStateOf("") }
    val addressCity = remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        androidx.wear.compose.material.Text(
            stringResource(id = R.string.registrationTitle),
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = firstName.value,
                onValueChange = { v -> firstName.value = v },
                singleLine = true,
                label = {
                    androidx.wear.compose.material.Text(
                        text = stringResource(
                            id = R.string.registrationFName
                        )
                    )
                },
                modifier = Modifier.weight(1f),
            )
            OutlinedTextField(
                value = lastName.value,
                onValueChange = { v -> lastName.value = v },
                singleLine = true,
                label = {
                    androidx.wear.compose.material.Text(
                        text = stringResource(
                            id = R.string.registrationLName
                        )
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = userName.value,
                onValueChange = { v -> userName.value = v },
                singleLine = true,
                label = {
                    androidx.wear.compose.material.Text(
                        text = stringResource(
                            id = R.string.registrationUsername
                        )
                    )
                },
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = credentialsEmail.value,
                onValueChange = { v -> credentialsEmail.value = v },
                singleLine = true,
                label = {
                    androidx.wear.compose.material.Text(
                        text = stringResource(
                            id = R.string.registrationEmail
                        )
                    )
                },
                modifier = Modifier.weight(1f),
                placeholder = {
                    androidx.wear.compose.material.Text(
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
                value = credentialsPassword.value,
                onValueChange = { v -> credentialsPassword.value = v },
                label = { androidx.wear.compose.material.Text(stringResource(id = R.string.registrationPassword)) },
                visualTransformation = PasswordVisualTransformation(),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = addressRoute.value,
                onValueChange = { v -> addressRoute.value = v },
                label = { androidx.wear.compose.material.Text(stringResource(R.string.addressRoute)) },
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
                label = { androidx.wear.compose.material.Text(stringResource(R.string.addressNumber)) },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = addressCity.value,
                onValueChange = { v -> addressCity.value = v },
                label = { androidx.wear.compose.material.Text(stringResource(R.string.addressCity)) },
                singleLine = true,
                modifier = Modifier.weight(2f)
            )
        }
        Button(onClick = {
            userViewModel.value.registration(
                firstName = firstName.value,
                lastName = lastName.value,
                username = userName.value,
                credentialsEmail = credentialsEmail.value,
                credentialsPassword = credentialsPassword.value,
                addressCity = addressCity.value,
                addressNumber = addressNumber.value,
                addressRoute = addressRoute.value
            )
        }) {
            androidx.wear.compose.material.Text(text = stringResource(id = R.string.registration))
        }
    }

}