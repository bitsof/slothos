package pro.selecto.slothos.data.repositories.implementations

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.dao.WorkDao
import pro.selecto.slothos.data.entities.Work
import pro.selecto.slothos.data.repositories.interfaces.WorkRepository
import javax.inject.Inject

class ImplWorkRepository @Inject constructor(private val workDao: WorkDao) : WorkRepository {
    override suspend fun getAllEntitiesStream(): Flow<List<Work>> = workDao.getAllWork()

    override suspend fun getAllWorkMatchingSetId(id: Int): Flow<List<Work>> = workDao.getAllWorkMatchingSet(id)

    override suspend fun getEntityById(id: Int): Flow<Work?> = workDao.getWork(id)

    override suspend fun getEntityIdByName(name: String): Int? = null

    override suspend fun insert(entity: Work) = workDao.insert(entity)

    override suspend fun delete(entity: Work) = workDao.delete(entity)

    override suspend fun update(entity: Work) = workDao.update(entity)
}