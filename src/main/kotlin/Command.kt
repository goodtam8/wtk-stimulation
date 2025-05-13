typealias Command = () -> Unit

fun AcediaExecute(general: General) {
    println(general.name + " judging the Acedia card.")
    val judgeCard: Card = general.drawJudgementCard()

    if (judgeCard.suit.equals("heart")) {
        println(general.name + " dodge the Acedia card.")
    } else {
        println(general.name + " can't dodge the Acedia card. Skipping one round of Play Phase.")
        general.skip = true
    }
}


fun LightningExecute(general: General) {

    println(general.name + " judging the Lightning card.")
    val judgeCard: Card = general.drawJudgementCard()

    val num = when (judgeCard.number) {
        "A" -> 1
        "J" -> 11
        "Q" -> 12
        "K" -> 13
        else -> Integer.parseInt(judgeCard.number)
    }

    if (judgeCard.suit.equals("spade") && (num in 2..9)) {
        println(general.name + " can't dodge the Lighting card, receive 3 damage")
        general.beingLightningDamage()


    } else {
        println(general.name + " dodge the Lighting card. Pass it to next player.")
        //TO DO, pass to next
        var num: Int = 0
        for (i in 0..Generalmanager.list.size - 1) {
            if (Generalmanager.list.get(i).equals(general))
                num = i
        }
        if (num == Generalmanager.list.size - 1)
            Generalmanager.list.get(0).invoker.addCommand(Lightning(Generalmanager.list.get(0)))
        else
            Generalmanager.list.get(num + 1).invoker.addCommand(Lightning(Generalmanager.list.get(num + 1)))


    }
}

typealias CommandGenerator = (General) -> Command


val Acedia: CommandGenerator = { general: General ->
    {
        AcediaExecute(general)
    }
}



val Lightning: CommandGenerator = { general: General ->
    {
        LightningExecute(general)
    }
}

class Invoker {
    var commandList: MutableList<Command> = mutableListOf()

    fun addCommand(command: Command) {
        commandList.add(command)

    }

    fun executeCommand() {
        for (i in 0..commandList.size - 1) {
            commandList.removeFirst()?.invoke()

        }
    }
}

