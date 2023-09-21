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
                held.lebenspunkte = held.standardLebenspunkte
                println("Die Lebenspunkte von ${held.name} werden um $heilungItem geheilt. Seine Lebenspunte betragen jetzt: ${held.lebenspunkte}")
            } else {
                held.lebenspunkte += heilungItem
                println("Die Lebenspunkte von ${held.name} werden um $heilungItem geheilt. Seine Lebenspunte betragen jetzt: ${held.lebenspunkte}")
            }
        }
        if (ruestungPlusItem > 0) {
            println("Die Rüstung von ${held.name} wird um $ruestungPlusItem erneut. Seine Rüstung beträgt jetzt: ${held.ruestungsPunkte}")
            held.ruestungsPunkte += ruestungPlusItem
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
            println("Die Aktionspunkte von ${held.name} auf $aktionsPunkteBoostItem erneut.")
            held.aktionspunkte = aktionsPunkteBoostItem
        }
    }
}