package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.ExerciseMechanicFK
import pro.selecto.slothos.data.entities.Mechanic

/**
 * Repository that provides insert, update, delete, and retrieve of [Mechanic] from a given data source.
 */
interface MechanicRepository {
    /**
     * Retrieve all the mechanics from the the given data source.
     */
    fun getAllMechanicsStream(): Flow<List<Mechanic>>

    /**
     * Retrieve an mechanic from the given data source that matches with the [id].
     */
    fun getMechanicStream(id: Int): Flow<Mechanic?>

    /**
     * Insert mechanic in the data source
     */
    suspend fun insertMechanic(mechanic: Mechanic)

    /**
     * Delete mechanic from the data source
     */
    suspend fun deleteMechanic(mechanic: Mechanic)

    /**
     * Update mechanic in the data source
     */
    suspend fun updateMechanic(mechanic: Mechanic)

    /**
     * Insert a given foreign key relationship
     */
    suspend fun insertFK(fkPair: ExerciseMechanicFK)

    /**
     * Delete a given foreign key relationship
     */
    suspend fun deleteFK(fkPair: ExerciseMechanicFK)
}