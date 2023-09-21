import Gegner.Gegner
import Helden.Held
import Helden.`Magier(DD)`

fun kampfBeginnt() {

    println("Der finale Kampf beginnt!")
}

fun zielAuswahl(){
    println("Wen möchtest du als Ziel auswählen?")
    for ((index: Int, gegner: Gegner) in gegnerListe.withIndex()){
        println("""
                ${index + 1} für ${gegner.name}
                """.trimIndent())
    }
    var zielEingabe: Int = readln().toInt()
    var ziel = gegnerListe[zielEingabe-1]


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
