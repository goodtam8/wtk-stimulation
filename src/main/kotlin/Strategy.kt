abstract class Strategy(val general: General) {
    open var target: Int = 87
    open var size = 3
    open var lvbu = false// make a varable to see whether the use of general is lv bu
    //inrange is the opponent list
//overide play next card

    open fun playNextCard() {

        var copy: MutableList<Card> = mutableListOf()
        // copy list
        println()
        for (i in 0..general.handCard.size - 1) {
            print("[$i] " + general.handCard.get(i).name + " ,")
            copy.add(general.handCard.get(i))
        }
        println()

        var dist = locktarget()

        for (i in 0..copy.size - 1) {

            //check current hp in PlayPhase
            if (general.currentHP > 0) {

                if (general.handCard.contains(copy.get(i))) {

                    var card = copy.get(i)

                    println()
                    for (i in 0..general.handCard.size - 1) {
                        print("[$i] " + general.handCard.get(i).name + " ,")
                    }
                    println()

                    println("thinking of card[$i] ${card.name}")

                    if (card is BasicCard) {
                        // peach
                        if (card.name.equals("Peach") && general.currentHP < general.maxHP) {
                            general.playPeachCard()
                        }
                        // attack
                        else if (card.name.equals("Attack") && dist != 87) {
                            if (general.attackedState == false) {
                                attack()
                            }
                        }
                    }

                    // use spell
                    else if (card is SpellCard) {
                        if (!(card.name.equals("Negation"))) {

                            if (card.name.equals("Duel") && Generalmanager.list.size > 1) {
                                var randomgen = Generalmanager.list.random()
                                while (general.equals(randomgen)) {
                                    randomgen = Generalmanager.list.random()
                                }
                                (card as Duel).spellExecute(general, randomgen, false)
                            } else
                                card.spellExecute(general)
                        }
                        // play next card
                        else {
                            continue
                        }
                    }
                    // placement
                    else {
                        if (!card.name.equals("Dodge") && !card.name.equals("Attack")) {
                            general.placeCard(card)
                            if (card is EquipmentCard || card is MountCardDec) {
                                println("reset attack range: ")
                                general.setAttackRangeList()
                            }
                        }
                        else
                            continue
                    }

                    // check last card of LuXun
                    if (general is LuXun) {
                        general.Coalition()
                    }
                }
                else
                    println("[$i] ${copy.get(i).name} removed. ")
            }
            else {
                println("${general.name} has no hp, stop play phase")
                return
            }
        }
        return
    }
    open fun extraPlay(){
        println("contiune")
        playNextCard()

    }

    fun lvmeng() {
        if (general is LvMeng && general.attackedState == false) {
            general.selfcontrol = true
        }
    }

    abstract fun attack()
    abstract fun sowdiscord()
    open fun specialskill() {
        if (general is ZhouYu)
            sowdiscord()
        else if (general is Sunshangxiang)
            checkbethroment()
        else if (general is HuanGai)
            general.sacrifie()
        else if (general is GanNing)
            general.ambushment()
        else if (general is DaQiao)
            general.nationalbeauty()
        else if (general is ZhaoYun)
            Braveryattack()


    }

    fun checkbethroment() {

    }

    abstract fun locktarget(): Int
    abstract fun Braveryattack()

}

open class LoyalistStrategy(open val a: General) : Strategy(a) {
    override var target: Int = 87
    private var gen3: MutableList<Int> = listOf(2, 4) as MutableList<Int>
    private var gen4: MutableList<Int> = listOf(2, 4, 5) as MutableList<Int>
    private var gen5: MutableList<Int> = listOf(2, 4, 5, 7) as MutableList<Int>
    override fun specialskill() {
        if (general is ZhouYu)
            sowdiscord()
        else if (general is Sunshangxiang)
            checkbethroment()
        else if (general is HuanGai)
            general.sacrifie()
        else if (general is GanNing)
            general.ambushment()
        else if (general is DaQiao)
            general.nationalbeauty()
        else if (general is ZhaoYun)
            Braveryattack()
        else if (general is LiuBei)
            aggressionattack()

    }

