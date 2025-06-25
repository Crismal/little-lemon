package com.cristianalmendro.littlelemon

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.cristianalmendro.littlelemon.ui.theme.Green
import com.cristianalmendro.littlelemon.ui.theme.Yellow

@Composable
fun Onboarding(navController: NavHostController, context: Context) {
    var firstname by remember {
        mutableStateOf("")
    }

    var lastname by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    fun formValidation() {
        if (firstname.isBlank() || lastname.isBlank() || email.isBlank())
            Toast.makeText(
                context,
                "Registration unsuccessful. Please enter all data.",
                Toast.LENGTH_SHORT
            ).show()
        else {

            val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
            with(
                sharedPreferences.edit()
            ) {
                putString("firstname", firstname)
                putString("lastname", lastname)
                putString("email", email)
                apply()
            }

            Toast.makeText(
                context,
                "Registration successful!",
                Toast.LENGTH_SHORT
            ).show()

            navController.navigate(Home.navigationRoute) {
                popUpTo(Onboarding.navigationRoute) { inclusive = true }
            }
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
        Box(
            modifier = Modifier
                .background(Green)
                .height(133.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                "Let's go to know you",
                color = Color.White, fontSize = 30.sp
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
            TextField(
                firstname,
                { firstname = it },
                label = { Text("First name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextField(
                lastname,
                { lastname = it },
                label = { Text("Last name") },
                modifier = Modifier
                    .fillMaxWidth()
            )
            TextField(
                email,
                { email = it },
                label = { Text("Email address") },
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    formValidation()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Yellow,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(5.dp)
            ) {
                Text("Register", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview() {
    Onboarding(
        navController = TODO(),
        context = TODO()
    )
}