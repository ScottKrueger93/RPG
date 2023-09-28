package beutel

class Beutel(
    var items: MutableList<Item> = mutableListOf(
        Item("Energiepille", 2, 50, 50, 50),
        Item("Schadenspille", 2, 0, 0, 0),
        Item("Verteidigungspille", 0, 0, 1000, 0),
        Item("AP-Trank", 0, 0, 0, 100),
        Item("Heiltrank", 0, 1000, 0, 0)
    ),
) {

    private fun itemBeschreibung() {
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

    fun itemAuswaehlen(): Item? {
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
        val itemEingabe = readln()
        if (items.size < 2) {
            if (itemEingabe != "1") {
                println("Ungültige Eingabe.")
                return itemAuswaehlen()
            }
        } else if (items.size < 3) {
            if (itemEingabe != "1" && itemEingabe != "2") {
                println("Ungültige Eingabe.")
                return itemAuswaehlen()
            }
        } else if (items.size < 4) {
            if (itemEingabe != "1" && itemEingabe != "2" && itemEingabe != "3") {
                println("Ungültige Eingabe.")
                return itemAuswaehlen()
            }
        } else if (items.size < 5) {
            if (itemEingabe != "1" && itemEingabe != "2" && itemEingabe != "3" && itemEingabe != "4") {
                println("Ungültige Eingabe.")
                return itemAuswaehlen()
            }
        } else if (items.size < 6) {
            if (itemEingabe != "1" && itemEingabe != "2" && itemEingabe != "3" && itemEingabe != "4" && itemEingabe != "5") {
                println("Ungültige Eingabe.")
                return itemAuswaehlen()
            }
        }
        println()
        var item: Item? = null
        when (itemEingabe) {
            "1" -> {
                item = items[0]
            }
            "2" -> {
                item = items[1]
            }
            "3" -> {
                item = items[2]
            }
            "4" -> {
                item = items[3]
            }
            "5" -> {
                item = items[4]
            }
        }
        items.remove(item)
        return item
    }
}