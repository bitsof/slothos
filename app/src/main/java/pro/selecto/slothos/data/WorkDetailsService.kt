package pro.selecto.slothos.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import pro.selecto.slothos.data.entities.Measurement
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import pro.selecto.slothos.data.repositories.interfaces.WorkRepository
import javax.inject.Inject

class WorkDetailsService @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default,
    private val workRepository: WorkRepository,
    private val measurementRepository: BaseRepository<Measurement>,
    ) {

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun getWorkDetails(workId: Int): Flow<WorkDetails> =
        workRepository.getEntityById(workId)
            .flatMapLatest { work ->
            .map { work ->
                if(work == null) {
                    throw IllegalArgumentException("Work with ID $workId not found")
                }
                WorkDetails(work = work)
            }

    @OptIn(FlowPreview::class)
    suspend fun getWorkDetailsListForSet(id: Int): Flow<List<WorkDetails>> =
        workRepository.getAllWorkMatchingSetId(id).flatMapConcat { works ->
            if (works.isEmpty()) {
                flowOf(emptyList<WorkDetails>()) // Ensure flow emits even if there are no works
            }
            val workDetailsFlows = works.map { work ->
                getWorkDetails(work.id)
            }
            combine(workDetailsFlows) {it.toList()}
        }

    suspend fun insertWork(workDetails: WorkDetails) = withContext(dispatcher) {
        workRepository.insert(workDetails.work)
    }
}