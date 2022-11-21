package unsolved

import org.junit.jupiter.api.Test

/**
 * https://leetcode.com/problems/pacific-atlantic-water-flow/
 */
class PacificAtlanticWaterFlow {

    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {

        if (heights.isEmpty()) return emptyList()

        val islandWidth = heights[0].size
        val islandLength = heights.size
        val atlantic: Array<BooleanArray> = Array(islandLength) { BooleanArray(islandWidth) { false } }
        val pacific: Array<BooleanArray> = Array(islandLength) { BooleanArray(islandWidth) { false } }

        heights.forEachIndexed { x, row ->
            row.forEachIndexed { y, value ->
                println("Starting dfs on ... ($x, $y)")
                val visited = Array(islandLength) { IntArray(islandWidth) { 0 } }
                dfs(x = x, y = y, islandWidth = islandWidth, islandLength = islandLength, visited = visited, atlantic = atlantic, pacific = pacific, heights = heights)
            }
        }

        return emptyList()
    }

    private fun dfs(
        x: Int,
        y: Int,
        islandWidth: Int,
        islandLength: Int,
        visited: Array<IntArray>,
        atlantic: Array<BooleanArray>,
        pacific: Array<BooleanArray>,
        heights: Array<IntArray>
    ): Pair<Int, Int> {
        if (visited[x][y] == 1) Pair(0, 0)
        // println("Visiting ($x, $y) - ${heights[x][y]}")
        visited[x][y] = 1 // Set to 1 for visited
        if (isByThePacific(x = x, y = y)) atlantic[x][y] = true
        if (isByTheAtlantic(x = x, y = y, islandWidth = islandWidth, islandLength = islandLength)) pacific[x][y]
        val score = when {
            isByThePacific && isByTheAtlantic -> Pair(1, 1)
            isByThePacific -> Pair(1, 0)
            isByTheAtlantic -> Pair(0, 1)
            else -> Pair(0, 0)
        }

        val childrenScores = getValidNearbyLands(x, y, islandWidth, islandLength).map { (nextX, nextY) ->
            if (canFlowTo(sourceX = x, sourceY = y, targetX = nextX, targetY = nextY, heights = heights)) {
                dfs(nextX, nextY, islandWidth, islandLength, visited, heights)
            } else {
                Pair(0, 0)
            }
        }
        return childrenScores.fold(score) { acc, aScore -> Pair(acc.first + aScore.first, acc.second + aScore.second) }
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
            x in 0 until islandWidth && y in 0 until islandLength
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

}