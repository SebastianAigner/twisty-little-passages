fun main() {
    val mask = Mask(5, 5)
    mask[0, 0] = false
    mask[2, 2] = false
    mask[4, 4] = false
    val grid = MaskedGrid(mask)
    repeat(10) {
        print(grid.randomCell())
    }
    RecursiveBacktracker().on(grid)
    println(grid)
}