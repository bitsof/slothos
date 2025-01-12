package pro.selecto.slothos.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.SetDetailsService
import pro.selecto.slothos.data.WorkDetailsService
import pro.selecto.slothos.data.WorkoutDetails
import pro.selecto.slothos.data.WorkoutDetailsService
import javax.inject.Inject

class DisplayWorkoutViewModel @Inject constructor(
    private val workoutDetailsService: WorkoutDetailsService,
    private val setDetailsService: SetDetailsService,
    private val workDetailsService: WorkDetailsService,
) : ViewModel() {
    private val _uiState = MutableStateFlow<WorkoutDisplayUiState?>(null)
    val uiState: StateFlow<WorkoutDisplayUiState?> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadWorkoutDetails(workoutId = 1)
        }
    }

    private suspend fun loadWorkoutDetails(workoutId: Int) {
        workoutDetailsService.getWorkoutDetails(workoutId).collect { workoutDetails ->
            _uiState.value = WorkoutDisplayUiState(workoutDetails = workoutDetails)
        }
    }
}

data class WorkoutDisplayUiState(
    val workoutDetails: WorkoutDetails
)