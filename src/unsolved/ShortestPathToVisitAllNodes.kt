package unsolved

import org.junit.jupiter.api.Test
import java.util.*

/**
 * https://leetcode.com/problems/shortest-path-visiting-all-nodes
 * TODO - Study bit-masking algorithm first
 */
class ShortestPathToVisitAllNodes {

    fun shortestPathLength(graph: Array<IntArray>): Int {
        return dfs(graph, currNode = 0, visited = mutableSetOf(), pathTook = mutableListOf(0))
    }

    private fun dfs(graph: Array<IntArray>, currNode: Int, visited: MutableSet<Int>, pathTook: List<Int>): Int {
        println("$currNode - $pathTook")
        visited.add(currNode)
        for (neighbor in graph[currNode]) {
            if (neighbor !in visited) dfs(graph, neighbor, visited, pathTook + neighbor)
        }
        return -1
    }

}

class ShortestPathToVisitAllNodesTest private constructor() {

    private val shortestPathToVisitAllNodes = ShortestPathToVisitAllNodes()

    @Test
    fun `test shortestPathLength - case 1`() {
        val graph = listOf(
            listOf(1,2,3), listOf(0), listOf(0), listOf(0)
        ).map { it.toIntArray() }.toTypedArray()
        val result = shortestPathToVisitAllNodes.shortestPathLength(graph = graph)
        println(result)
    }

    @Test
    fun `test shortestPathLength - case 2`() {
        val graph = listOf(
            listOf(1), listOf(0,2,4), listOf(1,3,4), listOf(2), listOf(1,2)
        ).map { it.toIntArray() }.toTypedArray()
        val result = shortestPathToVisitAllNodes.shortestPathLength(graph = graph)
        println(result)
    }

}