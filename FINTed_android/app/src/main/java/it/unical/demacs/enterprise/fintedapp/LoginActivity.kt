package it.unical.demacs.enterprise.fintedapp

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.ui.utility.AppIndex
import it.unical.demacs.enterprise.fintedapp.ui.utility.LoginIndex
import it.unical.demacs.enterprise.fintedapp.viewmodels.AuthViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginActivity(
    authViewModel: MutableState<AuthViewModel>,
    userViewModel: MutableState<UserViewModel>,
    context: Context
) {
    val loginIndex = remember { mutableStateOf(LoginIndex.LOGIN) }

    val username = remember { mutableStateOf("alessioaceto123") }
    val password = remember { mutableStateOf("1234") }

    Scaffold(bottomBar = {
        BottomAppBar {
            NavigationBar {
                NavigationBarItem(selected = loginIndex.value == LoginIndex.LOGIN,
                    onClick = { loginIndex.value = LoginIndex.LOGIN },
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Filled.Person,
                                contentDescription = stringResource(R.string.loginLabel)
                            )
                            Text(stringResource(R.string.loginLabel))
                        }
                    })
                NavigationBarItem(selected = loginIndex.value == LoginIndex.REGISTRATION,
                    onClick = { loginIndex.value = LoginIndex.REGISTRATION },
                    icon = {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                Icons.Filled.Create,
                                contentDescription = stringResource(R.string.registration)
                            )
                            Text(stringResource(R.string.registration))
                        }
                    })
            }
        }
    }) {
        if (loginIndex.value == LoginIndex.LOGIN) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.loginLabel),
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(value = username.value, onValueChange = { v ->
                    username.value = v
                }, label = { Text(stringResource(id = R.string.username)) })
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = password.value,
                    onValueChange = { v -> password.value = v },
                    visualTransformation = PasswordVisualTransformation(),
                    label = { Text(stringResource(id = R.string.registrationPassword)) })
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    authViewModel.value.login(
                        username = username.value, password = password.value,
                        context = context
                    )
                }) {
                    Text(text = stringResource(id = R.string.loginLabel))
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        } else {
            val firstName = remember { mutableStateOf("") }
            val lastName = remember { mutableStateOf("") }

            val email = remember { mutableStateOf("") }

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
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = username.value,
                        onValueChange = { v -> username.value = v },
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
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(value = email.value,
                        onValueChange = { v -> email.value = v },
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
                        })
                }
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = password.value,
                        onValueChange = { v -> password.value = v },
                        label = { androidx.wear.compose.material.Text(stringResource(id = R.string.registrationPassword)) },
                        visualTransformation = PasswordVisualTransformation(),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
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
                Spacer(modifier = Modifier.height(24.dp))
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
                Spacer(modifier = Modifier.height(24.dp))
                Button(onClick = {
                    userViewModel.value.registration(
                        firstName = firstName.value,
                        lastName = lastName.value,
                        username = username.value,
                        credentialsEmail = email.value,
                        credentialsPassword = password.value,
                        addressCity = addressCity.value,
                        addressNumber = addressNumber.value,
                        addressRoute = addressRoute.value
                    )
                }) {
                    androidx.wear.compose.material.Text(text = stringResource(id = R.string.registration))
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }

}