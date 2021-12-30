fun main() {
    val algorithms = listOf(BinaryTree(), Sidewinder(), AldousBroder(), Wilsons(), HuntAndKill())
    val tries = 100
    val size = 50
    val averages = mutableMapOf<Algorithm, Double>()
    for (algorithm in algorithms) {
        println("Running $algorithm")
        val list = (0 until tries).map {
            val grid = Grid(size, size)
            algorithm.on(grid)
            grid.deadEnds().count()
        }
        val totalDeadends = list.sum()
        averages[algorithm] = totalDeadends.toDouble() / list.count()
    }

    val totalCells = size * size
    println("")
    println("Average deadends per $size x $size maze ($totalCells cells)")
    println(averages.toList().sortedByDescending { it.second }.joinToString("\n") {
        "${it.first}: ${it.second}/$totalCells (${it.second * 100.0 / (size * size)}%)"
    })
}