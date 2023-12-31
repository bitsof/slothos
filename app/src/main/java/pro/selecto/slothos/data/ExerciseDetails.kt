package pro.selecto.slothos.data

import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise


data class ExerciseDetails(
    val exercise: Exercise,
    val categoryList: List<Category>,
    val equipmentList: List<Equipment>,
//    private val forceList: List<Force>,
//    private val level: Level,
//    private val mechanic: Mechanic,
//    private val tagList: List<Tag>,

) {
}