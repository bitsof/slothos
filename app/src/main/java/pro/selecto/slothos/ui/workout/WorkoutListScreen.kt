package pro.selecto.slothos.ui.workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.selecto.slothos.R
import pro.selecto.slothos.data.model.WorkoutDetails
import pro.selecto.slothos.ui.exercise.ListMode
import pro.selecto.slothos.ui.exercise.ListScreen
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

@Composable
fun WorkoutList(
    modifier: Modifier,
    workoutList: List<WorkoutDetails>?,
    onWorkoutClick: (WorkoutDetails) -> Unit,
    onAddClick: () -> Unit, // Consider making this return WorkoutDetails in situation from Select to Insert
    mode: ListMode = ListMode.VIEW,
) {
    Column {
        Text(
            text = "Workouts",
            style = MaterialTheme.typography.headlineSmall
        )

        ListScreen(
            modifier = Modifier,
            mode = mode,
            onAddClick = onAddClick,
        )
        { padding ->
            LazyColumn(
                modifier = modifier.padding(padding),
            ) {
                workoutList?.let { list ->
                    items(list.count()) { index ->
                        WorkoutItem(
                            workoutDetails = list[index],
                            onWorkoutClick = onWorkoutClick,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun WorkoutItem(
    workoutDetails: WorkoutDetails,
    onWorkoutClick: (WorkoutDetails) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp)
            .clickable { onWorkoutClick(workoutDetails) },
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = "Workout: ${workoutDetails.workout.name}")
        }
    }
}

