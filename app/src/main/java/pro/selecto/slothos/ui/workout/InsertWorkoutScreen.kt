package pro.selecto.slothos.ui.workout

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
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

    Text(
        text = "Add Workout Screen",
        style = MaterialTheme.typography.headlineSmall
    )

    // displays sets sequentially
    // sets of sets have border on left rather than indent
    // each set is clickable to edit info about it in another window
    // can insert new set with plus at top
    // sets are draggable
    // can click on exercises for additional info
    // search function for exercises



}