package solved

/**
 * https://leetcode.com/problems/maximum-number-of-occurrences-of-a-substring/
 */
object MaxNumberOfOccurrencesOfSubstring {
    fun maxFreq(s: String, maxLetters: Int, minSize: Int, maxSize: Int): Int {
        val substringCounts = mutableMapOf<String, Int>()
        for (i in 0..s.length - minSize) {
            val substring = s.substring(startIndex = i, endIndex = i + minSize)
            if (substring.toSet().size <= maxLetters) {
                substringCounts[substring] = substringCounts.getOrDefault(substring, 0) + 1
            }
        }
        return substringCounts.values.maxOrNull() ?: 0
    }
}

fun main() {
    MaxNumberOfOccurrencesOfSubstring
        .maxFreq(s = "aababcaababab", maxLetters = 2, minSize = 3, maxSize = 4)
        .also { assert(it == 3) { "Actual: $it" }  }
}