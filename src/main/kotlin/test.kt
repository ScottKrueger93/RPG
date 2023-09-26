import beutel.*
import gegner.*
import helden.*

fun main() {

    //Die Unterklassen von der Mutterklasse "Held"
    val krieger = Krieger()
    val magier = Magier()
    val heiler = Heiliger()

    //Die Unterklassen von der Mutterklasse "Gegner"
    val endBoss = Endgegner()
    val helfer = Helfer()

    // Klasse "Beutel"
    val beutel = Beutel()

    // Liste für die Rundenkämpfe
    val haeldenListe = mutableListOf(krieger, magier, heiler)
    val gegnerListe: MutableList<Gegner> = mutableListOf(endBoss)
    val hatBereitsGekaempftHeld: MutableList<Held> = mutableListOf()
    val hatBereitsGekaempftGegner: MutableList<Gegner> = mutableListOf()
    val heldIstTot: MutableList<Held> = mutableListOf()
    val gegnerIstTot: MutableList<Gegner> = mutableListOf()

    var zielGegner = gegnerListe[0]
    var zielHeld = haeldenListe[0]
    var zielVerbuendeter = haeldenListe[0]
    var istVerbuendeterZiel: Boolean
    var istGegnerZiel: Boolean

    fun kampfBeginnt() {
        println()
        println("Deine Truppe besteht aus folgenden Helden, mit den angezeigten Werten und Fähigkeiten:")
        println()
        for (held in haeldenListe) {
            println(
                """
                Name:               ${held.name} 
                Lebenspunkte (LP):  ${held.lebenspunkte} 
                Aktionspunkte (AP): ${held.aktionspunkte} 
                Rüstung:            ${held.ruestungsPunkte}
                """.trimIndent()
            )
            println()
            println(""" 
                 Fähigkeiten: 
                 
                 Name:      ${held.aktion1.name}                | Name:      ${held.aktion2.name}                | Name:      ${held.aktion3.name}                | Name:      ${held.aktion4.name}
                 Schaden:   ${held.aktion1.schaden}             | Schaden:   ${held.aktion2.schaden}             | Schaden:   ${held.aktion3.schaden}             | Schaden:   ${held.aktion4.schaden}
                 Heilung+:  ${held.aktion1.heilung}             | Heilung+:  ${held.aktion2.heilung}             | Heilung+:  ${held.aktion3.heilung}             | Heilung+:  ${held.aktion4.heilung}
                 Rüstung+:  ${held.aktion1.ruestungPlus}        | Rüstung+:  ${held.aktion2.ruestungPlus}        | Rüstung+:  ${held.aktion3.ruestungPlus}        | Rüstung+:  ${held.aktion4.ruestungPlus}
                 AP-Kosten: ${held.aktion1.aktionsPunkteKosten} | AP-Kosten: ${held.aktion2.aktionsPunkteKosten} | AP-Kosten: ${held.aktion3.aktionsPunkteKosten} | AP-Kosten: ${held.aktion4.aktionsPunkteKosten}
            """.trimIndent())
        }
        println("Wiederbelebung, belebt Helden.")
        println("Spott, zwingt einen Gegner bei seinem nächsten Angriff, den jeweiligen Helden anzugreifen.")

        println()
        println("Der finale Kampf beginnt!")
        println()
    }

    fun aktuellerStatusHeld(istAmZug: Held){
        println("""
                Status des Helden
                
                Name: ${istAmZug.name}
                Lebenspunkte (LP): ${istAmZug.lebenspunkte}
                Aktionspunkte (AP):${istAmZug.aktionspunkte} 
                Rüstung: ${istAmZug.ruestungsPunkte}
        """.trimIndent())
    }

    fun schadenUberZeitBerechnung(held: Held) {
        if (held.hatSchadenUeberZeitMalus) {
            if (held.lebenspunkte <= (held.standardLebenspunkte * 20 / 100)) {
                held.hatSchadenUeberZeitMalus = false
                println("${held.name} ist jetzt nicht mehr von Verseuchung betroffen.")
            } else {
                val verseuchungsSchaden = held.lebenspunkte * 10 / 100
                held.lebenspunkte -= verseuchungsSchaden
                println("${held.name} leidet an Verseuchung.")
                println("Verseuchung verursacht an ${held.name} $verseuchungsSchaden Schaden.")
                println("${held.name} hat jetzt ${held.lebenspunkte} Lebenspunkte.")
            }
        }
    }

    fun zurHeldIstTotListe(
        held: Held,
        haeldenListe: MutableList<Held>,
        hatBereitsGekaempftHeld: MutableList<Held>,
        heldIstTot: MutableList<Held>,
    ) {
        if (haeldenListe.isNotEmpty()) {
            if (held.lebenspunkte <= 0) {
                heldIstTot.add(held)
                haeldenListe.remove(held)
            }
        }
        if (hatBereitsGekaempftHeld.isNotEmpty()) {
            if (held.lebenspunkte <= 0) {
                heldIstTot.add(held)
                hatBereitsGekaempftHeld.remove(held)
            }
        }
    }

    fun zurGegnerIstTotListe(
        gegner: Gegner,
        gegnerListe: MutableList<Gegner>,
        hatBereitsGekaempftGegner: MutableList<Gegner>,
        gegnerIstTot: MutableList<Gegner>,
    ) {
        if (gegnerListe.isNotEmpty()) {
            if (gegner.lebenspunkte < 0) {
                gegnerIstTot.add(gegner)
                gegnerListe.remove(gegner)
            }
        }
        if (hatBereitsGekaempftGegner.isNotEmpty()) {
            if (gegner.lebenspunkte < 0) {
                gegnerIstTot.add(gegner)
                hatBereitsGekaempftGegner.remove(gegner)
            }
        }
    }

    fun zielAuswahlDesSpielers() {
        println("Was möchtest du als Ziel auswählen?")
        println(
            """1 für Helden
            |2 für Gegner
        """.trimMargin()
        )
        val heldenOderGegnerEingabe = readln().toInt()
        if (heldenOderGegnerEingabe == 1) {
            haeldenListe.addAll(hatBereitsGekaempftHeld)
            println("Welchen Helden möchtest du als Ziel auswählen?")
            for ((index: Int, held: Held) in haeldenListe.withIndex()) {
                println(
                    """
                ${index + 1} für ${held.name}
                """.trimIndent()
                )
            }
            val zielSpielerEingabe = readln().toInt()
            zielVerbuendeter = haeldenListe[zielSpielerEingabe - 1]
            istVerbuendeterZiel = true
            println("Du hast ${zielVerbuendeter.name} als Ziel für eine Fähigkeit ausgewählt.")
            haeldenListe.removeAll(hatBereitsGekaempftHeld)
        }
        if (heldenOderGegnerEingabe == 2) {
            println("Welchen Gegner möchtest du als Ziel auswählen?")
            gegnerListe.addAll(hatBereitsGekaempftGegner)
            for ((index: Int, gegner: Gegner) in gegnerListe.withIndex()) {
                println(
                    """
                ${index + 1} für ${gegner.name}
                """.trimIndent()
                )
            }
            val zielSpielerEingabe = readln().toInt()
            zielGegner = gegnerListe[zielSpielerEingabe - 1]
            istGegnerZiel = true
            println("Du hast ${zielGegner.name} als Ziel für eine Fähigkeit ausgewählt.")
            gegnerListe.removeAll(hatBereitsGekaempftGegner)
        }
    }

    fun zielAuswahlDesGegners(istAmZugGegner: Gegner) {
        if (istAmZugGegner.hatSpott) {
            println("${istAmZugGegner.name} wurde gespottet.")
            zielHeld = krieger
            println("${istAmZugGegner.name} wählt ${krieger.name} als sein Ziel aus.")
        } else {
            println("${istAmZugGegner.name} wählt ein Ziel zum Angreifen...")
            haeldenListe.addAll(hatBereitsGekaempftHeld)
            val zielGegnerEingabe = haeldenListe.random()
            zielHeld = zielGegnerEingabe
            println("${istAmZugGegner.name} wählt ${zielGegnerEingabe.name} als sein Ziel aus.")
            haeldenListe.removeAll(hatBereitsGekaempftHeld)
        }
    }

    fun heldenUeberPruefungGegenGegner(held: Held, gegner: Gegner) {
        when (held) {
            magier -> {
                held.angreifenMagierGegner(held, gegner)
            }

            heiler -> {
                held.angreifenHeilerGegner(held, gegner)
            }

            krieger -> held.angreifenKriegerGegner(held, gegner)
        }
    }

    fun gegnerUeberPruefungGegenHelden(gegner: Gegner, held: Held) {
        if (gegner == endBoss) {
            gegner.bossAngriff(gegner, held, helfer, gegnerListe, hatBereitsGekaempftHeld, gegnerIstTot)
        } else if (gegner == helfer) {
            gegner.helferAngriff(gegner, held)
        }
    }

    fun heldenUeberPruefungGegenVerbuendete(held: Held, zielHeld: Held) {
        when (held) {
            magier -> {
                magier.angreifenMagierVerbuendeter(held, zielHeld)
            }

            heiler -> {
                heiler.angreifenHeilerVerbuendeter(held, zielHeld, heldIstTot, hatBereitsGekaempftHeld)
            }

            krieger -> krieger.angreifenKriegerVerbuendeter(held, zielHeld)
        }
    }

    fun rundenKaempfe(haeldenListe: MutableList<Held>, gegnerListe: MutableList<Gegner>) {
        kampfBeginnt()
        var istAmZugHeld: Held
        var istAmZugGegner: Gegner
        while (krieger.lebenspunkte > 0 && magier.lebenspunkte > 0 && heiler.lebenspunkte > 0 || endBoss.lebenspunkte > 0 && helfer.lebenspunkte > 0) {
            while (haeldenListe.isNotEmpty()) {
                istAmZugHeld = haeldenListe.random()
                schadenUberZeitBerechnung(istAmZugHeld)
                istAmZugHeld.aktionspunkte = minOf(istAmZugHeld.standardAktionspunkte, istAmZugHeld.aktionspunkte + 20)
                haeldenListe.remove(istAmZugHeld)
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
                    val eingabe = readln().toInt()
                    if (eingabe != 1 && eingabe != 2) {
                        println("Ungültige Eingabe.")
                        continue
                    }
                    if (eingabe == 1) {
                        if (beutel.items.isEmpty()) {
                            println("Du hast keine Items mehr.")
                            continue
                        } else {
                            beutel.itemAuswaehlen().itemNutzen(istAmZugHeld)
                        }
                    }
                    if (eingabe == 2) {
                        zielAuswahlDesSpielers()
                    }
                } while (eingabe != 2)
                if (istGegnerZiel) {
                    heldenUeberPruefungGegenGegner(istAmZugHeld, zielGegner)
                }
                if (istVerbuendeterZiel) {
                    heldenUeberPruefungGegenVerbuendete(istAmZugHeld, zielVerbuendeter)
                }
                zurGegnerIstTotListe(zielGegner, gegnerListe, hatBereitsGekaempftGegner, gegnerIstTot)
                if (gegnerListe.isEmpty()) {
                    break
                }
            }
            if (gegnerListe.isEmpty()) {
                break
            }
            while (gegnerListe.isNotEmpty()) {
                istAmZugGegner = gegnerListe.random()
                istAmZugGegner.aktionspunkte =
                    minOf(istAmZugGegner.standardAktionspunkte, istAmZugGegner.aktionspunkte + 20)
                gegnerListe.remove(istAmZugGegner)
                hatBereitsGekaempftGegner.add(istAmZugGegner)
                println("${istAmZugGegner.name} ist an der Reihe.")
                zielAuswahlDesGegners(istAmZugGegner)
                gegnerUeberPruefungGegenHelden(istAmZugGegner, zielHeld)
                zurHeldIstTotListe(zielHeld, haeldenListe, hatBereitsGekaempftHeld, heldIstTot)
                if (haeldenListe.isEmpty()) {
                    break
                }
            }
            haeldenListe.addAll(hatBereitsGekaempftHeld)
            hatBereitsGekaempftHeld.clear()
            gegnerListe.addAll(hatBereitsGekaempftGegner)
            hatBereitsGekaempftGegner.clear()
            if (haeldenListe.isEmpty()) {
                break
            }
        }
        if (krieger.lebenspunkte < 0 && magier.lebenspunkte < 0 && heiler.lebenspunkte < 0) {
            println("Du hast verloren.")
        } else if (endBoss.lebenspunkte < 0 && helfer.lebenspunkte < 0) {
            println("Du hast gesiegt!!!")
        } else if (endBoss.lebenspunkte < 0) {
            println("Du hast gesiegt!!!")
        }
    }

    rundenKaempfe(haeldenListe, gegnerListe)

}
