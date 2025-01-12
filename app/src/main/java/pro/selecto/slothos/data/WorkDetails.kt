package pro.selecto.slothos.data

import kotlinx.serialization.Serializable
import pro.selecto.slothos.data.entities.Measurement
import pro.selecto.slothos.data.entities.Work

@Serializable
data class WorkDetails(
    var work: Work,
    var measurement: Measurement,
)
