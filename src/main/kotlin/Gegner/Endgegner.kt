package Gegner

import Helden.*

class Endgegner(
    name: String = "Erzbischof Ankylar des Heiligen Ordens",
    lebenspunkte: Int = 5000,
    aktionspunkte: Int = 100,
    ruestungsPunkte: Int = 1000,
    aktion1: Faehigkeit = Faehigkeit("Normaler Angriff", 50, 50, 0, 0,0),
    aktion2: Faehigkeit = Faehigkeit("Verteidigungshaltung", 0, 0, 0, 50,0),
    aktion3: Faehigkeit = Faehigkeit("Schatten-Miasma", 150, 150, 0, 0,50),
    aktion4: Faehigkeit = Faehigkeit("Verseuchung", 10, 10, 0, 0,50),
    aktion5: Faehigkeit = Faehigkeit("Schattenblitz", 350, 350, 0, 0,30),
    aktion6: Faehigkeit = Faehigkeit("Knochenschild", 0, 0, 0, 250,30)
) : Gegner(name, lebenspunkte, aktionspunkte,ruestungsPunkte, aktion1, aktion2, aktion3, aktion4) {

}
