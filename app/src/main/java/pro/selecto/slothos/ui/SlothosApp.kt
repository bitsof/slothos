package pro.selecto.slothos.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pro.selecto.slothos.R

enum class SlothosScreen(@StringRes val title: Int) {
    Home(title = R.string.app_name),
    ExerciseList(title = R.string.exercise_list),
}

@Composable
fun SlothosApp(
    navController: NavHostController = rememberNavController()
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
            ExerciseListScreen()
        }
    }
}