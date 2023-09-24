import Beutel.*
import Gegner.*
import Helden.*

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
    var hatBereitsGekaempftHeld: MutableList<Held> = mutableListOf()
    var hatBereitsGekaempftGegner: MutableList<Gegner> = mutableListOf()

    var zielGegner = gegnerListe[0]
    var zielHeld = kaempferListe[0]
    var zielVerbuendeter = kaempferListe[0]
    var istVerbuendeterZiel = false
    var istGegnerZiel = false



    fun zielAuswahlDesSpielers() {
        println("Was möchtest du als Ziel auswählen?")
        println(
            """1 für Helden
            |2 für Gegner
        """.trimMargin()
        )
        kaempferListe.addAll(hatBereitsGekaempftHeld)
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
            kaempferListe.removeAll(hatBereitsGekaempftHeld)
        }
        if (heldenOderGegnerEingabe == 2) {
            gegnerListe.addAll(hatBereitsGekaempftGegner)
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
            gegnerListe.removeAll(hatBereitsGekaempftGegner)
        }
    }

    fun zielAuswahlDesGegners(istAmZugGegner: Gegner) {
        println("${istAmZugGegner.name} wählt ein Ziel zum Angreifen...")
        kaempferListe.addAll(hatBereitsGekaempftHeld)
        var zielGegnerEingabe = kaempferListe.random()
            zielHeld = zielGegnerEingabe
            println("${istAmZugGegner.name} wählt ${zielGegnerEingabe.name} als sein Ziel aus.")
            kaempferListe.removeAll(hatBereitsGekaempftHeld)
        }

    fun heldenUeberPruefungGegenGegner(held: Held, gegner: Gegner) {
        if (held == magier) {
            magier.angreifenMagierGegner(held, gegner)
        } else if (held == heiler) {
            heiler.angreifenHeilerGegner(held, gegner)
        } else if (held == krieger)
            krieger.angreifenKriegerGegner(held, gegner)
    }

    fun gegnerUeberPruefungGegenHelden(gegner: Gegner, held: Held) {
        if (gegner == endBoss) {
            gegner.angreifenMagierGegner(gegner, held)
        } else if (gegner == helfer) {
            helfer.angreifenHeilerGegner(gegner, held)
    }

    fun heldenUeberPruefungGegenVerbuendete(held: Held, zielHeld: Held) {
        if (held == magier) {
            magier.angreifenMagierVerbuendeter(held, zielHeld)
        } else if (held == heiler) {
            heiler.angreifenHeilerVerbuendeter(held, zielHeld)
        } else if (held == krieger)
            krieger.angreifenKriegerVerbuendeter(held, zielHeld)
    }

    fun rundenKaempfe() {

        while (krieger.lebenspunkte != 0 && magier.lebenspunkte != 0 && heiler.lebenspunkte != 0 || endBoss.lebenspunkte != 0 && helfer.lebenspunkte != 0) {
            while (kaempferListe.isNotEmpty()) {
                var istAmZugHeld = kaempferListe.random()
                istAmZugHeld.aktionspunkte = minOf(istAmZugHeld.standardAktionspunkte, istAmZugHeld.aktionspunkte + 20)
                kaempferListe.remove(istAmZugHeld)
                hatBereitsGekaempftHeld.add(istAmZugHeld)
                istAmZugHeld.aktion1.schaden = istAmZugHeld.aktion1.standardSchaden
                istAmZugHeld.aktion3.schaden = istAmZugHeld.aktion3.standardSchaden
                istAmZugHeld.aktion4.schaden = istAmZugHeld.aktion4.standardSchaden
                istVerbuendeterZiel = false
                istGegnerZiel = false
                println("${istAmZugHeld.name} ist an der Reihe.")
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
                            beutel.itemAuswaehlen().itemNutzen(istAmZugHeld)
                        }
                    } else if (eingabe == 2) {
                        zielAuswahlDesSpielers()
                    }
                } while (eingabe != 2)
                if (istGegnerZiel == true) {
                    heldenUeberPruefungGegenGegner(istAmZugHeld, zielGegner)
                }
                if (istVerbuendeterZiel == true) {
                    heldenUeberPruefungGegenVerbuendete(istAmZugHeld, zielVerbuendeter)
                }
            }

            while (gegnerListe.isNotEmpty()) {
                var istAmZugGegner = gegnerListe.random()
                istAmZugGegner.aktionspunkte = minOf(istAmZugGegner.standardAktionspunkte, istAmZugGegner.aktionspunkte + 20)
                gegnerListe.remove(istAmZugGegner)
                hatBereitsGekaempftGegner.add(istAmZugGegner)
                istGegnerZiel = false
                println("${istAmZugGegner.name} ist an der Reihe.")
                zielAuswahlDesGegners(istAmZugGegner)
                gegnerUeberPruefungGegenHelden(istAmZugGegner, zielHeld)
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
