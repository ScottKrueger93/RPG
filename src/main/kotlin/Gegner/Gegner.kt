package Gegner

import Fähigkeiten.Faehigkeit
import Helden.Held

open class Gegner(
    var name: String,
    var lebenspunkte: Int,
    var aktionspunkte: Int = 100,
    var standardAktionspunkte: Int = 100,
    var ruestungsPunkte: Int = 0,
    val aktion1: Faehigkeit,
    val aktion2: Faehigkeit,
    var aktion3: Faehigkeit,
    var aktion4: Faehigkeit,
) {
    private var attackenListe: MutableList<Faehigkeit> = mutableListOf(aktion1, aktion2, aktion3, aktion4)

    open fun helferAngriff(gegner: Gegner, held: Held) {

        var aktionspunkteReichenAus = false
        while (!aktionspunkteReichenAus) {
            val attacke = attackenListe.random()
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
                    val verbeibenderSchaden = attacke.schaden - held.ruestungsPunkte
                    val verbleibendeRuestung = maxOf(0, held.ruestungsPunkte - attacke.schaden)
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

    open fun bossAngriff(
        gegner: Gegner,
        held: Held,
        helfer: Helfer,
        gegnerList: MutableList<Gegner>,
        hatBereitsGekaempftListe: MutableList<Held>,
        gegnerIstTotListe: MutableList<Gegner>,
    ) {
        var aktionspunkteReichenAus = false
        while (!aktionspunkteReichenAus) {
            val attacke = attackenListe.random()
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
                    val verbeibenderSchaden = attacke.schaden - held.ruestungsPunkte
                    val verbleibendeRuestung = maxOf(0, held.ruestungsPunkte - attacke.schaden)
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