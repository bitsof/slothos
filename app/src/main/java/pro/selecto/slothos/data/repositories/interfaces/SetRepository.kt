package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Set
import pro.selecto.slothos.data.repositories.base.BaseRepository

interface SetRepository: BaseRepository<Set> {
    suspend fun getAllEntitiesMatchingSetId(id: Int): Flow<List<Set>>

    suspend fun getAllEntitiesMatchingWorkoutId(id: Int): Flow<List<Set>>
}