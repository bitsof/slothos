package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Force

/**
 * Repository that provides insert, update, delete, and retrieve of [Force] from a given data source.
 */
interface ForceRepository {
    /**
     * Retrieve all the forces from the the given data source.
     */
    fun getAllForcesStream(): Flow<List<Force>>

    /**
     * Retrieve an force from the given data source that matches with the [id].
     */
    fun getForceStream(id: Int): Flow<Force?>

    /**
     * Insert force in the data source
     */
    suspend fun insertForce(force: Force)

    /**
     * Delete force from the data source
     */
    suspend fun deleteForce(force: Force)

    /**
     * Update force in the data source
     */
    suspend fun updateForce(force: Force)
}