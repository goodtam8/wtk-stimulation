abstract class WuGeneral(name: String, player: Player) : General(name, player) {

    fun handleRequest(): Boolean {
        if (this.hasPeachCard() == true) {//change it to has peach card
            println("${name} has peach card")
            this.playPeachCard()
            checkcoalition()

            return true
        } else {
            return false
        }

    }
    fun checkcoalition(){
        if(this is LuXun)
            this.Coalition()
    }


}