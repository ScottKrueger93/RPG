import Beutel.*
import Gegner.*
import Helden.*
import Kampf.Rundenkampf

fun main() {

    //Die Unterklassen von der Mutterklasse "Held"
    var krieger: `Krieger(Tank)` = `Krieger(Tank)`()
    var magier: `Magier(DD)` = `Magier(DD)`()
    var heiler: `Heiliger (Heal)` = `Heiliger (Heal)`()

    //Die Unterklassen von der Mutterklasse "Gegner"
    var endBoss: Endgegner = Endgegner()
    var helfer: Helfer = Helfer()

    // Klasse "Beutel"
    var beutel: Beutel = Beutel()

    // Liste für die Rundenkämpfe
    var kaempferListe = mutableListOf(krieger, magier, heiler)
    var gegnerListe: MutableList<Gegner> = mutableListOf(endBoss)
    var hatBereitsGekaempft: MutableList<Held> = mutableListOf()

    fun zielAuswahl(){
        println("Wen möchtest du als Ziel auswählen?")
        for ((index: Int, gegner: Gegner) in gegnerListe.withIndex()){
            println("""
                ${index + 1} für ${gegner.name}
                """.trimIndent())
        }
        var zielEingabe: Int = readln().toInt()
        var ziel = gegnerListe[zielEingabe-1]
    }

    fun rundenKaempfe() {

        while (krieger.lebenspunkte != 0 && magier.lebenspunkte != 0 && heiler.lebenspunkte != 0 || endBoss.lebenspunkte != 0 && helfer.lebenspunkte != 0) {
            var istAmZug = kaempferListe.random()
            kaempferListe.remove(istAmZug)
            hatBereitsGekaempft.add(istAmZug)
            println("${istAmZug.name} ist an der Reihe.")
            println("Was möchtest du tun?")
            println(
                """--- Menü ---
                |1 - Item benutzen
                |2 - Fähigkeit ausführen
            """.trimMargin()
            )
            when (readln()) {
                "1" -> beutel.itemAuswaehlen().itemNutzen(istAmZug)
                "2" -> zielAuswahl()
            }
        }
    }
}