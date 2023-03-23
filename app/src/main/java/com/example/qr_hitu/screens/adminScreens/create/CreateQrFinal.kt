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
import androidx.navigation.NavController
import com.example.qr_hitu.functions.CreateQR
import com.example.qr_hitu.functions.addDispositivo
import com.example.qr_hitu.functions.downloadQR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrCreateFinal(navController: NavController, viewModel1 : ViewModel1, viewModel2: ViewModel2){

    var content by remember{ mutableStateOf("") }
    var qrName by remember { mutableStateOf("") }
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val myData = viewModel1.myData.value
    val myData2 = viewModel2.myData.value
    val spec = hashMapOf(
        "Nome" to "${myData2?.name}",
        "Processador" to "${myData2?.processor}",
        "Ram" to "${myData2?.ram}",
        "Fonte" to "${myData2?.powerSupply}"
    )


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

        if (myData != null) {
            content = "${myData.block},${myData.room},${myData.machine}"
            addDispositivo(myData.block, myData.room, myData.machine, spec)
        }

        CreateQR(content)

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

        Button(onClick = { downloadQR(content, qrName, context) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Download QR Code")
        }
    }

}