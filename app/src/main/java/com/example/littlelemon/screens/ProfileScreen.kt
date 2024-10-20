package com.example.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.littlelemon.HOME_ROUTE
import com.example.littlelemon.ONBOARDING_ROUTE
import com.example.littlelemon.R

@Composable
fun ProfileScreen(context: Context, navController: NavHostController){
    val sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember {
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = screenHeight / 30)
    ){
        Image(
            modifier = Modifier
                .padding(bottom = screenHeight / 30)
                .align(Alignment.CenterHorizontally),
            painter = painterResource(id = R.drawable.littlelemonimgtxt_nobg),
            contentDescription = "Header Logo"
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = screenHeight / 15, start = 16.dp, bottom = screenHeight / 12)
        ){
            Text(
                text = "Personal Information",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(screenHeight / 17)
        ){
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                enabled = false,
                readOnly = true,
                value = firstName.value,
                onValueChange = {
                    firstName.value = it
                },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF496E57),
                    unfocusedBorderColor = Color(0xFF333333),
                    focusedTextColor = Color(0xFF496E57),
                    unfocusedTextColor = Color(0xFF333333),
                    focusedLabelColor = Color(0xFF496E57),
                    unfocusedLabelColor = Color(0xFF333333),
                    cursorColor = Color(0xFF496E57)
                ),
                label = {
                    Text(
                        text = "First Name"
                    )
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                enabled = false,
                readOnly = true,
                value = lastName.value,
                onValueChange = {
                    firstName.value = it
                },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF496E57),
                    unfocusedBorderColor = Color(0xFF333333),
                    focusedTextColor = Color(0xFF496E57),
                    unfocusedTextColor = Color(0xFF333333),
                    focusedLabelColor = Color(0xFF496E57),
                    unfocusedLabelColor = Color(0xFF333333),
                    cursorColor = Color(0xFF496E57)
                ),
                label = {
                    Text(
                        text = "Last Name"
                    )
                }
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                enabled = false,
                readOnly = true,
                value = email.value,
                onValueChange = {
                    firstName.value = it
                },
                singleLine = true,
                shape = RoundedCornerShape(15.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF496E57),
                    unfocusedBorderColor = Color(0xFF333333),
                    focusedTextColor = Color(0xFF496E57),
                    unfocusedTextColor = Color(0xFF333333),
                    focusedLabelColor = Color(0xFF496E57),
                    unfocusedLabelColor = Color(0xFF333333),
                    cursorColor = Color(0xFF496E57)
                ),
                label = {
                    Text(
                        text = "Email"
                    )
                }
            )
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.95f)
                    .padding(top = screenHeight / 25),
                onClick = {
                    sharedPreferences.edit()
                        .clear()
                        .apply()
                    navController.navigate(ONBOARDING_ROUTE){
                        popUpTo(HOME_ROUTE){
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14),
                    contentColor = Color(0xFF333333)
                )
            ) {
                Text(
                    text = "Log Out",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}