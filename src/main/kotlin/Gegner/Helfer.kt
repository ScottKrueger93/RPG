package Gegner

import Helden.*

class Helfer(
    name: String = "Mönströsität aus Leichen der Heiligen Ordnung",
    lebenspunkte: Int = 750,
    aktionspunkte: Int = 100,
    aktion1: Attacke = normalerAngriff,
    aktion2: Attacke = verteidiegung,
    aktion3: Attacke = leichenwurf,
    aktion4: Attacke = mauerAusLeichen,
    ruestungsPunkte: Int = 250
) : Gegner(name, lebenspunkte, aktionspunkte, aktion1, aktion2, aktion3, aktion4) {

}