    override fun sowdiscord() {
        if (general is ZhouYu) {
            var counter = 0
            var ran = Generalmanager.list.random()
            while (ran is ZhouYu) {
                ran = Generalmanager.list.random()
                println("${ran.name}")
                counter++
                if (counter >= 5)
                    return

            }
            general.sow(ran)

        }

    }

    fun skypierching() {
        if (general.hasAttackCard() == true && general.handCard.size == 1) {
            var target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }

            (general.equipment as SkyPiercingHalberd).skypiercingattack(general)
            general.attackedState = true


        } else {
            normalattack()
        }
    }

    fun lvbuattack() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }

            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            (general as LvBu).Unrival(Generalmanager.list.get(target))

            general.attackedState = true


        }
    }


    fun greendragonblade() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }

            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            var orignhp = Generalmanager.list.get(target).currentHP
            Generalmanager.list.get(target).beingAttacked(general)
            var currenthp = 0

            if (target >= Generalmanager.list.size)
                currenthp = 0
            else
                currenthp = Generalmanager.list.get(target).currentHP


            if (orignhp == currenthp) {
                println("Green dragon blade activate ")

                var size = Generalmanager.list.size-1
                while (general.hasAttackCard() == true && orignhp == currenthp && size <= Generalmanager.list.size - 1) {
                    Generalmanager.list.get(target).beingAttacked(general)

                    currenthp = Generalmanager.list.get(target).currentHP

                }
            }

            general.attackedState = true
        }


    }

    fun yanyangsword() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }

            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            if (general.gender != Generalmanager.list.get(target).gender) {
                if (Generalmanager.list.get(target).handCard.size > 0) {
                    var random = Generalmanager.list.get(target).handCard.random()
                    Generalmanager.list.get(target).handCard.remove(random)
                } else {
                    general.drawCards()
                }

            }

            Generalmanager.list.get(target).beingAttacked(general)

            general.attackedState = true
        }
    }

    fun Berserkatack() {
        target = locktarget()
        if (target == 87)
            return
        if (general is ZhangFei) {
            println("Beresk activated")
            var counter = general.Beresk()
            var time = 0
            var life = Generalmanager.list.get(target).currentHP
            var sameperson = Generalmanager.list.get(target)

            while (time < counter && life > 0 && target < Generalmanager.list.size) {
                Generalmanager.list.get(target).beingAttacked(general)
                time++
                counter = general.Beresk()

                println("$life")
            }
            general.attackedState = true
        }
    }

    open fun zhugecrossbowattack() {
        target = locktarget()
        if (target == 87)
            return
        var counter = general.zhugecrossbow()
        var time = 0
        var life = Generalmanager.list.get(target).currentHP
        var sameperson = Generalmanager.list.get(target)

        while (time < counter && life > 0 && target < Generalmanager.list.size

        ) {
            time++
            Generalmanager.list.get(target).beingAttacked(general)
            counter = general.zhugecrossbow()

            println("$life")
        }
        println("I end my attack")
        general.attackedState = true


    }


    fun kirinbowattack() {
        println("kirin")
        if (a.hasAttackCard() == true) {
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }


            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            var orignsize=Generalmanager.list.size
            Generalmanager.list.get(target).beingAttacked(general)
            var currentsize=Generalmanager.list.size
            general.attackedState = true

            if(orignsize==currentsize){
            if (Generalmanager.list.get(target).mountDec != null)
                Generalmanager.list.get(target).removePlacementCard("mountDec")
            else if (Generalmanager.list.get(target).mountInc != null)
                Generalmanager.list.get(target).removePlacementCard("mountInc")

        }
        }
    }

    fun rockcleaveattack() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }


            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            var orignhp = Generalmanager.list.get(target).currentHP
            var currenthp = 0
            Generalmanager.list.get(target)
                .beingAttacked(general)//bug reason:the target is the last index of list and it has one hp
            if (target >= Generalmanager.list.size)
                currenthp = 0
            else
                currenthp =
                    Generalmanager.list.get(target).currentHP//after attack it check the hp but it has already remove so error
            if (orignhp == currenthp && general.handCard.size >= 2) {
                var ran1 = general.handCard.random()
                general.handCard.remove(ran1)
                var ran2 = general.handCard.random()
                general.handCard.remove(ran2)
                println("Equipment rockcleave axe activate. Hp deuct")
                Generalmanager.list.get(target).currentHP--
                if (Generalmanager.list.get(target).currentHP <= 0)
                    Generalmanager.list.get(target).askForPeach()
            }

            general.attackedState = true
        }


    }


    override fun Braveryattack() {
        if (general is ZhaoYun) {//有閃牌先出殺閃
            if (general.hasDodgeCardCheckAll() == true) {
                target = locktarget()
                if (target == 87) {
                    println("no general in the range")
                    general.attackedState = true
                    return
                }


                Generalmanager.list.get(target).zhaoyunattackppl(general)
                println("[Bravery] Zhao Yun activate the bravery skill. Using dodge as attack")
                general.attackedState = true
            } else if (general.hasAttackCard() == true) {
                target = locktarget()
                if (target == 87) {
                    println("no general in the range")
                    general.attackedState = true
                    return
                }


                Generalmanager.list.get(target).beingAttacked(general)
                println("[Bravery] Zhao Yun activate the bravery skill. Using dodge as attack")
                general.attackedState = true

            }


        }

    }

    override fun locktarget(): Int {// need to use the inrange
        for (i in 0..general.inRange.size - 1) {
            if(general.inRange.get(i) is YuanShu)
            {
                if(general.inRange.get(i).handCard.size>general.inRange.get(i).currentHP){
                    println("Taunt activate! Yuan Shu become the target of all the general")
                    for (j in 0..Generalmanager.list.size - 1) {
                        if (Generalmanager.list.get(j).equals(general.inRange.get(i)))
                            return j
                    }

                }
            }
        }
        

        for (i in 0..general.inRange.size - 1) {
            if (general.inRange.get(i).strategy is RebelStrategy) {
                for (j in 0..Generalmanager.list.size - 1) {
                    if (Generalmanager.list.get(j).equals(general.inRange.get(i)))
                        return j
                }

            }

        }
        println("there is not general in the range ")
        return 87
    }

    fun locktarget(general: General): Int {
        for (i in 0..general.inRange.size - 1) {
            if(general.inRange.get(i) is YuanShu)
            {
                if(general.inRange.get(i).handCard.size>general.inRange.get(i).currentHP){
                    println("Taunt activate! Yuan Shu become the target of all the general")
                    for (j in 0..Generalmanager.list.size - 1) {
                        if (Generalmanager.list.get(j).equals(general.inRange.get(i)))
                            return j
                    }

                }
            }
        }

        for (i in 0..general.inRange.size - 1) {
            if (general.inRange.get(i).strategy is RebelStrategy) {

                for (j in 0..Generalmanager.list.size)
                    if (general.inRange.get(i).equals(Generalmanager.list.get(j)) && !Generalmanager.list.get(j)
                            .equals(general)
                    )
                        return j

            }

        }
        println("there is not general in the range ")
        return 87

    }

    fun normalattack() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }

            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            Generalmanager.list.get(target).beingAttacked(general)

            general.attackedState = true
        }
    }


    fun aggressionattack() {
        if (general is LiuBei)
            if (general.hasAttackCard() == false) {
                for (i in 0..general.shuchain.size - 1) {
                    var shu = general.shuchain.get(i).handleRequest()
                    if (shu == true) {
                        var tar = locktarget()
                        if (tar == 87) {
                            println("no general in the range")
                            general.attackedState = true
                            return
                        }
                        Generalmanager.list.get(tar).beingAttacked(general)
                        general.attackedState = true
                        return

                    }
                }
            }

    }

    fun bluesteelblade() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87)
                return
            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
            }
            Generalmanager.list.get(target).BlueSteelBladeattack(general)

            general.attackedState = true
        }

    }

    fun spear() {
        if (general.handCard.size >= 2) {

            var target = locktarget()
            if (target == 87) {
                println("There is not general in the range")
                return
            }
            Generalmanager.list.get(target).spearattack(general)
            //put it into spear equipment

        } else {
            normalattack()
        }
    }


    fun equipmentattack() {
        if (general.equipment is SerpentSpear)
            spear()
        else if (general.equipment is KirinBow)
            kirinbowattack()
        else if (general.equipment is RockCleavingAxe)
            rockcleaveattack()
        else if (general.equipment is ZhugeCrossbow)
            zhugecrossbowattack()
        else if (general.equipment is RockCleavingAxe)
            rockcleaveattack()
        else if (general.equipment is YinYangSwords)
            yanyangsword()
        else if (general.equipment is SkyPiercingHalberd)
            skypierching()
        else if (general.equipment is BlueSteelBlade)
            bluesteelblade()
        else if (general.equipment is GreenDragonBlade)
            greendragonblade()


    }

    override fun attack() {
        //assume 有spear 唔出殺用spear技
        if (general.equipment != null) {
            equipmentattack()
        } else if (general is ZhangFei) {
            Berserkatack()
        } else if (general is ZhaoYun) {
            Braveryattack()
        } else if (general is LvBu)
            lvbuattack()
        else {
            normalattack()
        }
    }

}


