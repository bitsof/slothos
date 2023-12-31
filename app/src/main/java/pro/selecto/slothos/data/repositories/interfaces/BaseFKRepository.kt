package pro.selecto.slothos.data.repositories.interfaces

interface BaseFKRepository<T> {

    /**
     * Insert a given foreign key relationship
     */
    suspend fun insertFK(fkPair: T)

    /**
     * Delete a given foreign key relationship
     */
    suspend fun deleteFK(fkPair: T)
}