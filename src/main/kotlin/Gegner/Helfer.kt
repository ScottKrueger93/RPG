package Gegner

import Fähigkeiten.Faehigkeit
import Helden.Held

class Helfer(
    name: String = "Mönströsität aus Leichen der Heiligen Ordnung",
    lebenspunkte: Int = 750,
    aktionspunkte: Int = 100,
    standardAktionspunkte: Int = 100,
    ruestungsPunkte: Int = 250,
    aktion1: Faehigkeit = Faehigkeit("Normaler Angriff", 50, 50, 0, 0, 0),
    aktion2: Faehigkeit = Faehigkeit("Verteidigungshaltung", 0, 0, 0, 50, 0),
    aktion3: Faehigkeit = Faehigkeit("Leichenwurf", 100, 100, 0, 0, 50),
    aktion4: Faehigkeit = Faehigkeit("Mauer aus Leichen", 0, 0, 0, 100, 50),
) : Gegner(name, lebenspunkte, aktionspunkte, standardAktionspunkte, ruestungsPunkte, aktion1, aktion2, aktion3, aktion4) {

    private var attackenListeHelfer: MutableList<Faehigkeit> = mutableListOf(aktion1, aktion2, aktion3, aktion4)

    override fun helferAngriff(gegner: Gegner, held: Held) {

        var aktionspunkteReichenAus: Boolean = false
        while (aktionspunkteReichenAus == false) {
            var attacke = attackenListeHelfer.random()
            if (attacke.aktionsPunkteKosten > gegner.aktionspunkte) {
                println("Der Gegner hat nicht genug AP für diese Fähigkeit.")
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