package helden

import faehigkeiten.Faehigkeit
import gegner.Gegner

class Magier(
    name: String = "Ezekil Houdini",
    lebenspunkte: Int = 1000,
    standardLebenspunkte: Int = 1000,
    aktionspunkte: Int = 100,
    standardAktionspunkte: Int = 100,
    ruestungsPunkte: Int = 200,
    aktion1: Faehigkeit = Faehigkeit("Normaler Angriff", 100, 100, 0, 0, 0),
    aktion2: Faehigkeit = Faehigkeit("Verteidigungshaltung", 0, 0, 0, 50, 0),
    aktion3: Faehigkeit = Faehigkeit("Elementar-Schwert", 250, 250, 0, 0, 50),
    aktion4: Faehigkeit = Faehigkeit("Elementar-Schuss", 500, 500, 0, 0, 80),
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

    override fun angreifenMagierGegner(held: Held, gegner: Gegner) {

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
            if (attackenEingabe != 1 && attackenEingabe != 2 && attackenEingabe != 3 && attackenEingabe != 4) {
                println("Ungültige Eingabe.")
                angreifenMagierGegner(held, gegner)
            }
            val attacke = attackenListe[attackenEingabe - 1]
            if (attacke.aktionsPunkteKosten > held.aktionspunkte) {
                println("Der Held hat nicht genug AP für diese Fähigkeit.")
                continue
            }
            if (attacke.schaden > 0) {
                if (gegner.ruestungsPunkte - attacke.schaden >= 0) {
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
                gegner.lebenspunkte += attacke.heilung
                println("${held.name} heilt ${gegner.name} mit ${attacke.name} um ${attacke.heilung}.")
                println("${gegner.name} hat jetzt ${gegner.lebenspunkte} Lebenspunkte.")
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

    override fun angreifenMagierVerbuendeter(held: Held, verbuendeter: Held) {

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
            if (attackenEingabe != 1 && attackenEingabe != 2 && attackenEingabe != 3 && attackenEingabe != 4) {
                println("Ungültige Eingabe.")
                angreifenMagierVerbuendeter(held, verbuendeter)
            }
            val attacke = attackenListe[attackenEingabe - 1]
            if (attacke.aktionsPunkteKosten > held.aktionspunkte) {
                println("Der Held hat nicht genug AP für diese Fähigkeit.")
                continue
            }
            if (attacke.schaden > 0) {
                if (verbuendeter.ruestungsPunkte - attacke.schaden >= 0) {
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
                verbuendeter.lebenspunkte += attacke.heilung
                println("${held.name} heilt ${verbuendeter.name} mit ${attacke.name} um ${attacke.heilung}.")
                println("${verbuendeter.name} hat jetzt ${verbuendeter.lebenspunkte} Lebenspunkte.")
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
}

