package pro.selecto.slothos.ui.exercise

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.selecto.slothos.R
import pro.selecto.slothos.data.ExerciseDetails
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.ui.navigation.NavigationDestination

object ExerciseListDestination : NavigationDestination {
    override val route = "exercise_list"
    override val titleRes = R.string.exercise_list
}

@Composable
fun ExerciseListScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory
) {
    val viewModel: ExerciseListViewModel = viewModel(
        factory = viewModelFactory
    )
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState(initial = ExerciseListUiState(emptyList()))
    val listSize = uiState?.exerciseDetailsList?.count()
    if(listSize != null) {
        ExerciseList(modifier, uiState?.exerciseDetailsList)
    }


}

@Composable
fun ExerciseList(modifier: Modifier, exerciseList: List<ExerciseDetails>?) {
    LazyColumn(modifier = modifier) {

        if (exerciseList?.count() != null) {
            items(exerciseList.count()) { index ->
                ExerciseItem(exerciseDetails = exerciseList[index])
            }
        }
    }
}

@Composable
fun ExerciseItem(exerciseDetails: ExerciseDetails) {
    Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
    ) {
        Column(
                modifier = Modifier
                        .padding(8.dp)
        ) {
            Text(text = "Exercise: ${exerciseDetails.exercise.name}")
            Text(text = "Categories: ${exerciseDetails.categoryList.joinToString { it.name }}")
            Text(text = "Equipment: ${exerciseDetails.equipmentList.joinToString { it.name }}")
        }
    }
}

object ExerciseListProvider {
    private val exercise1: Exercise = Exercise(nameId = "NameID", name = "Name", instructions = "Description")
    private val exercise2: Exercise = Exercise(nameId = "Completely Different", name = "Completely Different", instructions = "Description")
    private val category: Category = Category(name = "Name", description = "Description")
    private val equipment: Equipment = Equipment(name = "Name", description = "Description")

    fun getSampleExercises(): List<ExerciseDetails>{
        val exerciseDetails = ExerciseDetails(
            exercise = exercise1,
            mutableListOf(category),
            mutableListOf(equipment)
        )
        var list = mutableListOf<ExerciseDetails>(
            ExerciseDetails(
                exercise = exercise1,
                mutableListOf(category),
                mutableListOf(equipment)
            ),
            ExerciseDetails(exercise = exercise2, mutableListOf(category), mutableListOf(equipment))
        )
        for (i in 1..20) {
            list.add(exerciseDetails)
        }
        return list
    }


}

@Preview(showBackground = true)
@Composable
fun ExerciseListScreenPreview() {
    ExerciseList(modifier = Modifier
        .width(360.dp) // Set maximum width
        .height(640.dp),
        exerciseList = ExerciseListProvider.getSampleExercises())
}