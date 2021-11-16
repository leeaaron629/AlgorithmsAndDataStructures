package revisit

import java.util.*

/**
 * https://leetcode.com/problems/course-schedule/
 */
object CourseSchedule {

    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {

        if (prerequisites.isEmpty()) return true

        val adjacencyMatrix: Array<MutableList<Int>> = Array(numCourses) { mutableListOf<Int>() }
        prerequisites.forEach { req ->
            adjacencyMatrix[req[1]].add(req[0])
        }

//        val adjMatrixPretty = Array(numCourses) { IntArray(numCourses) { 0 } }
//        prerequisites.forEach { req ->
//            adjMatrixPretty[req[1]][req[0]] = 1
//        }
//        adjMatrixPretty.forEach {
//            println(it.joinToString(","))
//        }

//        val cyclicCourses = mutableSetOf<Int>()
//        val checkedCourse = BooleanArray(numCourses) { false }
        val finished = IntArray(numCourses) { 0 }
        val recStack = IntArray(numCourses) { 0 }
        return (0 until numCourses).all {
            recursiveIsCyclic(
                course = it,
                finished = finished,
                recStack = recStack,
                adjacencyMatrix = adjacencyMatrix
            )
        }
    }

    private fun isNonCyclic(
        courseN: Int,
        adjacencyMatrix: Array<MutableList<Int>>,
        checked: BooleanArray,
        isCyclic: MutableSet<Int>
    ): Boolean {

        if (courseN in isCyclic) return false
        if (checked[courseN]) return true

        println("Starting on $courseN")
        val recStack = mutableMapOf<Int, Int>()
        val stack: Stack<Int> = Stack()
        stack.push(courseN)


        while (stack.isNotEmpty()) {
            print("Stack: $stack ")
            val course = stack.peek()
            when (val nVisited = recStack[course] ?: 0) {
                0 -> {
                    recStack[course] = nVisited + 1
                    for (adjCourse in adjacencyMatrix[course]) {
                        // Cycle detected since we are re-visiting an old course -> Return false
                        when (recStack[adjCourse] ?: 0) {
                            0 -> stack.push(adjCourse)
                            1 -> {
                                println("$adjCourse in $recStack")
                                return false.also {
                                    // Also remember all courses are cyclic
                                    isCyclic.addAll(recStack.keys)
                                }
                            }
                        }
                    }
                }
                else -> {
                    stack.pop()
                    recStack[course] = nVisited + 1
                }
            }
        }
        // Remember all courses that are non-cyclic
        for (course in isCyclic) {
            checked[course] = true
        }

        return true
    }

    fun recursiveIsCyclic(
        course: Int,
        finished: IntArray,
        recStack: IntArray,
        adjacencyMatrix: Array<MutableList<Int>>
    ): Boolean {
        if (recStack[course] == 1) return false
        if (finished[course] == 1) return true
        recStack[course] = 1
        finished[course] = 1
        val adjCourses = adjacencyMatrix[course]
        for (adjCourse in adjCourses) {
            val isCyclic = recursiveIsCyclic(adjCourse, finished, recStack, adjacencyMatrix)
            if (!isCyclic) return isCyclic
        }
        recStack[course] = 0 // Popping out of call stack. Set it back to 0
        return true
    }

}

fun main() {

    arrayOf(
        intArrayOf(1,0),
        intArrayOf(2,0),
        intArrayOf(0,2),
    ).let { reqs ->
        println(reqs.joinToString(", ") { "[${it[0]}, ${it[1]}]"})
        CourseSchedule.canFinish(3, reqs)
    }.also { println("Answer: $it") }

    // [[1,4],[2,4],[3,1],[3,2]]
    arrayOf(
        intArrayOf(1,4),
        intArrayOf(2,4),
        intArrayOf(3,1),
        intArrayOf(3,2)
    ).let { reqs ->
        println(reqs.joinToString(", ") { "[${it[0]}, ${it[1]}]"})
        CourseSchedule.canFinish(5, reqs)
    }.also {
        println("Answer: $it")
        assert(it)
    }

    arrayOf(
        intArrayOf(1,0),
        intArrayOf(0,3),
        intArrayOf(0,2),
        intArrayOf(3,2),
        intArrayOf(2,5),
        intArrayOf(4,5),
        intArrayOf(5,6),
        intArrayOf(2,4),
        intArrayOf(6,1)
    ).let { reqs ->
        println(reqs.joinToString(", ") { "[${it[0]}, ${it[1]}]"})
        CourseSchedule.canFinish(7, reqs)
    }.also {
        println("Answer: $it")
        assert(it)
    }

    arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 2),
        intArrayOf(2, 0)
    ).let { reqs ->
        println(reqs.joinToString(", ") { "[${it[0]}, ${it[1]}]"})
        CourseSchedule.canFinish(3, reqs)
    }.also {
        println("Answer: $it")
        assert(!it)
    }

    arrayOf(
        intArrayOf(0, 1),
        intArrayOf(0, 2),
        intArrayOf(2, 1)
    ).let { reqs ->
        println(reqs.joinToString(", ") { "[${it[0]}, ${it[1]}]"})
        CourseSchedule.canFinish(3, reqs)
    }.also {
        println("Answer: $it")
        assert(!it)
    }

}