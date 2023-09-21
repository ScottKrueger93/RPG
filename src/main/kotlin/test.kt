import Beutel.*
import Gegner.*
import Helden.*
import Kampf.Rundenkampf

var schaden = 0

fun main() {

    //Die Unterklassen von der Mutterklasse "Held"
    var krieger: `Krieger(Tank)` = `Krieger(Tank)`()
    var magier: `Magier(DD)` = `Magier(DD)`()
    var heiler: `Heiliger (Heal)`= `Heiliger (Heal)`()

    //Die Unterklassen von der Mutterklasse "Gegner"
    var endBoss: Endgegner = Endgegner()
    var helfer: Helfer = Helfer()

    // Klasse "Beutel"
    var beutel: Beutel = Beutel()

    // Items der Klasse "Items"
    var energiePille: Item = Item("Energiepille", 2,50,50,50)
    var schadensPille: Item = Item("Schadenspille",2,0,0,0)
    var verteidigungspille: Item = Item("Verteidigungspille", 0,0,1000,0)
    var apTrank: Item = Item("AP-Trank", 0,0,0,100)
    var heilTrank: Item = Item("Heiltrank", 0,1000,0,0)

    // Liste für die Rundenkämpfe
    var kaempferListe = mutableListOf(krieger, magier, heiler)
    var gegnerListe: MutableList<Gegner> = mutableListOf(endBoss)

//Standard-Angriffe die alle "Helden" und "Gegner" haben:
    var normalerAngriff: Attacke= Attacke("Normaler Angriff", 50, 0,0,0)
    var verteidiegung: Attacke = Attacke("Verteidigungshaltung", 0, 0, 50,0)


//Krieger-Angriffe
    var spott: Attacke = Attacke("Spott", 0, 0, 0,50)
    var schuetzendesSchild: Attacke = Attacke("Schützendes Schild",0, 0,200, 50)

//Magier-Angriffe
    var elementarSchwert: Attacke = Attacke("Elementar-Schwert", 100, 0, 0,80)
    var elementarSchuss: Attacke = Attacke("Elementar-Schuss", 200, 0, 0,50)

//Heiler-Angriffe
    var heilung: Attacke = Attacke("Heilung durch Licht", 0, 200,0,50)
    var wiederbelebung: Attacke = Attacke("Engelsrettung (Wiederbelebung)",0,1000,0,100)

//Endgegner-Angriffe
    var schattenMiasmaAoE: Attacke = Attacke("Schatten-Miasma",150,0,0,50)
    var verseuchung: Attacke = Attacke("Verseuchung",10, 0,0,50)
    var schattenblitz: Attacke = Attacke("Schattenblitz",350,0,0,50)
    var knochenschild: Attacke = Attacke("Knochenschild", 0,0,250,30)

//Helfer-Angriffe
    var leichenwurf: Attacke = Attacke("Leichenwurf",100,0,0,20)
    var mauerAusLeichen: Attacke = Attacke("Mauer aus Leichen",0,0,100,50)


    var kampf = Rundenkampf()
    kampf.rundenKaempfe()

}