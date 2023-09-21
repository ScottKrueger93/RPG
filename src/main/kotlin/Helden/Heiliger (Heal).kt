package Helden

class `Heiliger (Heal)`(
    name: String = "Apostel Jutzius des Heiligen Ordens",
    lebenspunkte: Int = 1500,
    aktionspunkte: Int = 100,
    attackenListe: MutableList<Attacke> = mutableListOf(normalerAngriff, verteidiegung, heilung, wiederbelebung),
    ruestungsPunkte: Int = 0
) : Held(name, lebenspunkte, aktionspunkte, attackenListe, ruestungsPunkte) {

    override fun angreifen(held: Held): Attacke{

        println("Welche Attacke von ${held.name} möchtest du ausführen?")
        for ((index,attacken ) in attackenListe.withIndex()){
            println("""
            ${index + 1} für ${attacken.name}
            """.trimIndent())
        }
        var attackenEingabe: Int = readln().toInt()
        var attacke = attackenListe[attackenEingabe-1]
        return attacke
    }

}
