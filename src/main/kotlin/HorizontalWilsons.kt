import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class HorizontalWilsons {
    fun on(grid: Grid) {

        val unvisited = grid.cells().toMutableList()

        val first = unvisited.random()
        unvisited.remove(first)

        while (unvisited.isNotEmpty()) {
            var cell = unvisited.random()
            var path = mutableListOf(cell)
            while (unvisited.contains(cell)) {
                val neighbors = listOfNotNull(cell.east,
                    cell.east,
                    cell.west,
                    cell.west,
                    cell.east,
                    cell.east,
                    cell.west,
                    cell.west,
                    cell.east,
                    cell.east,
                    cell.west,
                    cell.west,
                    cell.east,
                    cell.east,
                    cell.west,
                    cell.west,
                    cell.north,
                    cell.south)
                cell = neighbors.random()
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
        val grid = ColoredGrid(200, 200)
        println(measureTime { HorizontalWilsons().on(grid) })
        val middle = grid[grid.rows / 2, grid.columns / 2]!!
        grid.distances = middle.distances()
        grid.toImage().savePng("Wilsons$it.png")
    }
}