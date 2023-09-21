import Helden.Held

fun kampfBeginnt() {

    println("Der finale Kampf beginnt!")
}

fun angreifen(held: Held) {
    if (held == magier) {
        magier.angreifen(held)
    } else if (held == magier) {
        heiler.angreifen(held)
    } else if (held == krieger)
        krieger.angreifenKrieger(held)
}
}


fun main(){


}
