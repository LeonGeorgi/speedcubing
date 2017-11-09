/**
 * @author leon
 */
class Session(lines: List<String>, val category: Category) {
    val times = lines.filter { it != "No.;Time;Comment;Scramble" && !it.contains("DNF(") }.map { Time(it) }

}