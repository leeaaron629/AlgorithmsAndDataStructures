package solved

/**
 * https://leetcode.com/problems/container-with-most-water/
 */
object ContainerWithMostWater {
    fun maxArea(height: IntArray): Int {

        var i = 0
        var j = height.size - 1
        var maxArea = 0

        while (i < j) {

            val minHeight = height[i].coerceAtMost(height[j])
            val distance = j - i
            val curArea = minHeight * distance

            maxArea = maxArea.coerceAtLeast(curArea)

            when {
                height[i] < height[j] -> i++
                height[j] < height[i] -> j--
                else -> i++
            }
        }

        return maxArea
    }
}

fun main() {
    ContainerWithMostWater
        .maxArea(intArrayOf(1,8,6,2,5,4,8,3,7))
        .also { println(it) }
}