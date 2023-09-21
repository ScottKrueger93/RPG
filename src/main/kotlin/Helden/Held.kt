package Helden

open class Held(
    var name: String,
    var lebenspunkte: Int,
    var standardLebenspunkte: Int,
    var aktionspunkte: Int = 100,
    var standardAktionspunkte: Int = 100,
    var ruestungsPunkte: Int = 0,
    var aktion1: Faehigkeit,
    var aktion2: Faehigkeit,
    var aktion3: Faehigkeit,
    var aktion4: Faehigkeit
) {

}