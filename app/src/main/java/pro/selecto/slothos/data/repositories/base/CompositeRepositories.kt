package pro.selecto.slothos.data.repositories.base

import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.fk_entities.ExerciseCategoryFK
import pro.selecto.slothos.data.entities.fk_entities.ExerciseEquipmentFK

interface CategoryRepository : RelatedToRepository<Category>, BaseFKRepository<ExerciseCategoryFK>

interface EquipmentRepository : RelatedToRepository<Equipment>,
    BaseFKRepository<ExerciseEquipmentFK>
