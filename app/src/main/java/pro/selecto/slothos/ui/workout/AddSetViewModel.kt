package pro.selecto.slothos.ui.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.model.ExerciseDetails
import pro.selecto.slothos.data.services.ExerciseDetailsService
import pro.selecto.slothos.data.model.SetDetails
import pro.selecto.slothos.data.model.WorkDetails
import pro.selecto.slothos.data.entities.Set
import javax.inject.Inject

class AddSetViewModel @Inject constructor(
    val exerciseDetailsService: ExerciseDetailsService,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddSetUiState>(AddSetUiState())
    val uiState: StateFlow<AddSetUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {

        }
    }

    fun updateSelectedExercise(exerciseDetails: ExerciseDetails) {
        _uiState.update { currentState ->
            currentState.copy(selectedExerciseDetails = exerciseDetails)
        }
    }

    fun updateSetName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(name = newName)
        }
    }

    fun updateSetNotes(newNotes: String) {
        _uiState.update { currentState ->
            currentState.copy(notes = newNotes)
        }
    }

    fun updateSetDate(newDate: Long) {
        _uiState.update { currentState ->
            currentState.copy(date = newDate)
        }
    }

    fun createSetDetails(): SetDetails {
        val set = Set(
            exerciseId = _uiState.value.selectedExerciseDetails.exercise.id,
            name = _uiState.value.name,
            notes = _uiState.value.notes,
        )
        return SetDetails (
            set = set,
            exerciseDetails = _uiState.value.selectedExerciseDetails,
            workDetailsList = _uiState.value.workDetailsList,
            setDetailsList = listOf(),
        )
    }

    fun addWorkDetails(workDetails: WorkDetails) {
        _uiState.update { currentState ->
            currentState.copy(
                workDetailsList = currentState.workDetailsList.plus(workDetails)
            )
        }
    }
}

data class AddSetUiState(
    var selectedExerciseDetails: ExerciseDetails = ExerciseDetails(),
    var name: String = "",
    var notes: String = "",
    var date: Long = 0,
    var workDetailsList: List<WorkDetails> = listOf(),
)