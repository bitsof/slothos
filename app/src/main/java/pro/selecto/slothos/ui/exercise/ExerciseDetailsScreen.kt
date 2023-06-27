package pro.selecto.slothos.ui.exercise

import pro.selecto.slothos.ui.navigation.NavigationDestination

object ExerciseDetailsDestination : NavigationDestination {
    override val route = "exercise_details"
    override val titleRes = 1 //REPLACE WITH EXERCISE NAME FROM REPO
    const val exerciseIdArg = "itemId"
    val routeWithArgs = "$route/{$exerciseIdArg}"
}