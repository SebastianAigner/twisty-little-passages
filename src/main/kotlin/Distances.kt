class Distances(val root: Cell) {
    val cells = mutableMapOf<Cell, Int>()

    init {
        cells[root] = 0
    }

    operator fun get(cell: Cell): Int? {
        return cells[cell]
    }

    operator fun set(cell: Cell, distance: Int) {
        cells[cell] = distance
    }

    fun cells(): MutableSet<Cell> {
        return cells.keys
    }

    fun pathTo(goal: Cell): Distances {
        var current = goal
        val breadcrumbs = Distances(root)
        breadcrumbs[current] = cells[current]!!
        while (current != root) {
            for (neighbor in current.links()) {
                // find a cell that is closer to the goal (has a lower distance) and add it to the breadcrumb trail
                if (cells[neighbor]!! < cells[current]!!) {
                    breadcrumbs[neighbor] = cells[neighbor]!!
                    current = neighbor
                    break
                }
            }
        }
        return breadcrumbs
    }
}