package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Equipment

/**
 * Repository that provides insert, update, delete, and retrieve of [Equipment] from a given data source.
 */
interface EquipmentRepository {
    /**
     * Retrieve all the equipment from the the given data source.
     */
    fun getAllEquipmentStream(): Flow<List<Equipment>>

    /**
     * Retrieve an equipment entry from the given data source that matches with the [id].
     */
    fun getEquipmentStream(id: Int): Flow<Equipment?>

    /**
     * Insert equipment in the data source
     */
    suspend fun insertEquipment(equipment: Equipment)

    /**
     * Delete exercise from the data source
     */
    suspend fun deleteEquipment(equipment: Equipment)

    /**
     * Update exercise in the data source
     */
    suspend fun updateEquipment(equipment: Equipment)
}