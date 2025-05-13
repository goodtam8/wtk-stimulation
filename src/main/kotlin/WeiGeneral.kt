import kotlin.random.Random

abstract class WeiGeneral(name:String, player:Player ) :General(name,player){

    fun handleRequest():Boolean{
        if(hasDodgeCardCheckAll()==true){
            println("$name has dodge card")
            playDodgeCard()
            return true

        }
        return false






    }


}

