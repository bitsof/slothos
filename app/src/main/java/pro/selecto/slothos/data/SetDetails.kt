package pro.selecto.slothos.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import pro.selecto.slothos.data.entities.Set

@Parcelize
data class SetDetails (
    val set: Set,
    val exerciseDetails: ExerciseDetails,
    var workDetailsList: List<WorkDetails> = listOf<WorkDetails>(),
    var setDetailsList: List<SetDetails> = listOf<SetDetails>()
) : Parcelable {
}