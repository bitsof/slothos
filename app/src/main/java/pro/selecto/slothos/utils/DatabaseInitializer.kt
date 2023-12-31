package pro.selecto.slothos.utils

import android.content.Context
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.entities.ExerciseCategoryFK
import pro.selecto.slothos.data.entities.ExerciseEquipmentFK
import pro.selecto.slothos.data.repositories.interfaces.BaseFKRepository
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import pro.selecto.slothos.data.repositories.interfaces.CategoryRepository
import pro.selecto.slothos.data.repositories.interfaces.EquipmentRepository
import pro.selecto.slothos.data.repositories.interfaces.RelatedToExerciseRepository
import javax.inject.Inject

class DatabaseInitializer @Inject constructor(
    // pass repositories as parameters
    private val exerciseRepository: BaseRepository<Exercise>,
    private val categoryRepository: CategoryRepository,
    private val equipmentRepository: EquipmentRepository,
//    private val forceRepository: ForceRepository,
//    private val levelRepository: LevelRepository,
//    private val mechanicRepository: MechanicRepository,
//    private val muscleRepository: PrimaryMuscleRepository,
//    private val muscleRepository: SecondaryMuscleRepository,
//    private val tagRepository: TagRepository
    private val jsonHandler: JsonHandler,
    private val context: Context
) {
    suspend fun prepopulateDatabase() {
        // get raw exercise data
        val rawExercises = jsonHandler.getRawExerciseData()
        // convert raw exercise data into exercises, and then insert them
        val exercises = mutableListOf<Exercise>()
        for (exercise in rawExercises) {
            exercises.add(
                Exercise(
                    nameId = exercise.id,
                    name = exercise.name,
                    instructions = exercise.instructions.joinToString()
                )
            )
        }
        populateEntities(exercises, exerciseRepository)

//        // get data from jsonFiles and insert it into database
//        val categories = jsonHandler.getData<Category>(context = context, jsonFileName = "category")
//        populateEntities(categories, categoryRepository)
//
//        val equipment = jsonHandler.getData<Equipment>(context = context, jsonFileName = "equipment")
//        populateEntities(equipment, equipmentRepository)
//
//        // generate foreign key relationships and insert them into the data
//        for (exercise in rawExercises) {
//            val exerciseId = exerciseRepository.getEntityIdByName(exercise.name)
//            val categoryId = categoryRepository.getEntityIdByName(exercise.category)
//            val equipmentId = exercise.equipment?.let { equipmentRepository.getEntityIdByName(it) }
//
//            if (exerciseId != null) {
//                if (categoryId != null) {
//                    val exerciseCategoryFK = ExerciseCategoryFK(exerciseId = exerciseId, categoryId = categoryId)
//                    categoryRepository.insertFK(exerciseCategoryFK)
//                }
//                if (equipmentId != null) {
//                    val exerciseEquipmentFK = ExerciseEquipmentFK(exerciseId = exerciseId, equipmentId = equipmentId)
//                    equipmentRepository.insertFK(exerciseEquipmentFK)
//                }
//            }
//
//        }

    }

    private suspend fun <T> populateEntities(entities: List<T>, repository: BaseRepository<T>) {
        for (entity in entities) {
            repository.insert(entity)
        }
    }

    private suspend fun <T> generateForeignKeyRelationships(
        entities: List<T>,
        repository: RelatedToExerciseRepository<T>,
        rawExercises: List<RawExercise>
    ) {

    }

    suspend fun insertRawExercises(rawExercises: List<RawExercise>){

    }
}