package pro.selecto.slothos.ui.workout

import android.util.Log
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.SetDetails
import pro.selecto.slothos.data.SetDetailsService
import pro.selecto.slothos.data.WorkDetailsService
import pro.selecto.slothos.data.WorkoutDetails
import pro.selecto.slothos.data.WorkoutDetailsService
import pro.selecto.slothos.data.entities.Workout
import javax.inject.Inject

class InsertWorkoutViewModel @Inject constructor(
    private val workoutDetailsService: WorkoutDetailsService,
    private val setDetailsService: SetDetailsService,
    private val workDetailsService: WorkDetailsService,
) : ViewModel() {
    // LiveData or State for text fields
    var workoutName = mutableStateOf("")
    val workoutNotes = mutableStateOf("")
    val workoutDescription = mutableStateOf("")
    var workoutDate = mutableLongStateOf(System.currentTimeMillis()) // for date time
    var sets = mutableStateOf<List<SetDetails>>(listOf())

    init {

    }


    // Call this function when the user confirms the form
    fun insertWorkout() {
        viewModelScope.launch {
            val workoutDetails = WorkoutDetails(
                workout= Workout(
                    name = workoutName.value,
                    description = workoutDescription.value,
                    date = workoutDate.value,
                ),
                setDetailsList = sets.value,
            )
            Log.v("Logged stuff", "Insert workout");
            workoutDetailsService.insertWorkout(workoutDetails)
        }
    }

    fun addSet(setDetails: SetDetails) {
        sets.value += setDetails
    }

    fun removeSet(setDetails: SetDetails) {
        sets.value -= setDetails
    }

    fun updateWorkoutName(it: String) {
        workoutName.value = it
    }

    fun updateWorkoutNotes(it: String) {
        workoutNotes.value = it
    }

    fun updateWorkoutDescription(it: String) {
        workoutDescription.value = it
    }

}