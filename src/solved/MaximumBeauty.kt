package solved


object MaximumBeauty {

    data class Event(
        val cost: Int,
        val type: Int,
        val beautyOrIndex: Int
    )

    fun maximumBeauty(items: Array<IntArray>, queries: IntArray): IntArray {

        if (queries.isEmpty()) return IntArray(0)

        val events = mutableListOf<Event>()

        for (query in queries.withIndex()) {
            events.add(Event(cost = query.value, type = 1, beautyOrIndex = query.index))
        }

        for (item in items) {
            events.add(Event(cost = item[0], type = 0, beautyOrIndex = item[1]))
        }

        val answers = IntArray(queries.size) { 0 }

        events.sortBy { it.type + it.cost } // Ensures items come before queries
        events.forEach { println(it) }
        var runningMax = 0
        events.forEach {
            when (it.type) {
                0 -> { // item
                    val itemBeauty = it.beautyOrIndex
                    runningMax = runningMax.coerceAtLeast(itemBeauty)
                }
                1 -> { // query
                    val queryIndex = it.beautyOrIndex
                    answers[queryIndex] = runningMax
                }
            }
        }

        return answers
    }
}
