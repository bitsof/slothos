package pro.selecto.slothos.data.repositories.implementations

import pro.selecto.slothos.data.dao.ExerciseTagFKDao
import pro.selecto.slothos.data.entities.ExerciseTagFK
import pro.selecto.slothos.data.repositories.interfaces.GenericFKRepository

class ExerciseTagFKRepository(private val exerciseTagDao: ExerciseTagFKDao):
    GenericFKRepository<ExerciseTagFK> {
    override suspend fun insertFK(fkPair: ExerciseTagFK) = exerciseTagDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseTagFK) = exerciseTagDao.delete(fkPair)
}