package datastructures

import org.junit.jupiter.api.Test

internal class DisjointSetTest {

    @Test
    fun `test non-cyclic graph from 1 to 5`() {
        val edges = listOf(
            1 to 2,
            2 to 3,
            3 to 4,
            4 to 5
        )

        val disjointSet = DisjointSet<Int>()
        for (e in edges) {
            println("Union of ${e.first} & ${e.second}")
            disjointSet.union(e1 = e.first, e2 = e.second)
        }
        println("Disjoint Set:\n${disjointSet}")
    }

    @Test
    fun `test cyclic graph from 1 to 5`() {
        val edges = listOf(
            1 to 2,
            2 to 3,
            3 to 4,
            4 to 5,
            5 to 1
        )

        val disjointSet = DisjointSet<Int>()
        for (e in edges) {
            disjointSet.union(e1 = e.first, e2 = e.second)
        }
        println("Disjoint Set:\n${disjointSet}")
    }

    @Test
    fun `test 4 node islands`() {
        val edges = listOf(
            // Island #1
            1 to 2,
            3 to 4,
            2 to 3,
            4 to 1,
            // Island #2
            5 to 6,
            7 to 8,
            6 to 7,
            8 to 5
        )
        val disjointSet = DisjointSet<Int>()
        for (e in edges) {
            disjointSet.union(e1 = e.first, e2 = e.second)
        }
        println("Disjoint Set:\n${disjointSet}")
    }


}