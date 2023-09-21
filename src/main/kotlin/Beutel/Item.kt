package Beutel

import Helden.Held

open class Item(var name: String,
                var schadensBoostItem: Int,
                var heilungItem: Int,
                var ruestungPlusItem: Int,
                var aktionsPunkteBoostItem:Int){

    open fun itemNutzen(held: Held) {
        held.lebenspunkte += heilungItem
        held.ruestungsPunkte += ruestungPlusItem
        held.aktion1.schaden += schadensBoostItem
        if (held.aktion3.schaden > 0) {
            held.aktion3.schaden += schadensBoostItem
        }
        if (held.aktion4.schaden > 0) {
            held.aktion4.schaden += schadensBoostItem
        }
        held.lebenspunkte += heilungItem
        held.aktionspunkte += aktionsPunkteBoostItem
    }
}