package com.cristianalmendro.littlelemon

import android.content.Context
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun Home(navController: NavHostController, context: Context) {

    fun navigateToProfile() {
        navController.navigate(Profile.navigationRoute)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "App logo",
                modifier = Modifier
                    .align(Alignment.Center)
                    .height(40.dp)
                    .width(140.dp)
            )

            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Profile icon",
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .height(40.dp)
                    .width(40.dp)
                    .clickable {
                        navigateToProfile()
                    }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home(
        navController = TODO(),
        context = TODO()
    )
}