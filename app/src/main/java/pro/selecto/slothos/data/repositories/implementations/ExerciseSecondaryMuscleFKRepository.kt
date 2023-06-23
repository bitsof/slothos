package pro.selecto.slothos.data.repositories.implementations

import pro.selecto.slothos.data.dao.ExerciseSecondaryMuscleFKDao
import pro.selecto.slothos.data.entities.ExerciseSecondaryMuscleFK
import pro.selecto.slothos.data.repositories.interfaces.GenericFKRepository

class ExerciseSecondaryMuscleFKRepository(private val exerciseSecondaryMuscleDao: ExerciseSecondaryMuscleFKDao):
    GenericFKRepository<ExerciseSecondaryMuscleFK> {
    override suspend fun insertFK(fkPair: ExerciseSecondaryMuscleFK) = exerciseSecondaryMuscleDao.insert(fkPair)

    override suspend fun deleteFK(fkPair: ExerciseSecondaryMuscleFK) = exerciseSecondaryMuscleDao.delete(fkPair)
}