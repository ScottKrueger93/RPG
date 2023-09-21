package Gegner

import Helden.*

class Helfer(
    name: String = "Mönströsität aus Leichen der Heiligen Ordnung",
    lebenspunkte: Int = 750,
    aktionspunkte: Int = 100,
    ruestungsPunkte: Int = 250,
    aktion1: Faehigkeit = Faehigkeit("Normaler Angriff", 50, 50, 0, 0,0),
    aktion2: Faehigkeit = Faehigkeit("Verteidigungshaltung", 0, 0, 0, 50,0),
    aktion3: Faehigkeit = Faehigkeit("Leichenwurf", 100, 100, 0, 0,50),
    aktion4: Faehigkeit = Faehigkeit("Mauer aus Leichen", 0, 0, 0, 100,50),
) : Gegner(name, lebenspunkte, aktionspunkte,ruestungsPunkte, aktion1, aktion2, aktion3, aktion4) {

}