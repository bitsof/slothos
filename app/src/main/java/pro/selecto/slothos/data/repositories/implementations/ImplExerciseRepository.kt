package pro.selecto.slothos.data.repositories.implementations

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.dao.ExerciseDao
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import javax.inject.Inject

class ImplExerciseRepository @Inject constructor(private val exerciseDao: ExerciseDao) : BaseRepository<Exercise> {
    override fun getAllEntitiesStream(): Flow<List<Exercise>> = exerciseDao.getAllExercises()

    override fun getEntityById(id: Int): Flow<Exercise?> = exerciseDao.getExercise(id)

    override fun getEntityIdByName(name: String): Int? = exerciseDao.getExerciseId(name)

    override suspend fun insert(entity: Exercise) = exerciseDao.insert(entity)

    override suspend fun delete(entity: Exercise) = exerciseDao.delete(entity)

    override suspend fun update(entity: Exercise) = exerciseDao.update(entity)

    fun count(): Int = exerciseDao.count()
}