import java.io.File
import javax.imageio.ImageIO
import kotlin.random.Random

class Sidewinder {
    fun on(grid: Grid) {
        for(row in grid.rows()) {
            val run = mutableListOf<Cell>()
            for(cell in row) {
                run += cell
                val atEasternBoundary = cell.east == null
                val atNortherBoundary = cell.north == null
                val shouldCloseOut = atEasternBoundary || (!atNortherBoundary && Random.nextBoolean())
                if(shouldCloseOut) {
                    val member = run.random()
                    member.north?.let { member.link(it) }
                    run.clear()
                } else {
                    cell.east?.let { cell.link(it) }
                }
            }
        }
    }
}

fun main() {
    val grid = Grid(4,4)
    Sidewinder().on(grid)
    println(grid)
    ImageIO.write(grid.toImage(10), "png", File("foo.png"))
}