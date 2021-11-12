package unsolved

object TwoSum {

    fun twoSum(nums: IntArray, target: Int): IntArray {

        val neededValueAndIndex: Map<Int, Int> = nums
            .withIndex().associate {
                val neededValue = target - it.value
                neededValue to it.index
            }


        nums.withIndex().forEach {
            if (it.value in neededValueAndIndex.keys) {
                val neededValueIndex: Int = neededValueAndIndex[it.value]!!
                if (neededValueIndex != it.index) {
                    return listOf(it.index, neededValueIndex).toIntArray()
                }
            }
        }

        return listOf<Int>().toIntArray()
    }

}

fun main() {
    TwoSum.twoSum(listOf(3, 2, 4).toIntArray(), 6).toList().also { println(it) }
}