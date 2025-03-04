package solved

import java.util.*

/**
 * https://leetcode.com/problems/simplify-path/
 */
class SimplifyPath {

    private val CURRENT = "."
    private val PARENT = ".."

    fun simplifyPath(path: String): String {

        val chunks = path.split("/")
        val stack = Stack<String>()
        for (c in chunks) {
            if (c == CURRENT || c == "") continue
            if (c == PARENT) {
                if (stack.isNotEmpty()) stack.pop()
            } else {
                stack.push(c)
            }
        }
        return "/" + stack.joinToString("/")
    }
}

fun main() {
    (0..5).joinToString(" ").also { println(it) }
    (0 until 5).joinToString(" ").also { println(it) }
    (0..5 step 2).joinToString(" ").also { println(it) }
    (5 downTo 0).joinToString(" ").also { println(it) }
    (5 downTo 0 step 2).joinToString(" ").also { println(it) }
}