package unsolved

import kotlin.math.abs

object CheckAlmostEquivalent {
    fun checkAlmostEquivalent(word1: String, word2: String): Boolean {
        var i = 0
        var j = 0
        val freqMap1 = mutableMapOf<Char, Int>()
        val freqMap2 = mutableMapOf<Char, Int>()
        while (i < word1.length || j < word2.length) {

            if (i < word1.length) {
                val letter1: Char = word1[i++]
                val freq = freqMap1[letter1]?.let { it + 1 } ?: 1
                freqMap1[letter1] = freq
            }

            if (j < word2.length) {
                val letter2 = word2[j++]
                val freq = freqMap2[letter2]?.let { it + 1 } ?: 1
                freqMap2[letter2] = freq
            }
        }

        return (freqMap1.keys + freqMap2.keys).all { letter ->
            val freq1 = freqMap1[letter] ?: 0
            val freq2 = freqMap2[letter] ?: 0
            abs(freq2 - freq1) <= 3
        }
    }
}