package unsolved

import org.junit.jupiter.api.Test
import java.util.*

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
class PacificAtlanticWaterFlow {

    data class Score(
        var atlantic: Boolean = false,
        var pacific: Boolean = false,
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
                if (score.atlantic && score.pacific ) {
                    results.add(listOf(x,y))
                }
            }
        }

        return results
    }

    fun calcScoresDfs(
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

        score.pacific = isByThePacific(x, y)
        score.atlantic = isByTheAtlantic(x, y, islandWidth, islandLength)
        score.calculated = true

        val validLands = getValidNearbyLands(x, y, islandWidth, islandLength)
            .filter { (x2, y2) -> !visited[x2][y2] }
            .filter { (x2, y2) -> canFlowTo(sourceX = x, sourceY = y, targetX = x2, targetY = y2, heights) }
        val downstreamScores = validLands
            .map { (x, y) -> calcScoresDfs(x, y, islandWidth, islandLength, heights, scoreGrid, visited) }
        val finalScore = downstreamScores
            .fold(score) { acc, s -> Score(atlantic = acc.atlantic || s.atlantic, pacific = acc.pacific || s.pacific, calculated = true) }

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
        return x == (islandLength - 1) || y == (islandWidth - 1)
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
    fun `find pacific atlantic squares - case 2`() {
        val heights = arrayOf(
            intArrayOf(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20),
            intArrayOf(76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,21),
            intArrayOf(75,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,95,22),
            intArrayOf(74,143,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,161,96,23),
            intArrayOf(73,142,203,256,257,258,259,260,261,262,263,264,265,266,267,268,219,162,97,24),
            intArrayOf(72,141,202,255,300,301,302,303,304,305,306,307,308,309,310,269,220,163,98,25),
            intArrayOf(71,140,201,254,299,336,337,338,339,340,341,342,343,344,311,270,221,164,99,26),
            intArrayOf(70,139,200,253,298,335,364,365,366,367,368,369,370,345,312,271,222,165,100,27),
            intArrayOf(69,138,199,252,297,334,363,384,385,386,387,388,371,346,313,272,223,166,101,28),
            intArrayOf(68,137,198,251,296,333,362,383,396,397,398,389,372,347,314,273,224,167,102,29),
            intArrayOf(67,136,197,250,295,332,361,382,395,400,399,390,373,348,315,274,225,168,103,30),
            intArrayOf(66,135,196,249,294,331,360,381,394,393,392,391,374,349,316,275,226,169,104,31),
            intArrayOf(65,134,195,248,293,330,359,380,379,378,377,376,375,350,317,276,227,170,105,32),
            intArrayOf(64,133,194,247,292,329,358,357,356,355,354,353,352,351,318,277,228,171,106,33),
            intArrayOf(63,132,193,246,291,328,327,326,325,324,323,322,321,320,319,278,229,172,107,34),
            intArrayOf(62,131,192,245,290,289,288,287,286,285,284,283,282,281,280,279,230,173,108,35),
            intArrayOf(61,130,191,244,243,242,241,240,239,238,237,236,235,234,233,232,231,174,109,36),
            intArrayOf(60,129,190,189,188,187,186,185,184,183,182,181,180,179,178,177,176,175,110,37),
            intArrayOf(59,128,127,126,125,124,123,122,121,120,119,118,117,116,115,114,113,112,111,38),
            intArrayOf(58,57,56,55,54,53,52,51,50,49,48,47,46,45,44,43,42,41,40,39)
        )
        val rowLength = heights[0].size
        val colLength = heights.size
        val scoreGrid = Array(colLength) { Array(rowLength) { PacificAtlanticWaterFlow.Score() } }
//        val result = driver.calcScoresDfs(
//            x = 10, y = 9, islandWidth = rowLength,
//            islandLength = colLength, scoreGrid = scoreGrid,
//            visited = Array(colLength) { BooleanArray(rowLength) { false } }, heights = heights
//        )
        val result = driver.pacificAtlantic(heights)
        println(result)
    }

    @Test
    fun `find pacific atlantic squares - case 3`() {
        val heights = arrayOf(
            intArrayOf(19,16,16,12,14,0,17,11,2,0,18,9,13,16,8,8,8,13,17,9,16,9,4,7,1,19,10,7,0,15),
            intArrayOf(0,11,4,14,9,0,6,13,16,5,19,9,4,5,4,12,0,13,0,7,9,12,13,15,3,7,4,9,15,1),
            intArrayOf(13,14,12,12,12,16,6,15,13,1,8,9,11,14,14,10,19,11,10,0,5,18,4,12,7,13,17,15,18,1),
            intArrayOf(16,14,19,5,8,2,11,17,7,1,4,6,5,18,7,15,6,19,18,12,1,14,2,2,0,9,15,14,13,19),
            intArrayOf(17,4,12,9,12,10,12,10,4,5,12,7,2,12,18,10,10,8,6,1,5,13,10,3,5,3,11,4,8,11),
            intArrayOf(8,19,18,9,6,2,7,3,19,6,0,17,9,12,11,1,15,11,18,1,8,11,1,11,16,7,8,17,15,0),
            intArrayOf(7,0,5,11,1,7,12,18,12,1,5,2,11,7,18,12,0,11,9,18,5,2,3,1,1,1,8,14,19,5),
            intArrayOf(2,14,2,16,17,19,10,16,1,16,16,3,19,12,13,17,19,12,16,10,16,8,16,12,6,12,13,17,9,12),
            intArrayOf(8,1,10,5,7,0,15,19,8,15,4,12,18,18,13,11,5,2,8,3,15,4,3,7,7,14,15,11,6,16),
            intArrayOf(0,5,13,19,1,1,2,4,16,2,16,9,15,15,10,10,18,11,17,1,5,14,5,19,7,0,13,7,13,7),
            intArrayOf(11,6,16,12,4,2,9,11,17,19,12,10,6,16,17,5,1,18,19,7,15,1,14,0,3,19,7,3,4,13),
            intArrayOf(4,11,8,10,10,19,7,18,4,2,2,14,6,9,18,14,2,16,5,3,19,17,4,3,7,1,12,2,4,3),
            intArrayOf(14,16,3,11,13,13,6,16,18,0,17,19,4,1,14,12,4,17,5,19,8,13,15,3,15,4,1,14,12,10),
            intArrayOf(13,2,12,2,16,12,19,10,19,12,19,14,12,17,16,3,13,7,3,15,16,7,10,15,14,10,6,5,2,18),
        )
        val result = driver.pacificAtlantic(heights = heights)
            .also { println(it) }
        // ANS: [[0,29],[1,28],[2,28],[12,0],[12,1],[13,0]]
    }

    @Test
    fun `find pacific atlanctic squares - equal heights`() {
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