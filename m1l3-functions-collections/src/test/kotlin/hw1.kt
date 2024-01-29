import kotlin.test.Test
import kotlin.test.assertEquals


/*
* Реализовать функцию, которая преобразует список словарей строк в ФИО
* Функцию сделать с использованием разных функций для разного числа составляющих имени
* Итого, должно получиться 4 функции
*
* Для успешного решения задания, требуется раскомментировать тест, тест должен выполняться успешно
* */
class HomeWork1Test {


    @Test
    fun mapListToNamesTest() {
        val input = listOf(
            mapOf(
                "first" to "Иван",
                "middle" to "Васильевич",
                "last" to "Рюрикович",
            ),
            mapOf(
                "first" to "Петька",
            ),
            mapOf(
                "first" to "Сергей",
                "last" to "Королев",
            ),
        )
        val expected = listOf(
            "Рюрикович Иван Васильевич",
            "Петька",
            "Королев Сергей",
        )
        val res = mapListToNames(input)
        assertEquals(expected, res)
    }


    private fun mapListToNames(inputList: List<Map<String, String>>): List<String> {
        val list: MutableList<String> = mutableListOf()
        for (map in inputList) {
            if (map.size == 3) {
                list.add(mapWhenThree(map))
            }
            if (map.size == 2) {
                list.add(mapWhenTwo(map))
            }
            if (map.size == 1) {
                list.add(mapWhenOne(map))
            }
        }
        return list
    }

    private fun mapWhenThree(map: Map<String, String>): String {
        val last = map.getOrDefault(KEY_1, "")
        val first = map.getOrDefault(KEY_2, "")
        val middle = map.getOrDefault(KEY_3, "")

        return "$last $first $middle";
    }

    private fun mapWhenTwo(map: Map<String, String>): String {
        val last = map.getOrDefault(KEY_1, "")
        val first = map.getOrDefault(KEY_2, "")

        return "$last $first";
    }

    private fun mapWhenOne(map: Map<String, String>): String {
        return map.get(KEY_1)
            ?: map.get(KEY_2)
            ?: map.getOrDefault(KEY_3, "")

    }


    companion object {
        const val KEY_1: String = "last"
        const val KEY_2: String = "first"
        const val KEY_3: String = "middle"
    }
}
