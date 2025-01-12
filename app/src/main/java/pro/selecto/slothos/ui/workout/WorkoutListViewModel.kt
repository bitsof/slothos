package pro.selecto.slothos.ui.workout

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.WorkoutDetails
import pro.selecto.slothos.data.WorkoutDetailsService
import javax.inject.Inject

class WorkoutListViewModel @Inject constructor(
    workoutDetailsService: WorkoutDetailsService,
) : ViewModel() {
    private val _uiState = MutableStateFlow<WorkoutListUiState?>(null)
    val uiState: StateFlow<WorkoutListUiState?> = _uiState.asStateFlow()

    //filter options

    init {
        Log.d("WorkoutListViewModel", "Started init")
        viewModelScope.launch {
            Log.d("WorkoutListViewModel", "Scoped launch")
            workoutDetailsService.getAllWorkoutDetails()
                .filterNotNull()
                .collect { workoutDetailsList ->
                    Log.d("WorkoutListViewModel", "Fetched workout details: $workoutDetailsList")
                    _uiState.value = WorkoutListUiState(workoutDetailsList = workoutDetailsList)
                }
        }
    }
}

data class WorkoutListUiState(
    val workoutDetailsList: List<WorkoutDetails>
)