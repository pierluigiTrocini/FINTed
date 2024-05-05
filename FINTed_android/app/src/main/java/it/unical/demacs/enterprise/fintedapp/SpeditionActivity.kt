package it.unical.demacs.enterprise.fintedapp

import android.widget.Space
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import it.unical.demacs.enterprise.fintedapp.models.SpeditionDto

@Composable
fun SpeditionActivity(
    spedition: SpeditionDto,
    scope: SpeditionActivityScope
) {
    Card(
        modifier = Modifier.padding(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (scope == SpeditionActivityScope.POPUP) {
                Text(
                    text = stringResource(id = R.string.showSpeditionCodeTitle),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            Text(text = stringResource(id = R.string.showSpeditionCode))

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = spedition.speditionCode ?: stringResource(id = R.string.unavailable),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Row { Text(text = stringResource(id = R.string.deliveryTo)) }
                Row {
                    Text(
                        text = (spedition.purchaserFirstName ?: stringResource(id = R.string.unavailable)),
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = (spedition.purchaserLastName ?: stringResource(id = R.string.unavailable)),
                        fontWeight = FontWeight.Bold
                    )
                }
                Row {
                    Text(
                        text = stringResource(id = R.string.addressRoute) + " : " + (spedition.purchaserAddressRoute
                            ?: stringResource(id = R.string.unavailable)),
                        fontWeight = FontWeight.Bold
                    )
                }
                Row {
                    Text(
                        text = stringResource(id = R.string.addressNumber) + " : " + (spedition.purchaserAddressNumber
                            ?: stringResource(id = R.string.unavailable)),
                        fontWeight = FontWeight.Bold
                    )
                }
                Row {
                    Text(
                        text = stringResource(id = R.string.addressCity) + " : " + (spedition.purchaserAddressCity
                            ?: stringResource(id = R.string.unavailable)),
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

enum class SpeditionActivityScope {
    POPUP, SPEDITION_LIST
}