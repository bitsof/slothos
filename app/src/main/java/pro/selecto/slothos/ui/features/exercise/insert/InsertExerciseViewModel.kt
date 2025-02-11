package pro.selecto.slothos.ui.features.exercise.insert

import android.os.Parcelable
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
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
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.model.ExerciseDetails
import pro.selecto.slothos.data.repositories.base.BaseRepository
import pro.selecto.slothos.data.repositories.base.CategoryRepository
import pro.selecto.slothos.data.repositories.base.EquipmentRepository
import pro.selecto.slothos.data.services.ExerciseDetailsService
import pro.selecto.slothos.ui.core.InsertExercise
import pro.selecto.slothos.ui.features.AssistedSavedStateViewModelFactory

class InsertExerciseViewModel @AssistedInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
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
    @AssistedFactory
    interface Factory : AssistedSavedStateViewModelFactory<InsertExerciseViewModel> {
        override fun create(savedStateHandle: SavedStateHandle): InsertExerciseViewModel
    }
    val exerciseId: Int? = savedStateHandle.toRoute<InsertExercise>().id

    private val _uiState = MutableStateFlow<InsertExerciseUiState>(savedStateHandle.get<InsertExerciseUiState>("uiState") ?: InsertExerciseUiState())
    val uiState: StateFlow<InsertExerciseUiState> = _uiState.asStateFlow()

    // List of categories and equipment for the drop-down menus
    val allCategories = mutableStateListOf<Category>()
    val allEquipment = mutableStateListOf<Equipment>()

    init {
        Log.d("InsertExerciseViewModel", "Launching viewmodel scope")
        viewModelScope.launch {
            loadInitialData(exerciseId)
            _uiState.collect { state ->
                savedStateHandle["uiState"] = state
            }
        }
    }

    private suspend fun loadInitialData(exerciseId: Int?) {
        // If there is an id, set it's values to that
        if (exerciseId != null) {
            val exerciseDetails = exerciseDetailsService.getExerciseDetails(exerciseId).first()
            _uiState.update { currentState ->
                currentState.copy(
                    exerciseId = exerciseId,
                    exerciseName = exerciseDetails.exercise.name,
                    exerciseInstructions = exerciseDetails.exercise.instructions,
                    selectedCategories = exerciseDetails.categoryList,
                    selectedEquipment = exerciseDetails.equipmentList,
                )
            }
        }
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
        val exercise = Exercise(
            name = _uiState.value.exerciseName,
            nameId = _uiState.value.exerciseName,
            instructions = _uiState.value.exerciseInstructions,
        )
        val exerciseDetails = ExerciseDetails(
            exercise = exercise,
            categoryList = _uiState.value.selectedCategories,
            equipmentList = _uiState.value.selectedEquipment,
        )
        viewModelScope.launch {
            exerciseDetailsService.insertExercise(exerciseDetails)
            // Handle success or error
        }
    }

    fun updateExerciseName(newName: String) {
        // add code to ensure name ends up unique
        _uiState.update { currentState ->
            currentState.copy(exerciseName = newName)
        }
    }

    fun updateExerciseInstructions(newInstructions: String) {
        _uiState.update { currentState ->
            currentState.copy(exerciseInstructions = newInstructions)
        }
    }

    fun onCategorySelected(categoryName: String) {
        // Logic to add or remove the equipment from selectedEquipment
        val category: Category? = allCategories.firstOrNull { it.name == categoryName }
        category?.let {
            if (category in _uiState.value.selectedCategories) {
                _uiState.update { currentState ->
                    currentState.copy(selectedCategories = currentState.selectedCategories - category)
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(selectedCategories = currentState.selectedCategories + category)
                }
            }
        }
    }

    fun onEquipmentSelected(equipmentName: String) {
        // Logic to add or remove the equipment from selectedEquipment
        val equipment: Equipment? = allEquipment.firstOrNull { it.name == equipmentName }
        equipment?.let {
            if (equipment in _uiState.value.selectedEquipment) {
                _uiState.update { currentState ->
                    currentState.copy(selectedEquipment = currentState.selectedEquipment - equipment)
                }
            } else {
                _uiState.update { currentState ->
                    currentState.copy(selectedEquipment = currentState.selectedEquipment + equipment)
                }
            }
        }
    }
}

@Parcelize
data class InsertExerciseUiState(
    var exerciseId: Int? = null,
    var exerciseName: String = "",
    var exerciseInstructions: String = "",
    var selectedCategories: List<Category> = listOf(),
    var selectedEquipment: List<Equipment> = listOf(),
) : Parcelable