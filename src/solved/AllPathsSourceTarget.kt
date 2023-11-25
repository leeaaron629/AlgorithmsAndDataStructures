package unsolved

import org.junit.jupiter.api.Test
import java.util.*

/**
 * https://leetcode.com/problems/all-paths-from-source-to-target
 */
class AllPathsSourceTarget {

    // My solution
    fun allPathsSourceTarget(graph: Array<IntArray>): List<List<Int>> {

        val answers = mutableListOf<List<Int>>()

        answers.add(findPath(
            graph = graph,
            current = 0,
            target = graph.size - 1,
            lineage = mutableListOf(),
            answers = answers
        ))

        return answers.filter { it.isNotEmpty() }
    }

    private fun findPath(
        graph: Array<IntArray>,
        current: Int,
        target: Int,
        lineage: List<Int>,
        answers: MutableList<List<Int>>
    ): List<Int> {

        if (current == target) return lineage + current

        for (neighbor in graph[current]) {
            answers.add(findPath(graph, neighbor, target, lineage + current, answers))
        }

        return emptyList()
    }

    // Solution is provided by a Leetcode User - Slow, but simple
    fun allPathsSourceTargetV2(graph: Array<IntArray>): List<List<Int>> {
        return aux(graph, 0)
    }

    private fun aux(graph: Array<IntArray>, index: Int): List<List<Int>> {
        return if (index == graph.lastIndex) {
            listOf(listOf(index))
        } else {
            val childrenPaths = graph[index]
                .map { child -> aux(graph, child) }
                .also { println(it) }
                .flatten()
                .also { println("Flatten: $it") }
            childrenPaths.map { listOf(index) + it }
        }
    }

    // Solution is provided by Leetcode
    fun allPathSourceTargetBacktrack(graph: Array<IntArray>): List<List<Int>> {
        val answers = mutableListOf<List<Int>>()
        backtrack(graph, 0, graph.lastIndex, LinkedList<Int>().apply { add(0) }, answers = answers)
        return answers
    }

    private fun backtrack(graph: Array<IntArray>, currNode: Int, target: Int, path: LinkedList<Int>, answers: MutableList<List<Int>>) {
        if (currNode == target) {
            answers.add(path.toList())
        }

        for (neighbor in graph[currNode]) {
            path.addLast(neighbor)
            backtrack(graph, neighbor, target, path, answers)
            path.removeLast()
        }
    }

    // Solution is provided by Leetcode
    fun allPathSourceTargetTopDownDp(graph: Array<IntArray>): List<List<Int>> {
        return allPathsToTarget(graph, 0, graph.lastIndex, mutableMapOf())
    }

    private fun allPathsToTarget(graph: Array<IntArray>, currNode: Int, target: Int, memo: MutableMap<Int, List<List<Int>>>): List<List<Int>> {

        if (memo.containsKey(currNode)) {
            return memo[currNode]!!
        }

        val results = mutableListOf<List<Int>>()
        if (currNode == target) {
            val path = mutableListOf(target)
            results.add(path)
            return results
        }

        for (neighbor in graph[currNode]) {
            for (path in allPathsToTarget(graph, neighbor, target, memo)) {
                val newPath = mutableListOf(currNode)
                newPath.addAll(path)
                results.add(newPath)
            }
        }

        memo[currNode] = results
        println(memo)
        return results
    }

}

class AllPathSourceTargetTest private constructor() {

    private val allPathSourceTarget = AllPathsSourceTarget()

    @Test
    fun `test allPathsSourceTarget - case 1`() {
        val graph = arrayOf(
            listOf(1,2), listOf(3), listOf(3), listOf()
        ).map { it.toIntArray() }.toTypedArray()

        val results = allPathSourceTarget.allPathSourceTargetTopDownDp(graph)
            .also { println(it) }
        assert(results == listOf(listOf(0,1,3), listOf(0,2,3)))

    }

    @Test
    fun `test allPathsSourceTarget - case 2`() {
        val graph = arrayOf(
            listOf(4,3,1), listOf(3,2,4), listOf(3), listOf(4), listOf()
        ).map { it.toIntArray() }.toTypedArray()

        val results = allPathSourceTarget.allPathSourceTargetTopDownDp(graph)
            .also { println(it) }
        assert(results == listOf(listOf(0,4), listOf(0,3,4), listOf(0,1,3,4), listOf(0,1,2,3,4), listOf(0,1,4)))
    }

}