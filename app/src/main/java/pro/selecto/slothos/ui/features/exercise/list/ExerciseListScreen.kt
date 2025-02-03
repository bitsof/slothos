package pro.selecto.slothos.ui.features.exercise.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import pro.selecto.slothos.R
import pro.selecto.slothos.data.model.ExerciseDetails
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.ui.common.lists.ListScreen
import pro.selecto.slothos.ui.navigation.NavigationDestination

object ExerciseListDestination : NavigationDestination {
    override val route = "exercise_list"
    override val titleRes = R.string.exercise_list
}

@Composable
fun ExerciseListScreen(
    modifier: Modifier = Modifier,
    viewModelFactory: ViewModelProvider.Factory,
    onExerciseClick: (ExerciseDetails) -> Unit,
    onAddClick: () -> Unit,
    mode: ListMode = ListMode.VIEW,
) {
    val viewModel: ExerciseListViewModel = viewModel(factory = viewModelFactory)
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()
    var showCategoryDialog by remember { mutableStateOf(false) }

    // Assuming viewModel provides filter options and categories
    val filterOptions by viewModel.filterOptions.collectAsState()
    val categories = uiState?.categoryList?: emptyList<Category>()

    ExerciseList(
        modifier = modifier,
        mode = mode,
        exerciseDetailsList = uiState?.exerciseDetailsList,
        categories = categories,
        onExerciseClick = onExerciseClick,
        onAddClick = onAddClick,
        filterOptions = filterOptions,
        onFilterChange = { newFilterOptions ->
            viewModel.updateFilterOptions(newFilterOptions)
                         },
    )
}

@Composable
fun ExerciseList(
    modifier: Modifier,
    exerciseDetailsList: List<ExerciseDetails>?,
    categories: List<Category>?,
    filterOptions: FilterOptions,
    onExerciseClick: (ExerciseDetails) -> Unit,
    onAddClick: () -> Unit, // Consider making this return ExerciseDetails in situation with Select to Insert
    onFilterChange: (FilterOptions) -> Unit,
    mode: ListMode = ListMode.VIEW,
) {
    var showFilterMenu by remember { mutableStateOf(false) }

    Column(modifier = modifier.padding(16.dp)) {
        if(mode == ListMode.SELECT) {
            Text("Select Mode")
        }
        Text(
            text = "Exercises",
            style = MaterialTheme.typography.headlineSmall
        )
        Button(onClick = { showFilterMenu = true }) {
            Text("Filter")
        }
        if (showFilterMenu) {
            FilterMenu(
                categories = categories,
                filterOptions = filterOptions,
                onDismiss = { showFilterMenu = false },
                onApplyFilter = onFilterChange,
                onCategorySelected = { categoryId ->
                    onFilterChange(filterOptions.copy(category = categoryId))
                }
            )
        }
        ListScreen(
            modifier = Modifier,
            mode = mode,
            onAddClick = onAddClick,
        ) { padding ->
            LazyColumn(
                modifier = modifier.padding(padding),
            ) {
                exerciseList?.let { list ->
                    items(list.count()) { index ->
                        val exerciseDetails = list[index]
                exerciseDetailsList?.let { list ->
                        ExerciseItem(
                            exerciseDetails = exerciseDetails,
                            onExerciseClick = onExerciseClick,
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun FilterMenu(
    categories: List<Category>?,
    filterOptions: FilterOptions,
    onDismiss: () -> Unit,
    onApplyFilter: (FilterOptions) -> Unit,
    onCategorySelected: (Int) -> Unit
) {
    var showCategoryDialog by remember { mutableStateOf(false) }
    Dialog(onDismissRequest = onDismiss) {
        Column() {
            Text("Select Filters", style = MaterialTheme.typography.bodySmall)

            // Button to open category selection dialog
            Button(onClick = { showCategoryDialog = true }) {
                Text("Select Category")
            }

            // Category Selection Dialog
            if (showCategoryDialog && categories != null) {
                CategorySelectionDialog(
                    categories = categories,
                    selectedCategoryId = filterOptions.category,
                    onCategorySelected = {
                        onCategorySelected(it)
                        showCategoryDialog = false
                    },
                    onDismissRequest = { showCategoryDialog = false }
                )
            }

            // Apply Filters Button
            Button(onClick = {
                onApplyFilter(filterOptions)
                onDismiss()
            }) {
                Text("Apply Filters")
            }
        }
    }
}


@Composable
fun ExerciseItem(
    exerciseDetails: ExerciseDetails,
    onExerciseClick: (ExerciseDetails) -> Unit,
) {
    Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { onExerciseClick(exerciseDetails) },
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

@Composable
fun CategorySelectionDialog(
    categories: List<Category>?,
    selectedCategoryId: Int?,
    onCategorySelected: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Select a Category", style = MaterialTheme.typography.displayLarge)
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                categories?.let { categoryList ->
                    items(categoryList) { category ->
                        Row(modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onCategorySelected(category.id) }
                        .padding(8.dp)) {
                        RadioButton(
                            selected = category.id == selectedCategoryId,
                            onClick = { onCategorySelected(category.id) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = category.name)
                        }
                    }
                }
            }
        }
    }
}

//object ExerciseListProvider {
//    private val exercise1: Exercise = Exercise(nameId = "NameID", name = "Name", instructions = "Description")
//    private val exercise2: Exercise = Exercise(nameId = "Completely Different", name = "Completely Different", instructions = "Description")
//    private val category: Category = Category(name = "Name", description = "Description")
//    private val equipment: Equipment = Equipment(name = "Name", description = "Description")
//
//    fun getSampleExercises(): List<ExerciseDetails>{
//        val exerciseDetails = ExerciseDetails(
//            exercise = exercise1,
//            mutableListOf(category),
//            mutableListOf(equipment)
//        )
//        val list = mutableListOf<ExerciseDetails>(
//            ExerciseDetails(
//                exercise = exercise1,
//                mutableListOf(category),
//                mutableListOf(equipment)
//            ),
//            ExerciseDetails(exercise = exercise2, mutableListOf(category), mutableListOf(equipment))
//        )
//        for (i in 1..20) {
//            list.add(exerciseDetails)
//        }
//        return list
//    }


enum class ListMode {
    VIEW,
    SELECT,
}