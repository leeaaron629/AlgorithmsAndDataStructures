package unsolved

import org.junit.jupiter.api.Test
import java.util.*

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
class PacificAtlanticWaterFlow {

    data class Score(
        var atlantic: Int = 0,
        var pacific: Int = 0,
        var calculated: Boolean = false
    )

    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {


        if (heights.isEmpty()) return emptyList()

        val rowLength = heights[0].size
        val colLength = heights.size

        // +1 for each time it reaches the atlantic or the pacific
        val scoreGrid = Array(colLength) { Array(rowLength) { Score() } }

        val toVisit: Queue<List<Int>> = LinkedList()
        heights.forEachIndexed { x, rowOfHeights ->
            rowOfHeights.forEachIndexed { y, _ ->
                toVisit.add(listOf(x,y))
            }
        }

        while (toVisit.isNotEmpty()) {
            val coords = toVisit.remove()
//            println("Calculating scores for ${coords[0]}, ${coords[1]}")
            val calculatedScore = calcScoresDfs(
                x = coords[0], y = coords[1],
                islandWidth = rowLength, islandLength = colLength,
                heights = heights, scoreGrid = scoreGrid, visited = Array(colLength) { BooleanArray(rowLength) { false } }
            )
            scoreGrid[coords[0]][coords[1]] = calculatedScore
        }

        // Collect the calculation into the list of results
        val results = mutableListOf<List<Int>>()
        scoreGrid.forEachIndexed { x, rowOfScores ->
            rowOfScores.forEachIndexed { y, score ->
                if (score.atlantic > 0 && score.pacific > 0) {
                    results.add(listOf(x,y))
                }
            }
        }

        return results
    }

    private fun calcScoresDfs(
        x: Int,
        y: Int,
        islandWidth: Int,
        islandLength: Int,
        heights: Array<IntArray>,
        scoreGrid: Array<Array<Score>>,
        visited: Array<BooleanArray>
    ): Score {

//        println("$x, $y")
        visited[x][y] = true
        if (scoreGrid[x][y].calculated) return scoreGrid[x][y]

        val score = Score()

        if (isByThePacific(x, y)) ++score.pacific
        if (isByTheAtlantic(x, y, islandWidth, islandLength)) ++score.atlantic
        score.calculated = true

        val validLands = getValidNearbyLands(x, y, islandWidth, islandLength)
            .filter { (x2, y2) -> !visited[x2][y2] }
            .filter { (x2, y2) -> canFlowTo(sourceX = x, sourceY = y, targetX = x2, targetY = y2, heights) }
        val downstreamScores = validLands
            .map { (x, y) -> calcScoresDfs(x, y, islandWidth, islandLength, heights, scoreGrid, visited) }
        val finalScore = downstreamScores
            .fold(score) { acc, s -> Score(atlantic = acc.atlantic + s.atlantic, pacific = acc.pacific + s.pacific, calculated = true) }

        return finalScore
    }

    private fun canFlowTo(sourceX: Int, sourceY: Int, targetX: Int, targetY: Int, heights: Array<IntArray>): Boolean {
        return heights[sourceX][sourceY] >= heights[targetX][targetY]
    }

    private fun getValidNearbyLands(x: Int, y: Int, islandWidth: Int, islandLength: Int): List<Pair<Int, Int>> {
        return listOf(
            x to y + 1,
            x to y - 1,
            x + 1 to y,
            x - 1 to y
        ).filter { (x, y) ->
            x in 0 until islandLength && y in 0 until islandWidth
        }
    }

    private fun isByThePacific(x: Int, y: Int): Boolean {
        return x == 0 || y == 0
    }

    private fun isByTheAtlantic(x: Int, y: Int, islandWidth: Int, islandLength: Int): Boolean {
        return x == (islandWidth - 1) || y == (islandLength - 1)
    }

}

internal class PacificAtlanticWaterFlowTest {

    private val driver = PacificAtlanticWaterFlow()

    @Test
    fun `find pacific atlantic squares - base`() {
        val heights = arrayOf(
            intArrayOf(1)
        )
        driver.pacificAtlantic(heights = heights)
            .let {
                assert(it.isNotEmpty())
            }
    }

    @Test
    fun `find pacific atlantic squares - case 1`() {
        // [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
        val heights = arrayOf(
            intArrayOf(1,2,2,3,5),
            intArrayOf(3,2,3,4,4),
            intArrayOf(2,4,5,3,1),
            intArrayOf(6,7,1,4,5),
            intArrayOf(5,1,1,2,4)
        )
        driver.pacificAtlantic(heights = heights)
            .also { println(it) }
    }

    @Test
    fun `find pacific atlanctic squares - `() {
        // [[1,1],[1,1],[1,1]]
        val heights = arrayOf(
            intArrayOf(1,1),
            intArrayOf(1,1),
            intArrayOf(1,1)
        )
        driver.pacificAtlantic(heights = heights)
            .also { println(it) }
    }

}