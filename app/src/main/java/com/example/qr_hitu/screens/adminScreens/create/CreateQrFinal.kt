package com.example.qr_hitu.screens.adminScreens.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.qr_hitu.CreateQR
import com.example.qr_hitu.downloadQR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrCreateFinal(navController: NavController){

    val viewModel1: QrCreate1ViewModel = viewModel()
    val viewModel2: QrCreate2ViewModel = viewModel()
    var qrName by remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .padding(horizontal = 16.dp)
            .background(Color.White)
    ){

        val query = "Block: ${viewModel1.selectedBlock}, " +
                "Room: ${viewModel1.selectedRoom}, " +
                "Machine: ${viewModel1.selectedMachine}," +
                "Name: ${viewModel2.name}, " +
                "Processor: ${viewModel2.processor}, " +
                "Ram: ${viewModel2.ram}, " +
                "Power Supply: ${viewModel2.powerSupply}"


        CreateQR(query)

        Spacer(modifier = Modifier.height(20.dp))
/*
        OutlinedTextField(
            value = content,
            onValueChange = {
                content = it
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next),
            keyboardActions = KeyboardActions(onDone = { focusManager.moveFocus(FocusDirection.Down) }),
            label = { Text(text = "Escreva texto para conversão") },
            placeholder = { Text(text = "Texto a converter") },
        )

 */

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = qrName,
            onValueChange = {
                qrName = it
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            label = { Text(text = "Escreva o nome do ficheiro") },
            placeholder = { Text(text = "Nome") },
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { downloadQR(query, qrName, context) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Download QR Code")
        }
    }

}