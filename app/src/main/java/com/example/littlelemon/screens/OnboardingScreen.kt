package com.example.littlelemon.screens

import android.content.Context
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavHostController
import com.example.littlelemon.R
import com.example.littlelemon.HOME_ROUTE
import com.example.littlelemon.ONBOARDING_ROUTE

@Composable
fun OnboardingScreen(context: Context, navController: NavHostController){
    val  sharedPreferences = context.getSharedPreferences("Little Lemon", Context.MODE_PRIVATE)
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val imeState = rememberImeState()
    val scrollState = rememberScrollState()
    val firstName = remember {
        mutableStateOf("")
    }
    val lastName = remember { 
        mutableStateOf("")
    }
    val email = remember {
        mutableStateOf("")
    }

    LaunchedEffect(key1 = imeState.value){
        if (imeState.value){
            scrollState.scrollTo(scrollState.maxValue)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = screenHeight / 30)
            .verticalScroll(scrollState)
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
                .background(Color(0xFF496E57))
                .padding(top = screenHeight / 30, bottom = screenHeight / 30)
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Let's get to know you",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displaySmall,
                color = Color.White
            )
        }
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
                value = lastName.value,
                onValueChange = {
                    lastName.value = it
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
                value = email.value,
                onValueChange = {
                    email.value = it
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
                    if(validateRegistrationData(firstName.value, lastName.value, email.value)){
                        sharedPreferences.edit()
                            .putString("firstName", firstName.value)
                            .putString("lastName", lastName.value)
                            .putString("email", email.value)
                            .putBoolean("userRegistered", true)
                            .apply()
                        Toast.makeText(context, "Registration Successful", Toast.LENGTH_SHORT).show()
                        navController.navigate(HOME_ROUTE){
                            popUpTo(ONBOARDING_ROUTE){
                                inclusive = true
                            }
                            launchSingleTop = true
                        }
                    }
                    else{
                        Toast.makeText(context, "Invalid Details, please try again", Toast.LENGTH_SHORT).show()
                    }
                },
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4CE14),
                    contentColor = Color(0xFF333333))
            ) {
                Text(
                    text = "Register",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

fun validateRegistrationData(firstName:String, lastName: String, email: String): Boolean{
    var validated = false

    if(firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()){
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
            validated = true
    }

    return validated
}

@Composable
fun rememberImeState(): State<Boolean> {
    val imeState = remember {
        mutableStateOf(false)
    }

    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val isKeyboardOpen = ViewCompat.getRootWindowInsets(view)
                ?.isVisible(WindowInsetsCompat.Type.ime()) ?: true
            imeState.value = isKeyboardOpen
        }

        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }
    return imeState
}