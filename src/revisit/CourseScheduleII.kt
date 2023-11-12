package revisit

import org.junit.jupiter.api.Test
import java.util.*

class CourseScheduleII {

    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {

        if (prerequisites.isEmpty()) return (0 until numCourses).toList().toIntArray()

        val graph = Array<MutableList<Int>>(numCourses) { mutableListOf() }
        val nRequisites = IntArray(numCourses)

        for (courseAndReq in prerequisites) {
            val course = courseAndReq[0]
            val req = courseAndReq[1]
            graph[req].add(course)
            nRequisites[course]++
        }

        val coursesWithoutRequisites: Queue<Int> = LinkedList()
        nRequisites.forEachIndexed { course, nReqs ->
            if (nReqs == 0) coursesWithoutRequisites.add(course)
        }

        val coursesFinishOrder = mutableListOf<Int>()
        while (coursesWithoutRequisites.isNotEmpty()) {
            val finishedCourse = coursesWithoutRequisites.remove()
            coursesFinishOrder.add(finishedCourse)

            // For each course available course
            for (course in graph[finishedCourse]) {
                nRequisites[course]--
                if (nRequisites[course] == 0) {
                    coursesWithoutRequisites.add(course)
                }
            }
        }

        return when (coursesFinishOrder.size == numCourses) {
            true -> coursesFinishOrder.toIntArray()
            false -> IntArray(0)
        }
    }

}

class CourseScheduleIITest private constructor() {

    private val courseScheduleII = CourseScheduleII()

    @Test
    fun `test findOrder - base case`() {
        val prerequisites = listOf(
            // 0 -> 1
            // 2 -> 1
            // 1 -> 0
            listOf(1,0), listOf(1,2), listOf(0, 1)
        ).map { it.toIntArray() }.toTypedArray()
        val order = courseScheduleII.findOrder(numCourses = 3, prerequisites = prerequisites)
        assert(order.contentEquals(listOf(1).toIntArray()))
    }

}
