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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.ui.theme.FINTed_androidTheme

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
                    Homepage();
                }
            }
        }
    }
}

@Composable
fun BottomBar( selectedIndex: MutableState<Index> ){
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
                        Icon(Icons.Filled.AddCircle, contentDescription = stringResource(R.string.sell))
                        Text(stringResource(R.string.sell))
                    }
                }
            )
            NavigationBarItem(
                selected = selectedIndex.value == Index.MAILBOX,
                onClick = { selectedIndex.value = Index.MAILBOX },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.MailOutline, contentDescription = stringResource(R.string.chat))
                        Text(stringResource(R.string.chat))
                    }
                }
            )
            NavigationBarItem(
                selected = selectedIndex.value == Index.OFFERLIST,
                onClick = { selectedIndex.value = Index.OFFERLIST },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.List, contentDescription = stringResource(R.string.offers))
                        Text(stringResource(R.string.offers))
                    }
                }
            )
            NavigationBarItem(
                selected = selectedIndex.value == Index.PERSONALPROFILE,
                onClick = { selectedIndex.value = Index.PERSONALPROFILE },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Person, contentDescription = stringResource(R.string.profile))
                        Text(stringResource(R.string.profile))
                    }
                }
            )
            }
        }
    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Homepage(){
    val selectedIndex = remember { mutableStateOf(Index.PERSONALPROFILE) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    var text = remember { mutableStateOf("") }

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopBar( selectedIndex );
        },
        bottomBar = { BottomBar(selectedIndex = selectedIndex) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            if(selectedIndex.value == Index.HOMEPAGE){
                HomepageActivity(context, selectedIndex)
            }
            if(selectedIndex.value == Index.SELL){
                SellActivity(context, selectedIndex)
            }
            if(selectedIndex.value == Index.MAILBOX){
                MailBoxActivity(context, selectedIndex)
            }
            if(selectedIndex.value == Index.OFFERLIST){
                OfferListActivity(context, selectedIndex)
            }
            if(selectedIndex.value == Index.PERSONALPROFILE){
                PersonalProfileActivity(context, selectedIndex, null)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(selectedIndex: MutableState<Index>) {
    var queryString = remember { mutableStateOf("") }
    var isActive = remember { mutableStateOf(false) }
    val contextForToast = LocalContext.current.applicationContext
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isActive.value) 0.dp else 8.dp),
        query = queryString.value,
        onQueryChange = { value -> queryString.value = value },
        placeholder = { Text(text = stringResource(id = R.string.search)) },
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = null) },
        active = isActive.value,
        onActiveChange = {
            value -> isActive.value = value
        },
        onSearch = {
            isActive.value = false
            // qui la funzione per chiamare la ricerca
        }
    ){}
}
