package pro.selecto.slothos.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
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
import pro.selecto.slothos.ui.exercise.ExerciseDetailsDestination
import pro.selecto.slothos.ui.exercise.ExerciseDetailsScreen
import pro.selecto.slothos.ui.exercise.ExerciseListScreen
import pro.selecto.slothos.ui.exercise.ViewModelFactory

enum class SlothosScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    ExerciseList(title = R.string.exercise_list),
    ExerciseDetails(title = R.string.exercise_details)
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
                onButtonClicked = { navController.navigate(SlothosScreen.ExerciseList.name) }
            )
        }
        composable(route = SlothosScreen.ExerciseList.name){
            ExerciseListScreen(viewModelFactory = viewModelFactory)
        }
        composable(ExerciseDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(ExerciseDetailsDestination.exerciseIdArg) {
                type = NavType.IntType
            })) {
            ExerciseDetailsScreen(viewModelFactory = viewModelFactory)
        }
    }
}