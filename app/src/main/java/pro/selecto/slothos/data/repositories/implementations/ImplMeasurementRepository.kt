package pro.selecto.slothos.data.repositories.implementations

import kotlinx.coroutines.flow.Flow
import pro.selecto.slothos.data.dao.MeasurementDao
import pro.selecto.slothos.data.entities.Measurement
import pro.selecto.slothos.data.repositories.interfaces.BaseRepository
import javax.inject.Inject

class ImplMeasurementRepository @Inject constructor(private val measurementDao: MeasurementDao) : BaseRepository<Measurement> {
    override suspend fun getAllEntitiesStream(): Flow<List<Measurement>> = measurementDao.getAllMeasurements()

    override suspend fun getEntityById(id: Int): Flow<Measurement?> = measurementDao.getMeasurement(id)

    override suspend fun getEntityIdByName(name: String): Int? = measurementDao.getMeasurementId(name)

    override suspend fun insert(entity: Measurement) = measurementDao.insert(entity)

    override suspend fun delete(entity: Measurement) = measurementDao.delete(entity)

    override suspend fun update(entity: Measurement) = measurementDao.update(entity)
}