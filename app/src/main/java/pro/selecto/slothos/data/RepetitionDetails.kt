package pro.selecto.slothos.data

import pro.selecto.slothos.data.entities.Repetition
import pro.selecto.slothos.data.entities.RepetitionMeasurement

data class RepetitionDetails (
    val repetition: Repetition,
    val repetitionMeasurementList: List<RepetitionMeasurement>,
) {
}