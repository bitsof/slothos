package pro.selecto.slothos.data

import org.junit.jupiter.api.Test
import pro.selecto.slothos.utils.generateJsonFiles
import pro.selecto.slothos.utils.getData

internal class JsonDataKtTest(){
    @Test
    fun checkWorks() {
        var check = true
        val data = getData()
        for (element in data) {
            println(element)
        }
        assert(check)
    }

    @Test
    fun checkGenerateJson(){
        generateJsonFiles()
    }


    fun checkGetList() {

    }
}