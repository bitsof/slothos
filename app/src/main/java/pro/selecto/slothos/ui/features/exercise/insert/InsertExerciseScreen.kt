package pro.selecto.slothos.ui.features.exercise.insert

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun InsertExerciseScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    navController: NavHostController,
) {
    val viewModel: InsertExerciseViewModel = viewModel(factory = viewModelFactory)
    val coroutineScope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Add Exercise Screen",
            style = MaterialTheme.typography.headlineSmall
        )
        TextField(
            value = uiState.exerciseName,
            onValueChange = { viewModel.updateExerciseName(it) },
            label = { Text("Exercise Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = uiState.exerciseInstructions,
            onValueChange = { viewModel.updateExerciseInstructions(it) },
            label = { Text("Exercise Instructions") },
            modifier = Modifier.fillMaxWidth()
        )
        MultiSelectDropdown(
            items = viewModel.allCategories.map { it.name },
            selectedItems = uiState.selectedCategories.map { it.name },
            onSelectionChanged = { category -> viewModel.onCategorySelected(category) },
            label = "Select Categories"
        )

        MultiSelectDropdown(
            items = viewModel.allEquipment.map { it.name },
            selectedItems = uiState.selectedEquipment.map { it.name },
            onSelectionChanged = { equipment -> viewModel.onEquipmentSelected(equipment) },
            label = "Select Equipment"
        )
        Button(
            onClick = {
                viewModel.insertExercise()
                navController.popBackStack()
            }
        ) {
            Text(text = "Add Exercise")
        }
    }



}

@Composable
fun MultiSelectDropdown(
    items: List<String>, // List of category/equipment names
    selectedItems: List<String>, // Currently selected items
    onSelectionChanged: (String) -> Unit, // Lambda to handle changes
    label: String // Dropdown label
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        Modifier
            .fillMaxWidth()
            .clickable { expanded = true }) {
        Text(label, Modifier.clickable { expanded = true })

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = { onSelectionChanged(item) },
                    text = {
                        Row(
                            Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = item in selectedItems,
                                onCheckedChange = { onSelectionChanged(item) }
                            )
                            Text(text = item, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                )
            }
        }
    }
}
