import Gegner.Gegner
import Helden.Held
import Helden.`Magier(DD)`

fun kampfBeginnt() {

    println("Der finale Kampf beginnt!")
}

fun faehigleitAuswaehlen(held: Held) {
    if (held == `Magier(DD)`()) {
        angreifen(held)
    } else if (held == magier) {
        heiler.angreifen(held)
    } else if (held == krieger)
        krieger.angreifenKrieger(held)
}

fun attackeAusfuehren(attacke: Attacke){

}

fun main(){


}
