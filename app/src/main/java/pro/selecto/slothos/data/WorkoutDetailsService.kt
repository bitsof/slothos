package pro.selecto.slothos.data

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pro.selecto.slothos.data.entities.Workout
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import javax.inject.Inject

class WorkoutDetailsService @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val workoutRepository: BaseRepository<Workout>,
    private val setDetailsService: SetDetailsService
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getWorkoutDetails(workoutId: Int) : Flow<WorkoutDetails> =
        workoutRepository.getEntityById(workoutId)
            .flatMapLatest { workout ->
                if (workout == null) {
                    throw IllegalArgumentException("Workout with ID $workoutId not found")
                }
                setDetailsService.getSetDetailsListForWorkout(workoutId).map { sets ->
                    WorkoutDetails(
                        workout = workout,
                        setDetailsList = sets,
                    )
                }
            }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getAllWorkoutDetails() : Flow<List<WorkoutDetails>> =
        workoutRepository.getAllEntitiesStream().flatMapConcat { workouts ->
            if (workouts.isEmpty()) {
                Log.d("WorkoutDetailsService", "No workouts")
                flowOf(emptyList())
            } else {
                Log.d("WorkoutDetailsService", "Get All Workout Details")
                val workoutDetailsFlows = workouts.map { workout ->
                    getWorkoutDetails(workout.id)
                }
                combine(workoutDetailsFlows) {
                    val list = it.toList()
                    Log.d("WorkoutDetails", "Emitting list with size: ${list.size}")
                    list
                }
            }
        }


    suspend fun insertWorkout(workoutDetails: WorkoutDetails) = withContext(dispatcher) {
        workoutRepository.insert(workoutDetails.workout)
        for (setDetails in workoutDetails.setDetailsList) {
            setDetailsService.insertSet(setDetails)
        }
    }
}