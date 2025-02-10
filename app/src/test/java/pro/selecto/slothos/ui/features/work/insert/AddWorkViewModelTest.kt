package pro.selecto.slothos.ui.features.work.insert

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import pro.selecto.slothos.data.model.StandardMeasurementType
import pro.selecto.slothos.data.repositories.interfaces.WorkRepository

class AddWorkViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockWorkRepository = mockk<WorkRepository>(relaxed = true)

    private lateinit var viewModel: AddWorkViewModel

    @Before
    fun setup() {
        coEvery { mockWorkRepository.getAllEntitiesStream() } returns flowOf()

        viewModel = AddWorkViewModel(
            mockWorkRepository,
        )
    }

    @Test
    fun `initial state is empty when id is null`() = runTest {
        assertEquals(0.0, viewModel.uiState.first().value)
        assertEquals("", viewModel.uiState.first().name, )
        assertEquals(StandardMeasurementType.POUNDS, viewModel.uiState.first().selectedMeasurement, )
    }

    @Test
    fun `initial state is populated when id is not null`() = runTest {

    }

    @Test
    fun `updateValue updates state`() = runTest {
        viewModel.updateValue(33.0)
        assertEquals(33.0, viewModel.uiState.value.value, )
    }

    @Test
    fun `updateName updates state`() = runTest {
        viewModel.updateName("Weight Lifted")
        assertEquals("Weight Lifted", viewModel.uiState.first().name, )
    }

    @Test
    fun `updateMeasurement updates state`() = runTest{
        viewModel.updateMeasurement(StandardMeasurementType.INCHES)
        assertEquals(viewModel.uiState.first().selectedMeasurement, StandardMeasurementType.INCHES)
    }

    @Test
    fun `updateMeasurement sets value to zero on category change`() = runTest {
        viewModel.updateMeasurement(StandardMeasurementType.INCHES)
        viewModel.updateValue(20.0)
        viewModel.updateMeasurement(StandardMeasurementType.RIR)
        assertEquals(0.0, viewModel.uiState.first().value, )
    }

    @Test
    fun `updateMeasurement correctly converts and truncates value for conversions`() = runTest {
        viewModel.updateMeasurement(StandardMeasurementType.POUNDS)
        viewModel.updateValue(23.00)
        viewModel.updateMeasurement(StandardMeasurementType.KILOGRAMS)
        assertEquals(10.43262451, viewModel.uiState.first().value)

        // Check that conversion is the same after changing back to initial measurement
        viewModel.updateMeasurement(StandardMeasurementType.POUNDS)
        assertEquals(23.0, viewModel.uiState.first().value)
    }

    @Test
    fun `createWorkDetails creates work with selected details`() = runTest{
        viewModel.updateMeasurement(StandardMeasurementType.INCHES)
        viewModel.updateValue(1.0)
        viewModel.updateName("Sample")

        val newWorkDetails = viewModel.createWorkDetails()

        assertEquals(1.0, newWorkDetails.work.value, )
        assertEquals("Sample", newWorkDetails.work.name)
        assertEquals(StandardMeasurementType.INCHES, newWorkDetails.work.measurementType, )
    }
}