class HuanGaiLoyalistStrategy(override val a: HuanGai) : LoyalistStrategy(a) {
    override var target: Int = 87
    var state: State = HuanGaiHealthyState(this)
    override fun playNextCard() {
        if (a.currentHP >2) {
           state = HuanGaiHealthyState(this)
        }
        else{
            state = HuanGaiUnHealthyState(this)
        }

        state.playNextCard()
        super.playNextCard()

    }

}

class HuanGaiRebelStrategy(override val a: HuanGai) : RebelStrategy(a) {
    override var target: Int = 87
    var state: State = HuanGaiHealthyState(this)


    override fun playNextCard() {
        if (a.currentHP >2) {
            state = HuanGaiHealthyState(this)
        }
        else{
            state = HuanGaiUnHealthyState(this)
        }

        state.playNextCard()
        super.playNextCard()

    }


}

class DiaoChanLoyalistStrategy(override val a: DiaoChan) : LoyalistStrategy(a) {
    //    override fun playNextCard() {
//        if (a.handCard.size >= 2) {
//            alienation()
//        } else {
//            super.playNextCard()
//        }
//
//    }
    override fun specialskill() {
        alienation()

    }

    fun alienation() {

        var counter = 0
        var firstmale: General? = null
        var secondmale: General? = null
        for (i in 0..Generalmanager.list.size - 1) {
            if (Generalmanager.list.get(i).gender == false && counter == 0) {
                counter++
                firstmale = Generalmanager.list.get(i)

            }
            if (Generalmanager.list.get(i).gender == false && counter == 1) {
                counter++
                secondmale = Generalmanager.list.get(i)

            }

        }
        if (firstmale != null && secondmale != null) {
            println("[Alienation]Diao Chan activate her alienation skills for force a duel")
            var duelcard = Duel("Duel", "99", "99")
            if (firstmale != secondmale) {
                var discard = a.handCard.random()
                a.handCard.remove(discard)
                if (firstmale is LvBu || secondmale is LvBu)
                    duelcard.diaochanduel(firstmale, secondmale, true)
                else
                    duelcard.diaochanduel(firstmale, secondmale, false)

            }
        }


    }
}

