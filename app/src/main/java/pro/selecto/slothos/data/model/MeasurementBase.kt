package pro.selecto.slothos.data.model

interface MeasurementBase {
    val id: Int
    val name: String
    val description: String
    val symbol: String
    val conversionToBase: Double
    val categoryType: MeasurementCategory
    val defaultPrecision: Int
}