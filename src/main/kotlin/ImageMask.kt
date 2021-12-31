import java.io.File
import javax.imageio.ImageIO

fun main() {
    val m = Mask.fromImage(ImageIO.read(File("smask.png"))).apply { invert() }
    val grid = MaskedGrid(m)
    RecursiveBacktracker().on(grid)
    grid.toImage().savePng("SMasked.png")
}