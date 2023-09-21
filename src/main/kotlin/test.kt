import Beutel.*
import Gegner.*
import Helden.*
import Kampf.Rundenkampf

fun main() {

    //Die Unterklassen von der Mutterklasse "Held"
    var krieger: `Krieger(Tank)` = `Krieger(Tank)`()
    var magier: `Magier(DD)` = `Magier(DD)`()
    var heiler: `Heiliger (Heal)` = `Heiliger (Heal)`()

    //Die Unterklassen von der Mutterklasse "Gegner"
    var endBoss: Endgegner = Endgegner()
    var helfer: Helfer = Helfer()

    // Klasse "Beutel"
    var beutel: Beutel = Beutel()

    // Items der Klasse "Items"
    var energiePille: Item = Item("Energiepille", 2, 50, 50, 50)
    var schadensPille: Item = Item("Schadenspille", 2, 0, 0, 0)
    var verteidigungspille: Item = Item("Verteidigungspille", 0, 0, 1000, 0)
    var apTrank: Item = Item("AP-Trank", 0, 0, 0, 100)
    var heilTrank: Item = Item("Heiltrank", 0, 1000, 0, 0)

    // Liste für die Rundenkämpfe
    var kaempferListe = mutableListOf(krieger, magier, heiler)
    var gegnerListe: MutableList<Gegner> = mutableListOf(endBoss)
    var hatBereitsGekaempft: MutableList<Held> = mutableListOf()


    var kampf = Rundenkampf()
    kampf.rundenKaempfe()

}