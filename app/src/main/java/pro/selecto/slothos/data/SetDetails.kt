package pro.selecto.slothos.data

import kotlinx.serialization.Serializable
import pro.selecto.slothos.data.entities.Set

@Serializable
data class SetDetails (
    val set: Set,
    val exerciseDetails: ExerciseDetails,
    var workDetailsList: List<WorkDetails> = listOf<WorkDetails>(),
    var setDetailsList: List<SetDetails> = listOf<SetDetails>()
) {
}