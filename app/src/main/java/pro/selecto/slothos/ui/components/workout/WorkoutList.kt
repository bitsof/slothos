package pro.selecto.slothos.ui.components.workout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import pro.selecto.slothos.data.model.WorkoutDetails
import pro.selecto.slothos.ui.common.lists.ListScreen
import pro.selecto.slothos.ui.features.exercise.list.ListMode

@Composable
fun WorkoutList(
    modifier: Modifier,
    workoutDetailsList: List<WorkoutDetails>?,
    onWorkoutClick: (WorkoutDetails) -> Unit,
    onAddClick: () -> Unit, // Consider making this return WorkoutDetails in situation from Select to Insert
    mode: ListMode = ListMode.VIEW,
    onEditClick: (WorkoutDetails) -> Unit,
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
                workoutDetailsList?.let { workoutDetailsList ->
                    items(
                        workoutDetailsList,
                        key = { workoutDetails ->
                            workoutDetails.workout.id
                        }
                    ) { workoutDetails ->
                        WorkoutItem(
                            workoutDetails = workoutDetails,
                            onWorkoutClick = onWorkoutClick,
                            onEditClick = onEditClick,
                        )
                    }
                }
            }
        }
    }
}