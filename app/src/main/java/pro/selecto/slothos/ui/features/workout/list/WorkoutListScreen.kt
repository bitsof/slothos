package pro.selecto.slothos.ui.features.workout.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.selecto.slothos.R
import pro.selecto.slothos.data.model.WorkoutDetails
import pro.selecto.slothos.ui.components.workout.WorkoutList
import pro.selecto.slothos.ui.features.exercise.list.ListMode
import pro.selecto.slothos.ui.navigation.NavigationDestination

object WorkoutListScreen : NavigationDestination {
    override val route = "workout_list"
    override val titleRes = R.string.workout_list
}

@Composable
fun WorkoutListScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    onWorkoutClick: (WorkoutDetails) -> Unit,
    onAddClick: () -> Unit,
    mode: ListMode = ListMode.VIEW,
) {
    val viewModel: WorkoutListViewModel = viewModel(factory = viewModelFactory)
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()

    WorkoutList(
        modifier = modifier,
        workoutList = uiState.workoutDetailsList,
        onWorkoutClick = onWorkoutClick,
        onAddClick = onAddClick,
        mode = mode,
    )

}

