package pro.selecto.slothos.data.repositories.implementations

import pro.selecto.slothos.data.dao.ExerciseCategoryFKDao
import pro.selecto.slothos.data.entities.ExerciseCategoryFK
import pro.selecto.slothos.data.repositories.interfaces.GenericFKRepository
import javax.inject.Inject

class ExerciseCategoryFKRepository @Inject constructor(private val exerciseCategoryDao: ExerciseCategoryFKDao):
    GenericFKRepository<ExerciseCategoryFK> {
    override suspend fun insertFK(fkPair: ExerciseCategoryFK) = exerciseCategoryDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseCategoryFK) = exerciseCategoryDao.delete(fkPair)
}