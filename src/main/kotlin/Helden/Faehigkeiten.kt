package Helden

class Faehigkeit(var name: String,
              var schaden: Int,
              var standardSchaden: Int,
              var heilung: Int,
              var ruestungPlus: Int,
              var aktionsPunkteKosten:Int){
}


var normaler :Faehigkeit = Faehigkeit("Normaler Angriff", 50, 0, 0,0,0)