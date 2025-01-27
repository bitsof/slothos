package pro.selecto.slothos.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise

@Parcelize
data class ExerciseDetails(
    val exercise: Exercise,
    val categoryList: List<Category>,
    val equipmentList: List<Equipment>,
//    private val forceList: List<Force>,
//    private val level: Level,
//    private val mechanic: Mechanic,
//    private val tagList: List<Tag>,

) : Parcelable {
}