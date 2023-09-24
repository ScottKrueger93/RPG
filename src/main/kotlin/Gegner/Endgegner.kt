package Gegner

import Fähigkeiten.Faehigkeit
import Helden.Held

class Endgegner(
    name: String = "Erzbischof Ankylar des Heiligen Ordens",
    lebenspunkte: Int = 5000,
    aktionspunkte: Int = 100,
    standardAktionspunkte: Int = 100,
    ruestungsPunkte: Int = 1000,
    aktion1: Faehigkeit = Faehigkeit("Normaler Angriff", 50, 50, 0, 0, 0),
    aktion2: Faehigkeit = Faehigkeit("Verteidigungshaltung", 0, 0, 0, 50, 0),
    aktion3: Faehigkeit = Faehigkeit("Schatten-Miasma", 150, 150, 0, 0, 50),
    aktion4: Faehigkeit = Faehigkeit("Verseuchung", 10, 10, 0, 0, 50),
    aktion5: Faehigkeit = Faehigkeit("Schattenblitz", 350, 350, 0, 0, 30),
    aktion6: Faehigkeit = Faehigkeit("Knochenschild", 0, 0, 0, 250, 30),
) : Gegner(name, lebenspunkte, aktionspunkte, standardAktionspunkte, ruestungsPunkte, aktion1, aktion2, aktion3, aktion4) {

    private var attackenListe: MutableList<Faehigkeit> = mutableListOf(aktion1, aktion2, aktion3, aktion4, aktion5, aktion6)

    fun bossAngriff(gegner: Gegner, held: Held) {

        var aktionspunkteReichenAus: Boolean = false
        while (aktionspunkteReichenAus == false) {
            var attacke = attackenListe.random()
            if (attacke.aktionsPunkteKosten > gegner.aktionspunkte) {
                println("Der Held hat nicht genug AP für diese Fähigkeit.")
                continue
            }
            if (attacke.schaden > 0) {
                if (held.ruestungsPunkte - attacke.schaden > 0) {
                    held.ruestungsPunkte -= attacke.schaden
                    println("${gegner.name} greift ${held.name} mit ${attacke.name} an und verursacht ${attacke.schaden} Schaden.")
                    println("${held.name} hat jetzt noch ${held.ruestungsPunkte} Rüstung.")
                } else if (held.ruestungsPunkte - attacke.schaden < 0) {
                    var verbeibenderSchaden = attacke.schaden - held.ruestungsPunkte
                    var verbleibendeRuestung = maxOf(0, held.ruestungsPunkte - attacke.schaden)
                    held.ruestungsPunkte = verbleibendeRuestung
                    held.lebenspunkte -= verbeibenderSchaden
                    println("${gegner.name} greift ${held.name} mit ${attacke.name} an und verursacht ${attacke.schaden} Schaden.")
                    println("${held.name} hat jetzt noch ${held.lebenspunkte} Lebenspunkte.")
                }
            }
            if (attacke.heilung > 0) {
                held.lebenspunkte += attacke.heilung
                println("${gegner.name} heilt ${held.name} mit ${attacke.name} um ${attacke.heilung}.")
                println("${held.name} hat jetzt ${held.lebenspunkte} Lebenspunkte.")
            }
            if (attacke.ruestungPlus > 0) {
                gegner.ruestungsPunkte += attacke.ruestungPlus
                println("${gegner.name} geht in Verteidigungsposition und erhält ${attacke.ruestungPlus} Rüstung hinzu.")
                println("${gegner.name} hat jetzt ${gegner.ruestungsPunkte} Rüstung.")
            }
            if (attacke.aktionsPunkteKosten > 0) {
                gegner.aktionspunkte -= attacke.aktionsPunkteKosten
                println("Diese Fähigkeit hat ${attacke.aktionsPunkteKosten} AP gekostet.")
                println("${gegner.name} hat jetzt noch ${gegner.aktionspunkte} AP. ")
            }
            aktionspunkteReichenAus = true
        }
    }

}
