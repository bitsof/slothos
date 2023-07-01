package pro.selecto.slothos.utils

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pro.selecto.slothos.data.entities.Category
import pro.selecto.slothos.data.entities.Equipment
import pro.selecto.slothos.data.entities.Exercise
import pro.selecto.slothos.data.entities.Force
import pro.selecto.slothos.data.entities.Level
import pro.selecto.slothos.data.entities.Mechanic
import pro.selecto.slothos.data.entities.Muscle
import java.io.File
import java.net.URL

fun generateJsonFiles(): Unit {
    // use json files to generate entries for Category, Equipment, Force, Level, Mechanic, Muscle, Tag
    // insert those entries
    // Category
    val categoryNames: Array<String> = arrayOf("powerlifting", "strength", "stretching", "cardio", "olympic weightlifting", "strongman", "plyometrics")
    val categoryList = mutableListOf<Category>()
    for(name in categoryNames){
        categoryList.add(Category(name = name, description = ""))
    }
    writeToAsset(serializableList = categoryList, jsonFileName = "category")

    // Equipment
    val equipmentNames: Array<String> = arrayOf("medicine ball", "dumbbell", "body only", "bands", "kettlebells", "foam roll", "cable", "machine", "barbell", "exercise ball", "e-z curl bar", "other")
    val equipmentList = mutableListOf<Equipment>()
    for(name in equipmentNames){
        equipmentList.add(Equipment(name = name, description = ""))
    }
    writeToAsset(serializableList = equipmentList, jsonFileName = "equipment")

    // Force
    val forceNames: Array<String> = arrayOf("static", "pull", "push")
    val forceList = mutableListOf<Force>()
    for(name in forceNames){
        forceList.add(Force(name = name, description = ""))
    }
    writeToAsset(serializableList = forceList, jsonFileName = "force")

    // Level
    val levelNames: Array<String> = arrayOf("beginner", "intermediate", "expert")
    val levelList = mutableListOf<Level>()
    for(name in levelNames){
        levelList.add(Level(name = name))
    }
    writeToAsset(serializableList = levelList, jsonFileName = "level")

    // Mechanic
    val mechanicNames: Array<String> = arrayOf("isolation", "compound",)
    val mechanicList = mutableListOf<Mechanic>()
    for(name in mechanicNames){
        mechanicList.add(Mechanic(name = name, description = ""))
    }
    writeToAsset(serializableList = mechanicList, jsonFileName = "mechanic")

    // Muscle
    val muscleNames: Array<String> = arrayOf("abdominals", "abductors", "adductors", "biceps", "calves", "chest", "forearms", "glutes", "hamstrings", "lats", "lower back", "middle back", "neck", "quadriceps", "shoulders", "traps", "triceps")
    val muscleList = mutableListOf<Muscle>()
    for(name in muscleNames){
        muscleList.add(Muscle(name = name, description = ""))
    }
    writeToAsset(serializableList = muscleList, jsonFileName = "muscle")
}

fun getData(): List<Exercise> {
    // Tag
    val tagNames: Array<String> = arrayOf("Default")

    // gets json data from github
    val rawJson: String = URL("https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/dist/exercises.json").readText()
    val data = Json.decodeFromString<List<RawExercise>>(rawJson)
    val exerciseList = mutableListOf<Exercise>()

    for (element in data) {
        // add exercise to list of exercises
        exerciseList.add(Exercise(nameId = element.id, name = element.name, instructions = element.instructions.joinToString()))

        // use json data and queries to build fk relationships
        // iterate through all of category, equipment, force, level, mechanic, muscle, tag
        // build fk relationship

    }
    println("Initial exercise count: " + exerciseList.size)
    return exerciseList
}

// Data class and enums representing the schema of raw exercise database
// Defined here : https://github.com/yuhonas/free-exercise-db/blob/main/schema.json
// Enums can be simply represented as either a string or list of strings

@Serializable
data class RawExercise(
    val id: String,
    val name: String,
    val force: String?,
    val level: String,
    val mechanic: String?,
    val equipment: String?,
    val primaryMuscles: List<String>,
    val secondaryMuscles: List<String?>,
    val instructions: List<String>,
    val category: String,
    val images: List<String>,
)

class JsonGenerator {
}

fun main() {
    generateJsonFiles()
}

inline fun <reified T> writeToAsset(
    serializableList: List<T>,
    jsonFileName: String,
) : Unit {
    val jsonAssetPath = "./app/src/main/assets/json/"
    val format = Json{ prettyPrint = true }
    val file = File("$jsonAssetPath$jsonFileName.json")
    file.createNewFile()
    println(format.encodeToString(serializableList))
    file.writeText(format.encodeToString(serializableList))
    return
}