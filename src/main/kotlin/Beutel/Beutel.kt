package Beutel

class Beutel(var items: MutableList<Item> = mutableListOf()) {

    fun itemAuswaehlen(): Item {
        println("Welches Item möchtest du verwenden?")
        for ((index,item ) in items.withIndex()){
        println("""
            ${index + 1} für ${item.name}
            """.trimIndent())
        }
        var itemEingabe: Int = readln().toInt()
        var item = items[itemEingabe-1]
        return item
    }

}