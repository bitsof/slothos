package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow

interface RelatedToExerciseRepository<T> : BaseRepository<T> {
    /**
     * Get all entities that match a given exercise id
     */
    fun getAllEntitiesMatchingIdStream(id: Int): Flow<List<T>>

//    /**
//     * Insert a given foreign key relationship
//     */
//    suspend fun insertFK(fkPair: )
//
//    /**
//     * Delete a given foreign key relationship
//     */
//    suspend fun deleteFK(fkPair: T)
}