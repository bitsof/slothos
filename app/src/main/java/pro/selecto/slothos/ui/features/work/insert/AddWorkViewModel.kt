package pro.selecto.slothos.ui.features.work.insert

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pro.selecto.slothos.data.entities.Work
import pro.selecto.slothos.data.model.StandardMeasurementType
import pro.selecto.slothos.data.model.WorkDetails
import pro.selecto.slothos.data.repositories.interfaces.WorkRepository
import javax.inject.Inject

class AddWorkViewModel @Inject constructor(
    workRepository: WorkRepository,
    //measurementRepository: BaseRepository<Measurement>,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddWorkUiState>(AddWorkUiState())
    val uiState: StateFlow<AddWorkUiState> = _uiState.asStateFlow()

    fun updateValue(value: Double) {
        _uiState.update { currentState ->
            currentState.copy(value = value)
        }
    }

    fun updateName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(name = name)
        }
    }

    fun updateMeasurement(newMeasurement: StandardMeasurementType) {
        _uiState.update { currentState ->
            val currentMeasruement = currentState.selectedMeasurement

            when {
                // When changing category type (e.g. time to RPE), resets to 0
                newMeasurement.categoryType != currentMeasruement.categoryType -> {
                    currentState.copy(
                        value = 0.0,
                        selectedMeasurement = newMeasurement,
                    )
                }
                // When they are the same category (lbs to kgs, or mm to inches), convert them
                else -> {
                    val convertedValue = currentState.value / currentMeasruement.conversionToBase * newMeasurement.conversionToBase

                    currentState.copy(
                        value = convertedValue,
                        selectedMeasurement = newMeasurement,
                    )
                }
            }
        }
    }

    fun createWorkDetails(): WorkDetails {
        val work = Work(
            value = _uiState.value.value,
            name = _uiState.value.name,
            measurementType = _uiState.value.selectedMeasurement,
        )
        return WorkDetails(work = work)
    }
}

data class AddWorkUiState(
    val value: Double = 0.0,
    val name: String = "",
    val selectedMeasurement: StandardMeasurementType = StandardMeasurementType.POUNDS,
)