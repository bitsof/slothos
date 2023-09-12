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
    override fun getAllEquipmentStream(): Flow<List<Equipment>> = equipmentDao.getAllEquipment()

    override fun getAllEquipmentMatchingIDStream(id: Int): Flow<List<Equipment>> = equipmentDao.getAllEquipmentMatchingExerciseID(id = id)

    override fun getEquipmentStream(id: Int): Flow<Equipment?> = equipmentDao.getEquipment(id)

    override suspend fun insertEquipment(equipment: Equipment) = equipmentDao.insert(equipment)

    override suspend fun deleteEquipment(equipment: Equipment) = equipmentDao.delete(equipment)

    override suspend fun updateEquipment(equipment: Equipment) = equipmentDao.update(equipment)

    override suspend fun insertFK(fkPair: ExerciseEquipmentFK) = exerciseEquipmentFKDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseEquipmentFK) = exerciseEquipmentFKDao.delete(fkPair)

}