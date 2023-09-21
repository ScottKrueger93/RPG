package Helden

import Kampf.Rundenkampf
import Kampf.kampf

class `Krieger(Tank)`(
    name: String = "Dieter das Bollwerk",
    lebenspunkte: Int = 2000,
    aktionspunkte: Int = 100,
    ruestungsPunkte: Int = 500,
    aktion1: Faehigkeit = Faehigkeit("Normaler Angriff", 50, 0, 0, 0),
    aktion2: Faehigkeit = Faehigkeit("Verteidigungshaltung", 0, 0, 50, 0),
    aktion3: Faehigkeit = Faehigkeit("Spott", 0, 0, 0, 50),
    aktion4: Faehigkeit = Faehigkeit("Schützendes Schild", 0, 0, 200, 50),
) : Held(name, lebenspunkte, aktionspunkte, ruestungsPunkte, aktion1, aktion2, aktion3, aktion4) {

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