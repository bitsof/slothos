package pro.selecto.slothos.ui.features.exercise.details

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
import pro.selecto.slothos.data.model.ExerciseDetails
import pro.selecto.slothos.data.services.ExerciseDetailsService
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.repositories.base.BaseRepository
import pro.selecto.slothos.ui.features.AssistedSavedStateViewModelFactory

class ExerciseDetailsViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val exerciseDetailsService: ExerciseDetailsService,
    private val exerciseRepository: BaseRepository<Exercise>,
) : ViewModel() {
    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<ExerciseDetailsViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): ExerciseDetailsViewModel
    }

    val exerciseId: Int = savedStateHandle["exerciseId"] ?: throw IllegalArgumentException("Exercise Id is missing")

    private val _uiState = MutableStateFlow<ExerciseDetailsUiState?>(null)
    val uiState: StateFlow<ExerciseDetailsUiState?> = _uiState.asStateFlow()
    init {
        viewModelScope.launch {
            loadExerciseDetails(exerciseId = exerciseId)
        }
    }

    private suspend fun loadExerciseDetails(exerciseId: Int) {
        exerciseDetailsService.getExerciseDetails(exerciseId).collect { exerciseDetails ->
            _uiState.value = ExerciseDetailsUiState(exerciseDetails)

        }
    }

    // opts into API that is in a preview state that may change
    // referring to flatMapConcat
    /*val uiState: StateFlow<ExerciseDetailsUiState?> = exerciseRepository.getEntityById(exerciseId)
        .filterNotNull() // Filters out null Exercise objects, ensuring we only work with non-null Exercises
        .flatMapConcat { exercise ->
            // At this point, exercise is a non-null Exercise object
            exerciseDetailsService.getExerciseDetails(exercise)
                .filterNotNull() // Ensures we only work with non-null ExerciseDetails
                .map { exerciseDetails ->
                    // Map the non-null ExerciseDetails to ExerciseDetailsUiState
                    ExerciseDetailsUiState(exerciseDetails = exerciseDetails)
                }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )*/
}

data class ExerciseDetailsUiState(
    val exerciseDetails: ExerciseDetails
)