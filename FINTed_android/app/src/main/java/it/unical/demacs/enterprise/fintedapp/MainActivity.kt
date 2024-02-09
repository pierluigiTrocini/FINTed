package it.unical.demacs.enterprise.fintedapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun BottomBar(selectedIndex: MutableState<Index>) {
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
                selected = selectedIndex.value == Index.MAILBOX,
                onClick = { selectedIndex.value = Index.MAILBOX },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.Filled.MailOutline,
                            contentDescription = stringResource(R.string.chat)
                        )
                        Text(stringResource(R.string.chat))
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
        }
    }
}

@Composable
fun Homepage() {
    val coroutineScope = rememberCoroutineScope()

    val postSheetStates = remember { mutableStateMapOf<Long, Boolean>() }
    val offerSheetStates = remember { mutableStateMapOf<Long, OfferInfos>() }

    val selectedIndex = remember { mutableStateOf(Index.HOMEPAGE) }
    val accountState = remember { mutableStateOf(AccountState.NO_ACCOUNT) }

    val postViewModel = remember { mutableStateOf(PostViewModel()) }
    val offerViewModel = remember { mutableStateOf(OfferViewModel()) }
    var userViewModel = remember { mutableStateOf(UserViewModel()) }

    userViewModel.value.getPersonalProfile(3)

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar()
        },
        bottomBar = { BottomBar(selectedIndex = selectedIndex) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            if (selectedIndex.value == Index.HOMEPAGE) {
                HomepageActivity(
                    context,
                    selectedIndex,
                    coroutineScope,
                    postSheetStates = postSheetStates,
                    postViewModel = postViewModel.value,
                    userViewModel = userViewModel.value,
                    offerViewModel = offerViewModel.value
                )
            }
            if (selectedIndex.value == Index.SELL) {
                SellActivity(
                    context = context,
                    selectedIndex = selectedIndex,
                    userViewModel = userViewModel.value,
                    postViewModel = postViewModel.value
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
                    offerSheetStates = offerSheetStates
                )
            }
            if (selectedIndex.value == Index.PERSONAL_PROFILE) {
                PersonalProfileActivity(
                    context = context,
                    selectedIndex = selectedIndex,
                    accountState = accountState,
                    coroutineScope = coroutineScope,
                    userViewModel = userViewModel.value,

                    postViewModel = postViewModel.value,
                    offerViewModel = offerViewModel.value,
                    postSheetStates = postSheetStates,
                    offerSheetStates = offerSheetStates
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    val queryString = remember { mutableStateOf("") }
    val isActive = remember { mutableStateOf(false) }
    LocalContext.current.applicationContext
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isActive.value) 0.dp else 8.dp),
        query = queryString.value,
        onQueryChange = { value -> queryString.value = value },
        placeholder = { Text(text = stringResource(id = R.string.search)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        active = isActive.value,
        onActiveChange = { value ->
            isActive.value = value
        },
        onSearch = {
            isActive.value = false
            // qui la funzione per chiamare la ricerca
        }
    ) {}
}
