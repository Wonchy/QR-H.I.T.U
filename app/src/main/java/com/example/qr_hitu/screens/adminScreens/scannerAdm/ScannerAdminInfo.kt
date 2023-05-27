package com.example.qr_hitu.screens.adminScreens.scannerAdm

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.qr_hitu.ViewModels.ScannerViewModel
import com.example.qr_hitu.functions.seeDispositivo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScannerAdminInfo(navController: NavController, viewModel: ScannerViewModel) {

    val (block, room, machine) = viewModel.myData.value.toString().split(",")
    val spec = seeDispositivo(block, room, machine)
    val focusManager = LocalFocusManager.current
    val style = MaterialTheme.typography.titleMedium
    val name = spec["Nome"]
    val processor = spec["Processador"]
    val ram = spec["Ram"]
    val powerSupply = spec["Fonte"]



    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .padding(horizontal = 16.dp)
            .background(Color.White)
    ) {

        Spacer(modifier = Modifier.padding(30.dp))

        Column() {
            Text("Bloco: $block ", style = style)
            Spacer(modifier = Modifier.padding(10.dp))
            Text("Sala: $room ", style = style)
            Spacer(modifier = Modifier.padding(10.dp))
            Text("Máquina: $machine", style = style)
        }

        Spacer(modifier = Modifier.padding(30.dp))

        Text("Especificações: $name", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = "$processor",
            onValueChange = {},
            label = { Text("Processador:") },
            singleLine = true,
            readOnly = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = "$ram",
            onValueChange = {},
            label = { Text("Ram:") },
            singleLine = true,
            readOnly = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = "$powerSupply",
            onValueChange = {},
            placeholder = { Text("Fonte:") },
            singleLine = true,
            readOnly = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}