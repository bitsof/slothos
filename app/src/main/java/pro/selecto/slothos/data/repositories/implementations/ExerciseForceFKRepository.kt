package pro.selecto.slothos.data.repositories.implementations

import pro.selecto.slothos.data.dao.ExerciseForceFKDao
import pro.selecto.slothos.data.entities.ExerciseForceFK
import pro.selecto.slothos.data.repositories.interfaces.GenericFKRepository

class ExerciseForceFKRepository(private val exerciseForceDao: ExerciseForceFKDao):
    GenericFKRepository<ExerciseForceFK> {
    override suspend fun insertFK(fkPair: ExerciseForceFK) = exerciseForceDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseForceFK) = exerciseForceDao.delete(fkPair)
}