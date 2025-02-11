package pro.selecto.slothos.ui.core

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import pro.selecto.slothos.R
import pro.selecto.slothos.ui.features.exercise.details.ExerciseDetailsScreen
import pro.selecto.slothos.ui.features.exercise.insert.InsertExerciseScreen
import pro.selecto.slothos.ui.features.exercise.list.ExerciseListScreen
import pro.selecto.slothos.ui.features.exercise.list.ListMode
import pro.selecto.slothos.ui.features.set.insert.AddSetScreen
import pro.selecto.slothos.ui.features.work.insert.AddWorkScreen
import pro.selecto.slothos.ui.features.workout.details.DisplayWorkoutScreen
import pro.selecto.slothos.ui.features.workout.insert.InsertWorkoutScreen
import pro.selecto.slothos.ui.features.workout.list.WorkoutListScreen

enum class SlothosScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    ExerciseList(title = R.string.exercise_list),
    ExerciseDetails(title = R.string.exercise_details),
    InsertExercise(title = R.string.insert_exercise),
    InsertWorkout(title = R.string.insert_workout),
    DisplayWorkout(title = R.string.display_workout),
    AddSet(title = R.string.add_set),
    WorkoutList(title = R.string.workout_list),
    AddWork(title = R.string.add_work)
}

@Composable
fun SlothosApp(
    navController: NavHostController = rememberNavController(),
    viewModelFactory: ViewModelProvider.Factory
) {
    NavHost(
        navController = navController,
        startDestination = SlothosScreen.Home.name,
        modifier = Modifier,
    ) {
        composable(route = SlothosScreen.Home.name) {
            HomeScreen(
                onButtonClicked = { navController.navigate(ExerciseListDestination()) },
                onButtonClicked2 = { navController.navigate(InsertExercise()) },
                onButtonClicked3 = { navController.navigate(InsertWorkout())},
                onButtonClicked5 = { navController.navigate(WorkoutList)},
                )
        }
        composable<ExerciseListDestination>{ backStackEntry ->
            val mode = backStackEntry.arguments?.getString("mode")
            ExerciseListScreen(
                viewModelFactory = viewModelFactory,
                onExerciseClick = { exerciseDetails ->
                    when (mode) {
                        "view" -> {
                            navController.navigate(DisplayExerciseDetails(exerciseDetails.exercise.id))
                        }
                        "select" -> {
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("selectedExercise", exerciseDetails)
                            navController.popBackStack()
                        }
                        else -> {
                            navController.navigate(DisplayExerciseDetails(exerciseDetails.exercise.id))
                        }
                    }
                                  },
                onEditClick = { exerciseDetails ->
                    navController.navigate(InsertExercise(exerciseDetails.exercise.id))
                },
                onAddClick = { navController.navigate(InsertExercise()) },
                mode = when(mode) {
                    "view" -> {
                        ListMode.VIEW
                    }
                    "select" -> {
                        ListMode.SELECT
                    }
                    else -> {
                        /* TODO possible error handling */
                        ListMode.VIEW
                    }
                }

            )
        }
        composable<InsertExercise> {
            InsertExerciseScreen(
                viewModelFactory = viewModelFactory,
                navController = navController,
            )
        }
        composable<InsertWorkout> {
            InsertWorkoutScreen(
                viewModelFactory = viewModelFactory,
                navigateToAddSetScreen = { navController.navigate(AddSet())},
                navController = navController,
            )
        }
        
        composable<AddSet> {
            AddSetScreen(
                navController = navController,
                viewModelFactory = viewModelFactory,
                onSetAdded = { setDetails ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("newSetDetails", setDetails)
                    navController.popBackStack()
                },
                navigateToAddWorkScreen = { navController.navigate(AddWork()) },
                navigateToSelectExerciseScreen = { navController.navigate(ExerciseListDestination(mode = "select")) },
            )
        }

        composable<AddWork> {
            AddWorkScreen(
                navController = navController,
                viewModelFactory = viewModelFactory,
                onWorkAdded = { workDetails ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("newWorkDetails", workDetails)
                    navController.popBackStack()
                }
            )
        }
        
        composable<DisplayWorkoutDetails> { backStackEntry ->
            DisplayWorkoutScreen(
                viewModelFactory = viewModelFactory,
            )
        }

        composable<WorkoutList> {
            WorkoutListScreen(
                viewModelFactory = viewModelFactory,
                onWorkoutClick = { workoutDetails ->
                    navController.navigate(DisplayWorkoutDetails(workoutDetails.workout.id))
                },
                onEditClick = { workoutDetails ->
                    navController.navigate(InsertWorkout(workoutDetails.workout.id))
                },
                onAddClick = { navController.navigate(InsertWorkout())},
                mode = ListMode.VIEW,
            )
        }
        composable<DisplayExerciseDetails> { backStackEntry ->
            ExerciseDetailsScreen(
                viewModelFactory = viewModelFactory,
            )
        }

    }
}

// data classes for navigation
// https://developer.android.com/develop/ui/compose/navigation#nav-with-args
@Serializable
data class InsertExercise(val id: Int? = null)

@Serializable
data class InsertWorkout(val id: Int? = null)

@Serializable
data class ExerciseListDestination(val mode: String? = null)

@Serializable
object WorkoutList

@Serializable
data class AddSet(val id: Int? = null)

@Serializable
data class AddWork(val id: Int? = null)

@Serializable
data class DisplayWorkoutDetails(val id: Int)

@Serializable
data class DisplayExerciseDetails(val id: Int)
