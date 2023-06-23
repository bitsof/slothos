package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Muscle

/**
 * Repository that provides insert, update, delete, and retrieve of [Muscle] from a given data source.
 */
interface MuscleRepository {
    /**
     * Retrieve all the muscles from the the given data source.
     */
    fun getAllMusclesStream(): Flow<List<Muscle>>

    /**
     * Retrieve an muscle from the given data source that matches with the [id].
     */
    fun getMuscleStream(id: Int): Flow<Muscle?>

    /**
     * Insert muscle in the data source
     */
    suspend fun insertMuscle(muscle: Muscle)

    /**
     * Delete muscle from the data source
     */
    suspend fun deleteMuscle(muscle: Muscle)

    /**
     * Update muscle in the data source
     */
    suspend fun updateMuscle(muscle: Muscle)
}