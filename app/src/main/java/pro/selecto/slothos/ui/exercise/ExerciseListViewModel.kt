package pro.selecto.slothos.ui.exercise

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import pro.selecto.slothos.data.ExerciseDetails
import pro.selecto.slothos.data.ExerciseDetailsService
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.repositories.base.CategoryRepository
import pro.selecto.slothos.data.repositories.base.EquipmentRepository
import javax.inject.Inject

class ExerciseListViewModel @Inject constructor (
    exerciseDetailsService: ExerciseDetailsService,
    categoryRepository: CategoryRepository,
    equipmentRepository: EquipmentRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<ExerciseListUiState?>(null)
    val uiState: StateFlow<ExerciseListUiState?> = _uiState.asStateFlow()

    private val _filterOptions = MutableStateFlow(FilterOptions())
    val filterOptions: StateFlow<FilterOptions> = _filterOptions.asStateFlow()

    init {
        viewModelScope.launch {
            val exercisesFlow = exerciseDetailsService.getAllExerciseDetails().filterNotNull()
            val categoriesFlow = categoryRepository.getAllEntitiesStream().filterNotNull()
            val equipmentFlow = equipmentRepository.getAllEntitiesStream().filterNotNull()

            combine(
                exercisesFlow,
                categoriesFlow,
                equipmentFlow,
                _filterOptions
            ) { exercises, categories, equipment, filterOptions ->
                val filteredExercises = if (filterOptions.category != null) {
                    exercises.filter { exercise ->
                        exercise.categoryList.any { category ->
                            category.id == filterOptions.category
                        }
                    }
                } else {
                    exercises
                }
                ExerciseListUiState(
                    exerciseDetailsList = filteredExercises,
                    categoryList = categories,
                    equipmentList = equipment
                )
            }.collect { combinedUiState ->
                _uiState.value = combinedUiState
            }
        }
    }

    fun updateFilterOptions(newFilterOptions: FilterOptions) {
        _filterOptions.value = newFilterOptions
    }
}

data class ExerciseListUiState(
    val exerciseDetailsList: List<ExerciseDetails>,
    val categoryList: List<Category>,
    val equipmentList: List<Equipment>
)


data class FilterOptions(
    val category: Int? = null,
    val equipment: Int? = null,
    val exerciseInfo: String? = null
)
