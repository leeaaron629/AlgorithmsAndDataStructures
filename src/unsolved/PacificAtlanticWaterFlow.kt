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
    fun `find pacific atlantic squares - case 2`() {
        // [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20],
        // [76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,21],
        // [75,144,145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,95,22],
        // [74,143,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,161,96,23],
        // [73,142,203,256,257,258,259,260,261,262,263,264,265,266,267,268,219,162,97,24],
        // [72,141,202,255,300,301,302,303,304,305,306,307,308,309,310,269,220,163,98,25],
        // [71,140,201,254,299,336,337,338,339,340,341,342,343,344,311,270,221,164,99,26],
        // [70,139,200,253,298,335,364,365,366,367,368,369,370,345,312,271,222,165,100,27],
        // [69,138,199,252,297,334,363,384,385,386,387,388,371,346,313,272,223,166,101,28],
        // [68,137,198,251,296,333,362,383,396,397,398,389,372,347,314,273,224,167,102,29],
        // [67,136,197,250,295,332,361,382,395,400,399,390,373,348,315,274,225,168,103,30],
        // [66,135,196,249,294,331,360,381,394,393,392,391,374,349,316,275,226,169,104,31],
        // [65,134,195,248,293,330,359,380,379,378,377,376,375,350,317,276,227,170,105,32],
        // [64,133,194,247,292,329,358,357,356,355,354,353,352,351,318,277,228,171,106,33],
        // [63,132,193,246,291,328,327,326,325,324,323,322,321,320,319,278,229,172,107,34],
        // [62,131,192,245,290,289,288,287,286,285,284,283,282,281,280,279,230,173,108,35],
        // [61,130,191,244,243,242,241,240,239,238,237,236,235,234,233,232,231,174,109,36],
        // [60,129,190,189,188,187,186,185,184,183,182,181,180,179,178,177,176,175,110,37],
        // [59,128,127,126,125,124,123,122,121,120,119,118,117,116,115,114,113,112,111,38],
        // [58,57,56,55,54,53,52,51,50,49,48,47,46,45,44,43,42,41,40,39]

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