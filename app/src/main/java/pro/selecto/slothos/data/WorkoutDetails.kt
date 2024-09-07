package pro.selecto.slothos.data

import pro.selecto.slothos.data.entities.Workout

data class WorkoutDetails (
    val workout: Workout,
    val setList: List<SetDetails>,
) {
}