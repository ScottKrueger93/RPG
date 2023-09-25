package helden

import faehigkeiten.Faehigkeit
import gegner.Gegner

class Heiliger(
    name: String = "Apostel Jutzius des Heiligen Ordens",
    lebenspunkte: Int = 1500,
    standardLebenspunkte: Int = 1500,
    aktionspunkte: Int = 100,
    standardAktionspunkte: Int = 100,
    ruestungsPunkte: Int = 0,
    aktion1: Faehigkeit = Faehigkeit("Normaler Angriff", 50, 50, 0, 0, 0),
    aktion2: Faehigkeit = Faehigkeit("Verteidigungshaltung", 0, 0, 0, 50, 0),
    aktion3: Faehigkeit = Faehigkeit("Heilung durch Licht", 0, 0, 200, 0, 50),
    aktion4: Faehigkeit = Faehigkeit("Engelsrettung (Wiederbelebung)", 0, 0, 1000, 0, 100),
    hatSchadenUeberZeitMalus: Boolean = false,
) : Held(
    name,
    lebenspunkte,
    standardLebenspunkte,
    aktionspunkte,
    standardAktionspunkte,
    ruestungsPunkte,
    aktion1,
    aktion2,
    aktion3,
    aktion4,
    hatSchadenUeberZeitMalus
) {

    override var attackenListe: MutableList<Faehigkeit> = mutableListOf(aktion1, aktion2, aktion3, aktion4)

    override fun angreifenHeilerGegner(held: Held, gegner: Gegner) {

        println("Welche Fähigkeit von ${held.name} möchtest du ausführen?")
        for ((index, attacken) in attackenListe.withIndex()) {
            println(
                """
            ${index + 1} für ${attacken.name}
            """.trimIndent()
            )
        }
        var aktionspunkteReichenAus = false
        while (!aktionspunkteReichenAus) {
            val attackenEingabe: Int = readln().toInt()
            val attacke = attackenListe[attackenEingabe - 1]
            if (attacke.aktionsPunkteKosten > held.aktionspunkte) {
                println("Der Held hat nicht genug AP für diese Fähigkeit.")
                continue
            }
            if (attacke.schaden > 0) {
                if (gegner.ruestungsPunkte - attacke.schaden > 0) {
                    gegner.ruestungsPunkte -= attacke.schaden
                    println("${held.name} greift ${gegner.name} mit ${attacke.name} an und verursacht ${attacke.schaden} Schaden.")
                    println("${gegner.name} hat jetzt noch ${gegner.ruestungsPunkte} Rüstung.")
                } else if (gegner.ruestungsPunkte - attacke.schaden < 0) {
                    val verbeibenderSchaden = attacke.schaden - gegner.ruestungsPunkte
                    val verbleibendeRuestung = maxOf(0, gegner.ruestungsPunkte - attacke.schaden)
                    gegner.ruestungsPunkte = verbleibendeRuestung
                    gegner.lebenspunkte -= verbeibenderSchaden
                    println("${held.name} greift ${gegner.name} mit ${attacke.name} an und verursacht ${attacke.schaden} Schaden.")
                    println("${gegner.name} hat jetzt noch ${gegner.lebenspunkte} Lebenspunkte.")
                }
            }
            if (attacke.heilung > 0) {
                if (attacke.name == "Engelsrettung (Wiederbelebung)"){
                    println("${held.name} bestraft mit der Heiligen Macht der Engel ${gegner.name}.")
                    gegner.lebenspunkte -= attacke.heilung
                    println("${held.name} greift ${gegner.name} mit ${attacke.name} an und verursacht ${attacke.heilung} Schaden.")
                    println("${gegner.name} hat jetzt ${gegner.lebenspunkte} Lebenspunkte.")
                } else {
                gegner.lebenspunkte -= attacke.heilung
                println("${held.name} greift ${gegner.name} mit ${attacke.name} an und verursacht ${attacke.heilung} Schaden.")
                println("${gegner.name} hat jetzt ${gegner.lebenspunkte} Lebenspunkte.")
                }
            }
            if (attacke.ruestungPlus > 0) {
                held.ruestungsPunkte += attacke.ruestungPlus
                println("${held.name} geht in Verteidigungsposition und erhält ${attacke.ruestungPlus} Rüstung hinzu.")
                println("${held.name} hat jetzt ${held.ruestungsPunkte} Rüstung.")
            }
            if (attacke.aktionsPunkteKosten > 0) {
                held.aktionspunkte -= attacke.aktionsPunkteKosten
                println("Diese Fähigkeit hat ${attacke.aktionsPunkteKosten} AP gekostet.")
                println("${held.name} hat jetzt noch ${held.aktionspunkte} AP. ")
            }
            aktionspunkteReichenAus = true
        }
    }

    override fun angreifenHeilerVerbuendeter(held: Held, verbuendeter: Held, heldIstTot: MutableList<Held>, hatBereitsGekaempftListe: MutableList<Held>) {

        println("Welche Fähigkeit von ${held.name} möchtest du ausführen?")
        for ((index, attacken) in attackenListe.withIndex()) {
            println(
                """
            ${index + 1} für ${attacken.name}
            """.trimIndent()
            )
        }
        var aktionspunkteReichenAus = false
        while (!aktionspunkteReichenAus) {
            val attackenEingabe: Int = readln().toInt()
            val attacke = attackenListe[attackenEingabe - 1]
            if (attacke.aktionsPunkteKosten > held.aktionspunkte) {
                println("Der Held hat nicht genug AP für diese Fähigkeit.")
                continue
            }
            if (attacke.schaden > 0) {
                if (verbuendeter.ruestungsPunkte - attacke.schaden > 0) {
                    verbuendeter.ruestungsPunkte -= attacke.schaden
                    println("${held.name} greift ${verbuendeter.name} mit ${attacke.name} an und verursacht ${attacke.schaden} Schaden.")
                    println("${verbuendeter.name} hat jetzt noch ${verbuendeter.ruestungsPunkte} Rüstung.")
                } else if (verbuendeter.ruestungsPunkte - attacke.schaden < 0) {
                    val verbeibenderSchaden = attacke.schaden - verbuendeter.ruestungsPunkte
                    val verbleibendeRuestung = maxOf(0, verbuendeter.ruestungsPunkte - attacke.schaden)
                    verbuendeter.ruestungsPunkte = verbleibendeRuestung
                    verbuendeter.lebenspunkte -= verbeibenderSchaden
                    println("${held.name} greift ${verbuendeter.name} mit ${attacke.name} an und verursacht ${attacke.schaden} Schaden.")
                    println("${verbuendeter.name} hat jetzt noch ${verbuendeter.lebenspunkte} Lebenspunkte.")
                }

            }
            if (attacke.heilung > 0) {
                if (attacke.name == "Engelsrettung (Wiederbelebung)"){
                    if (heldIstTot.isEmpty()){
                        println("Alle Helden sind am Leben.")
                        angreifenHeilerVerbuendeter(held,verbuendeter,heldIstTot,hatBereitsGekaempftListe)
                    } else {
                        println("Welchen Helden willst du Wiederbeleben?")
                        for ((index: Int, toterHeld: Held) in heldIstTot.withIndex()){
                            println("${index+1} für ${toterHeld.name}")
                        }
                        try {
                            val spielerauswahlFuerWiederbelebung = readln().toInt()
                            if (spielerauswahlFuerWiederbelebung == 1) {
                                wiederbelebung(heldIstTot[0], hatBereitsGekaempftListe, heldIstTot)
                            } else if (spielerauswahlFuerWiederbelebung == 2) {
                                if (heldIstTot.size < 2) {
                                    println("Ungültige Eingabe.")
                                    continue
                                }else{
                                    wiederbelebung(heldIstTot[1], hatBereitsGekaempftListe, heldIstTot)
                                }
                            }
                        }catch (e: Exception){
                            println("Ungültige Eingabe.")
                            continue
                        }
                    }
                }
                else if (held.lebenspunkte + attacke.heilung > held.standardLebenspunkte) {
                    println(
                        "Die Lebenspunkte von ${held.name} werden um ${held.standardLebenspunkte - held.lebenspunkte} geheilt. " +
                                "Die Überheilung beträgt ${held.lebenspunkte + attacke.heilung - held.standardLebenspunkte}." +
                                " Seine Lebenspunkte betragen jetzt: ${held.standardLebenspunkte}"
                    )
                    held.lebenspunkte = held.standardLebenspunkte
                } else {
                    verbuendeter.lebenspunkte += attacke.heilung
                    println("${held.name} heilt ${verbuendeter.name} mit ${attacke.name} um ${attacke.heilung}.")
                    println("${verbuendeter.name} hat jetzt ${verbuendeter.lebenspunkte} Lebenspunkte.")
                }
            }
            if (attacke.ruestungPlus > 0) {
                held.ruestungsPunkte += attacke.ruestungPlus
                println("${held.name} geht in Verteidigungsposition und erhält ${attacke.ruestungPlus} Rüstung hinzu.")
                println("${held.name} hat jetzt ${held.ruestungsPunkte} Rüstung.")
            }
            if (attacke.aktionsPunkteKosten > 0) {
                //TODO: AP dürfen nicht unter 0 fallen können
                held.aktionspunkte -= attacke.aktionsPunkteKosten
                println("Diese Fähigkeit hat ${attacke.aktionsPunkteKosten} AP gekostet.")
                println("${held.name} hat jetzt noch ${held.aktionspunkte} AP. ")
            }
            aktionspunkteReichenAus = true
        }
    }


    private fun wiederbelebung(toterHeld: Held, hatBereitsGekaempftListe: MutableList<Held>, heldIstTot: MutableList<Held>){
        println("${toterHeld.name} wird Wiederbelebt.")
        hatBereitsGekaempftListe.add(toterHeld)
        heldIstTot.remove(toterHeld)
    }

}