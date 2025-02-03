package pro.selecto.slothos.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import pro.selecto.slothos.data.entities.Workout

@Parcelize
data class WorkoutDetails (
    var workout: Workout = Workout(),
    var setDetailsList: List<SetDetails> = listOf<SetDetails>(),
) : Parcelable {

}