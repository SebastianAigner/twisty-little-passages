import java.io.File
import javax.imageio.ImageIO
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class AldousBroder : Algorithm {
    override fun on(grid: Grid) {
        var cell = grid.randomCell()
        var unvisited = grid.size - 1
        while (unvisited > 0) {
            val neighbor = cell.neighbors().random()
            if (neighbor.links().isEmpty()) {
                cell.link(neighbor)
                unvisited--
            }
            cell = neighbor
        }
    }
}

@OptIn(ExperimentalTime::class)
fun main() {
    repeat(6) {
        val grid = ColoredGrid(200, 200)
        println(measureTime { AldousBroder().on(grid) })
        val middle = grid[grid.rows / 2, grid.columns / 2]!!
        grid.distances = middle.distances()
        ImageIO.write(grid.toImage(), "png", File("AldousBroder$it.png"))
    }

}