class DiaoChanRebelStrategy(override val a: DiaoChan) : RebelStrategy(a) {

    override fun specialskill() {
        alienation()

    }

    fun alienation() {
        if (general.handCard.size == 0)
            return

        var counter = 0
        var firstmale: General? = null
        var secondmale: General? = null
        for (i in 0..Generalmanager.list.size - 1) {
            if (Generalmanager.list.get(i).gender == false && counter == 0) {
                counter++
                firstmale = Generalmanager.list.get(i)

            } else if (Generalmanager.list.get(i).gender == false && counter == 1) {
                counter++
                secondmale = Generalmanager.list.get(i)

            }

        }
        if (firstmale != null && secondmale != null) {
            println("[Alienation]Diao Chan activate her alienation skills for force a duel")
            var duelcard = Duel("Duel", "99", "99")
            if (firstmale != secondmale) {
                var discard = a.handCard.random()
                a.handCard.remove(discard)
                if (firstmale is LvBu || secondmale is LvBu)
                    duelcard.diaochanduel(firstmale, secondmale, true)
                else
                    duelcard.diaochanduel(firstmale, secondmale, false)

            }
        }


    }
}


open class RebelStrategy(open val a: General) : Strategy(a) {
    override var target: Int = 87


    override fun sowdiscord() {
        if (general is ZhouYu) {
            var ran = Generalmanager.list.random()
            while (ran is ZhouYu) {
                ran = Generalmanager.list.random()
                println("${ran.name}")

            }

            general.sow(ran)


        }


    }

