package pro.selecto.slothos.ui.features.work.insert

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import pro.selecto.slothos.data.model.StandardMeasurementType
import pro.selecto.slothos.data.model.WorkDetails
import pro.selecto.slothos.data.entities.Work
import pro.selecto.slothos.data.repositories.interfaces.WorkRepository
import javax.inject.Inject

class AddWorkViewModel @Inject constructor(
    workRepository: WorkRepository,
    //measurementRepository: BaseRepository<Measurement>,
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddWorkUiState>(AddWorkUiState())
    val uiState: StateFlow<AddWorkUiState> = _uiState.asStateFlow()

    fun updateValue(value: Float) {
        _uiState.update { currentState ->
            currentState.copy(value = value)
        }
    }

    fun updateDescription(description: String) {
        _uiState.update { currentState ->
            currentState.copy(description = description)
        }
    }

    fun updateMeasurement(measurement: StandardMeasurementType) {
        _uiState.update { currentState ->
            currentState.copy(selectedMeasurement = measurement)
        }
    }

    fun createWorkDetails(): WorkDetails {
        val work = Work(
            value = _uiState.value.value,
            name = _uiState.value.description,
            measurementType = _uiState.value.selectedMeasurement,
        )
        return WorkDetails(work = work)
    }
}

data class AddWorkUiState(
    val value: Float = 0F,
    val description: String = "",
    val selectedMeasurement: StandardMeasurementType = StandardMeasurementType.POUNDS,
)