package pro.selecto.slothos.data.model

enum class StandardMeasurementType(
    override val categoryType: MeasurementCategory,
    override val symbol: String,
    override val conversionToBase: Double,
    override val defaultPrecision: Int,
): MeasurementBase {
    // Distance
    METERS(MeasurementCategory.DISTANCE, "m", 1.0, 2),
    KILOMETERS(MeasurementCategory.DISTANCE, "km", 1000.0, 3),
    MILES(MeasurementCategory.DISTANCE, "mi", 1609.344, 3),

    // Size
    MILLIMETERS(MeasurementCategory.SIZE, "mm", 1.0, 1),
    INCHES(MeasurementCategory.SIZE, "in", 25.4, 2),

    // Weight
    KILOGRAMS(MeasurementCategory.WEIGHT, "kg", 1.0, 2),
    POUNDS(MeasurementCategory.WEIGHT, "lbs", 0.45359237, 2),

    // Effort
    RPE(MeasurementCategory.EFFORT, "RPE", 1.0, 1),
    RIR(MeasurementCategory.EFFORT, "RIR", 1.0, 1);

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