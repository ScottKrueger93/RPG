package Gegner

import Helden.*

class Endgegner(
    name: String = "Erzbischof Ankylar des Heiligen Ordens",
    lebenspunkte: Int = 5000,
    aktionspunkte: Int = 100,
    aktion1: Attacke = normalerAngriff,
    aktion2: Attacke = verteidiegung,
    aktion3: Attacke = schattenMiasmaAoE,
    aktion4: Attacke = verseuchung,
    aktion5: Attacke = schattenblitz,
    aktion6: Attacke = knochenschild,
    ruestungsPunkte: Int = 1000
) : Gegner(name, lebenspunkte, aktionspunkte, aktion1, aktion2, aktion3, aktion4, ruestungsPunkte) {

}

var endBoss: Endgegner = Endgegner()