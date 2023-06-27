package pro.selecto.slothos.ui.exercise

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import pro.selecto.slothos.data.repositories.interfaces.ExerciseRepository

class ExerciseDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val exerciseRepository: ExerciseRepository
) : ViewModel() {

    private val itemId: Int = checkNotNull(savedStateHandle[ExerciseDetailsDestination.exerciseIdArg])

}

data class ExerciseDetails(
    val id: Int = 0,
)

data class ExerciseDetailsUiState(
    val exerciseDetails: ExerciseDetails = ExerciseDetails()
)