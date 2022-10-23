package datastructures

class DisjointSet<E>(
    private val disjointSets: MutableMap<E, Set<E>> = mutableMapOf(),
    private val parents: MutableMap<E, E?> = mutableMapOf()
) {

    /**
     * Given an element E find its set
     */
    fun find(e: E): E {
        var parent: E = e
        while (parents[parent] != null) {
            parent = parents[parent] ?: error("parents[parent] cannot be NULL: ${parents[parent]}")
        }
        return parent
    }

    fun union(e1: E, e2: E) {

        val parent1 = find(e1)
        val parent2 = find(e2)

        when {
            parent1 == parent2 -> {
            println("Cycle detected, when adding ${listOf(e1, e2)}. Skipping...")
            }
            parent1 == e1 && parent2 == e2 -> { // itself is the parent
                disjointSets[e1] = setOf(e1, e2)
                parents[e1] = null
                parents[e2] = e1
            }
            parent1 != e1 && parent2 == e2 -> { // E2 itself is the parent
                val s1 = disjointSets[parent1] ?: emptySet()
                val s2 = disjointSets[parent2] ?: setOf(e2)
                disjointSets[parent1] = s1 + s2
                parents[parent2] = parent1
                // Remove the secondary set
                disjointSets.remove(parent2)
            }
            parent1 == e1 && parent2 != e1 -> { // E1 itself is the parent
                val s1 = disjointSets[parent1] ?: setOf(e1)
                val s2 = disjointSets[parent2] ?: emptySet()
                disjointSets[parent2] = s1 + s2
                parents[parent1] = parent2
                // Remove the secondary set
                disjointSets.remove(parent1)
            }
            parent1 != e1 && parent2 != e2 -> { // Both E1 and E2 has different parents and belongs to different set
                val s1 = disjointSets[parent1] ?: emptySet()
                val s2 = disjointSets[parent2] ?: emptySet()
                disjointSets[parent1] = s1 + s2
                parents[parent2] = parent1
                disjointSets.remove(parent2)
            }
            else -> error("Illegal state between parents=${listOf(parent1, parent2)} and nodes=${listOf(e1, e2)}")
        }

    }

    override fun toString(): String {
        return disjointSets.entries.joinToString("\n") { (k, v) -> "$k -> $v" }
    }

}
