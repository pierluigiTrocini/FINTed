package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel


@Composable
fun LoginActivity(
    userViewModel: MutableState<UserViewModel>
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column() {
        OutlinedTextField(
            label = { androidx.compose.material3.Text(text = stringResource(id = R.string.usernameLabel)) },
            value = username.value,
            onValueChange = { v ->
                username.value = v
            },
            singleLine = true
        )
        OutlinedTextField(
            value = password.value,
            onValueChange = { v -> password.value = v },
            label = { androidx.wear.compose.material.Text(stringResource(id = R.string.registrationPassword)) },
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        androidx.compose.material3.Button(onClick = {
            userViewModel.value.login(username = username.value, password = password.value)
        })
        {
            androidx.wear.compose.material.Text(stringResource(id = R.string.loginLabel))
        }
    }
}