package com.example.tendederoapp.vistas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.tendederoapp.R
import com.google.firebase.auth.FirebaseAuth


@Composable
fun LoginScreen(navController: NavController, auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Smart Dry👕",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.mi_logo1),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Campo Correo

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electronico") },
                singleLine = true,
                trailingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Username Icon") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Contrasenia

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "Password Icon") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botón de Login
            OutlinedButton(
                onClick = { validarCredencial(email, password, auth, context, navController) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Ingresar")
            }

            // Olvidar Contrasenia
            TextButton(onClick = { /* Futura olvidar contraseña */ }) {
                Text("Olvidaste tu Contraseña?", color = Color.Blue)
            }



            Spacer(modifier = Modifier.height(30.dp))

            // Login Redes Sociales
            Text("Inicia Sesion con")
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                IconButton(onClick = { /* Futuro login con Facebook */ }) {
                    Image(painter = painterResource(id = R.drawable.face_icon), contentDescription = "Facebook Login", modifier = Modifier.size(40.dp))
                }
                Spacer(modifier = Modifier.width(16.dp))
                IconButton(onClick = { /* Futuro login con X */ }) {
                    Image(painter = painterResource(id = R.drawable.x_icon), contentDescription = "Twitter Login", modifier = Modifier.size(40.dp))
                }
            }
             Spacer(modifier = Modifier.height(16.dp))

            // Botón de registro
            TextButton(onClick = { navController.navigate("Register") }) {
                Text("¿No tienes cuenta? Regístrate")
            }
        }
    }
}

private fun ColumnScope.validarCredencial(
    email: String,
    password: String,
    auth: FirebaseAuth,
    context: Context,
    navController: NavController
) {
    if(email.isNotEmpty() && password.isNotEmpty()) {
        println("No son Vacios")

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(context, "Bienvenido a SmartDry", Toast.LENGTH_SHORT).show()
                navController.navigate("Index")

            } else {
                Toast.makeText(context, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show()
            }
        }


    } else {
        Toast.makeText(context, "Ingresa Credenciales", Toast.LENGTH_SHORT).show()
    }

}
