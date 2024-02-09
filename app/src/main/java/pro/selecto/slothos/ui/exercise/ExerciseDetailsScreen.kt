package pro.selecto.slothos.ui.exercise

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.selecto.slothos.R
import pro.selecto.slothos.ui.navigation.NavigationDestination

object ExerciseDetailsDestination : NavigationDestination {
    override val route = "exercise_list"
    override val titleRes = R.string.exercise_details
    const val exerciseIdArg = "exerciseId"
    val routeWithArgs = "$route/{$exerciseIdArg}"
}

@Composable
fun ExerciseDetailsScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: ExerciseDetailsViewModel = viewModel(
        factory = viewModelFactory
    )
/*
    val uiState = viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    ExerciseDetailsBody(
        exerciseDetailsUiState = uiState.value!!,
        modifier = Modifier,
    )*/
}

@Composable
private fun ExerciseDetailsBody(
    exerciseDetailsUiState: ExerciseDetailsUiState,
    modifier: Modifier = Modifier
) {
    Column {
        Text(text = exerciseDetailsUiState.exerciseDetails.exercise.name)
    }
}