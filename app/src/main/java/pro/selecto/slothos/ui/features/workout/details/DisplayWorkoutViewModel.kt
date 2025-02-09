package pro.selecto.slothos.ui.features.workout.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.model.WorkoutDetails
import pro.selecto.slothos.data.services.SetDetailsService
import pro.selecto.slothos.data.services.WorkDetailsService
import pro.selecto.slothos.data.services.WorkoutDetailsService
import pro.selecto.slothos.ui.core.DisplayWorkoutDetails
import pro.selecto.slothos.ui.features.AssistedSavedStateViewModelFactory

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

    private val workoutId = savedStateHandle.toRoute<DisplayWorkoutDetails>().id

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
    val workoutDetails: WorkoutDetails = WorkoutDetails()
)