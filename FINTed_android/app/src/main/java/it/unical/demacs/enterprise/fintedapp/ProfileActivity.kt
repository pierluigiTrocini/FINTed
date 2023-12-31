package it.unical.demacs.enterprise.fintedapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.wear.compose.material.Text

@Composable
fun ProfileActivity() {
    Text(text = stringResource(id = R.string.profile))
}
