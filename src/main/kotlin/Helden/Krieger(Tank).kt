package Helden

import Kampf.Rundenkampf
import Kampf.kampf

class `Krieger(Tank)`(
    name: String = "Dieter das Bollwerk",
    lebenspunkte: Int = 2000,
    aktionspunkte: Int = 100,
    attackenListe : MutableList<Attacke> = mutableListOf(normalerAngriff, verteidiegung, spott, schuetzendesSchild),
    ruestungsPunkte: Int = 500
) : Held(name, lebenspunkte, aktionspunkte, attackenListe, ruestungsPunkte) {

    fun angreifenKrieger(held: Held, kampf: Rundenkampf) {

        println("Welche Attacke von ${held.name} möchtest du ausführen?")
        for ((index,attacken ) in attackenListe.withIndex()){
            println("""
            ${index + 1} für ${attacken.name}
            """.trimIndent())
        }
        var attackenEingabe: Int = readln().toInt()
        var attacke = attackenListe[attackenEingabe-1]
        kampf.attackeAusfuehren(attacke)
    }

}

var krieger: `Krieger(Tank)` = `Krieger(Tank)`()