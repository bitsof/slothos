package pro.selecto.slothos.data.repositories.implementations

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.dao.ExerciseDao
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.repositories.interfaces.ExerciseRepository
import javax.inject.Inject

class ImplExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDao) : ExerciseRepository {
    override fun getAllExercisesStream(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    override fun getExerciseStream(id: Int): Flow<Exercise?> = exerciseDao.getExercise(id)

    override suspend fun insertExercise(exercise: Exercise) = exerciseDao.insert(exercise)

    override suspend fun deleteExercise(exercise: Exercise) = exerciseDao.delete(exercise)

    override suspend fun updateExercise(exercise: Exercise) = exerciseDao.update(exercise)

    override suspend fun count(): Int = exerciseDao.count()
}