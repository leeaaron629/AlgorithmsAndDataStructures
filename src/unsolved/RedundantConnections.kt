package unsolved

import datastructures.DisjointSet
import datastructures.DisjointSetDetailed

/**
 * https://leetcode.com/problems/redundant-connection/submissions/
 */
class RedundantConnections {

    fun findRedundantConnection(edges: Array<IntArray>): IntArray {

        val disjointSet = DisjointSet<Int>()

        for (e in edges) {
            val p1 = disjointSet.find(e[0])
            val p2 = disjointSet.find(e[1])
            if (p1 == p2) return e
            disjointSet.union(e[0], e[1])
        }

        return intArrayOf()
    }

}

fun main() {

    val driver = RedundantConnections()
    // [[1,2],[1,3],[2,3]]
    val edges: Array<IntArray> = arrayOf(
        intArrayOf(1,2), intArrayOf(1,3), intArrayOf(2,3),
    )

    val disjointSet: DisjointSetDetailed<Int> = DisjointSetDetailed()

    for (e in edges) {
        println("Union between ${e[0]} & ${e[1]}")
        disjointSet.union(e1 = e[0], e2 = e[1])
    }

    println("Disjoint Set:\n$disjointSet")

}

