package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Exercise

/**
 * Repository that provides insert, update, delete, and retrieve of [Exercise] from a given data source.
 */
interface ExerciseRepository {
    /**
     * Retrieve all the exercises from the the given data source.
     */
    fun getAllExercisesStream(): Flow<List<Exercise>>

    /**
     * Retrieve an exercise from the given data source that matches with the [id].
     */
    fun getExerciseStream(id: Int): Flow<Exercise?>

    /**
     * Insert exercise in the data source
     */
    suspend fun insertExercise(exercise: Exercise)

    /**
     * Delete exercise from the data source
     */
    suspend fun deleteExercise(exercise: Exercise)

    /**
     * Update exercise in the data source
     */
    suspend fun updateExercise(exercise: Exercise)

    /**
     * Return number of exercises in database
     */
    suspend fun count(): Int
}