import java.io.File

/**
 * @author leon
 */
class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val folder = File("/home/leon/Dokumente/Cubing/speedcubing/times")
            val categoryFolders = folder.listFiles { file -> file.isDirectory }.asList().sorted()
            val sessions = categoryFolders.flatMap {
                val category = Category(it.name)
                val files: List<File> = it.listFiles { file -> file.isFile }.sortedBy { it.name }
                return@flatMap files.map { Session(it.readLines(), category) }
            }
            sessions.groupBy { it.category }.forEach { (category, sessions) ->
                println(category.name)

                val bests = ArrayList<Time>()
                val times = sessions.flatMap { it.times }
                times.forEach {
                    val min = bests.map { it.timeInMillis }.min()
                    if (min == null || min > it.timeInMillis)
                        bests.add(it)
                }
                println()
                if (category.name == "3x3") println("sub10s: ${times.filter { it.timeInMillis < 10000}.size}\n")
                println("PBs: ${times.historyOfPBSingles}")
                println("Mo3: ${times.historyMo3}")
                println("Ao5: ${(times historyAverageOf 5)}")
                println("Ao12: ${(times historyAverageOf 12)}")
                println("Ao50: ${(times historyAverageOf 50)}")
                println("Ao100: ${(times historyAverageOf 100)}")
                println("Ao1000: ${(times historyAverageOf 1000)}")
                println()
            }


        }
    }
}