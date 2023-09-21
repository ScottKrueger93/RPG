package Kampf

import Beutel.beutel
import Gegner.Gegner
import Gegner.endBoss
import Gegner.gegnerListe
import Gegner.helfer
import Helden.*

class Rundenkampf {

    fun rundenKaempfe(){

        while (krieger.lebenspunkte != 0 && magier.lebenspunkte != 0 && heiler.lebenspunkte != 0 || endBoss.lebenspunkte != 0 && helfer.lebenspunkte != 0) {
            var istAmZug =  kaempferListe.random()
            kaempferListe.remove(istAmZug)
            hatBereitsGekaempft.add(istAmZug)
            println("${istAmZug.name} ist an der Reihe.")
            println("Was möchtest du tun?")
            println("""--- Menü ---
                |1 - Item benutzen
                |2 - Fähigkeit ausführen
            """.trimMargin())
            when(readln()){
                "1" -> beutel.itemAuswaehlen().itemNutzen(istAmZug)
                "2" -> istAmZug.angreifen(istAmZug)
            }
        }
    }

    fun zielAuswahl(){
        println("Wen möchtest du als Ziel auswählen?")
        for ((index: Int, gegner:Gegner) in gegnerListe.withIndex()){
            println("""
                ${index + 1} für ${gegner.name}
                """.trimIndent())
        }
        var zielEingabe: Int = readln().toInt()
        var ziel = gegnerListe[zielEingabe-1]


    }

    fun attackeAusfuehren(attacke: Attacke){

    }
}

var hatBereitsGekaempft: MutableList<Held> = mutableListOf()