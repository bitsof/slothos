package pro.selecto.slothos.data

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import pro.selecto.slothos.data.repositories.interfaces.CategoryRepository
import pro.selecto.slothos.data.repositories.interfaces.EquipmentRepository
import pro.selecto.slothos.data.repositories.interfaces.RelatedToExerciseRepository
import javax.inject.Inject

class ExerciseDetailsService @Inject constructor(
    // pass repositories as parameters
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
    fun getExerciseDetails(exercise: Exercise) : Flow<ExerciseDetails> =
        categoryRepository.getAllEntitiesMatchingIdStream(exercise.id)
            .combine(equipmentRepository.getAllEntitiesMatchingIdStream(exercise.id)) { categories, equipment ->
                ExerciseDetails(
                    exercise = exercise,
                    categoryList = categories,
                    equipmentList = equipment
                )
            }

    // returns all exercise details
    @OptIn(FlowPreview::class)
    fun getAllExerciseDetails() : Flow<List<ExerciseDetails>> =
        exerciseRepository.getAllEntitiesStream().flatMapConcat { exercises ->
            val exerciseDetailsFlows = exercises.map { exercise ->
                getExerciseDetails(exercise)
            }
            combine(exerciseDetailsFlows) { it.toList() }
        }
}
