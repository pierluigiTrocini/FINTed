package it.unical.demacs.enterprise.fintedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.ui.theme.FINTed_androidTheme
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

@Composable
fun BottomBar(selectedIndex: MutableState<Index>, userViewModel: MutableState<UserViewModel>) {
    BottomAppBar() {
        NavigationBar {
            NavigationBarItem(
                selected = selectedIndex.value == Index.HOMEPAGE,
                onClick = { selectedIndex.value = Index.HOMEPAGE },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Home, contentDescription = stringResource(R.string.home))
                        Text(stringResource(R.string.home))
                    }
                }
            )
            NavigationBarItem(
                selected = selectedIndex.value == Index.SELL,
                onClick = { selectedIndex.value = Index.SELL },
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
                selected = selectedIndex.value == Index.OFFER_LIST,
                onClick = { selectedIndex.value = Index.OFFER_LIST },
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
                selected = selectedIndex.value == Index.PERSONAL_PROFILE,
                onClick = { selectedIndex.value = Index.PERSONAL_PROFILE },
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
            NavigationBarItem(
                selected = selectedIndex.value == Index.REFRESH,
                onClick = {
                    userViewModel.value.personalProfile.value.username?.let {
                        userViewModel.value.getPersonalProfile(
                            it
                        )
                    }
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.Refresh,
                            contentDescription = stringResource(R.string.refresh)
                        )
                        Text(stringResource(R.string.refresh))
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Homepage() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val postSheetStates = remember { mutableStateMapOf<Long, Boolean>() }
    val offerSheetStates = remember { mutableStateMapOf<Long, OfferInfos>() }

    val selectedIndex = remember { mutableStateOf(Index.HOMEPAGE) }
    val accountState = remember { mutableStateOf(AccountState.NO_ACCOUNT) }

    val userViewModel = remember { mutableStateOf(UserViewModel()) }
    val postViewModel = remember { mutableStateOf(PostViewModel(userViewModel = userViewModel)) }
    val offerViewModel = remember { mutableStateOf(OfferViewModel(userViewModel = userViewModel)) }
    val reviewViewModel = remember { mutableStateOf(ReviewViewModel(userViewModel = userViewModel)) }

    DisposableEffect(key1 = userViewModel) {
        onDispose {
            userViewModel.value.logout(context = context)
        }
    }

    if (userViewModel.value.logged.value) {
        Scaffold(
            bottomBar = { BottomBar(selectedIndex = selectedIndex, userViewModel = userViewModel) }
        ) {
            Box(
                modifier = Modifier.padding(it)
            ) {
                if (selectedIndex.value == Index.HOMEPAGE) {
                    HomepageActivity(
                        context,
                        selectedIndex,
                        coroutineScope,
                        postSheetStates = postSheetStates,
                        postViewModel = postViewModel,
                        userViewModel = userViewModel,
                        offerViewModel = offerViewModel,
                    )
                }
                if (selectedIndex.value == Index.SELL) {
                    SellActivity(
                        context = context,
                        selectedIndex = selectedIndex,
                        userViewModel = userViewModel,
                        postViewModel = postViewModel
                    )
                }
                if (selectedIndex.value == Index.OFFER_LIST) {
                    OfferListActivity(
                        context,
                        selectedIndex,
                        coroutineScope = coroutineScope,
                        offerViewModel = offerViewModel,
                        userViewModel = userViewModel,
                        postViewModel = postViewModel,
                        offerSheetStates = offerSheetStates,
                        reviewViewModel = reviewViewModel
                    )
                }
                if (selectedIndex.value == Index.PERSONAL_PROFILE) {
                    PersonalProfileActivity(
                        context = context,
                        selectedIndex = selectedIndex,
                        accountState = accountState,
                        coroutineScope = coroutineScope,
                        userViewModel = userViewModel,

                        postViewModel = postViewModel,
                        offerViewModel = offerViewModel,
                        reviewViewModel = reviewViewModel,

                        postSheetStates = postSheetStates,
                        offerSheetStates = offerSheetStates,
                    )
                }
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.noProfileTitle),
                    style = MaterialTheme.typography.headlineMedium
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                val authFormState = remember { mutableStateOf(AuthFormState.LOGIN) }

                Column() {
                    Row() {
                        Row() {
                            androidx.compose.material3.Button(onClick = {
                                authFormState.value = AuthFormState.LOGIN
                            })
                            {
                                androidx.wear.compose.material.Text(stringResource(id = R.string.loginLabel))
                            }
                            androidx.compose.material3.Button(onClick = {
                                authFormState.value = AuthFormState.REGISTRATION
                            })
                            {
                                androidx.wear.compose.material.Text(stringResource(id = R.string.registration))
                            }
                        }
                    }
                    if (authFormState.value == AuthFormState.LOGIN) {
                        LoginActivity(userViewModel = userViewModel)
                    } else {
                        RegistrationActivity(userViewModel = userViewModel)
                    }

                }
            }
        }
    }
}
