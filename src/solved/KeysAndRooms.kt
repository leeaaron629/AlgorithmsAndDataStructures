package solved

import org.junit.jupiter.api.Test
import java.util.*

/**
 * https://leetcode.com/problems/keys-and-rooms/
 */
class KeysAndRooms {

    /**
     * Input: rooms = [[1],[2],[3],[]]
     * Output: true
     * Explanation:
     *  We visit room 0 and pick up key 1.
     *  We then visit room 1 and pick up key 2.
     *  We then visit room 2 and pick up key 3.
     *  We then visit room 3.
     *  Since we were able to visit every room, we return true.
     *
     *  Optimize by marking rooms visited before going into it
     */
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {

        val visited = mutableSetOf<Int>()
        // Let's do a BFS
        val roomsQ: Queue<Int> = LinkedList()
        roomsQ.add(0)
        visited.add(0)

        while (roomsQ.isNotEmpty()) {
            val curRoom = roomsQ.poll()
            rooms[curRoom].forEach { key ->
                if (key !in visited) roomsQ.add(key)
                visited.add(key)
            }
        }

        return visited.size == rooms.size
    }

}

internal class KeysAndRoomsTest {

    private val keysAndRooms = KeysAndRooms()

    @Test
    fun `test canVisitAllRooms - base case`() {
        val rooms = listOf(
            listOf(1), listOf(2), listOf()
        )
        assert(keysAndRooms.canVisitAllRooms(rooms) == true)
    }

    @Test
    fun `test canVisitAllRooms - base case with extras`() {
        val rooms = listOf(
            listOf(1), listOf(1,2,3), listOf(1,2,3), listOf(1,2,3)
        )
        assert(keysAndRooms.canVisitAllRooms(rooms) == true)
    }

    @Test
    fun `test canVisitAllRooms - base case with cycles`() {
        val rooms = listOf(
            listOf(1), listOf(2), listOf(1)
        )
        assert(keysAndRooms.canVisitAllRooms(rooms) == true)
    }

}