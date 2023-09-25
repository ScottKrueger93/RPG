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

    fun itemBeschreibung() {
        for (item in items) {
            println()
            println(item.name)
            if (item.schadensBoostItem > 0) {
                println("Verdoppelt den Schaden für den nächsten Angriff.")
            }
            if (item.heilungItem > 0) {
                println("Heilt den aktuellen Helden um ${item.heilungItem} Lebenspunkte.")
            }
            if (item.ruestungPlusItem > 0) {
                println("Fügt ${item.ruestungPlusItem} Rüstungspunkte dem aktuellen Helden hinzu.")
            }
            if (item.aktionsPunkteBoostItem > 0) {
                println("Fügt ${item.aktionsPunkteBoostItem} Aktionspunkte dem aktuelle Helden hinzu.")
            }
        }
    }

    fun itemAuswaehlen(): Item {
        itemBeschreibung()
        println()
        println("Welches Item möchtest du verwenden?")
        for ((index, item) in items.withIndex()) {
            println(
                """
            ${index + 1} für ${item.name}. 
            """.trimIndent()
            )
        }
        var itemEingabe: Int = readln().toInt()
        var item = items[itemEingabe - 1]
        items.remove(item)
        return item
    }
}