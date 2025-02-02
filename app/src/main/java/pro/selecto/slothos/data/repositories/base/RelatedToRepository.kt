package pro.selecto.slothos.data.repositories.base

import kotlinx.coroutines.flow.Flow

interface RelatedToRepository<T> : BaseRepository<T> {
    /**
     * Get all entities that match a given exercise, set, etc. id
     */
    suspend fun getAllEntitiesMatchingIdStream(id: Int): Flow<List<T>>

}