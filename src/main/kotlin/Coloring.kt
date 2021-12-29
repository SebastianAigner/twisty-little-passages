import java.io.File
import javax.imageio.ImageIO

fun main() {
    val grid = ColoredGrid(250, 250)
    Sidewinder().on(grid)
    val start = grid[grid.rows / 2, grid.columns / 2]!!
    grid.distances = start.distances()
    ImageIO.write(grid.toImage(), "png", File("colorized.png"))
}