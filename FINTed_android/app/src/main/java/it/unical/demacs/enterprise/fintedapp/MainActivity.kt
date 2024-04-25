package it.unical.demacs.enterprise.fintedapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import it.unical.demacs.enterprise.fintedapp.ui.theme.FINTed_androidTheme
import it.unical.demacs.enterprise.fintedapp.ui.utility.AppIndex
import it.unical.demacs.enterprise.fintedapp.viewmodels.AuthValues
import it.unical.demacs.enterprise.fintedapp.viewmodels.AuthViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.OfferViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.PostViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.ReviewViewModel
import it.unical.demacs.enterprise.fintedapp.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FINTed_androidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Homepage()
                }
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Homepage() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val appIndex = remember { mutableStateOf(AppIndex.HOMEPAGE) }

    val authViewModel = remember { mutableStateOf(AuthViewModel(context = context)) }
    val userViewModel = remember { mutableStateOf(UserViewModel(context = context)) }
    val postViewModel = remember { mutableStateOf(PostViewModel(context = context)) }
    val offerViewModel = remember { mutableStateOf(OfferViewModel(context = context)) }
    val reviewViewModel = remember { mutableStateOf(ReviewViewModel(context = context)) }

    if (AuthValues.accessToken.value.accessToken == null) {
        LoginActivity(
            authViewModel = authViewModel,
            userViewModel = userViewModel,
            context = context,
            appIndex = appIndex
        )
    } else {
        Scaffold(
            bottomBar = { BottomBar(appIndex = appIndex) }
        ) {
            Box(modifier = Modifier.padding(it)) {
                if (CurrentIndex.appIndex.value == AppIndex.HOMEPAGE) {
                    HomepageActivity(
                        context = context,
                        appIndex = CurrentIndex.appIndex,
                        userViewModel = userViewModel,
                        postViewModel = postViewModel,
                        offerViewModel = offerViewModel
                    )
                }
                if (CurrentIndex.appIndex.value == AppIndex.SELL) {
                    SellingActivity(
                        userViewModel = userViewModel,
                        postViewModel = postViewModel,
                        appIndex = appIndex
                    )
                }
                if (CurrentIndex.appIndex.value == AppIndex.PROFILE) {
                    ProfileActivity(
                        userViewModel = userViewModel,
                        postViewModel = postViewModel,
                        offerViewModel = offerViewModel,
                        scope = ProfileActivityScope.PERSONAL_PROFILE
                    )
                }
            }
        }
    }

}

@Composable
fun BottomBar(appIndex: MutableState<AppIndex>) {
    BottomAppBar {
        NavigationBar {
            NavigationBarItem(
                selected = CurrentIndex.appIndex.value == AppIndex.HOMEPAGE,
                onClick = { CurrentIndex.appIndex.value = AppIndex.HOMEPAGE },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.home))
                        Text(stringResource(R.string.home))
                    }
                }
            )
            NavigationBarItem(
                selected = CurrentIndex.appIndex.value == AppIndex.SELL,
                onClick = { CurrentIndex.appIndex.value = AppIndex.SELL },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.AddCircle,
                            contentDescription = stringResource(R.string.sell)
                        )
                        Text(stringResource(R.string.sell))
                    }
                }
            )
            NavigationBarItem(
                selected = CurrentIndex.appIndex.value == AppIndex.OFFERS,
                onClick = { CurrentIndex.appIndex.value = AppIndex.OFFERS },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.List,
                            contentDescription = stringResource(R.string.offers)
                        )
                        Text(stringResource(R.string.offers))
                    }
                }
            )
            NavigationBarItem(
                selected = CurrentIndex.appIndex.value == AppIndex.PROFILE,
                onClick = { CurrentIndex.appIndex.value = AppIndex.PROFILE },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.Person,
                            contentDescription = stringResource(R.string.profile)
                        )
                        Text(stringResource(R.string.profile))
                    }
                }
            )
        }
    }
}
