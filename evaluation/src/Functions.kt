/**
 * @author leon
 */
val List<Time>.bestAo5: Float
    get() {
        return historyAverageOf(5).min()?.div(1000) ?: 0F
    }

val List<Time>.bestMo3: Float
    get() {
        return historyMo3.min()?.div(1000) ?: 0F
    }

val List<Time>.historyOfPBSingles: List<Float>
    get() {
        val bests = ArrayList<Time>()
        forEach {
            val min = bests.map { it.timeInMillis }.min()
            if (min == null || min > it.timeInMillis)
                bests.add(it)
        }
        return bests.map { it.time }
    }

val List<Time>.historyMo3: List<Float>
    get() {
        var i = 0
        val list = ArrayList<Float>()
        forEach {
            if (i + 3 >= size) return@forEach
            val avg = subList(i, i + 3).map { it.timeInMillis }.average().toFloat().formattedTime
            list.min().let {
                if (it == null || it > avg)
                    list.add(avg)
            }
            i++
        }
        return list
    }

infix fun List<Time>.historyAverageOf(count: Int): List<Float> {
    var i = 0
    val list = ArrayList<Float>()
    forEach {
        if (i + count >= size) return@forEach
        val avg = subList(i, i + count).map { it.timeInMillis }.sorted().drop(1).dropLast(1).average().toFloat().formattedTime
        list.min().let {
            if (it == null || it > avg)
                list.add(avg)
        }
        i++
    }
    return list
}

val Float.formattedTime
    get() = Math.round(this / 10).div(100F)