    fun Berserkatack() {
        target = locktarget()
        if (target == 87)
            return
        if (general is ZhangFei) {
            println("Beresk activated")
            var counter = general.Beresk()
            var time = 0
            var life = Generalmanager.list.get(target).currentHP

            while (time < counter && life > 0) {
                time++
                Generalmanager.list.get(target).beingAttacked(general)
                life = Generalmanager.list.get(target).currentHP
                counter = general.Beresk()

                println("$life")
            }


        }

    }

    open fun zhugecrossbowattack() {
        target = locktarget()
        var counter = general.zhugecrossbow()
        var time = 0
        var life = Generalmanager.list.get(target).currentHP
        var sameperson = Generalmanager.list.get(target)

        while (time < counter && life > 0 && target < Generalmanager.list.size) {//bug
            time++
            Generalmanager.list.get(target).beingAttacked(general)
            counter = general.zhugecrossbow()

            println("$life")
        }


    }

    fun kirinbowattack() {
        if (a.hasAttackCard() == true) {
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }


            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            var orignsize=Generalmanager.list.size
            Generalmanager.list.get(target).beingAttacked(general)
            var currentsize=Generalmanager.list.size
            general.attackedState = true

            if(orignsize==currentsize){
                if (Generalmanager.list.get(target).mountDec != null)
                    Generalmanager.list.get(target).removePlacementCard("mountDec")
                else if (Generalmanager.list.get(target).mountInc != null)
                    Generalmanager.list.get(target).removePlacementCard("mountInc")

            }



        }

    }

    fun skypierching() {
        if (general.hasAttackCard() == true && general.handCard.size == 1) {
            var target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }//skypierching have bug
            //because it 出多左張attack牌

            (general.equipment as SkyPiercingHalberd).skypiercingattack(general)
            general.attackedState = true


        } else {
            normalattack()
        }


    }

    fun yanyangsword() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }


            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            if (general.gender != Generalmanager.list.get(target).gender) {
                if (Generalmanager.list.get(target).handCard.size > 0) {
                    var random = Generalmanager.list.get(target).handCard.random()
                    Generalmanager.list.get(target).handCard.remove(random)
                } else {
                    general.drawCards()
                }

            }

            Generalmanager.list.get(target).beingAttacked(general)

            general.attackedState = true
        }
    }


    fun rockcleaveattack() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }


            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            var orignhp = Generalmanager.list.get(target).currentHP
            Generalmanager.list.get(target).beingAttacked(general)
            var currenthp = Generalmanager.list.get(target).currentHP
            if (orignhp == currenthp && general.handCard.size >= 2) {
                var ran1 = general.handCard.random()
                general.handCard.remove(ran1)
                var ran2 = general.handCard.random()
                general.handCard.remove(ran2)
                println("Equipment rockcleave axe activate. Hp deuct")
                Generalmanager.list.get(target).currentHP--
                if (Generalmanager.list.get(target).currentHP <= 0)
                    Generalmanager.list.get(target).askForPeach()

            }

            general.attackedState = true
        }


    }


    override fun Braveryattack() {
        if (general is ZhaoYun) {//有閃牌先出殺閃
            if (general.hasDodgeCardCheckAll() == true) {
                target = locktarget()
                if (target == 87) {
                    println("no general in the range")
                    general.attackedState = true
                    return
                }


                Generalmanager.list.get(target).zhaoyunattackppl(general)
                println("[Bravery] Zhao Yun activate the bravery skill. Using dodge as attack")
                general.attackedState = true
            } else if (general.hasAttackCard() == true) {
                target = locktarget()
                if (target == 87) {
                    println("no general in the range")
                    general.attackedState = true
                    return
                }


                Generalmanager.list.get(target).beingAttacked(general)
                println("[Bravery] Zhao Yun activate the bravery skill. Using dodge as attack")
                general.attackedState = true

            }


        }

    }

    override fun locktarget(): Int {
        for (i in 0..general.inRange.size - 1) {
            if(general.inRange.get(i) is YuanShu)
            {
                if(general.inRange.get(i).handCard.size>general.inRange.get(i).currentHP){
                    println("Taunt activate! Yuan Shu become the target of all the general")
                    for (j in 0..Generalmanager.list.size - 1) {
                        if (Generalmanager.list.get(j).equals(general.inRange.get(i)))
                            return j
                    }

                }
            }
        }

        for (i in 0..general.inRange.size - 1) {
            if (general.inRange.get(i).player is Lord || general.inRange.get(i).player is Loyalist) {

                for (j in 0..Generalmanager.list.size - 1)
                    if (general.inRange.get(i).equals(Generalmanager.list.get(j)))
                        return j

            }

        }
        println("there is not general in the range ")
        return 87
    }

    fun locktarget(general: General): Int {
        for (i in 0..general.inRange.size - 1) {
            if(general.inRange.get(i) is YuanShu)
            {
                if(general.inRange.get(i).handCard.size>general.inRange.get(i).currentHP){
                    println("Taunt activate! Yuan Shu become the target of all the general")
                    for (j in 0..Generalmanager.list.size - 1) {
                        if (Generalmanager.list.get(j).equals(general.inRange.get(i)))
                            return j
                    }

                }
            }
        }

        for (i in 0..general.inRange.size - 1) {
            if (general.inRange.get(i).strategy is LoyalistStrategy) {

                for (j in 0..Generalmanager.list.size - 1)
                    if (general.inRange.get(i).equals(Generalmanager.list.get(j)) && !Generalmanager.list.get(j)
                            .equals(general)
                    )
                        return j

            }

        }
        println("there is not general in the range ")
        return 87

    }

    fun normalattack() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }

            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++

                if (counter >= 3) {
                    println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                    return
                }
                if (target == 87) {
                    println("no general in the range")
                    general.attackedState = true
                    return
                }

                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
            }
            Generalmanager.list.get(target).beingAttacked(general)

            general.attackedState = true
        }

    }

    fun spear() {
        if (general.handCard.size >= 2) {

            var target = locktarget()
            Generalmanager.list.get(target).spearattack(general)
            //put it into spear equipment

        } else {
            normalattack()
        }
    }


    fun bluesteelblade() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }

            Generalmanager.list.get(target).BlueSteelBladeattack(general)

            general.attackedState = true
        }

    }

    fun lvbuattack() {
        if (a.hasAttackCard() == true) {
            //play card fun
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }

            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            (general as LvBu).Unrival(Generalmanager.list.get(target))

            general.attackedState = true


        }
    }

    fun equipmentattack() {
        if (general.equipment is SerpentSpear)
            spear()
        else if (general.equipment is KirinBow)
            kirinbowattack()
        else if (general.equipment is RockCleavingAxe)
            rockcleaveattack()
        else if (general.equipment is ZhugeCrossbow)
            zhugecrossbowattack()
        else if (general.equipment is RockCleavingAxe)
            rockcleaveattack()
        else if (general.equipment is YinYangSwords)
            yanyangsword()
        else if (general.equipment is SkyPiercingHalberd)
            skypierching()
        else if (general.equipment is BlueSteelBlade)
            bluesteelblade()
        else if (general.equipment is GreenDragonBlade)
            greendragonblade()


    }

    override fun attack() {
        //assume 有spear 唔出殺用spear技
        if (general.equipment != null) {
            equipmentattack()
        } else if (general is ZhangFei) {
            Berserkatack()
        } else if (general is ZhaoYun) {
            Braveryattack()
        } else if (general is LvBu)
            lvbuattack()
        else {
            normalattack()
        }
    }


    fun greendragonblade() {
        if (a.hasAttackCard() == true) {
            target = locktarget()
            if (target == 87) {
                println("no general in the range")
                general.attackedState = true
                return
            }


            //play card fun
            var counter = 0
            while (Generalmanager.list.get(target) is ZhugeLiang && Generalmanager.list.get(target).handCard.size == 0 && counter < 3) {
                target = locktarget()
                counter++
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
            }
            if (counter >= 3) {
                println("[Empty City strategy activate] Zhuge Liang cannot pick as an target")
                return
            }
            var orignhp = Generalmanager.list.get(target).currentHP
            Generalmanager.list.get(target).beingAttacked(general)
            var currenthp = Generalmanager.list.get(target).currentHP
            if (orignhp == currenthp) {
                println("Green dragon blade activate ")
                var size = Generalmanager.list.size-1
                while (general.hasAttackCard() == true && orignhp == currenthp && size <= Generalmanager.list.size - 1) {
                    Generalmanager.list.get(target).beingAttacked(general)
                    currenthp = Generalmanager.list.get(target).currentHP

                }
            }

            general.attackedState = true
        }


    }

}


