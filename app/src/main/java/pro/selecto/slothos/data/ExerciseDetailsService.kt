package pro.selecto.slothos.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.withContext
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.entities.ExerciseCategoryFK
import pro.selecto.slothos.data.entities.ExerciseEquipmentFK
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import pro.selecto.slothos.data.repositories.interfaces.CategoryRepository
import pro.selecto.slothos.data.repositories.interfaces.EquipmentRepository
import javax.inject.Inject

class ExerciseDetailsService @Inject constructor(
    // pass repositories as parameters
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val exerciseRepository: BaseRepository<Exercise>,
    private val categoryRepository: CategoryRepository,
    private val equipmentRepository: EquipmentRepository,
//    private val forceRepository: ForceRepository,
//    private val levelRepository: LevelRepository,
//    private val mechanicRepository: MechanicRepository,
//    private val primaryMuscleRepository: PrimaryMuscleRepository,
//    private val secondaryMuscleRepository: SecondaryMuscleRepository,
//    private val tagRepository: TagRepository
    ) {

    // returns exercise details class for a given exercise id
    suspend fun getExerciseDetails(exerciseId: Int) : Flow<ExerciseDetails> =
        categoryRepository.getAllEntitiesMatchingIdStream(exerciseId)
            .combine(equipmentRepository.getAllEntitiesMatchingIdStream(exerciseId)) { categories, equipment ->
                categories to equipment
            }
            .combine(exerciseRepository.getEntityById(exerciseId)) { (categories, equipment), exercise ->
                if (exercise == null) {
                    throw IllegalArgumentException("Measurement with id $exerciseId not found")
                }
                ExerciseDetails(
                    exercise = exercise,
                    categoryList = categories,
                    equipmentList = equipment
                )
            }

    // returns all exercise details
    @OptIn(FlowPreview::class)
    suspend fun getAllExerciseDetails() : Flow<List<ExerciseDetails>> =
        exerciseRepository.getAllEntitiesStream().flatMapConcat { exercises ->
            val exerciseDetailsFlows = exercises.map { exercise ->
                getExerciseDetails(exercise.id)
            }
            combine(exerciseDetailsFlows) { it.toList() }
        }

    suspend fun insertExercise(exerciseDetails: ExerciseDetails) = withContext(dispatcher) {
        exerciseRepository.insert(exerciseDetails.exercise)
        val exerciseId = exerciseRepository.getEntityIdByName(exerciseDetails.exercise.nameId)
        val id = requireNotNull(exerciseId) { "Exercise ID must not be null" }
        for (category in exerciseDetails.categoryList) {
            categoryRepository.insertFK(ExerciseCategoryFK(exerciseId = id, categoryId = category.id))
        }
        for (equipment in exerciseDetails.equipmentList) {
            equipmentRepository.insertFK(ExerciseEquipmentFK(exerciseId = id, equipmentId = equipment.id))
        }
    }
}
