package pro.selecto.slothos.data.services

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import pro.selecto.slothos.data.model.SetDetails
import pro.selecto.slothos.data.repositories.interfaces.SetRepository
import javax.inject.Inject

class SetDetailsService @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val setRepository: SetRepository,
    private val workDetailsService: WorkDetailsService,
    private val exerciseDetailsService: ExerciseDetailsService,
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getSetDetails(setId: Int): Flow<SetDetails> =
        setRepository.getEntityById(setId)
            .flatMapLatest { set ->
                if (set == null) {
                    throw IllegalArgumentException("Set with ID $setId not found")
                }

                val setDetailsFlow = getSetDetailsListForSet(setId)
                val workDetailsFlow = workDetailsService.getWorkDetailsListForSet(setId)
                val exerciseDetailsFlow = exerciseDetailsService.getExerciseDetails(set.exerciseId)

                combine(setDetailsFlow, workDetailsFlow, exerciseDetailsFlow) { sets, works, exercise ->
                    SetDetails(
                        set = set,
                        setDetailsList = sets,
                        workDetailsList = works,
                        exerciseDetails = exercise
                    )
            }
        }


    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getSetDetailsListForSet(id: Int): Flow<List<SetDetails>> =
        setRepository.getAllEntitiesMatchingSetId(id).flatMapConcat { sets ->
            val setDetailsFlows = sets.map { set ->
                getSetDetails(set.id)
            }
            combine(setDetailsFlows) {it.toList()}
            if (sets.isEmpty()){
                flowOf(emptyList<SetDetails>())
            }
            else {
                val setDetailsFlows = sets.map { set ->
                    getSetDetails(set.id)
                }
                combine(setDetailsFlows) { it.toList() }
            }
        }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getSetDetailsListForWorkout(id: Int): Flow<List<SetDetails>> =
        setRepository.getAllEntitiesMatchingWorkoutId(id).flatMapConcat { sets ->
            if (sets.isEmpty()) {
                flowOf(emptyList<SetDetails>()) // Emit an empty list if there are no sets
            } else {
                val setDetailsFlows = sets.map { set ->
                    getSetDetails(set.id) // Get details for each set
                }
                combine(setDetailsFlows) { it.toList() }
            }
        }

    suspend fun insertSet(setDetails: SetDetails) {
        withContext(dispatcher) {
            val setId = setRepository.insert(setDetails.set)
            for (workDetails in setDetails.workDetailsList) {
                workDetails.work.setId = setId.toInt()
                workDetailsService.insertWork(workDetails)
            }
            for (subsetDetails: SetDetails in setDetails.setDetailsList) {
                subsetDetails.set.setId = setId.toInt()
                insertSet(subsetDetails)
            }
        }
    }
}