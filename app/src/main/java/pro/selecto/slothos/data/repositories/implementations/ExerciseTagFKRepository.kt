package pro.selecto.slothos.data.repositories.implementations

import pro.selecto.slothos.data.dao.ExerciseTagFKDao
import pro.selecto.slothos.data.entities.ExerciseTagFK
import pro.selecto.slothos.data.repositories.interfaces.GenericFKRepository
import javax.inject.Inject

class ExerciseTagFKRepository @Inject constructor(private val exerciseTagDao: ExerciseTagFKDao):
    GenericFKRepository<ExerciseTagFK> {
    override suspend fun insertFK(fkPair: ExerciseTagFK) = exerciseTagDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseTagFK) = exerciseTagDao.delete(fkPair)
}