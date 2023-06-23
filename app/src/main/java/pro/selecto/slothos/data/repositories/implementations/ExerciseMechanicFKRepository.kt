package pro.selecto.slothos.data.repositories.implementations

import pro.selecto.slothos.data.dao.ExerciseMechanicFKDao
import pro.selecto.slothos.data.entities.ExerciseMechanicFK
import pro.selecto.slothos.data.repositories.interfaces.GenericFKRepository

class ExerciseMechanicFKRepository(private val exerciseMechanicDao: ExerciseMechanicFKDao):
    GenericFKRepository<ExerciseMechanicFK> {
    override suspend fun insertFK(fkPair: ExerciseMechanicFK) = exerciseMechanicDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseMechanicFK) = exerciseMechanicDao.delete(fkPair)
}