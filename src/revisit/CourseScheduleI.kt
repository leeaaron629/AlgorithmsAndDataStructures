package revisit

import java.util.*

/**
 * https://leetcode.com/problems/course-schedule/
 */
object CourseScheduleI {

    fun canFinishOptimized(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val nRequisites = IntArray(numCourses) { 0 }
        val graph = Array<MutableList<Int>>(numCourses) { mutableListOf() }

        for (pair in prerequisites) {
            val course = pair[0]
            val prerequisite = pair[1]

            graph[prerequisite].add(course) // B -> A
            // Keep track of all the degrees of a course
            // Courses with a degree of 0 has no prerequisites
            nRequisites[course]++
        }

        val coursesToFinish: Queue<Int> = LinkedList()

        // Handle all courses with no requisites (vertex degrees)
        for (i in 0 until numCourses) {
            if (nRequisites[i] == 0) {
                coursesToFinish.offer(i) // The queue is a list of all courses with no requisites
            }
        }

        var count = 0
        while (coursesToFinish.isNotEmpty()) {
            // Finish the courses with no requisites
            val course = coursesToFinish.poll()
            count++

            // Mark the requisites as completed in the isDegree
            for (nextCourse in graph[course]) {
                // Subtract the number of requisites from the course
                nRequisites[nextCourse]--
                if (nRequisites[nextCourse] == 0) { // If requisites is 0, then add it to the queue of coursesToFinish
                    coursesToFinish.offer(nextCourse)
                }
            }
        }

        return count == numCourses
    }
    /**
     * @param numCourses - the total number of courses to take
     * @param prerequisites - definition of the course requirements - [A, B], where course B must be taken before A
     */
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {

        fun printAdjacencyMatrix(prefix: String? = null, adjacencyMatrix: Array<MutableSet<Int>>) {
            if (prefix != null) println(prefix)
            adjacencyMatrix.forEachIndexed { idx, req ->
                println("$idx <- ${req.joinToString(",")}")
            }
        }

        fun setPreReqs(adjacencyMatrix: Array<MutableSet<Int>>, courseA: Int, courseB: Int) {
            adjacencyMatrix[courseA].add(courseB)
        }

            fun completeCourse(adjacencyMatrix: Array<MutableSet<Int>>, unfinishedCourses: MutableSet<Int>, courseToComplete: Int) {
            require(courseToComplete in unfinishedCourses) { "Course to be complete must be in unfinished course set" }
            unfinishedCourses.remove(courseToComplete)
            unfinishedCourses.forEach { c ->
                adjacencyMatrix[c].remove(courseToComplete)
            }
        }

        // Build Adjacency Matrix
        val adjacencyMatrix = Array(numCourses) { mutableSetOf<Int>() }
        val unfinishedCourses = (0 until numCourses).toMutableSet()
        prerequisites.forEach { req ->
            setPreReqs(adjacencyMatrix = adjacencyMatrix, courseA = req[0], courseB = req[1])
        }

        printAdjacencyMatrix("Finished course completion...", adjacencyMatrix)

        // Finish each course at a time
        while (true) {
            var aCourseIsCompleted = false
            for (course in unfinishedCourses.toList()) {
                val courseHasNoPreReqs = adjacencyMatrix[course].isEmpty()
                if (courseHasNoPreReqs) {
                    completeCourse(
                        adjacencyMatrix = adjacencyMatrix,
                        unfinishedCourses = unfinishedCourses,
                        courseToComplete = course
                    )
                    aCourseIsCompleted = true
                }
            }
            if (!aCourseIsCompleted) break
        }

        printAdjacencyMatrix("Finished course completion...", adjacencyMatrix)

        return unfinishedCourses.isEmpty()
    }

    fun canFinishV1(numCourses: Int, prerequisites: Array<IntArray>): Boolean {

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
        CourseScheduleI.canFinish(3, reqs)
    }.also { println("Answer: $it") }

    // [[1,4],[2,4],[3,1],[3,2]]
    arrayOf(
        intArrayOf(1,4),
        intArrayOf(2,4),
        intArrayOf(3,1),
        intArrayOf(3,2)
    ).let { reqs ->
        println(reqs.joinToString(", ") { "[${it[0]}, ${it[1]}]"})
        CourseScheduleI.canFinish(5, reqs)
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
        CourseScheduleI.canFinish(7, reqs)
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
        CourseScheduleI.canFinish(3, reqs)
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
        CourseScheduleI.canFinish(3, reqs)
    }.also {
        println("Answer: $it")
        assert(!it)
    }

}