package pro.selecto.slothos.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import pro.selecto.slothos.data.ExerciseDetails
import pro.selecto.slothos.data.ExerciseDetailsService
import javax.inject.Inject

class ExerciseListViewModel @Inject constructor (
    exerciseDetailsService: ExerciseDetailsService
) : ViewModel() {
    val uiState: StateFlow<ExerciseListUiState?> =
        exerciseDetailsService.getAllExerciseDetails()
            .filterNotNull()
            .map { ExerciseListUiState(exerciseDetailsList = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Lazily,
                initialValue = null
            )
}

data class ExerciseListUiState(
    val exerciseDetailsList: List<ExerciseDetails>
)