class LiuBeiStrategy(override val a: LiuBei) : LoyalistStrategy(a) {

    var state: State = HealthyState(this)
    override fun playNextCard() {
        //check hp first
        if (a.currentHP > 2) {
            state = HealthyState(this)
        } else if (a.currentHP in 1..2) {
            state = UnhealthyState(this)
        }
        state.playNextCard()
        super.playNextCard()
    }

    override fun extraPlay() {
        println("contiune")
        super.playNextCard()
    }



}


class SunQuanStratrgy(override val a: SunQuan) : LoyalistStrategy(a) {

    override fun playNextCard() {
        if(a.handCard.size >=2) {
            a.rebalancing()
        }
        super.playNextCard()
    }
}

class MaChaoLoyalStrategy(val Ma: MaChao) : LoyalistStrategy(Ma) {
    override fun attack() {
        if (Ma.cavalryExecute()) {
            if (Ma.hasAttackCard()) {
                Ma.cavalry(Ma.playAttackCard(), Generalmanager.list.get(locktarget()))
            }

        } else {
            println("No cavalry execute")
            super.attack()
        }
    }

    override fun zhugecrossbowattack() {
        if (Ma.hasAttackCard()) {
            Generalmanager.list.get(locktarget()).beingAttacked(Ma)
            Ma.attackedState = false
        }
    }
}

class MaChaoRebelStrategy(val Ma: MaChao) : RebelStrategy(Ma) {
    override fun attack() {
        if (Ma.cavalryExecute()) {
            if (Ma.hasAttackCard()) {
                Ma.cavalry(Ma.playAttackCard(), Generalmanager.list.get(locktarget()))
            }
        } else {
            println("No cavalry execute")
            super.attack()
        }
    }

    override fun zhugecrossbowattack() {
        if (Ma.hasAttackCard()) {
            Generalmanager.list.get(locktarget()).beingAttacked(Ma)
            Ma.attackedState = false
        }
    }
}