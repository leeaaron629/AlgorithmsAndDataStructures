package solved

/**
 * https://leetcode.com/problems/my-calendar-i/
 */
object MyCalendarI {

    private val meetings = mutableListOf<Pair<Int, Int>>()

    fun book(startTime: Int, endTime: Int): Boolean {
        if (meetings.isEmpty()) {
            meetings.add(startTime to endTime)
            return true
        }
        var (beg, end) = 0 to meetings.size
        while (beg <= end) {
            val mid = (beg + end) / 2
            if (startTime > meetings[mid].first) {
                beg = mid + 1
            } else {
                end = mid - 1
            }
        }
        val prev = meetings.getOrNull(beg - 1)
        val next = meetings.getOrNull(beg)
        val worksWithPrev = prev?.let { (_, end) -> startTime >= end } ?: true
        val worksWithNext = next?.let { (beg, _) -> endTime <= beg } ?: true
        if (worksWithNext && worksWithPrev) {
            meetings.add(beg, startTime to endTime)
            return true
        } else return false
    }

}
