package pro.selecto.slothos.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import pro.selecto.slothos.data.entities.Work

@Parcelize
data class WorkDetails(
    var work: Work,
    //var measurement: StandardMeasurementType,
) : Parcelable
