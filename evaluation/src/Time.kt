/**
 * @author leon
 */
class Time(line: String) {
    val numberInSession: Int
    val timeInMillis: Int
    val time
        get() = timeInMillis / 1000F
    val comment: String
    val scramble: String

    init {
        val strings = line.split(";")
        numberInSession = strings.getOrNull(0)?.toInt() ?: 0
        timeInMillis = strings.getOrNull(1)?.removeSuffix("+")?.run {
            if (contains(":")) {
                val colonIndex = indexOf(":")
                return@run substring(0, colonIndex).toInt().times(1000 * 60).plus(
                        substring(colonIndex + 1).toFloat().times(1000).toInt())
            } else {
                return@run toFloat().times(1000).toInt()
            }
        } ?: 0
        comment = strings.getOrNull(2) ?: ""
        scramble = strings.getOrNull(3) ?: ""
    }
}