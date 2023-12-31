package pro.selecto.slothos.ui.exercise

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
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
    private val exerciseId: Int = checkNotNull(savedStateHandle[ExerciseDetailsDestination.exerciseIdArg])
    @Inject
    lateinit var exerciseDetailsService: ExerciseDetailsService
    @Inject
    lateinit var exerciseRepository: BaseRepository<Exercise>

    init {
        DaggerAppComponent.builder().build().inject(this)
    }

    // opts into API that is in a preview state that may change
    // referring to flatMapConcat
    @OptIn(kotlinx.coroutines.FlowPreview::class)
    // Use flow to access the Exercise and map it to ExerciseDetails
    val uiState: StateFlow<ExerciseDetailsUiState?> =
        exerciseRepository.getEntityById(exerciseId)
        .filterNotNull()
        .flatMapConcat { exercise ->
            exerciseDetailsService.getExerciseDetails(exercise)
                .filterNotNull()
                .map { ExerciseDetailsUiState(exerciseDetails = it) }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = null
        )
}

data class ExerciseDetailsUiState(
    val exerciseDetails: ExerciseDetails
)