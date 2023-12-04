package solved

import org.junit.jupiter.api.Test

data class TravelInfo(
    val travelerCount: Long,
    val fuelCost: Long
)

/**
 * https://leetcode.com/problems/minimum-fuel-cost-to-report-to-the-capital/
 */
class MinimumFuelCostToReportToCapital {

    fun minimumFuelCost(roads: Array<IntArray>, seats: Int): Long {
        if (roads.isEmpty()) return 0

        val graph = Array<MutableList<Int>>(roads.size + 1) { mutableListOf() }
        roads.forEach { road ->
            graph[road[0]].add(road[1])
            graph[road[1]].add(road[0])
        }

        graph
            .joinToString("\n") { it.toString() }
            .also { println(it) }

        val travelInfo = travelToCapital(graph = graph, node = 0, visited = mutableSetOf(), seats = seats)
        return travelInfo.fuelCost.toLong()
    }

    private fun travelToCapital(
        graph: Array<MutableList<Int>>,
        node: Int,
        seats: Int,
        visited: MutableSet<Int>
    ): TravelInfo {

        visited.add(node)

        val travelInfoList = mutableListOf<TravelInfo>()
        for (neighbor in graph[node]) {
            if (neighbor !in visited) {
                travelInfoList.add(travelToCapital(graph = graph, node = neighbor, visited = visited, seats = seats))
            }
        }

        // Initialize with 1 travel of the current node. No fuel cost
        val travelInfoAccumulated = travelInfoList.fold(TravelInfo(travelerCount = 1, fuelCost = 0)) { acc, travelInfo ->
            TravelInfo(travelerCount = travelInfo.travelerCount + acc.travelerCount, fuelCost = travelInfo.fuelCost + acc.fuelCost)
        }

        // If it's 0 do no add additional cost cause capital is reached
        if (node == 0) return travelInfoAccumulated

        val additionalFuelCost: Long = ceilDiv(travelInfoAccumulated.travelerCount.toLong(), seats.toLong())
        return TravelInfo(
            travelerCount = travelInfoAccumulated.travelerCount,
            fuelCost = travelInfoAccumulated.fuelCost + additionalFuelCost
        )
    }

    private fun ceilDiv(x: Long, y: Long): Long {
        val q = x / y
        // if the signs are the same and modulo not zero, round up
        // if the signs are the same and modulo not zero, round up
        return if (x xor y >= 0 && q * y != x) {
            q + 1
        } else q
    }

}

class MinimumFuelCostToReportToCapitalTest {

    private val minimumFuelCostToReportToCapital = MinimumFuelCostToReportToCapital()

    @Test
    fun `test minimumFuelCost - Case 1`() {
        // [[0,1],[0,2],[0,3]]
        val roads = listOf(
            listOf(0,1), listOf(0,2), listOf(0,3)
        ).map { it.toIntArray() }.toTypedArray()
        minimumFuelCostToReportToCapital
            .minimumFuelCost(roads = roads, seats = 2)
            .also { println(it) }
    }

    @Test
    fun `test minimumFuelCost - Case 2`() {
        // [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]]
        val roads = listOf(
            listOf(3,1), listOf(3,2), listOf(1,0), listOf(0,4), listOf(0,5), listOf(4,6)
        ).map { it.toIntArray() }.toTypedArray()
        minimumFuelCostToReportToCapital
            .minimumFuelCost(roads = roads, seats = 2)
            .also { println(it) }
    }

    @Test
    fun `test minimumFuelCost - Case 3 - seats=1`() {
        // [0,1], [1,2], [3,4], [4,5]
        val nCities = 99
        val roads = (0..nCities).toList()
            .map { n -> listOf(n, n + 1).toIntArray() }
            .toTypedArray()
        minimumFuelCostToReportToCapital
            .minimumFuelCost(roads = roads, seats = 1)
            .also { println(it) }
    }

}