package solved

import datastructures.TreeNode
import java.util.*

/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/solution/
 */
object LowestCommonAncestor {

    fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {

        var node = root
        val stack = Stack<TreeNode>()
        val pathP = linkedSetOf<TreeNode>()
        val pathQ = linkedSetOf<TreeNode>()

        while (true) {

            while (node != null) {
                stack.push(node)
                stack.push(node)
                node = node.left
            }

            if (stack.empty()) break

            node = stack.pop()

            if (stack.isNotEmpty() && stack.peek() == node) {
                node = node.right
            } else {
                println(node.`val`)
                if (node == p) {
                    pathP.addAll(stack)
                    pathP.add(node)
                }
                if (node == q) {
                    pathQ.addAll(stack)
                    pathQ.add(node)
                }
                node = null
            }

        }

        println("PathP: ${pathP.joinToString(",") { n -> n.`val`.toString() }}")
        println("PathQ: ${pathQ.joinToString(",") { n -> n.`val`.toString() }}")

        return pathP.zip(pathQ).lastOrNull { it.first == it.second }?.first
    }

}

fun main() {
    val listOne = listOf(1, 2, 3, 4, 5)
    val listTwo = listOf(1, 2, 3)
    println("lineOne.zip(listTwo)")
    listOne.zip(listTwo).forEach { println(it) }
    println("listTwo.zip(listOne)")
    listTwo.zip(listOne).forEach { println(it) }
}