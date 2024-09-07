package pro.selecto.slothos.ui.workout

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.ExerciseDetails
import pro.selecto.slothos.data.ExerciseDetailsService
import pro.selecto.slothos.data.WorkoutDetails
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import javax.inject.Inject

class InsertWorkoutViewModel @Inject constructor(
//    private val workoutDetailsService: WorkoutDetailsService,
//    private val exerciseDetailsService: ExerciseDetailsService,
//    private val exerciseRepository: BaseRepository<Exercise>,
//    private val categoryRepository: CategoryRepository,
//    private val equipmentRepository: EquipmentRepository,
//    private val forceRepository: ForceRepository,
//    private val levelRepository: LevelRepository,
//    private val mechanicRepository: MechanicRepository,
//    private val primaryMuscleRepository: PrimaryMuscleRepository,
//    private val secondaryMuscleRepository: SecondaryMuscleRepository,
//    private val tagRepository: TagRepository
) : ViewModel() {
    // LiveData or State for text fields
    var workoutName = mutableStateOf("")
    val workoutNotes = mutableStateOf("")
    var workoutDate = mutableStateOf("") // for date time

    // LiveData or State for drop-down menus
    // add way to store set data

    // List all exercises


    init {

    }


    // Call this function when the user confirms the form
    fun insertWorkout() {
        val workoutDetails = WorkoutDetails(
            //
        )
        viewModelScope.launch {
            workoutDetailsService.insertWorkout(workoutDetails)
            // Handle success or error
        }
    }

    fun updateWorkoutName(it: String) {
        // add code to ensure name ends up unique
        workoutName.value = it
    }

    fun updateWorkoutNotes(it: String) {
        // add code to ensure name ends up unique
        workoutNotes.value = it
    }

//    fun updateWorkoutDate(it: //date time format) {
//        // add code to ensure name ends up unique
//        workoutNotes.value = it
//    }

    // add set to list

    // add set to other set list


}