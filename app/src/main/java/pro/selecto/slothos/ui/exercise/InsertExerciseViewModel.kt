package pro.selecto.slothos.ui.exercise

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.model.ExerciseDetails
import pro.selecto.slothos.data.services.ExerciseDetailsService
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.repositories.base.BaseRepository
import pro.selecto.slothos.data.repositories.base.CategoryRepository
import pro.selecto.slothos.data.repositories.base.EquipmentRepository
import javax.inject.Inject

class InsertExerciseViewModel @Inject constructor(
    private val exerciseDetailsService: ExerciseDetailsService,
    private val exerciseRepository: BaseRepository<Exercise>,
    private val categoryRepository: CategoryRepository,
    private val equipmentRepository: EquipmentRepository,
//    private val forceRepository: ForceRepository,
//    private val levelRepository: LevelRepository,
//    private val mechanicRepository: MechanicRepository,
//    private val primaryMuscleRepository: PrimaryMuscleRepository,
//    private val secondaryMuscleRepository: SecondaryMuscleRepository,
//    private val tagRepository: TagRepository
) : ViewModel() {
    // LiveData or State for text fields
    var exerciseName = mutableStateOf("")
    val exerciseInstructions = mutableStateOf("")

    // LiveData or State for drop-down menus
    val selectedCategories = mutableStateListOf<Category?>()
    val selectedEquipment = mutableStateListOf<Equipment?>()

    // List of categories and equipment for the drop-down menus
    val allCategories = mutableStateListOf<Category>()
    val allEquipment = mutableStateListOf<Equipment>()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        // Load categories and equipment from the repositories
        viewModelScope.launch {
            launch {
                categoryRepository.getAllEntitiesStream().collect { categoryList ->
                    Log.d("ViewModel", "Loaded equipment: ${categoryList.size}")
                    allCategories.clear()
                    allCategories.addAll(categoryList)
                }
            }

            launch {
                equipmentRepository.getAllEntitiesStream().collect { equipmentList ->
                    Log.d("ViewModel", "Loaded equipment: ${equipmentList.size}")
                    allEquipment.clear()
                    allEquipment.addAll(equipmentList)
                }
            }
        }
    }

    // Call this function when the user confirms the form
    fun insertExercise() {
        val exercise = Exercise(name = exerciseName.value, nameId = exerciseName.value, instructions = exerciseInstructions.value)
        val exerciseDetails = ExerciseDetails(
            exercise = exercise,
            categoryList = selectedCategories.filterNotNull(),
            equipmentList = selectedEquipment.filterNotNull(),
        )
        viewModelScope.launch {
            exerciseDetailsService.insertExercise(exerciseDetails)
            // Handle success or error
        }
    }

    fun updateExerciseName(it: String) {
        // add code to ensure name ends up unique
        exerciseName.value = it
    }

    fun onCategorySelected(categoryName: String) {
        // Logic to add or remove the equipment from selectedEquipment
        val equipment = allCategories.firstOrNull { it.name == categoryName }
        equipment?.let {
            if (it in selectedCategories) {
                selectedCategories.remove(it)
            } else {
                selectedCategories.add(it)
            }
        }
    }

    fun onEquipmentSelected(equipmentName: String) {
        // Logic to add or remove the equipment from selectedEquipment
        val equipment = allEquipment.firstOrNull { it.name == equipmentName }
        equipment?.let {
            if (it in selectedEquipment) {
                selectedEquipment.remove(it)
            } else {
                selectedEquipment.add(it)
            }
        }
    }
}