package pro.selecto.slothos.ui.features.workout.insert

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import pro.selecto.slothos.R
import pro.selecto.slothos.data.model.SetDetails
import pro.selecto.slothos.ui.navigation.NavigationDestination

object InsertWorkoutDestination : NavigationDestination {
    override val route = "insert_workout"
    override val titleRes = R.string.insert_workout
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertWorkoutScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    navigateToAddSetScreen: () -> Unit
) {
    val viewModel: InsertWorkoutViewModel = viewModel(factory = viewModelFactory)
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getStateFlow<SetDetails?>("newSetDetails", null)
            ?.collect { setDetails ->
                if ( setDetails != null) {
                    viewModel.addSet(setDetails)
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.remove<SetDetails>("newSetDetails")
                }
            }
    }
    
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Add Workout Screen",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        TextField(
            value = uiState.workoutName,
            onValueChange = { viewModel.updateWorkoutName(it) },
            label = { Text("Workout Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = uiState.workoutNotes,
            onValueChange = { viewModel.updateWorkoutNotes(it) },
            label = { Text("Workout Notes") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = uiState.workoutDescription,
            onValueChange = { viewModel.updateWorkoutDescription(it) },
            label = { Text("Workout Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Sets",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column {
            LazyColumn(modifier = modifier) {
                uiState.setDetailsList.let { list ->
                    items(list.count()) { index ->
                        val setDetails = list[index]
                        SetItem(setDetails = setDetails)
                    }
                }
            }
            Button(onClick = navigateToAddSetScreen) {
                Text("Add Set")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                viewModel.insertWorkout()
                navController.popBackStack()
            }) {
            Text("Save Workout")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetItem(setDetails: SetDetails) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = { /* Navigate to edit set screen eventually */ }
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = "Set: ${setDetails.set.name}")
            Text(text = "Notes: ${setDetails.set.notes}")
        }
    }
}