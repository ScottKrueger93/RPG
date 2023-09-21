package Helden

import Gegner.Gegner

class `Heiliger (Heal)`(
    name: String = "Apostel Jutzius des Heiligen Ordens",
    lebenspunkte: Int = 1500,
    standardLebenspunkte: Int = 1500,
    aktionspunkte: Int = 100,
    standardAktionspunkte: Int = 100,
    ruestungsPunkte: Int = 0,
    aktion1: Faehigkeit = Faehigkeit("Normaler Angriff", 50, 50, 0, 0,0),
    aktion2: Faehigkeit = Faehigkeit("Verteidigungshaltung", 0, 0, 0, 50,0),
    aktion3: Faehigkeit = Faehigkeit("Heilung durch Licht", 0, 0,200, 0, 50),
    aktion4: Faehigkeit = Faehigkeit("Engelsrettung (Wiederbelebung)", 0, 0, 1000, 0,100),
) : Held(name, lebenspunkte, standardLebenspunkte, aktionspunkte,standardAktionspunkte, ruestungsPunkte, aktion1, aktion2, aktion3, aktion4){

    private var attackenListe: MutableList<Faehigkeit> = mutableListOf(aktion1,aktion2,aktion3,aktion4)

    fun angreifenHeiler(held: Held, gegner: Gegner){

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