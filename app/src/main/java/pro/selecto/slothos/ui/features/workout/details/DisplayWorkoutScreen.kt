package pro.selecto.slothos.ui.features.workout.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.selecto.slothos.R
import pro.selecto.slothos.ui.features.workout.insert.SetItem
import pro.selecto.slothos.ui.navigation.NavigationDestination

object DisplayWorkoutDestination : NavigationDestination {
    override val route = "display_workout"
    override val titleRes = R.string.display_workout
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayWorkoutScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
) {
    val viewModel: DisplayWorkoutViewModel = viewModel(factory = viewModelFactory)
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()
    
    Column {
        Text(
            text = uiState.workoutDetails.workout.name,
        )
        Text(
            text = uiState.workoutDetails.workout.description,
        )
        Text(
            text = uiState.workoutDetails.workout.date.toString(),
        )
        Text(
            text = uiState.workoutDetails.workout.id.toString(),
        )
        LazyColumn {
            items(uiState.workoutDetails.setDetailsList) { setDetails ->
                SetItem(setDetails = setDetails)
            }
        }
    }
}