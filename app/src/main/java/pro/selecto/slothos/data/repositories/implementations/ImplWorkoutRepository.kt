package pro.selecto.slothos.data.repositories.implementations

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.dao.WorkoutDao
import pro.selecto.slothos.data.entities.Workout
import pro.selecto.slothos.data.repositories.base.BaseRepository
import javax.inject.Inject

class ImplWorkoutRepository @Inject constructor(private val workoutDao: WorkoutDao) :
    BaseRepository<Workout> {
    override suspend fun getAllEntitiesStream(): Flow<List<Workout>> = workoutDao.getAllWorkouts()

    override suspend fun getEntityById(id: Int): Flow<Workout?> = workoutDao.getWorkout(id)

    override suspend fun getEntityIdByName(name: String): Int? = workoutDao.getWorkoutId(name)

    override suspend fun insert(entity: Workout) = workoutDao.insert(entity)

    override suspend fun delete(entity: Workout) = workoutDao.delete(entity)

    override suspend fun update(entity: Workout) = workoutDao.update(entity)
}