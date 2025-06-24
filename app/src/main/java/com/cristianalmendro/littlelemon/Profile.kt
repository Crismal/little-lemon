package com.cristianalmendro.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cristianalmendro.littlelemon.ui.theme.Yellow

@Composable
fun Profile(navController: NavHostController, context: Context) {
    val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    val firstname = sharedPreferences.getString("firstname", "") ?: ""
    val lastname = sharedPreferences.getString("lastname", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

    fun logOut() {
        sharedPreferences.edit().clear().apply()
        navController.navigate(Onboarding.navigationRoute) {
            popUpTo(Profile.navigationRoute) { inclusive = true }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(90.dp)
                .fillMaxWidth(), contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "App logo",
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth()
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp), verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            Box(
                modifier = Modifier
                    .height(133.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.CenterStart

            ) {
                Text(
                    "Personal Information",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            }
            Text("First name")
            Text(firstname, fontWeight = FontWeight.Bold)
            HorizontalDivider()
            Text("Last name")
            Text(lastname, fontWeight = FontWeight.Bold)
            HorizontalDivider()
            Text("Email address")
            Text(email, fontWeight = FontWeight.Bold)
            HorizontalDivider()

            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    logOut()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
            ) {
                Text("Log Out", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    Profile(
        navController = TODO(),
        context = TODO()
    )
}