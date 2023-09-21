package Beutel

class Beutel(
    var items: MutableList<Item> = mutableListOf(
        Item("Energiepille", 2, 50, 50, 50),
        Item("Schadenspille", 2, 0, 0, 0),
        Item("Verteidigungspille", 0, 0, 1000, 0),
        Item("AP-Trank", 0, 0, 0, 100),
        Item("Heiltrank", 0, 1000, 0, 0)
    ),
) {

    fun itemAuswaehlen(): Item {
        println("Welches Item möchtest du verwenden?")
        for ((index, item) in items.withIndex()) {
            println(
                """
            ${index + 1} für ${item.name}
            """.trimIndent()
            )
        }
        var itemEingabe: Int = readln().toInt()
        var item = items[itemEingabe - 1]
        return item
    }
}