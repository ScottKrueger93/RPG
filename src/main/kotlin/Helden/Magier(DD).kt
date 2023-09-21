package Helden

import Gegner.Endgegner
import Gegner.Gegner

class `Magier(DD)`(
    name: String = "Ezekil Houdini",
    lebenspunkte: Int = 1000,
    standardLebenspunkte: Int = 1000,
    aktionspunkte: Int = 100,
    standardAktionspunkte: Int = 100,
    ruestungsPunkte: Int = 200,
    aktion1: Faehigkeit = Faehigkeit("Normaler Angriff", 50, 50, 0, 0,0),
    aktion2: Faehigkeit = Faehigkeit("Verteidigungshaltung", 0, 0, 0,50,0),
    aktion3: Faehigkeit = Faehigkeit("Elementar-Schwert", 100, 100, 0, 0,50),
    aktion4: Faehigkeit = Faehigkeit("Elementar-Schuss", 200, 200, 0, 0,80),
) : Held(name, lebenspunkte, standardLebenspunkte, aktionspunkte,standardAktionspunkte, ruestungsPunkte, aktion1, aktion2, aktion3, aktion4){

    private var attackenListe: MutableList<Faehigkeit> = mutableListOf(aktion1,aktion2,aktion3,aktion4)

    fun angreifenMagier(held: Held, gegner: Gegner){

        println("Welche Attacke von ${held.name} möchtest du ausführen?")
        for ((index,attacken ) in attackenListe.withIndex()){
            println("""
            ${index + 1} für ${attacken.name}
            """.trimIndent())
        }
        var attackenEingabe: Int = readln().toInt()
        var attacke = attackenListe[attackenEingabe-1]
        gegner.lebenspunkte - attacke.schaden
    }

}

