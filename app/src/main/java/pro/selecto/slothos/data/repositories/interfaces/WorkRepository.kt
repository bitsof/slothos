package pro.selecto.slothos.data.repositories.interfaces

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.entities.Work
import pro.selecto.slothos.data.repositories.base.BaseRepository

interface WorkRepository: BaseRepository<Work> {
    suspend fun getAllWorkMatchingSetId(id: Int): Flow<List<Work>>
}