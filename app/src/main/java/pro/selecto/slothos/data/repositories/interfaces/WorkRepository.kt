package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Work

interface WorkRepository: BaseRepository<Work> {
    suspend fun getAllWorkMatchingSetId(id: Int): Flow<List<Work>>
}