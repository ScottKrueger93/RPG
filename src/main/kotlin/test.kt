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
    var haeldenListe = mutableListOf(krieger, magier, heiler)
    var gegnerListe: MutableList<Gegner> = mutableListOf(endBoss)
    var hatBereitsGekaempftHeld: MutableList<Held> = mutableListOf()
    var hatBereitsGekaempftGegner: MutableList<Gegner> = mutableListOf()
    var heldIstTot: MutableList<Held> = mutableListOf()
    var gegnerIstTot: MutableList<Gegner> = mutableListOf()

    var zielGegner = gegnerListe[0]
    var zielHeld = haeldenListe[0]
    var zielVerbuendeter = haeldenListe[0]
    var istVerbuendeterZiel = false
    var istGegnerZiel = false

    fun zurHeldIstTotListe(held: Held, haeldenListe: MutableList<Held>, hatBereitsGekaempftHeld: MutableList<Held>, heldIstTot: MutableList<Held>){
        if (haeldenListe.isNotEmpty()){
            if (held.lebenspunkte <= 0){
                heldIstTot.add(held)
                haeldenListe.remove(held)
            }
        }
        if (hatBereitsGekaempftHeld.isNotEmpty()) {
            if (held.lebenspunkte <= 0){
                heldIstTot.add(held)
                hatBereitsGekaempftHeld.remove(held)
            }
        }
    }

    fun zurGegnerIstTotListe(gegner: Gegner, gegnerListe: MutableList<Gegner>, hatBereitsGekaempftGegner: MutableList<Gegner>, gegnerIstTot: MutableList<Gegner>){
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
        var heldenOderGegnerEingabe = readln().toInt()
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
            var zielSpielerEingabe = readln().toInt()
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
            var zielSpielerEingabe = readln().toInt()
            zielGegner = gegnerListe[zielSpielerEingabe - 1]
            istGegnerZiel = true
            println("Du hast ${zielGegner.name} als Ziel für eine Fähigkeit ausgewählt.")
            gegnerListe.removeAll(hatBereitsGekaempftGegner)
        }
    }

    fun zielAuswahlDesGegners(istAmZugGegner: Gegner) {
        println("${istAmZugGegner.name} wählt ein Ziel zum Angreifen...")
        haeldenListe.addAll(hatBereitsGekaempftHeld)
        var zielGegnerEingabe = haeldenListe.random()
            zielHeld = zielGegnerEingabe
            println("${istAmZugGegner.name} wählt ${zielGegnerEingabe.name} als sein Ziel aus.")
            haeldenListe.removeAll(hatBereitsGekaempftHeld)
        }

    fun heldenUeberPruefungGegenGegner(held: Held, gegner: Gegner) {
        if (held == magier) {
            held.angreifenMagierGegner(held, gegner)
        } else if (held == heiler) {
            held.angreifenHeilerGegner(held, gegner)
        } else if (held == krieger)
            held.angreifenKriegerGegner(held, gegner)
    }

    fun gegnerUeberPruefungGegenHelden(gegner: Gegner, held: Held) {
        if (gegner == endBoss) {
            gegner.bossAngriff(gegner, held)
        } else if (gegner == helfer) {
            gegner.helferAngriff(gegner, held)
        }
    }

    fun heldenUeberPruefungGegenVerbuendete(held: Held, zielHeld: Held) {
        if (held == magier) {
            magier.angreifenMagierVerbuendeter(held, zielHeld)
        } else if (held == heiler) {
            heiler.angreifenHeilerVerbuendeter(held, zielHeld)
        } else if (held == krieger)
            krieger.angreifenKriegerVerbuendeter(held, zielHeld)
    }

    fun rundenKaempfe(haeldenListe: MutableList<Held>, gegnerListe: MutableList<Gegner>) {
        var istAmZugHeld: Held
        var istAmZugGegner: Gegner
        while (krieger.lebenspunkte != 0 && magier.lebenspunkte != 0 && heiler.lebenspunkte != 0 || endBoss.lebenspunkte != 0 && helfer.lebenspunkte != 0) {
            while (haeldenListe.isNotEmpty()) {
                istAmZugHeld = haeldenListe.random()
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
                zurHeldIstTotListe(zielHeld,haeldenListe, hatBereitsGekaempftHeld, heldIstTot)
                if (haeldenListe.isEmpty()) {
                    break
                }
            }
            if (haeldenListe.isEmpty()) {
                break
            }
            haeldenListe.addAll(hatBereitsGekaempftHeld)
            hatBereitsGekaempftHeld.clear()
            gegnerListe.addAll(hatBereitsGekaempftGegner)
            hatBereitsGekaempftGegner.clear()
        }
        if (krieger.lebenspunkte < 0 && magier.lebenspunkte < 0 && heiler.lebenspunkte < 0) {
            println("Du hast verloren.")
        } else if (endBoss.lebenspunkte < 0 && helfer.lebenspunkte < 0) {
            println("Du hast gesiegt!!!")
        } else if (endBoss.lebenspunkte < 0){
            println("Du hast gesiegt!!!")
        }
    }

    rundenKaempfe(haeldenListe, gegnerListe)

}
