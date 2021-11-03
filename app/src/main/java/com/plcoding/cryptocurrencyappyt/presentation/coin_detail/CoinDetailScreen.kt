package com.plcoding.cryptocurrencyappyt.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowRow
import com.plcoding.cryptocurrencyappyt.data.remote.dto.TeamMember
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components.CoinTag
import com.plcoding.cryptocurrencyappyt.presentation.coin_detail.components.TeamListItem


@Preview
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let {
            coinDetail ->
            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${coinDetail.rank}. ${coinDetail.name} (${coinDetail.symbol}",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f)
                        )

                        Text(
                            text      = if (coinDetail.isActive) "active" else "inactive",
                            color     = if (coinDetail.isActive) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Red,
                            fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                            textAlign = TextAlign.End,
                            style     = MaterialTheme.typography.body2,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )

                    }
                    
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = coinDetail.description,
                        style = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.h3
                    )

                    FlowRow (
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxWidth()
                    ){
                        coinDetail.tags.forEach { tag ->
                            CoinTag( tag = tag )
                        }
                    }

                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Team Members",
                        style = MaterialTheme.typography.h3
                    )

                    Spacer(modifier = Modifier.height(15.dp))

                }

                items( coinDetail.team ){
                        teamMember: TeamMember ->
                        TeamListItem(
                            teamMember = teamMember,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                    Divider()
                }

            }
        }

        if ( state.error.isNotBlank() ){
           Text(
               text = state.error,
               color = MaterialTheme.colors.error,
               textAlign = TextAlign.Center,
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = 20.dp)
                   .align(Alignment.Center)

           )
        }

        if ( state.isLoading )
        {
            CircularProgressIndicator( modifier = Modifier.align(Alignment.Center) )
        }
    }

}