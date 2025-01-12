package pro.selecto.slothos.data.repositories.implementations

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.dao.SetDao
import pro.selecto.slothos.data.entities.Set
import pro.selecto.slothos.data.repositories.interfaces.SetRepository
import javax.inject.Inject

class ImplSetRepository @Inject constructor(private val setDao: SetDao) : SetRepository {
    override suspend fun getAllEntitiesMatchingSetId(id: Int): Flow<List<Set>> = setDao.getAllSetsMatchingSetId(id = id)

    override suspend fun getAllEntitiesMatchingWorkoutId(id: Int): Flow<List<Set>> = setDao.getAllSetsMatchingWorkoutId(id = id)

    override suspend fun getAllEntitiesStream(): Flow<List<Set>> = setDao.getAllSets()

    override suspend fun getEntityById(id: Int): Flow<Set?> = setDao.getSet(id)

    override suspend fun getEntityIdByName(name: String): Int? = setDao.getSetId(name)

    override suspend fun insert(entity: Set) = setDao.insert(entity)

    override suspend fun delete(entity: Set) = setDao.delete(entity)

    override suspend fun update(entity: Set) = setDao.update(entity)
}