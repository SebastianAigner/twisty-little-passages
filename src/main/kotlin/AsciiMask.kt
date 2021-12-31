import java.io.File

fun main() {
    val mask = Mask.fromText(File("mask.txt").readLines())
    val grid = MaskedGrid(mask)
    RecursiveBacktracker().on(grid)
    grid.toImage().savePng("Masked.png")
}