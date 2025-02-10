package pro.selecto.slothos.ui.features.work.insert

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pro.selecto.slothos.data.model.StandardMeasurementType
import pro.selecto.slothos.data.model.WorkDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddWorkScreen(
    navController: NavHostController,
    onWorkAdded: (WorkDetails) -> Unit,
    viewModelFactory: ViewModelProvider.Factory,
) {
    val viewModel: AddWorkViewModel = viewModel(factory = viewModelFactory)
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()

    var showMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            text = "Add Work Screen",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        TextField(
            value = uiState.name,
            onValueChange = { viewModel.updateName(it) },
            label = { Text(text = "Description")},
            modifier = Modifier,
        )
        TextField(
            value = uiState.value.toString(),
            onValueChange = { newValue: String ->
                val filteredValue = newValue.filter { it.isDigit() || it == '.' }
                if (filteredValue.count { it == '.' } <= 1) {
                    filteredValue.toDoubleOrNull()?.let { doubleValue ->
                        viewModel.updateValue(doubleValue)
                    }
                }
            },
            label = { Text("Value")},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier,
        )
        Text(
            text = "Selected Measurement: ${uiState.selectedMeasurement.name} (${uiState.selectedMeasurement.symbol})"
        )
        Box() {
            Button(
                onClick = { showMenu = true }
            ) {
                Text("Select Measurement Type")
            }
            SelectMeasurementTypeMenu(
                expanded = showMenu,
                onMeasurementSelected = { index ->
                    val selectedType = StandardMeasurementType.entries[index]
                    viewModel.updateMeasurement(selectedType)
                },
                onDismissRequest = {
                    showMenu = false
                }
            )
        }
        Button(
            onClick = {
                val newWorkDetails = viewModel.createWorkDetails()
                onWorkAdded(newWorkDetails)
            },
            modifier = Modifier,
        ) {
            Text("Add Work")
        }
    }


}

@Composable
fun SelectMeasurementTypeMenu(
    expanded: Boolean,
    onMeasurementSelected: (Int) -> Unit,
    onDismissRequest: () -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
    ) {
        StandardMeasurementType.entries.forEachIndexed { index, measurementType ->
            DropdownMenuItem(
                text = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(measurementType.name)
                        Text(
                            text = measurementType.symbol,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                onClick = {
                    onMeasurementSelected(index)
                    onDismissRequest()
                },
                modifier = Modifier.fillMaxWidth(),
            )
        }

    }
}