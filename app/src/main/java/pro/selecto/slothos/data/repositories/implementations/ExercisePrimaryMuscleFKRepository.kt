package pro.selecto.slothos.data.repositories.implementations

import pro.selecto.slothos.data.dao.ExercisePrimaryMuscleFKDao
import pro.selecto.slothos.data.entities.ExercisePrimaryMuscleFK
import pro.selecto.slothos.data.repositories.interfaces.GenericFKRepository
import javax.inject.Inject

class ExercisePrimaryMuscleFKRepository @Inject constructor(private val exercisePrimaryMuscleDao: ExercisePrimaryMuscleFKDao):
    GenericFKRepository<ExercisePrimaryMuscleFK> {
    override suspend fun insertFK(fkPair: ExercisePrimaryMuscleFK) = exercisePrimaryMuscleDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExercisePrimaryMuscleFK) = exercisePrimaryMuscleDao.delete(fkPair)
}