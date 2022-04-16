package unsolved

import datastructures.Node
import java.util.*

class CloneGraph {
    fun cloneGraph(node: Node?): Node? {

        if (node == null) return null

        val queue = LinkedList<Node>()
        val nodeLookup = mutableMapOf<Int, Node>()
        val visited = mutableSetOf<Int>()
        queue.add(node)
        while (queue.isNotEmpty()) {

            val curNode = queue.poll()
            if (curNode.`val` in visited) continue
            visited.add(curNode.`val`)
            val newNode = nodeLookup.computeIfAbsent(curNode.`val`) { Node(it) }

            println("${curNode.`val`}")

            for (n in curNode.neighbors) {
                queue.add(n!!)
                val neighbor = nodeLookup.computeIfAbsent(n.`val`) { Node(it) }
                newNode.neighbors.add(neighbor)
            }
        }

        return nodeLookup[node.`val`]
    }
}

fun main() {
    val driver = CloneGraph()
    println("First clone")
    driver.cloneGraph(Node.simpleGraph())
        .let {
            println("2nd clone")
            driver.cloneGraph(it)
        }
        .let {
            println("3rd clone")
            driver.cloneGraph(it)
        }
}