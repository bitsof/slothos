package pro.selecto.slothos.ui.workout

import androidx.compose.foundation.clickable
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
import pro.selecto.slothos.data.ExerciseDetails
import pro.selecto.slothos.data.SetDetails
import pro.selecto.slothos.data.WorkDetails


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSetScreen(
    navController: NavHostController,
    navigateToAddWorkScreen: () -> Unit,
    navigateToSelectExerciseScreen: () -> Unit,
    onSetAdded: (SetDetails) -> Unit,
    viewModelFactory: ViewModelProvider.Factory,
) {
    val viewModel: AddSetViewModel = viewModel(factory = viewModelFactory)
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getStateFlow<ExerciseDetails?>("selectedExercise", null)
            ?.collect { exercise ->
                if (exercise != null) {
                    viewModel.updateSelectedExercise(exercise)
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.remove<ExerciseDetails>("selectedExercise")
                }
            }
    }

    LaunchedEffect(Unit) {
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.getStateFlow<WorkDetails?>("newWorkDetails", null)
            ?.collect { workDetails ->
                if (workDetails != null) {
                    viewModel.addWorkDetails(workDetails)
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.remove<WorkDetails>("newWorkDetails")
                }
            }
    }

    Column(
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            text = "Add Set",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        TextField(
            value = uiState.name,
            onValueChange = { viewModel.updateSetName(it) },
            label = { Text("Set Name")},
            modifier = Modifier.fillMaxWidth(),
        )
        TextField(
            value = uiState.notes,
            onValueChange = { viewModel.updateSetNotes(it) },
            label = { Text("Set Notes") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Selected Exercise: " + uiState.selectedExerciseDetails.exercise.name,
        )

        Button(
            onClick = {
                navigateToSelectExerciseScreen()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Select Exercise")
        }

        Button(
            onClick = navigateToAddWorkScreen,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Work")
        }

        Button(
            onClick = {
                val newSetDetails = viewModel.createSetDetails()
                onSetAdded(newSetDetails)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Set")
        }
    }
}

@Composable
fun WorkList(
    modifier: Modifier,
    workList: List<WorkDetails>?,
    onWorkClick: (WorkDetails) -> Unit,
) {
    Column(modifier = modifier.padding(16.dp)) {
        LazyColumn(modifier = modifier) {
            workList?.let { list ->
                items(list.count()) { index ->
                    val workDetails = list[index]
                    WorkItem(
                        workDetails = workDetails,
                        onWorkClick = onWorkClick,
                    )
                }
            }
        }
    }
}

@Composable
fun WorkItem(
    workDetails: WorkDetails,
    onWorkClick: (WorkDetails) -> Unit, /* Currently unused */
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onWorkClick(workDetails) },
    ) {
        Text(text = workDetails.work.name)
        Text(text = "Value: ${workDetails.work.value} (${workDetails.work.measurementType.symbol})")
    }
}