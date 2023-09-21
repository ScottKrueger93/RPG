package Gegner

import Helden.Attacke
import Helden.Faehigkeit
import Helden.normalerAngriff
import Helden.verteidiegung

open class Gegner(
    var name: String,
    var lebenspunkte: Int,
    var aktionspunkte: Int = 100,
    var ruestungsPunkte: Int = 0,
    val aktion1: Faehigkeit,
    val aktion2: Faehigkeit,
    var aktion3: Faehigkeit,
    var aktion4: Faehigkeit) {

}