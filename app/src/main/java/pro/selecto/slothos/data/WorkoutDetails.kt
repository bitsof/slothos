package pro.selecto.slothos.data

import kotlinx.serialization.Serializable
import pro.selecto.slothos.data.entities.Workout

@Serializable
data class WorkoutDetails (
    var workout: Workout = Workout(),
    var setDetailsList: List<SetDetails> = listOf<SetDetails>(),
) {

}