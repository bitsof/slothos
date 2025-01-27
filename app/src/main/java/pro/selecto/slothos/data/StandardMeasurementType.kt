package pro.selecto.slothos.data

import java.math.BigDecimal

enum class StandardMeasurementType(
    override val categoryType: MeasurementCategory,
    override val symbol: String,
    override val conversionToBase: BigDecimal,
    override val defaultPrecision: Int,
):MeasurementBase {
    // Distance
    METERS(MeasurementCategory.DISTANCE, "m", BigDecimal("1"), 2),
    KILOMETERS(MeasurementCategory.DISTANCE, "km", BigDecimal("1000"), 3),
    MILES(MeasurementCategory.DISTANCE, "mi", BigDecimal("1609.34"), 3),

    // Size
    MILLIMETERS(MeasurementCategory.SIZE, "mm", BigDecimal("1"), 1),
    INCHES(MeasurementCategory.SIZE, "in", BigDecimal("25.4"), 2),

    // Weight
    KILOGRAMS(MeasurementCategory.WEIGHT, "kg", BigDecimal("1"), 2),
    POUNDS(MeasurementCategory.WEIGHT, "lbs", BigDecimal("0.453592"), 2),

    // Effort
    RPE(MeasurementCategory.EFFORT, "RPE", BigDecimal("1"), 1),
    RIR(MeasurementCategory.EFFORT, "RIR", BigDecimal("1"), 1);

    // Time
    

    override val id: Int = 0 // Hardcoded measurements have id = 0
    override val description: String = "Standard measurement type: $name"
}

enum class MeasurementCategory {
    DISTANCE,
    SIZE,
    WEIGHT,
    EFFORT,
    TIME,
}