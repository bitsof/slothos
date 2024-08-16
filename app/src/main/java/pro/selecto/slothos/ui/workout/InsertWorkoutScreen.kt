package pro.selecto.slothos.ui.workout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.selecto.slothos.R
import pro.selecto.slothos.ui.navigation.NavigationDestination

object InsertExerciseDestination : NavigationDestination {
    override val route = "insert_workout"
    override val titleRes = R.string.insert_workout
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertExerciseScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: InsertWorkoutViewModel = viewModel(factory = viewModelFactory)
    val coroutineScope = rememberCoroutineScope()
    val workoutName by viewModel.workoutName
    val workoutNotes by viewModel.workoutNotes

    Text(
        text = "Add Workout Screen",
        style = MaterialTheme.typography.headlineSmall
    )

    // name box
    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = workoutName,
            onValueChange = { viewModel.updateWorkoutName(it) },
            label = { Text("Exercise Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = workoutNotes,
            onValueChange = { viewModel.updateWorkoutNotes(it) },
            label = { Text("") },
            modifier = Modifier.fillMaxWidth()
        )
    }
    // notes box
    // description box
    // displays sets sequentially, in top down format
    // sets of sets have border on left rather than indent
    // each set is clickable to edit info about it in another window
    // can insert new set with plus at top
    // sets are draggable
    // can click on exercises for additional info
    // search function for exercises



}