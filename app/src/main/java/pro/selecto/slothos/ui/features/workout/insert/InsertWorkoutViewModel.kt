package pro.selecto.slothos.ui.features.workout.insert

import android.os.Parcelable
import android.util.Log
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize
import pro.selecto.slothos.data.entities.Workout
import pro.selecto.slothos.data.model.SetDetails
import pro.selecto.slothos.data.model.WorkoutDetails
import pro.selecto.slothos.data.services.SetDetailsService
import pro.selecto.slothos.data.services.WorkDetailsService
import pro.selecto.slothos.data.services.WorkoutDetailsService
import pro.selecto.slothos.ui.core.InsertWorkout
import pro.selecto.slothos.ui.features.AssistedSavedStateViewModelFactory

class InsertWorkoutViewModel @AssistedInject constructor(
    private val workoutDetailsService: WorkoutDetailsService,
    private val setDetailsService: SetDetailsService,
    private val workDetailsService: WorkDetailsService,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<InsertWorkoutViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): InsertWorkoutViewModel
    }
    val workoutId: Int? = savedStateHandle.toRoute<InsertWorkout>().id

    private val _uiState = MutableStateFlow<InsertWorkoutUiState>(savedStateHandle.get<InsertWorkoutUiState>("uiState") ?: InsertWorkoutUiState())
    val uiState: StateFlow<InsertWorkoutUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            loadInitialWorkout(workoutId)
            _uiState.collect { state ->
                savedStateHandle["uiState"] = state
            }
        }
    }

    private suspend fun loadInitialWorkout(workoutId: Int?) {
        if (workoutId != null) {
            val workoutDetails = workoutDetailsService.getWorkoutDetails(workoutId).first()
            _uiState.update { currentState ->
                currentState.copy(
                    workoutId = workoutId,
                    workoutName = workoutDetails.workout.name,
                    workoutNotes = workoutDetails.workout.notes,
                    workoutDescription = workoutDetails.workout.description,
                    workoutDate = workoutDetails.workout.date,
                    setDetailsList = workoutDetails.setDetailsList,
                )
            }
        }
    }

    // Call this function when the user confirms the form
    fun insertWorkout() {
        viewModelScope.launch {
            val workoutDetails = WorkoutDetails(
                workout= Workout(
                    name = _uiState.value.workoutName,
                    notes = _uiState.value.workoutNotes,
                    description = _uiState.value.workoutDescription,
                    date = _uiState.value.workoutDate,
                ),
                setDetailsList = _uiState.value.setDetailsList,
            )
            Log.v("Logged stuff", "Insert workout");
            workoutDetailsService.insertWorkout(workoutDetails)
        }
    }

    fun addSet(setDetails: SetDetails) {
        _uiState.update { currentState ->
            currentState.copy(setDetailsList = currentState.setDetailsList + setDetails)
        }
    }

    fun removeSet(setDetails: SetDetails) {
        fun removeSet(setDetails: SetDetails) {
            _uiState.update { currentState ->
                currentState.copy(setDetailsList = currentState.setDetailsList - setDetails)
            }
        }
    }

    fun updateWorkoutName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(workoutName = newName)
        }
    }

    fun updateWorkoutNotes(newNotes: String) {
        _uiState.update { currentState ->
            currentState.copy(workoutNotes = newNotes)
        }
    }

    fun updateWorkoutDescription(newDescription: String) {
        _uiState.update { currentState ->
            currentState.copy(workoutDescription = newDescription)
        }
    }

}

@Parcelize
data class InsertWorkoutUiState(
    var workoutName: String = "",
    var workoutNotes: String = "",
    var workoutDescription: String = "",
    var workoutDate: Long = System.currentTimeMillis(),
    var setDetailsList: List<SetDetails> = listOf(),
    var workoutId: Int? = null,
) : Parcelable