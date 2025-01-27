package pro.selecto.slothos.ui

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import pro.selecto.slothos.R
import pro.selecto.slothos.ui.exercise.ExerciseDetailsScreen
import pro.selecto.slothos.ui.exercise.ExerciseListMode
import pro.selecto.slothos.ui.exercise.ExerciseListScreen
import pro.selecto.slothos.ui.exercise.InsertExerciseScreen
import pro.selecto.slothos.ui.workout.AddWorkScreen
import pro.selecto.slothos.ui.workout.DisplayWorkoutScreen
import pro.selecto.slothos.ui.workout.InsertWorkoutScreen
import pro.selecto.slothos.ui.workout.WorkoutListScreen

enum class SlothosScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    ExerciseList(title = R.string.exercise_list),
    ExerciseDetails(title = R.string.exercise_details),
    InsertExercise(title = R.string.insert_exercise),
    InsertWorkout(title = R.string.insert_workout),
    DisplayWorkout(title = R.string.display_workout),
    AddSet(title = R.string.add_set),
    WorkoutList(title = R.string.workout_list)
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
                onButtonClicked = { navController.navigate(SlothosScreen.ExerciseList.name) },
                onButtonClicked2 = { navController.navigate(SlothosScreen.InsertExercise.name) },
                onButtonClicked3 = { navController.navigate(SlothosScreen.InsertWorkout.name)},
                onButtonClicked4 = { navController.navigate(SlothosScreen.DisplayWorkout.name)},
                onButtonClicked5 = { navController.navigate(SlothosScreen.WorkoutList.name)},
                )
        }
        composable(route = SlothosScreen.ExerciseList.name + "?mode={mode}"){ backStackEntry ->
            val mode = backStackEntry.arguments?.getString("mode")
            ExerciseListScreen(
                viewModelFactory = viewModelFactory,
                onExerciseClick = { exerciseDetails ->
                    when (mode) {
                        "view" -> {
                            navController.navigate("${SlothosScreen.ExerciseDetails}/${exerciseDetails.exercise.id}")
                        }
                        "select" -> {
                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("selectedExercise", exerciseDetails)
                            navController.popBackStack()
                        }
                    }
                                  },
                mode = when(mode) {
                    "view" -> {
                        ExerciseListMode.VIEW
                    }
                    "select" -> {
                        ExerciseListMode.SELECT
                    }
                    else -> {
                        /* TODO possible error handling */
                        ExerciseListMode.VIEW
                    }
                }

            )
        }
        composable(route = SlothosScreen.InsertExercise.name){
            InsertExerciseScreen(viewModelFactory = viewModelFactory)
        }
        composable(route = SlothosScreen.InsertWorkout.name){
            InsertWorkoutScreen(
                viewModelFactory = viewModelFactory,
                navigateToAddSetScreen = { navController.navigate(SlothosScreen.AddSet.name)}
                navigateToAddSetScreen = { navController.navigate(SlothosScreen.AddSet.name)},
                navController = navController,
            )
        }
        
                navigateToSelectExerciseScreen = { navController.navigate(SlothosScreen.ExerciseList.name + "?mode=select") },
            )
        }

        composable(route = SlothosScreen.AddWork.name) {
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
        composable(route = SlothosScreen.WorkoutList.name){
            WorkoutListScreen(viewModelFactory = viewModelFactory)
        }
        composable(
            route = "${SlothosScreen.ExerciseDetails}/{exerciseId}",
            arguments = listOf(navArgument("exerciseId") { type = NavType.IntType })
        ) { backStackEntry ->
            val exerciseId = backStackEntry.arguments?.getInt("exerciseId")
            if (exerciseId != null) {
                ExerciseDetailsScreen(
                    viewModelFactory = viewModelFactory,
                    exerciseId = exerciseId,
                )
            }
        }

    }
}