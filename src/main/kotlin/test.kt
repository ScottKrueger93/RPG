import Beutel.*
import Gegner.*
import Helden.*

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

var ziel = gegnerListe[0]

fun main() {

    fun zielAuswahl() {
        println("Wen möchtest du als Ziel auswählen?")
        for ((index: Int, gegner: Gegner) in gegnerListe.withIndex()) {
            println(
                """
                ${index + 1} für ${gegner.name}
                """.trimIndent()
            )
            var zielSpielerEingabe = readln().toInt()
            if (zielSpielerEingabe == 1) {
                ziel = gegnerListe[zielSpielerEingabe - 1]
            }
        }
    }

    fun heldenUeberPruefung(held: Held, gegner: Gegner) {
        if (held == magier) {
            magier.angreifenMagier(held, gegner)
        } else if (held == heiler) {
            heiler.angreifenHeiler(held, gegner)
        } else if (held == krieger)
            krieger.angreifenKrieger(held, gegner)
    }

    fun rundenKaempfe() {

        while (krieger.lebenspunkte != 0 && magier.lebenspunkte != 0 && heiler.lebenspunkte != 0 || endBoss.lebenspunkte != 0 && helfer.lebenspunkte != 0) {
            var istAmZug = kaempferListe.random()
            kaempferListe.remove(istAmZug)
            hatBereitsGekaempft.add(istAmZug)
            println("${istAmZug.name} ist an der Reihe.")
            while (readln().toInt() != 2) {
                println("Was möchtest du tun?")
                println(
                    """--- Menü ---
                |1 - Item benutzen
                |2 - Fähigkeit ausführen
                """.trimMargin()
                )
                if (readln().toInt() == 1) {
                    beutel.itemAuswaehlen().itemNutzen(istAmZug)
                } else if (readln().toInt() == 2) {
                    zielAuswahl()
                }
            }
            heldenUeberPruefung(istAmZug, ziel)
        }

    }
    if (krieger.lebenspunkte == 0 && magier.lebenspunkte == 0 && heiler.lebenspunkte == 0) {
        println("Du hast gewonnen!!!")
    } else if (endBoss.lebenspunkte == 0 && helfer.lebenspunkte == 0) {
        println("Du hast gesiegt!!!")
    }
    rundenKaempfe()
}
