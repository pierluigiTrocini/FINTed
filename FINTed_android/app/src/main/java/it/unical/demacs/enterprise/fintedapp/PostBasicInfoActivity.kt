package it.unical.demacs.enterprise.fintedapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.models.PostBasicInfoDto
import it.unical.demacs.enterprise.fintedapp.viewmodels.AuthValues
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

@Composable
fun PostBasicInfoActivity(
    post: PostBasicInfoDto,
    postViewModel: MutableState<PostViewModel>,
    userViewModel: MutableState<UserViewModel>,
    offerViewModel: MutableState<OfferViewModel>
) {
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = post.title ?: stringResource(id = R.string.unavailable),
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = stringResource(id = R.string.postStartingPrice) + " : " + post.startingPrice
                    ?: stringResource(id = R.string.unavailable),
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                postViewModel.value.delete(AuthValues.username.value, post.id!!)
                userViewModel.value.getPersonal(AuthValues.username.value)
            }) {
                Text(text = stringResource(id = R.string.deletePost))
            }
        }
    }
}
