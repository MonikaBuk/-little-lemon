package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.ui.theme.Typography

// Profile screen: reads the details saved by Onboarding from SharedPreferences
// and displays them. "Log out" clears the saved details and sends the user
// back to Onboarding.
@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences(ONBOARDING_PREFS, Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString(KEY_FIRST_NAME, "") ?: ""
    val lastName = sharedPreferences.getString(KEY_LAST_NAME, "") ?: ""
    val email = sharedPreferences.getString(KEY_EMAIL, "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Personal information", style = Typography.h1)

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "First name: $firstName", style = Typography.body1)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Last name: $lastName", style = Typography.body1)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Email: $email", style = Typography.body1)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                sharedPreferences.edit().clear().apply()
                navController.navigate(Onboarding.route) {
                    popUpTo(Home.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log out")
        }
    }
}
