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
        held.attackenListe[0].schaden += schadensBoostItem
        if (held.attackenListe[2].schaden > 0) {
            held.attackenListe[2].schaden += schadensBoostItem
        }
        if (held.attackenListe[3].schaden > 0) {
            held.attackenListe[3].schaden += schadensBoostItem
        }
        held.lebenspunkte += heilungItem
        held.aktionspunkte += aktionsPunkteBoostItem
    }
}