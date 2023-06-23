package pro.selecto.slothos.data.repositories.implementations

import pro.selecto.slothos.data.dao.ExerciseEquipmentFKDao
import pro.selecto.slothos.data.entities.ExerciseEquipmentFK
import pro.selecto.slothos.data.repositories.interfaces.GenericFKRepository

class ExerciseEquipmentFKRepository(private val exerciseEquipmentDao: ExerciseEquipmentFKDao):
    GenericFKRepository<ExerciseEquipmentFK> {
    override suspend fun insertFK(fkPair: ExerciseEquipmentFK) = exerciseEquipmentDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseEquipmentFK) = exerciseEquipmentDao.delete(fkPair)
}