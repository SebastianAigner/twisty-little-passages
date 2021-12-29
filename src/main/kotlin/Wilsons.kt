import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class Wilsons {
    fun on(grid: Grid) {

        val unvisited = grid.cells().toMutableList()

        val first = unvisited.random()
        unvisited.remove(first)

        while (unvisited.isNotEmpty()) {
            var cell = unvisited.random()
            var path = mutableListOf(cell)
            while (unvisited.contains(cell)) {
                cell = cell.neighbors().random()
                val position = path.indexOf(cell)
                if (position != -1) {
                    // we've seen this before
                    path = path.slice(0..position).toMutableList()
                } else {
                    // this is new
                    path += cell
                }
            }
            // the path has found a visited cell
            for (index in 0..path.size - 2) {
                path[index].link(path[index + 1])
                unvisited.remove(path[index])
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
fun main() {
    repeat(6) {
        val grid = ColoredGrid(20, 20)
        println(measureTime { Wilsons().on(grid) })
        val middle = grid[grid.rows / 2, grid.columns / 2]!!
        grid.distances = middle.distances()
        grid.toImage().savePng("Wilsons$it.png")
    }
}