import beutel.*
import gegner.*
import helden.*

fun main() {

    //Die Unterklassen von der Mutterklasse "Held" werden Inztanziert
    val krieger = Krieger()
    val magier = Magier()
    val heiler = Heiliger()

    //Die Unterklassen von der Mutterklasse "Gegner" werden Inztanziert
    val endBoss = Endgegner()
    val helfer = Helfer()

    // Klasse "Beutel" wird Inztanziert
    val beutel = Beutel()

    // Listen für die Rundenkämpfe werden gespeichert
    val haeldenListe =
        mutableListOf(krieger, magier, heiler)   // Liste in der die Helden sind, die noch nicht am Zug waren
    val gegnerListe: MutableList<Gegner> =
        mutableListOf(endBoss)               // Liste in der die Gegner sind, die noch nicht am Zug waren
    val hatBereitsGekaempftHeld: MutableList<Held> =
        mutableListOf()            // Liste in der die Helden sind, die schon am Zug waren
    val hatBereitsGekaempftGegner: MutableList<Gegner> =
        mutableListOf()        // Liste in der die Gegner sind, die schon am Zug waren
    val heldIstTot: MutableList<Held> =
        mutableListOf()                         // Liste in der später die toten Helden kommen
    val gegnerIstTot: MutableList<Gegner> =
        mutableListOf()                     // Liste in der später die toten Gegner kommen

    var zielGegner =
        gegnerListe[0]         // später wird hier das Ziel des Helden eingefügt, durch eine Spieler-Eingabe
    var zielHeld = haeldenListe[0]            // später wird hier das Ziel des Gegners per Zufall eingefügt
    var zielVerbuendeter =
        haeldenListe[0]    // später wird hier das Ziel des Helden, welcher einen Helden angreifen soll eingefügt, durch eine Spieler-Eingabe
    var istVerbuendeterZiel: Boolean                // variable wird später auf "true" gesetzt, wenn der Spieler einen Verbündeten Helden Angreifen will
    var istGegnerZiel: Boolean                      // variable wird später auf "true" gesetzt, wenn der Spieler einen Gegner Angreifen will

    // Leitet eine Story zum Spiel ein.
    fun story() {
        println()
        Thread.sleep(2000)
        println("Durch zahlreiche Herausforderungen haben sich drei tapfere Helden bewiesen den dunklen Mächten der Welt zu trotzen.")
        Thread.sleep(5000)
        println()
        println("Eren das Bollwerk, welcher mit seinem mächtigen Schild und durch schier unmenschliche Stärke es mit einer Armee von 20 Mann alleine aufnehmen konnte.")
        Thread.sleep(5000)
        println()
        println("Jutzius, von dem man sagt, er wurde von den Heiligen selbst, den Göttern des Lichts, auf die Welt gesandt.")
        Thread.sleep(5000)
        println()
        println("Und Ezekiel, ein mächtiger Magier des Zirkels der drei Zauberer.")
        Thread.sleep(5000)
        println()
        println("Lange zeit war unklar wie die mächte des Bösen, in die Heiligen Hauptstädte der Heiligen Ordnung einfallen konnten und diese einen nach dem anderen einnehmen konnten.")
        Thread.sleep(5000)
        println()
        println("Doch nach dem sich die Gruppe aus den drei Helden auf eine lange Reise begaben die dringend benötigte Wende in diesem Krieg herbeizuführen,")
        println("machten sie eine abscheuliche entdeckung.")
        println()
        Thread.sleep(5000)
        println("Der Erzbischof, der engste berater des Obersten Bischoffs der Heiligen Kirsche, war in wirklichkeit der Nekromant Ankylar, ")
        println("welcher über die Jahre hin weg getarnt Informationen weitergab und dafür verantwortlich war das hunderte Mitglieder des Heiligen Ordens spurlos verschwanden.")
        Thread.sleep(5000)
        println()
        println("Unsere tapferen Helden schließen sich zu einem letzten Kampf zusammen um dem Befehlshaber der Dunklen Legionen einhalt zu bieten...")
    }

    // Gibt eine Übersicht zu allen Helden und dessen Fähigkeiten aus.
    fun kampfBeginnt() {
        Thread.sleep(5000)
        println()
        println("Deine Truppe besteht aus folgenden Helden, mit den angezeigten Werten und Fähigkeiten:")
        println()
        Thread.sleep(5000)
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
            Thread.sleep(5000)
            println(
                """ 
                 Fähigkeiten: 
                 
                 Name:      ${held.aktion1.name}
                 Schaden:   ${held.aktion1.schaden}
                 Heilung+:  ${held.aktion1.heilung}
                 Rüstung+:  ${held.aktion1.ruestungPlus}
                 AP-Kosten: ${held.aktion1.aktionsPunkteKosten}""".trimIndent()
            )
            println()
            Thread.sleep(5000)
            println(
                """
                 Name:      ${held.aktion2.name}
                 Schaden:   ${held.aktion2.schaden}
                 Heilung+:  ${held.aktion2.heilung}
                 Rüstung+:  ${held.aktion2.ruestungPlus}
                 AP-Kosten: ${held.aktion2.aktionsPunkteKosten}""".trimIndent()
            )
            println()
            Thread.sleep(5000)
            println(
                """
                 Name:      ${held.aktion3.name}
                 Schaden:   ${held.aktion3.schaden}
                 Heilung+:  ${held.aktion3.heilung}
                 Rüstung+:  ${held.aktion3.ruestungPlus}
                 AP-Kosten: ${held.aktion3.aktionsPunkteKosten}""".trimIndent()
            )
            println()
            Thread.sleep(5000)
            println(
                """
                 Name:      ${held.aktion4.name}
                 Schaden:   ${held.aktion4.schaden}
                 Heilung+:  ${held.aktion4.heilung}
                 Rüstung+:  ${held.aktion4.ruestungPlus}
                 AP-Kosten: ${held.aktion4.aktionsPunkteKosten}""".trimIndent()
            )
            println()
            Thread.sleep(5000)
        }
        println("Wiederbelebung, belebt Helden.")
        println()
        Thread.sleep(5000)
        println("Spott, zwingt einen Gegner bei seinem nächsten Angriff, den jeweiligen Helden anzugreifen.")
        println()
        Thread.sleep(5000)
        println("Der finale Kampf beginnt!")
    }

    /**
     * @fun gibt zu jedem Rundenbeginn den aktuellen Status der Werte des Helden aus, der am Zug ist.
     * @param istAmZug der Held, welcher aktuell in der Runde an der Reihe ist, wird eingesetzt.
     */
    fun aktuellerStatusHeld(istAmZug: Held) {
        println()
        Thread.sleep(5000)
        println(
            """
                Status des Helden
                
                Name: ${istAmZug.name}
                Lebenspunkte (LP): ${istAmZug.lebenspunkte}
                Aktionspunkte (AP):${istAmZug.aktionspunkte} 
                Rüstung: ${istAmZug.ruestungsPunkte}
        """.trimIndent()
        )
        Thread.sleep(5000)
    }

    /**
     * @fun prüft, ob ein Held vom Angriff des Bosses, welcher Schaden über Zeit macht, betroffen ist und wenn ja, dann berechnet er den Schaden.
     * @param held hier wird der Held, welcher aktuell an der Reihe ist eingesetzt, welcher dann überprüft wird.
     */
    fun schadenUberZeitBerechnung(held: Held) {
        if (held.hatSchadenUeberZeitMalus) {
            if (held.lebenspunkte <= (held.standardLebenspunkte * 20 / 100)) {
                held.hatSchadenUeberZeitMalus = false
                Thread.sleep(5000)
                println()
                println("${held.name} ist jetzt nicht mehr von Verseuchung betroffen.")
            } else {
                val verseuchungsSchaden = held.lebenspunkte * 10 / 100
                held.lebenspunkte -= verseuchungsSchaden
                Thread.sleep(5000)
                println()
                println("${held.name} leidet an Verseuchung.")
                Thread.sleep(5000)
                println()
                println("Verseuchung verursacht an ${held.name} $verseuchungsSchaden Schaden.")
                Thread.sleep(5000)
                println()
                println("${held.name} hat jetzt ${held.lebenspunkte} Lebenspunkte.")
            }
        }
    }

    /**
     * @fun nach einem Angriff des Helden oder des Gegners wird überprüft, ob ein Held tot ist, und wenn ja, dann wird dieser zur Liste der toten Helden hinzugefügt und aus den anderen Listen entfernt.
     * @param held hier wird der Held, welcher angegriffen wurde eingesetzt.
     * @param haeldenListe Liste der Helden, welche noch am Zug sein können, wird eingesetzt und überprüft, ob darin ein Held ist, der Tot ist.
     * @param hatBereitsGekaempftHeld Liste der Helden, welche schon am Zug waren, wird eingesetzt und überprüft, ob darin ein Held ist, der Tot ist.
     * @param heldIstTot hier wird die Liste der Toten Helden eingesetzt.
     */
    fun zurHeldIstTotListe(
        held: Held,
        haeldenListe: MutableList<Held>,
        hatBereitsGekaempftHeld: MutableList<Held>,
        heldIstTot: MutableList<Held>,
    ) {
        if (haeldenListe.isNotEmpty()) {
            if (held.lebenspunkte <= 0) {
                println()
                Thread.sleep(5000)
                println("${held.name} ist gestorben.")
                heldIstTot.add(held)
                haeldenListe.remove(held)
            }
        }
        if (hatBereitsGekaempftHeld.isNotEmpty()) {
            if (held.lebenspunkte <= 0) {
                println()
                Thread.sleep(5000)
                println("${held.name} ist gestorben.")
                heldIstTot.add(held)
                hatBereitsGekaempftHeld.remove(held)
            }
        }
    }

    /**
     * @fun nach einem Angriff von einer der Helden wird überprüft, ob ein Gegner tot ist, und wenn ja, dann wird dieser zur Liste der toten Gegner hinzugefügt und aus den anderen Listen entfernt.
     * @param gegner hier wird der Gegner, welcher angegriffen wurde eingesetzt.
     * @param gegnerListe Liste der Gegner, welche noch am Zug sein können, wird eingesetzt und überprüft, ob darin ein Gegner ist, der Tot ist.
     * @param hatBereitsGekaempftGegner Liste der Gegner, welche schon am Zug waren, wird eingesetzt und überprüft, ob darin ein Gegner ist, der Tot ist.
     * @param gegnerIstTot hier wird die Liste der Toten Gegner eingesetzt.
     */
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
                println()
                Thread.sleep(5000)
                println("${gegner.name} ist gestorben.")
            }
        }
        if (hatBereitsGekaempftGegner.isNotEmpty()) {
            if (gegner.lebenspunkte < 0) {
                gegnerIstTot.add(gegner)
                hatBereitsGekaempftGegner.remove(gegner)
                println()
                Thread.sleep(5000)
                println("${gegner.name} ist gestorben.")
            }
        }
    }

    //  Ermöglicht dem Spieler das Auswählen eines Verbündeten oder eines Gegners als Ziel
    fun zielAuswahlDesSpielers() {
        println()
        println("Was möchtest du als Ziel auswählen?")
        println(
            """1 für Helden
            |2 für Gegner
        """.trimMargin()
        )
        val heldenOderGegnerEingabe = readln().toInt()
        if (heldenOderGegnerEingabe == 1) {
            haeldenListe.addAll(hatBereitsGekaempftHeld)
            println()
            println("Welchen Helden möchtest du als Ziel auswählen?")
            for ((index: Int, held: Held) in haeldenListe.withIndex()) {
                println(
                    """
                ${index + 1} für ${held.name}
                """.trimIndent()
                )
            }
            val zielSpielerEingabe = readln().toInt()
            println()
            zielVerbuendeter = haeldenListe[zielSpielerEingabe - 1]
            istVerbuendeterZiel = true
            println("Du hast ${zielVerbuendeter.name} als Ziel für eine Fähigkeit ausgewählt.")
            haeldenListe.removeAll(hatBereitsGekaempftHeld)
            println()
        }
        if (heldenOderGegnerEingabe == 2) {
            println()
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
            println()
            zielGegner = gegnerListe[zielSpielerEingabe - 1]
            istGegnerZiel = true
            println("Du hast ${zielGegner.name} als Ziel für eine Fähigkeit ausgewählt.")
            gegnerListe.removeAll(hatBereitsGekaempftGegner)
            println()
        }
    }

    /**
     * @fun prüft als erstes, ob der Spieler mit einem Helden "Spott" eingesetzt hat und wenn ja, wird dieser Held als Ziel für den Angriff des Gegners ausgewählt.
     *      Wenn nein, dann wird das Ziel des Gegners zufällig ausgewählt.
     * @param istAmZugGegner der aktuelle Gegner, welcher am Zug ist, wird eingesetzt
     */
    fun zielAuswahlDesGegners(istAmZugGegner: Gegner) {
        println()
        Thread.sleep(5000)
        if (istAmZugGegner.hatSpott) {
            println("${istAmZugGegner.name} wurde gespottet.")
            zielHeld = krieger
            println()
            Thread.sleep(5000)
            println("${istAmZugGegner.name} wählt ${krieger.name} als sein Ziel aus.")
        } else {
            println("${istAmZugGegner.name} wählt ein Ziel zum Angreifen...")
            haeldenListe.addAll(hatBereitsGekaempftHeld)
            val zielGegnerEingabe = haeldenListe.random()
            zielHeld = zielGegnerEingabe
            println()
            Thread.sleep(5000)
            println("${istAmZugGegner.name} wählt ${zielGegnerEingabe.name} als sein Ziel aus.")
            haeldenListe.removeAll(hatBereitsGekaempftHeld)
        }
    }

    /**
     * @fun überprüft, welcher Typ Held einen Angriff gegen einen Gegner ausführt und führt die entsprechende Methode der Class aus
     * @param held aktuelle Held, welcher am Zug ist, wird eingesetzt.
     * @param gegner der Gegner, welchen der Spieler zuvor ausgewählt hat, wird eingesetzt.
     */
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

    /**
     * @fun überprüft, welcher Typ Gegner einen Angriff gegen einen Helden ausführt und führt die entsprechende Methode der Class aus
     * @param gegner aktuelle Gegner, welcher am Zug ist, wird eingesetzt.
     * @param held der Held, welchen der Gegner zuvor zufällig ausgewählt hat, wird eingesetzt.
     */
    fun gegnerUeberPruefungGegenHelden(gegner: Gegner, held: Held) {
        if (gegner == endBoss) {
            gegner.bossAngriff(gegner, held, helfer, gegnerListe, hatBereitsGekaempftHeld, gegnerIstTot)
        } else if (gegner == helfer) {
            gegner.helferAngriff(gegner, held)
        }
    }

    /**
     * @fun überprüft, welcher Typ Held einen Angriff gegen einen verbündeten Helden ausführt und führt die entsprechende Methode der Class aus
     * @param held aktuelle Held, welcher am Zug ist, wird eingesetzt.
     * @param zielHeld der Held, welchen der Spieler zuvor ausgewählt hat, wird eingesetzt.
     */
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

    /**
     * @fun Die Kampfphase des Spiels wird ausgeführt. Enthält außerdem alle Funktionen und damit auch alle Methoden der Classes.
     * @param haeldenListe Liste der Helden, welche noch nicht am Zug waren, wird eingesetzt.
     * @param gegnerListe Liste der Gegner, welche noch nicht am Zug waren, wird eingesetzt.
     */
    fun rundenKaempfe(haeldenListe: MutableList<Held>, gegnerListe: MutableList<Gegner>) {
        story()
        kampfBeginnt()
        var istAmZugHeld: Held
        var istAmZugGegner: Gegner
        while ((krieger.lebenspunkte > 0 && magier.lebenspunkte > 0 && heiler.lebenspunkte > 0) || (endBoss.lebenspunkte > 0 && helfer.lebenspunkte > 0)) {
            while (haeldenListe.isNotEmpty()) {
                istAmZugHeld = haeldenListe.random()
                println()
                Thread.sleep(5000)
                println("${istAmZugHeld.name} ist an der Reihe.")
                aktuellerStatusHeld(istAmZugHeld)
                schadenUberZeitBerechnung(istAmZugHeld)
                istAmZugHeld.aktionspunkte = minOf(istAmZugHeld.standardAktionspunkte, istAmZugHeld.aktionspunkte + 20) // Aktionspunkte des Helden, welcher am Zug ist, werden + 20 erhöht. Kann nicht höher als die Standardaktionspunkte liegen.
                haeldenListe.remove(istAmZugHeld)
                hatBereitsGekaempftHeld.add(istAmZugHeld)
                istAmZugHeld.aktion1.schaden = istAmZugHeld.aktion1.standardSchaden     // Wenn der Held zuvor ein Item einsetzt, was seinen Schaden erhöht, wird hiermit der Schaden auf den Standardwert zurückgesetzt
                istAmZugHeld.aktion3.schaden = istAmZugHeld.aktion3.standardSchaden     // Wenn der Held zuvor ein Item einsetzt, was seinen Schaden erhöht, wird hiermit der Schaden auf den Standardwert zurückgesetzt
                istAmZugHeld.aktion4.schaden = istAmZugHeld.aktion4.standardSchaden     // Wenn der Held zuvor ein Item einsetzt, was seinen Schaden erhöht, wird hiermit der Schaden auf den Standardwert zurückgesetzt
                istVerbuendeterZiel = false
                istGegnerZiel = false
                Thread.sleep(5000)
                do {
                    println()
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
                zurHeldIstTotListe(zielHeld, haeldenListe, hatBereitsGekaempftHeld, heldIstTot)
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
                println()
                Thread.sleep(5000)
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
        if (krieger.lebenspunkte <= 0 && magier.lebenspunkte <= 0 && heiler.lebenspunkte <= 0) {
            println("Du hast verloren.")
        } else if (endBoss.lebenspunkte <= 0 && helfer.lebenspunkte <= 0) {
            println("Du hast gesiegt!!!")
        } else if (endBoss.lebenspunkte <= 0) {
            println("Du hast gesiegt!!!")
        }
    }

    rundenKaempfe(haeldenListe, gegnerListe)

}
