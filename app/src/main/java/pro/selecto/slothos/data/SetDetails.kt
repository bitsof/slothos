package pro.selecto.slothos.data

import pro.selecto.slothos.data.entities.Repetition
import pro.selecto.slothos.data.entities.Set

data class SetDetails (
    val set: Set,
    val repetitionList: List<Repetition>,
) {
}