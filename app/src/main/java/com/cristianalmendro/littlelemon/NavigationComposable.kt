package com.cristianalmendro.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavigation(navController: NavHostController, context: Context, dao: MenuItemDao) {

    val sharedPreferences = context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    val isLogged =
        sharedPreferences.contains("firstname") && sharedPreferences.contains("lastname") && sharedPreferences.contains(
            "email"
        )

    NavHost(
        navController = navController,
        startDestination = if (isLogged) Home.navigationRoute else Onboarding.navigationRoute
    ) {
        composable(Onboarding.navigationRoute) { Onboarding(navController, context) }
        composable(Home.navigationRoute) { Home(navController, context, dao) }
        composable(Profile.navigationRoute) { Profile(navController, context) }
    }
}
