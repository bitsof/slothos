package pro.selecto.slothos.ui.components.workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import pro.selecto.slothos.data.model.WorkoutDetails

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