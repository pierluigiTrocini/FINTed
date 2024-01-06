package it.unical.demacs.enterprise.fintedapp

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Text
import it.unical.demacs.enterprise.fintedapp.models.UserPersonalProfileDto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginActivity(
    context: Context,
    selectedIndex: MutableState<Index>,
    profile: UserPersonalProfileDto?,
    accountState: MutableState<AccountState>,
    coroutineScope: CoroutineScope,
    bottomSheetChoice: MutableState<ProfileBottomSheet>
) {
    val text = remember { mutableStateOf("") }

    ModalBottomSheet(onDismissRequest = { bottomSheetChoice.value = ProfileBottomSheet.NONE }) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                Button(onClick = {
                    coroutineScope.launch {
                        bottomSheetChoice.value = ProfileBottomSheet.NONE
                        makeToast(context, context.resources.getString(R.string.reviewPublishedToast))
                    }
                }) {
                    Text(stringResource(id = R.string.publish))
                }
            }
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}

private fun makeToast(context: Context, string: String){
    Toast.makeText(context, string, Toast.LENGTH_LONG).show()
}