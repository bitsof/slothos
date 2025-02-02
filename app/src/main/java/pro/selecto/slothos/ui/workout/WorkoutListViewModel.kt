package pro.selecto.slothos.ui.workout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.model.WorkoutDetails
import pro.selecto.slothos.data.services.WorkoutDetailsService
import javax.inject.Inject

class WorkoutListViewModel @Inject constructor(
    workoutDetailsService: WorkoutDetailsService,
) : ViewModel() {
    private val _uiState = MutableStateFlow<WorkoutListUiState>(WorkoutListUiState())
    val uiState: StateFlow<WorkoutListUiState> = _uiState.asStateFlow()

    init {
        Log.d("WorkoutListViewModel", "Started init")
        viewModelScope.launch {
            Log.d("WorkoutListViewModel", "Scoped launch")
            workoutDetailsService.getAllWorkoutDetails()
                .filterNotNull()
                .collect { workoutDetailsList ->
                    Log.d("WorkoutListViewModel", "Fetched workout details: $workoutDetailsList")
                    _uiState.update { currentState ->
                        currentState.copy(workoutDetailsList = workoutDetailsList)
                    }
                }
        }
    }
}

data class WorkoutListUiState(
    val workoutDetailsList: List<WorkoutDetails> = listOf(),
)