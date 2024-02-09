package pro.selecto.slothos.data.repositories.implementations

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.dao.EquipmentDao
import pro.selecto.slothos.data.dao.ExerciseEquipmentFKDao
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.ExerciseEquipmentFK
import pro.selecto.slothos.data.repositories.interfaces.EquipmentRepository
import javax.inject.Inject

class ImplEquipmentRepository @Inject constructor(
    private val equipmentDao: EquipmentDao,
    private val exerciseEquipmentFKDao: ExerciseEquipmentFKDao
) : EquipmentRepository {
    override suspend fun getAllEntitiesStream(): Flow<List<Equipment>> = equipmentDao.getAllEquipment()

    override suspend fun getAllEntitiesMatchingIdStream(id: Int): Flow<List<Equipment>> = equipmentDao.getAllEquipmentMatchingExerciseID(id = id)

    override suspend fun getEntityById(id: Int): Flow<Equipment?> = equipmentDao.getEquipment(id)

    override suspend fun getEntityIdByName(name: String): Int? = equipmentDao.getEquipmentId(name)

    override suspend fun insert(entity: Equipment) = equipmentDao.insert(entity)

    override suspend fun delete(entity: Equipment) = equipmentDao.delete(entity)

    override suspend fun update(entity: Equipment) = equipmentDao.update(entity)

    override suspend fun insertFK(fkPair: ExerciseEquipmentFK) = exerciseEquipmentFKDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseEquipmentFK) = exerciseEquipmentFKDao.delete(fkPair)

}