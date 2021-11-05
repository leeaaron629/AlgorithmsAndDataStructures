package solved

import datastructures.TreeNode
import java.util.*

object SumOfLeftLeaves {

    fun sumOfLeftLeaves(root: TreeNode?): Int {

        var sum = 0
        val nodeQ = LinkedList<TreeNode>().also { it.push(root) }
        while (nodeQ.isNotEmpty()) {

            val curNode = nodeQ.poll()
            curNode.left?.let {
                if (it.isLeaf()) sum += it.`val`
                nodeQ.add(it)
            }
            curNode.right?.let { nodeQ.add(it) }

        }

        return sum
    }

    private fun TreeNode.isLeaf(): Boolean {
        return left == null && right == null
    }

}
