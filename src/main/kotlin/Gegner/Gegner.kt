package Gegner

import Helden.Attacke
import Helden.normalerAngriff
import Helden.verteidiegung

open class Gegner(
    var name: String,
    var lebenspunkte: Int,
    var aktionspunkte: Int = 100,
    val aktion1: Attacke = normalerAngriff,
    val aktion2: Attacke = verteidiegung,
    var aktion3: Attacke,
    var aktion4: Attacke,
    var ruestungsPunkte: Int = 0
) {

}