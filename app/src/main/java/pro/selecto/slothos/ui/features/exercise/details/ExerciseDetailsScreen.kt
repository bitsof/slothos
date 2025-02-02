package pro.selecto.slothos.ui.features.exercise.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.selecto.slothos.R
import pro.selecto.slothos.data.model.ExerciseDetails
import pro.selecto.slothos.ui.navigation.NavigationDestination

object ExerciseDetailsDestination : NavigationDestination {
    override val route = "exercise_list"
    override val titleRes = R.string.exercise_details
    const val exerciseidarg = "exerciseId"
    val routeWithArgs = "$route/{$exerciseidarg}"
}

@Composable
fun ExerciseDetailsScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    exerciseId: Int,
) {
    val viewModel: ExerciseDetailsViewModel = viewModel(factory = viewModelFactory)

    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Column {
        Text(text = viewModel.exerciseId.toString(),)
    }

    ExerciseDetailsBody(
        exerciseDetails = uiState?.exerciseDetails,
        modifier = Modifier,
    )
}

@Composable
private fun ExerciseDetailsBody(
    exerciseDetails: ExerciseDetails?,
    modifier: Modifier = Modifier
) {
    Column {
        exerciseDetails?.let {
            Text(text = exerciseDetails.exercise.name)
        }
    }
}