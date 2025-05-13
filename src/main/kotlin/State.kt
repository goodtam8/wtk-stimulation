interface State {
    open fun playNextCard(

    )

}

class UnhealthyState(val strategy: LiuBeiStrategy) : State {

    override fun playNextCard() {
        println("Liu Bei is in unhealthy state. ")
        println("His hand card number: " +strategy.general.handCard.size)
        if(strategy.general.handCard.size >=2) {
            (strategy.general as LiuBei).benevolence()
        }

    }
}

class HealthyState(val strategy: LiuBeiStrategy) : State {


    override fun playNextCard() {
        println("Liu Bei is in healthy state. ")

    }


}

class HuanGaiHealthyState(var strategy: Strategy) : State {

    override fun playNextCard() {
        println("Huan Gai is healthy")
        //(strategy.general as HuanGai).sacrifie()
    }
}

class HuanGaiUnHealthyState(var strategy: Strategy) : State {
    override fun playNextCard() {
        println("Huan Gai is unhealthy")
    }
}


