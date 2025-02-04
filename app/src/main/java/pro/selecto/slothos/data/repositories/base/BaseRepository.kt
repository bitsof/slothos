package pro.selecto.slothos.data.repositories.base

import kotlinx.coroutines.flow.Flow

interface BaseRepository<T> {

    /**
     * Insert entity in the data source
     */
    suspend fun insert(entity: T): Long

    /**
     * Update entity in the data source
     */
    suspend fun update(entity: T)

    /**
     * Delete entity from the data source
     */
    suspend fun delete(entity: T)

    /**
     * Retrieve a entity from the given data source that matches with the [id].
     */
    suspend fun getEntityById(id: Int): Flow<T?>

    /**
     * Retrieve a entity id number from the given data source that matches with [name].
     */
    suspend fun getEntityIdByName(name: String): Int?

    /**
     * Retrieve all the entities from the the given data source.
     */
    suspend fun getAllEntitiesStream(): Flow<List<T>>

}