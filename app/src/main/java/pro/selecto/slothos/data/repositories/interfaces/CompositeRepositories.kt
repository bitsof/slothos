package pro.selecto.slothos.data.repositories.interfaces

import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.ExerciseCategoryFK
import pro.selecto.slothos.data.entities.ExerciseEquipmentFK

interface CategoryRepository : RelatedToExerciseRepository<Category>, BaseFKRepository<ExerciseCategoryFK>

interface EquipmentRepository : RelatedToExerciseRepository<Equipment>, BaseFKRepository<ExerciseEquipmentFK>