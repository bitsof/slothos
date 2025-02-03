package pro.selecto.slothos.data.model

import java.math.BigDecimal

interface MeasurementBase {
    val id: Int
    val name: String
    val description: String
    val symbol: String
    val conversionToBase: BigDecimal
    val categoryType: MeasurementCategory
    val defaultPrecision: Int
}