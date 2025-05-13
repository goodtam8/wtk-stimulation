interface Player {

open  var currentHP:Int
open var identity:String
open var revealed:Boolean


}
interface Observer{
    fun update(dodged:Boolean)
}
interface Subject{
    fun notifyObservers(dodged:Boolean)
}

class Lord:Player, Subject{
    override var currentHP: Int=0
    override var identity: String="Lord"
    override var revealed: Boolean=false

    val observers= mutableListOf<Observer>()
    override fun notifyObservers(dodged: Boolean) {
        observers.forEach{it.update(dodged)}

    }


    fun shouldHelpLord():Boolean{
        println("I am a lord. I cannot dodge the attack")
        return false
    }
}

class Loyalist:Player{
    override var currentHP: Int=0
    override var identity: String="Loyalist"
    override var revealed: Boolean=false

    fun shouldHelpLord():Boolean{
     println("I am Loyalist. I can dodge the attack")
        return true

    }

}
class Spy:Player ,Observer{
    override var currentHP: Int=0
    override var identity: String="Spy"
    var risklevel:Int=0
    override var revealed=false


    override fun update(dodged:Boolean){
        if(dodged==false){
            risklevel+=1
            shouldHelpLord()
        }
        else{
            if(risklevel>0){
            risklevel-=1
            }
            shouldHelpLord()

        }
    }

    fun shouldHelpLord():Boolean{
        println("Current Risk Level: $risklevel")
        return false

    }

}
class Rebel:Player{
    override var currentHP: Int=0
    override var identity: String="Rebel"
    override var revealed=false
    fun shouldHelpLord():Boolean{
        println("I am a Rebel. I cannot dodge the attack")
        return false

    }

}