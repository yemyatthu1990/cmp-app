package com.yemyatthu.usercentricsappchallenge.presentation.ui.screens.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.usercentrics.sdk.BannerSettings
import com.usercentrics.sdk.ButtonLayout
import com.usercentrics.sdk.ButtonSettings
import com.usercentrics.sdk.ButtonType
import com.usercentrics.sdk.SecondLayerStyleSettings
import com.usercentrics.sdk.UsercentricsBanner
import com.usercentrics.sdk.UsercentricsConsentUserResponse
import com.yemyatthu.usercentricsappchallenge.R
import com.yemyatthu.usercentricsappchallenge.presentation.ui.theme.UsercentricsColor


@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val state by viewModel.uiState.collectAsState()
    var totalCost by remember { mutableIntStateOf(0) }

    when (state) {
        is HomeScreenUiState.ShowConsentBanner -> {
            showConsentBanner(LocalContext.current, onConsentResult = { response ->
                viewModel.onConsentResult(response)
            })
        }

        is HomeScreenUiState.Error -> {
            //Show error message on screen?
            Log.e("HomeScreen", (state as HomeScreenUiState.Error).message)
        }

        is HomeScreenUiState.ShowCost -> {
            totalCost = (state as HomeScreenUiState.ShowCost).totalCost
        }

        is HomeScreenUiState.Initial -> {
            totalCost = 0
        }
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        DisplayCosts(totalCost = totalCost)
        Spacer(modifier = Modifier.weight(1.0f))
        Button(
            onClick = { viewModel.onConsentButtonClicked() },
            modifier = Modifier.padding(24.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                UsercentricsColor
            )
        ) {
            Text(
                stringResource(R.string.label_banner_button),
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 48.dp, vertical = 8.dp)
            )

        }
    }
}

@Composable
fun DisplayCosts(totalCost: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$totalCost", fontSize = 100.sp, modifier = Modifier.padding(16.dp))
        Text(
            stringResource(R.string.label_total_score),
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
    }


}

fun showConsentBanner(
    context: Context,
    onConsentResult: (response: UsercentricsConsentUserResponse?) -> Unit
) {
    try {
        val settings = BannerSettings(
            secondLayerStyleSettings =
            SecondLayerStyleSettings(
                buttonLayout = ButtonLayout.Column(
                    listOf(
                        ButtonSettings(type = ButtonType.ACCEPT_ALL),
                        ButtonSettings(type = ButtonType.DENY_ALL),
                        ButtonSettings(type = ButtonType.SAVE)
                    )
                )
            )
        )
        val banner = UsercentricsBanner(context, settings = settings)
        banner.showSecondLayer { response ->
            onConsentResult(response)
        }
    } catch (e: Exception) {
        Log.e("HomeScreen", e.localizedMessage ?: "Unknown error")
        //Handle error meaningfully
        onConsentResult(null)
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1.0f))
        DisplayCosts(totalCost = 10)
        Spacer(modifier = Modifier.weight(1.0f))
        Button(
            onClick = { },
            modifier = Modifier.padding(24.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                UsercentricsColor
            )
        ) {
            Text(
                stringResource(R.string.label_banner_button),
                fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 48.dp, vertical = 8.dp)
            )
        }

    }
}
