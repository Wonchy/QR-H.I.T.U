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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.qr_hitu.R
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
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.padding(20.dp))

        Column() {
            Text(stringResource(R.string.MInfBlock)+" "+block, style = style, color = MaterialTheme.colorScheme.onSecondary)
            Spacer(modifier = Modifier.padding(10.dp))
            Text(stringResource(R.string.MInfRoom)+" "+room, style = style, color = MaterialTheme.colorScheme.onSecondary)
            Spacer(modifier = Modifier.padding(10.dp))
            Text(stringResource(R.string.MInfMachine)+" "+machine, style = style, color = MaterialTheme.colorScheme.onSecondary)
        }

        Spacer(modifier = Modifier.padding(20.dp))

        Text(stringResource(R.string.MInfSpecs)+" "+name, style = style, color = MaterialTheme.colorScheme.onSecondary)

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = "$processor",
            onValueChange = {},
            label = { Text(stringResource(R.string.MInfProcessor)) },
            singleLine = true,
            readOnly = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = "$ram",
            onValueChange = {},
            label = { Text(stringResource(R.string.MInfRam)) },
            singleLine = true,
            readOnly = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            value = "$powerSupply",
            onValueChange = {},
            label = { Text(stringResource(R.string.MInfPower)) },
            singleLine = true,
            readOnly = true,
            shape = MaterialTheme.shapes.large,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}