package pro.selecto.slothos.ui.core

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
import pro.selecto.slothos.ui.features.exercise.details.ExerciseDetailsScreen
import pro.selecto.slothos.ui.features.exercise.list.ExerciseListScreen
import pro.selecto.slothos.ui.features.exercise.insert.InsertExerciseScreen
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
                onButtonClicked = { navController.navigate(SlothosScreen.ExerciseList.name + "?mode=view") },
                onButtonClicked2 = { navController.navigate(SlothosScreen.InsertExercise.name) },
                onButtonClicked3 = { navController.navigate(SlothosScreen.InsertWorkout.name)},
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
                        else -> {
                            navController.navigate("${SlothosScreen.ExerciseDetails}/${exerciseDetails.exercise.id}")
                        }
                    }
                                  },
                onAddClick = { navController.navigate(SlothosScreen.InsertExercise.name) },
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
        composable(route = SlothosScreen.InsertExercise.name){
            InsertExerciseScreen(
                viewModelFactory = viewModelFactory,
                navController = navController,
            )
        }
        composable(route = SlothosScreen.InsertWorkout.name){
            InsertWorkoutScreen(
                viewModelFactory = viewModelFactory,
                navigateToAddSetScreen = { navController.navigate(SlothosScreen.AddSet.name)},
                navController = navController,
            )
        }
        
        composable(route = SlothosScreen.AddSet.name) {
            AddSetScreen(
                navController = navController,
                viewModelFactory = viewModelFactory,
                onSetAdded = { setDetails ->
                    navController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set("newSetDetails", setDetails)
                    navController.popBackStack()
                },
                navigateToAddWorkScreen = { navController.navigate(SlothosScreen.AddWork.name) },
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
        
        composable(
            route = "${SlothosScreen.DisplayWorkout.name}/{workoutId}",
            arguments = listOf(navArgument("workoutId") { type = NavType.IntType })
        ){ backStackEntry ->
            val workoutId = backStackEntry.arguments?.getInt("workoutId")
            if (workoutId != null) {
                DisplayWorkoutScreen(
                    viewModelFactory = viewModelFactory,
                    workoutId = workoutId,
                )
            }
        }

        composable(route = SlothosScreen.WorkoutList.name){
            WorkoutListScreen(
                viewModelFactory = viewModelFactory,
                onWorkoutClick = { workoutDetails ->
                    navController.navigate("${SlothosScreen.DisplayWorkout}/${workoutDetails.workout.id}")
                },
                onAddClick = { navController.navigate(SlothosScreen.InsertWorkout.name)},
                mode = ListMode.VIEW,
            )
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