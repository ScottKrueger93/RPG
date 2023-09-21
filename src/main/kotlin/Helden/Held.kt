package Helden

open class Held(
    var name: String,
    var lebenspunkte: Int,
    var aktionspunkte: Int = 100,
    var attackenListe : MutableList<Attacke> = mutableListOf(),
    var ruestungsPunkte: Int = 0,
) {

    open fun angreifen(held: Held) {
        if (held == magier) {
            magier.angreifen(held)
        } else if (held == magier) {
            heiler.angreifen(held)
        } else if (held == krieger)
            krieger.angreifenKrieger(held)
    }
}

var kaempferListe = mutableListOf(krieger, magier, heiler)