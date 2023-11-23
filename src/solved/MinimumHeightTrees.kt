package solved

import org.junit.jupiter.api.Test
import java.util.*

/**
 * https://leetcode.com/problems/minimum-height-trees/
 */
class MinimumHeightTrees {

    private fun print(nGraphEdges: Array<Int>) {
        nGraphEdges.mapIndexed { idx, value -> "$idx -> $value" }.joinToString(" | ").also { println(it) }
    }

    fun findMinHeightTrees(n: Int, edges: Array<IntArray>): List<Int> {

        // Build Tree
        val neighbors = Array(n) { mutableSetOf<Int>() }
        val nDegrees = Array(n) { 0 }
        for (edge in edges) {
            val a = edge[0]
            val b = edge[1]
            neighbors[a].add(b)
            neighbors[b].add(a)
            nDegrees[a]++
            nDegrees[b]++
        }

        // Cut each layer off
        val layers = mutableListOf<List<Int>>()
        val notVisited = (0 until n).toHashSet()
        while (notVisited.size > 1) {

            // Add leaves node to visit
            val queue: Queue<Int> = LinkedList()
            neighbors.forEachIndexed { idx, _ ->
                if (nDegrees[idx] == 1) queue.add(idx)
            }

            val leaves = mutableListOf<Int>()
            while (queue.isNotEmpty()) {
                val leaf = queue.remove() // Get the leaf node
                notVisited.remove(leaf)
                leaves.add(leaf)
                // Cut off each leave (node)
                for (neighbor in neighbors[leaf]) {
                    nDegrees[neighbor]--
                    nDegrees[leaf]--
                }
            }
            // Add the cut-off leaves to the new layer
            layers.add(leaves)
        }

        return if (notVisited.isNotEmpty()) notVisited.toList() else layers.last()
    }


}

class MinimumHeightTreesTest private constructor() {

    private val minimumHeightTrees = MinimumHeightTrees()

    @Test
    fun `test findMinHeightTrees base case 1`() {
        val edges = listOf(
            listOf(1,0), listOf(1,2), listOf(1,3)
        ).map { it.toIntArray() }.toTypedArray()
        val minHeightRoots = minimumHeightTrees
            .findMinHeightTrees(n = 4, edges = edges)
            .also { println(it) }

        assert(minHeightRoots == listOf(1))
    }

    @Test
    fun `test findMinHeightTrees base case 2`() {
        val edges = listOf(
            listOf(3,0), listOf(3,1), listOf(3,2), listOf(3,4), listOf(5,4)
        ).map { it.toIntArray() }.toTypedArray()
        val minHeightRoots = minimumHeightTrees
            .findMinHeightTrees(n = 6, edges = edges)
            .also { println(it) }

        assert(minHeightRoots == listOf(3,4))
    }


}