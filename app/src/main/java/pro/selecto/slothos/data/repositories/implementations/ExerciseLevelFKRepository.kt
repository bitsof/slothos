package pro.selecto.slothos.data.repositories.implementations

import pro.selecto.slothos.data.dao.ExerciseLevelFKDao
import pro.selecto.slothos.data.entities.ExerciseLevelFK
import pro.selecto.slothos.data.repositories.interfaces.GenericFKRepository

class ExerciseLevelFKRepository(private val exerciseLevelDao: ExerciseLevelFKDao):
    GenericFKRepository<ExerciseLevelFK> {
    override suspend fun insertFK(fkPair: ExerciseLevelFK) = exerciseLevelDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseLevelFK) = exerciseLevelDao.delete(fkPair)
}