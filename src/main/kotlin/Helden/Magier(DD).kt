package Helden

class `Magier(DD)`(
    name: String = "Ezekil Houdini",
    lebenspunkte: Int = 1000,
    aktionspunkte: Int = 100,
    attackenListe: MutableList<Attacke> = mutableListOf(normalerAngriff, verteidiegung,elementarSchwert,elementarSchuss),
    ruestungsPunkte: Int = 200
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

var magier: `Magier(DD)` = `Magier(DD)`()
