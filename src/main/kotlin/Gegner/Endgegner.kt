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
    aktion4: Faehigkeit = Faehigkeit("Verseuchung", 0, 0, 0, 0, 50),
    aktion5: Faehigkeit = Faehigkeit("Schattenblitz", 350, 350, 0, 0, 30),
    aktion6: Faehigkeit = Faehigkeit("Monströsität beschwören", 0, 0, 0, 0, 0),
    hatSpott:Boolean = false
    ) : Gegner(
    name,
    lebenspunkte,
    aktionspunkte,
    standardAktionspunkte,
    ruestungsPunkte,
    aktion1,
    aktion2,
    aktion3,
    aktion4,
    hatSpott,
) {

    private var attackenListeBoss: MutableList<Faehigkeit> =
        mutableListOf(aktion1, aktion2, aktion3, aktion4, aktion5, aktion6)

    override fun bossAngriff(
        gegner: Gegner,
        held: Held,
        helfer: Helfer,
        gegnerList: MutableList<Gegner>,
        hatBereitsGekaempftListe: MutableList<Held>,
        gegnerIstTotListe: MutableList<Gegner>,
    ) {

        var attacke: Faehigkeit
        var aktionspunkteReichenAus = false
        while (aktionspunkteReichenAus == false) {
            attacke = attackenListeBoss.random()
            if (attacke.aktionsPunkteKosten > gegner.aktionspunkte) {
                println("Der Gegner hat nicht genug AP für diese Fähigkeit.")
                continue
            }
            if (attacke.name == "Verseuchung") {
                if (held.hatSchadenUeberZeitMalus == true) {
                    println("${held.name} ist bereits verseucht.")
                    continue
                } else {
                    schadenUeberZeitAngriff(attacke, held, gegner)
                }
            }
            if (attacke.name == "Monströsität beschwören") {
                if (helfer in gegnerList || helfer in gegnerIstTotListe) {
                    continue
                } else {
                    helferBeschwoeren(attacke, gegner, helfer, gegnerList)
                }
            }
            if (attacke.schaden > 0) {
                if (attacke.name == "Schatten-Miasma") {
                    flaechenAngriff(attacke, gegner, hatBereitsGekaempftListe)
                } else if (held.ruestungsPunkte - attacke.schaden > 0) {
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
                if (attacke.name == "Verteidigungshaltung") {
                    gegner.ruestungsPunkte += attacke.ruestungPlus
                    println("${gegner.name} geht in Verteidigungsposition und erhält ${attacke.ruestungPlus} Rüstung hinzu.")
                    println("${gegner.name} hat jetzt ${gegner.ruestungsPunkte} Rüstung.")
                }
            }
            if (attacke.aktionsPunkteKosten > 0) {
                gegner.aktionspunkte -= attacke.aktionsPunkteKosten
                println("Diese Fähigkeit hat ${attacke.aktionsPunkteKosten} AP gekostet.")
                println("${gegner.name} hat jetzt noch ${gegner.aktionspunkte} AP. ")
            }
            aktionspunkteReichenAus = true
            gegner.hatSpott = false
        }
    }

    fun flaechenAngriff(attacke: Faehigkeit, gegner: Gegner, hatBereitsGekaempftListe: MutableList<Held>) {
        for (heldVonFlaechenangriffGetroffen in hatBereitsGekaempftListe) {
            if (heldVonFlaechenangriffGetroffen.ruestungsPunkte - attacke.schaden > 0) {
                heldVonFlaechenangriffGetroffen.ruestungsPunkte -= attacke.schaden
                println("${gegner.name} greift ${heldVonFlaechenangriffGetroffen.name} mit ${attacke.name} an und verursacht ${attacke.schaden} Schaden.")
                println("${heldVonFlaechenangriffGetroffen.name} hat jetzt noch ${heldVonFlaechenangriffGetroffen.ruestungsPunkte} Rüstung.")
            } else if (heldVonFlaechenangriffGetroffen.ruestungsPunkte - attacke.schaden < 0) {
                var verbeibenderSchaden = attacke.schaden - heldVonFlaechenangriffGetroffen.ruestungsPunkte
                var verbleibendeRuestung = maxOf(0, heldVonFlaechenangriffGetroffen.ruestungsPunkte - attacke.schaden)
                heldVonFlaechenangriffGetroffen.ruestungsPunkte = verbleibendeRuestung
                heldVonFlaechenangriffGetroffen.lebenspunkte -= verbeibenderSchaden
                println("${gegner.name} greift ${heldVonFlaechenangriffGetroffen.name} mit ${attacke.name} an und verursacht ${attacke.schaden} Schaden.")
                println("${heldVonFlaechenangriffGetroffen.name} hat jetzt noch ${heldVonFlaechenangriffGetroffen.lebenspunkte} Lebenspunkte.")
            }
        }
    }

    fun helferBeschwoeren(attacke: Faehigkeit, gegner: Gegner, helfer: Helfer, gegnerList: MutableList<Gegner>) {
        println("${gegner.name} setzt ${attacke.name} ein und beschwört eine ${helfer.name}.")
        gegnerList.add(helfer)
    }

    fun schadenUeberZeitAngriff(attacke: Faehigkeit, held: Held, gegner: Gegner) {
        println("${gegner.name} setzt ${attacke.name} ein. ${held.name} ist nun verseucht. ")
        println("${held.name} nimmt ab jetzt jede Runde 10% Schaden seiner aktuellen Lebenspunkte, bis diese 20% seiner maximalen Lebenspunkte betragen.")
        held.hatSchadenUeberZeitMalus = true
    }
}
