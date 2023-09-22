package Beutel

import Helden.Held

open class Item(
    var name: String,
    var schadensBoostItem: Int,
    var heilungItem: Int,
    var ruestungPlusItem: Int,
    var aktionsPunkteBoostItem: Int,
) {

    open fun itemNutzen(held: Held) {
        if (heilungItem > 0) {
            if (heilungItem + held.lebenspunkte > held.standardLebenspunkte) {
                println("Die Lebenspunkte von ${held.name} werden um ${held.standardLebenspunkte - held.lebenspunkte} geheilt. " +
                        "Die Überheilung beträgt ${held.lebenspunkte + heilungItem - held.standardLebenspunkte}." +
                        " Seine Lebenspunkte betragen jetzt: ${held.standardLebenspunkte}")
                held.lebenspunkte = held.standardLebenspunkte
            } else {
                held.lebenspunkte += heilungItem
                println("Die Lebenspunkte von ${held.name} werden um $heilungItem geheilt. Seine Lebenspunte betragen jetzt: ${held.lebenspunkte}")
            }
        }
        if (ruestungPlusItem > 0) {
            held.ruestungsPunkte += ruestungPlusItem
            println("Der Rüstung von ${held.name} werden $ruestungPlusItem Rüstungspunkte hinzugefügt. Seine Rüstung beträgt jetzt: ${held.ruestungsPunkte}")
        }
        if (schadensBoostItem > 0) {
            println("Der Schaden von ${held.name} wird für den nächsten Angriff verdoppelt.")
            held.aktion1.schaden *= schadensBoostItem
            if (held.aktion3.schaden > 0) {
                held.aktion3.schaden *= schadensBoostItem
            }
            if (held.aktion4.schaden > 0) {
                held.aktion4.schaden *= schadensBoostItem
            }
        }
        if (aktionsPunkteBoostItem > 0) {
            if (held.aktionspunkte + aktionsPunkteBoostItem > held.standardAktionspunkte){
                println("Die Aktionspunkte von ${held.name} wurden auf $aktionsPunkteBoostItem erneuert.")
                held.aktionspunkte = aktionsPunkteBoostItem
            } else {
                held.aktionspunkte += aktionsPunkteBoostItem
                println("Die Aktionspunkte von ${held.name} betragen jetzt $aktionsPunkteBoostItem.")
            }
        }
    }
}