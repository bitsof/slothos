package pro.selecto.slothos.ui.exercise

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import pro.selecto.slothos.data.ExerciseDetails
import pro.selecto.slothos.data.ExerciseDetailsService
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import pro.selecto.slothos.ui.DaggerAppComponent
import javax.inject.Inject

class ExerciseDetailsViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val exerciseId: Int =
        checkNotNull(savedStateHandle[ExerciseDetailsDestination.exerciseIdArg])

    @Inject
    lateinit var exerciseDetailsService: ExerciseDetailsService

    @Inject
    lateinit var exerciseRepository: BaseRepository<Exercise>

    init {
        DaggerAppComponent.builder().build().inject(this)
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