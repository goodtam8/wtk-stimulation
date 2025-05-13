abstract class ShuGeneral (name:String, player:Player):General(name, player){

    var nextHandler:ShuGeneral ?= null

    fun handleRequest():Boolean{
        if(hasAttackCard()==true){//check whether the general have the attack card
            //if it has attack card it will return true
            //otherwise it will return false
            playAttackCard()
            Generalmanager.list.get(0).handCard.add(BasicCard("Attack","spade","8"))
            println("$name have attack card")
            return true
        }
        println("$name don't have attack card")
        return false

    }


}