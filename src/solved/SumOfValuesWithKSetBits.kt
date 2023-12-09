package solved

import org.junit.jupiter.api.Test

/**
 * https://leetcode.com/problems/sum-of-values-at-indices-with-k-set-bits/
 */

class SumOfValuesWithKSetBits {

    fun sumIndicesWithKSetBits(nums: List<Int>, k: Int): Int {
        fun hasKBits(index: Int, k: Int): Boolean {
            var currentValue = index
            var bitCount = 0
            while (currentValue != 0) {
                if ((currentValue and 1) == 1) bitCount++
                if (bitCount > k) return false
                currentValue = currentValue shr 1
            }
            return bitCount == k
        }
        var sum = 0
        nums.forEachIndexed { index, n ->
            if (hasKBits(index = index, k = k)) {
                println("$index has $k bits")
                sum += n
            } else {
                println("$index does not have $k bits")
            }
        }
        Integer.bitCount(1)
        return sum
    }

}

class SumOfValuesWithKSetBitsTest {

    private val sumOfValuesWithKSetBits = SumOfValuesWithKSetBits()

    @Test
    fun `test sumIndiciesWithSetBits - case 1`() {
        val result = sumOfValuesWithKSetBits.sumIndicesWithKSetBits(
            nums = listOf(5,10,1,5,2), k = 1
        ).also { println(it) }
        assert(result == 13)
    }

    @Test
    fun `sandbox`() {
    }

}