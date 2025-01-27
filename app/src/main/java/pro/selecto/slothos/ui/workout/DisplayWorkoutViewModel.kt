package pro.selecto.slothos.ui.workout

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.SetDetailsService
import pro.selecto.slothos.data.WorkDetailsService
import pro.selecto.slothos.data.WorkoutDetails
import pro.selecto.slothos.data.WorkoutDetailsService
import pro.selecto.slothos.ui.exercise.AssistedSavedStateViewModelFactory

class DisplayWorkoutViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val workoutDetailsService: WorkoutDetailsService,
    private val setDetailsService: SetDetailsService,
    private val workDetailsService: WorkDetailsService,
) : ViewModel() {
    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<DisplayWorkoutViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): DisplayWorkoutViewModel
    }

    private val workoutId: Int = savedStateHandle["workoutId"] ?: throw IllegalArgumentException("Workout Id is missing")

    private val _uiState = MutableStateFlow<WorkoutDisplayUiState>(WorkoutDisplayUiState())
    val uiState: StateFlow<WorkoutDisplayUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadWorkoutDetails(workoutId = workoutId)
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