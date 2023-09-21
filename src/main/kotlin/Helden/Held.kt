package Helden

open class Held(
    var name: String,
    var lebenspunkte: Int,
    var aktionspunkte: Int = 100,
    var ruestungsPunkte: Int = 0,
    var aktion1: Faehigkeit,
    var aktion2: Faehigkeit,
    var aktion3: Faehigkeit,
    var aktion4: Faehigkeit
) {

    fun angreifen(held: Held) {
        if (held == magier) {
            magier.angreifen(held)
        } else if (held == magier) {
            heiler.angreifen(held)
        } else if (held == krieger)
            krieger.angreifenKrieger(held)
    }
}