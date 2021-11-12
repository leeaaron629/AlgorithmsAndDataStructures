package unsolved

import java.util.*

data class Traveler(val x: Int, val y: Int)

/**
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 */
object ShortestPathBinaryMatrix {
    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {

        if (grid.isEmpty() && grid[0].isEmpty()) return -1
        if (grid[0][0] == 1) return -1

        val spotsToGoTo: Queue<Traveler> = LinkedList()
        fun goToCell(x: Int, y: Int, steps: Int) {
            // Index out of bounds check
            if (x >= grid.size || y >= grid[0].size || x < 0 || y < 0) return

            if (grid[x][y] == 0) {
                grid[x][y] = steps
                spotsToGoTo.offer(Traveler(x, y))
            }
        }
        goToCell(0, 0, 1)
        val destinationX = grid.size - 1
        val destinationY = grid[0].size - 1
        while (spotsToGoTo.isNotEmpty()) {
            val (x, y) = spotsToGoTo.remove()

            if (x == destinationX && y == destinationY) {
                return grid[x][y]
            }

            val nextSteps = grid[x][y] + 1

            // Check the adjacent cell that we can go to
            goToCell(x+1, y, nextSteps)
            goToCell(x, y+1, nextSteps)
            goToCell(x+1, y+1, nextSteps)
            goToCell(x-1, y, nextSteps)
            goToCell(x, y-1, nextSteps)
            goToCell(x-1, y-1, nextSteps)
            goToCell(x-1, y+1, nextSteps)
            goToCell(x+1, y-1, nextSteps)
        }

        return -1
    }
}


fun main() {

}