import Beutel.*
import Gegner.*
import Helden.*
import kotlin.reflect.typeOf

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

    var zielGegner = gegnerListe[0]
    var zielVerbuendeter = kaempferListe[0]
    var istVerbuendeterZiel = false
    var istGegnerZiel = false


    fun zielAuswahl() {
        println("Was möchtest du als Ziel auswählen?")
        println("""1 für Helden
            |2 für Gegner
        """.trimMargin())
        var heldenOderGegnerEingabe = readln().toInt()
        if (heldenOderGegnerEingabe == 1) {
            println("Welchen Helden möchtest du als Ziel auswählen?")
            for ((index: Int, held: Held) in kaempferListe.withIndex()) {
                println(
                    """
                ${index + 1} für ${held.name}
                """.trimIndent()
                )
            }
                var zielSpielerEingabe = readln().toInt()
                zielVerbuendeter = kaempferListe[zielSpielerEingabe - 1]
                istVerbuendeterZiel = true
                println("Du hast ${zielVerbuendeter.name} als Ziel für eine Fähigkeit ausgewählt.")
            }
            if (heldenOderGegnerEingabe == 2){
            for ((index: Int, gegner: Gegner) in gegnerListe.withIndex()) {
                println(
                    """
                ${index + 1} für ${gegner.name}
                """.trimIndent()
                )
            }
                var zielSpielerEingabe = readln().toInt()
                zielGegner = gegnerListe[zielSpielerEingabe - 1]
                istGegnerZiel = true
                println("Du hast ${zielGegner.name} als Ziel für eine Fähigkeit ausgewählt.")
            }
        }

    fun heldenUeberPruefungGegenGegner(held: Held, gegner: Gegner) {
        if (held == magier) {
            magier.angreifenMagierGegner(held, gegner)
        } else if (held == heiler) {
            heiler.angreifenHeilerGegner(held, gegner)
        } else if (held == krieger)
            krieger.angreifenKriegerGegner(held, gegner)
    }
    fun heldenUeberPruefungGegenVerbuendete(held: Held, zielHeld: Held) {
        if (held == magier) {
            magier.angreifenMagierVerbuendeter(held, zielHeld)
        } else if (held == heiler) {
            heiler.angreifenHeilerVerbuendeter(held, zielHeld)
        } else if (held == krieger)
            krieger.angreifenKriegerVerbuendeter(held, zielHeld)
    }

    fun gegnerangriff(){

    }

    fun rundenKaempfe() {

        while (krieger.lebenspunkte != 0 && magier.lebenspunkte != 0 && heiler.lebenspunkte != 0 || endBoss.lebenspunkte != 0 && helfer.lebenspunkte != 0) {
            while (kaempferListe.isNotEmpty()){
            var istAmZug = kaempferListe.random()
            istAmZug.aktionspunkte = minOf(istAmZug.standardAktionspunkte,istAmZug.aktionspunkte + 20)
            kaempferListe.remove(istAmZug)
            hatBereitsGekaempft.add(istAmZug)
            istAmZug.aktion1.schaden = istAmZug.aktion1.standardSchaden
            istAmZug.aktion3.schaden = istAmZug.aktion3.standardSchaden
            istAmZug.aktion4.schaden = istAmZug.aktion4.standardSchaden
            istVerbuendeterZiel = false
            istGegnerZiel = false
            println("${istAmZug.name} ist an der Reihe.")
            do {
                println("Was möchtest du tun?")
                println(
                    """--- Menü ---
                |1 - Item benutzen
                |2 - Ziel auswählen
                """.trimMargin()
                )
                var eingabe = readln().toInt()
                if (eingabe == 1) {
                    if (beutel.items.isEmpty()) {
                        println("Du hast keine Items mehr.")
                        continue
                    } else {
                        beutel.itemAuswaehlen().itemNutzen(istAmZug)
                    }
                } else if (eingabe == 2) {
                    zielAuswahl()
                }
            } while (eingabe != 2)
            if (istGegnerZiel == true) {
                heldenUeberPruefungGegenGegner(istAmZug, zielGegner)
            }
            if (istVerbuendeterZiel == true){
                heldenUeberPruefungGegenVerbuendete(istAmZug, zielVerbuendeter)
            }
            }
        }
        if (krieger.lebenspunkte == 0 && magier.lebenspunkte == 0 && heiler.lebenspunkte == 0) {
            println("Du hast verloren.")
        } else if (endBoss.lebenspunkte == 0 && helfer.lebenspunkte == 0) {
            println("Du hast gesiegt!!!")
        }
    }

    rundenKaempfe()

}
