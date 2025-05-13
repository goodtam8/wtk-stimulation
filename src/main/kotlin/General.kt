import sun.util.resources.cldr.ar.CalendarData_ar_DZ
import java.util.*
import kotlin.random.Random

abstract class General(open var name: String, var player: Player) : Player by player {
    lateinit var strategy: Strategy
    open var gender: Boolean = false//男係false, 女係true

    var invoker = Invoker()


    //inherit 想要用ge classes
    open var maxHP: Int = 4
    var numOfCards: Int = 4
    open var ac = false
    open var skip = false
    open var lvbu = false

    // check attack time( should attack once only)
    open var attackedState = false
    open var attackDamage = 1

    // attack range( can be override by equipment )
    open var attackRange = 1
    open var inRange: MutableList<General> = mutableListOf()
    open var horseRiding = 0


    // placement cards
    var equipment: EquipmentCard? = null
    var armor: ArmorCard? = null
    var armexe = false
    var mountInc: MountCardInc? = null//for 減加距離的馬
    var mountDec: MountCardDec? = null//for 減距離的馬


    // hand card list + opening
    var handCard: MutableList<Card> = mutableListOf()
    fun gameOpenCard() {
        for (i in 0..3) {
            handCard.add(Generalmanager.cardList.removeFirst())
        }
    }

    init {
        if (name.equals("null"))
            println("no target in list.")
        else
            println("General $name created.")
    }

    // check in armor + hand
    open fun hasDodgeCardCheckAll(): Boolean {
        if (armor != null) {
            println("$name excute armor :")
            if (armor!!.armorExecute(this)) {
                println("armor excute successfully!")
                armexe = true
                return true
            } else {
                println("armor excute fail!")
                return hasDodgeCard()
            }
        } else
            return hasDodgeCard()
    }


    // check dodgecard in hand
    open fun hasDodgeCard(): Boolean {
        var dodge = false
        if (handCard.size == 0) {
            println("$name has no hand card.")
            return false
        }

        for (i in 0..handCard.size - 1) {

            if (handCard.get(i).name.equals("Dodge")) {
                dodge = true
                println("$name has a dodge card. ")
                return dodge
            } else {
                dodge = false
                println("$name does not have a dodge card. ")
                return dodge
            }
        }
        return dodge
    }

    open fun has2DodgeCard(): Boolean {
        var counter = 0
        for (i in 0..handCard.size - 1) {

            if (handCard.get(i).name.equals("Dodge")) {
                counter++

            }

        }
        if (counter >= 2) {
            return true
        }
        return false
    }


    open fun has2AttackCard(): Boolean {
        var counter = 0
        for (i in 0..handCard.size - 1) {

            if (handCard.get(i).name.equals("Attack")) {
                counter++

            }

        }
        if (counter >= 2) {
            return true
        }
        return false
    }

    open fun hasAttackCard(): Boolean {

        for (i in 0..handCard.size - 1) {

            if (handCard.get(i).name.equals("Attack")) {

                return true
            }
        }
        println("$name does not have attack card")
        return false

    }

    open fun playredsuit(): Card {
        lateinit var card: Card
        armexe = false
        for (i in 0..handCard.size - 1) {
            //println(i.toString() + general.handCard.get(i).name)
            if (handCard.get(i).suit.equals("heart") || handCard.get(i).suit.equals("diamond")) {
                card = handCard.removeAt(i)
                break
            }
        }
        return card
    }

    open fun hasPeachCard(): Boolean {
        for (i in 0..handCard.size - 1) {

            if (handCard.get(i).name.equals("Peach")) {
                println("$name has Peach card")
                return true
            }
        }
        //checking
        println("$name does not have Peach card")
        return false
    }

    fun hasDuelCard(): Boolean {
        for (i in 0..handCard.size - 1) {

            if (handCard.get(i).name.equals("Duel")) {

                return true
            }
        }
        //checking
        println("$name does not have Duel card")
        return false
    }

    fun playDuelCard(opponent: General) {
        for (i in 0..handCard.size - 1) {

            if (handCard.get(i).name.equals("Duel")) {
                if (this is LvBu){
                    (handCard.get(i) as Duel).spellExecute(this, opponent, true)
                break}
                else {
                    (handCard.get(i) as Duel).spellExecute(this, opponent, false)
break
                }
            }

        }
    }

    fun testPeach(): Boolean {
        if (numOfCards > 0) {
            for (i in 0..numOfCards) {
                var prob: Int = Random.nextInt(0, 100)
                if (prob < 10) {

                    return true
                }

            }
        }
        println("${this.name} doesn't has the peach card")
        return false
    }

    open fun askForPeach() {
        var noOneHasPeach = false

        for (h in 0..(1 - this.currentHP) - 1) {
            if (this.currentHP <= 0) {
                noOneHasPeach = false
                println("$h time for ask peach")

                if (hasPeachCard()) {
                    playPeachCard()
                    continue
                }

                println("$name ask other for peach.")
                // check all players
                for (i in 0..Generalmanager.list.size - 1) {

                    // not equal to 0hp general
                    if (Generalmanager.list.get(i) != this) {

                            // check general(i) hasPeach (for other) cards
                            if (Generalmanager.list.get(i).playSavePeachCheck(this)) {
                                noOneHasPeach = false
                                Generalmanager.list.get(i).playSavePeach()
                                this.currentHP += 1
                                println("$name receive a peach, HP + 1, currnet HP is ${this.currentHP}. ")
                                break
                            } else {
                                noOneHasPeach = true
                                continue
                            }

                    }
                }
                if (noOneHasPeach) {
                    println("no one play peach for $name.")
                    println("General removed ($name). hp($currentHP). ")
                    Generalmanager.removeGeneral(this)
                    Generalmanager.win()
                    return
                }
            }
        }
    }

    // peach for other, giving out(check)
    open fun playSavePeachCheck(general: General): Boolean {
        if ((player is Loyalist && general.player is Lord)
            || player is Lord && general.player is Loyalist
            || player is Loyalist && general.player is Loyalist
        ) {
            if(hasPeachCard())
                return true
        } else if (player is Rebel && general.player is Rebel) {
            if(hasPeachCard())
                return true
        }
        println("$name does not play peach")
        return false
    }

    // play peach to other
    open fun playSavePeach() {
        for (i in 0..handCard.size - 1) {
            if (handCard.get(i).name.equals("Peach")) {
                handCard.removeAt(i)
                println("$name gives out a peach")
                break

            }
        }
    }

    // self use peach
    open fun playPeachCard() {
        for (i in 0..handCard.size - 1) {
            if (handCard.get(i).name.equals("Peach")) {
                handCard.removeAt(i)
                println("$name use peach to heal 1 HP. ")
                currentHP += 1
                println("$name current hp is: $currentHP. ")
                break
            }
        }
    }

    //check health,
    open fun beingDamage(num: Int, card: Card, general: General) {
        currentHP -= num
        println("$name - $num HP. Now $currentHP hp")
        if (currentHP <= 0) {
            askForPeach()
            Generalmanager.win()

        }
    }

    open fun beingDamage(num: Int, card: MutableList<Card>, general: General) {
        currentHP -= num
        println("$name - $num HP. Now $currentHP hp")
        if (currentHP <= 0) {
            askForPeach()
            Generalmanager.win()

        }
    }


    open fun zhaoyunattackppl(general: General) {
        println("$name being attacked")

        val card = general.playDodgeCard()//攻擊人出殺
        var dodge = this.hasDodgeCardCheckAll()

        if (dodge == true) {
            playDodgeCard()
        } else
            beingDamage(general.attackDamage, card, general)
    }

    //test simple attack
    open fun beingAttacked(general: General) {
        println("$name being attacked")

        val card = general.playAttackCard()//攻擊人出殺
        var dodge = this.hasDodgeCardCheckAll()

        if (dodge == true) {
            playDodgeCard()
        } else
            beingDamage(general.attackDamage, card, general)
    }

    open fun beingAttacked(card: Card, general: General) {
        println("$name being attacked")

        var dodge = this.hasDodgeCardCheckAll()

        if (dodge == true) {
            playDodgeCard()
        } else
            beingDamage(1, card, general)
    }


    fun spearattack(general: General) {
        var attacked: MutableList<Card> = mutableListOf()

        var card1 = general.handCard.random()
        general.handCard.remove(card1)
        var card2 = general.handCard.random()
        general.handCard.remove(card2)
        attacked.add(card1)
        attacked.add(card2)
        if(this.hasDodgeCardCheckAll()==true){
            playDodgeCard()
            return
        }
        else{

        println("Spear attack! ${name} get deducted one hp")
        beingDamage(1, attacked, this)

    }}

    open fun BlueSteelBladeattack(general: General) {
        println("$name being attacked")
        val card = general.playAttackCard()
        if (this.hasDodgeCard()) {
            playDodgeCard()
        } else
            beingDamage(general.attackDamage, card, general)
    }


    fun zhugecrossbow(): Int {
        var count = 0
        for (i in 0..handCard.size - 1) {
            if (handCard.get(i).name.equals("Attack"))
                count++
        }
        return count
    }


    open fun playAttackCard(): Card {
        lateinit var card: Card
        for (i in 0..handCard.size - 1) {
            if (handCard.get(i).name.equals("Attack")) {
                card = handCard.removeAt(i)
                println("$name play an attack card.")
                break
            }
        }
        return card
    }


    open fun playDodgeCard(): Card {
        if (armexe) {
            // armor skill, default
            println("$name dodge it by using Eight Trigrams Formation!")
            // reset armexe
            armexe = false
            return BasicCard("Dodge", "heart", "2")
        }

        lateinit var card: Card
        for (i in 0..handCard.size - 1) {
            if (handCard.get(i).name.equals("Dodge")) {
                card = handCard.removeAt(i)
                println("$name play a dodge card.")
                return card
            }
        }
        return card
    }

    open fun play2DodgeCard(): MutableList<Card>? {
        var counter = 2
        var carddeck: MutableList<Card>? = null
        var firstdodge: Card? = null
        var seconddodge: Card? = null
        //for loop to while loop
        for (i in 0..handCard.size - 1) {
            //println(i.toString() + general.handCard.get(i).name)
            if (handCard.get(i).name.equals("Dodge") && counter == 2) {
                carddeck?.add(handCard.get(i))
                firstdodge = handCard.get(i)
                counter--
                //have bugs

            } else if (handCard.get(i).name.equals("Dodge") && counter == 1) {
                carddeck?.add(handCard.get(i))
                seconddodge = handCard.get(i)
                counter--
            }
        }
        handCard.remove(firstdodge)
        handCard.remove(seconddodge)
        return carddeck
    }

    open fun play2AttackCard() {
        var counter = 2
        var firstattack: Card? = null
        var secondattack: Card? = null
        for (i in 0..handCard.size - 1) {
            //println(i.toString() + general.handCard.get(i).name)
            if (handCard.get(i).name.equals("Attack") && counter == 2) {
                firstattack = handCard.get(i)
                counter--

            } else if (handCard.get(i).name.equals("Attack") && counter == 1) {
                secondattack = handCard.get(i)
                counter--

            }
            handCard.remove(firstattack)
            handCard.remove(secondattack)
        }
    }

    open fun setAttackRangeList() {
        var num: Int = -2
        val size = Generalmanager.list.size
        // find location
        for (i in 0..size - 1) {
            if (Generalmanager.list.get(i).name.equals(this.name)) {
                num = i
                break
            }
        }
        // reset attack range
        setAttackRange()
        // range
        var range = this.attackRange

        if (range >= Generalmanager.list.size) {
            for (i in 0..Generalmanager.list.size - 1) {
                if (!(Generalmanager.list.get(i).equals(Generalmanager.list.get(num))))
                    inRange.add(Generalmanager.list.get(i))
            }
        }
        else {
            // left
            var leftCount = 1
            var leftNum = 1
            while (leftCount <= range) {

                if (!(Generalmanager.list.get((num - leftNum).mod(size)).equals(Generalmanager.list.get(num)))) {
                    if (Generalmanager.list.get((num - leftNum).mod(size)).mountInc != null) {
                        leftCount++
                    }
                    if (leftCount <= range) {
                        if (!(inRange.contains(Generalmanager.list.get((num - leftNum).mod(size))))) {
                            inRange.add(Generalmanager.list.get((num - leftNum).mod(size)))
                            leftCount++
                            leftNum++
                        } else {
                            leftCount++
                            leftNum++
                        }
                    }
                }
            }

            //right
            var rightCount = 1
            var rightNum = 1
            while (rightCount <= range) {
                if (!Generalmanager.list.get((num + rightNum).mod(size)).equals(Generalmanager.list.get(num))) {
                    if (Generalmanager.list.get((num + rightNum).mod(size)).mountInc != null) {
                        rightCount++
                    }
                    if (rightCount <= range) {
                        if (!(inRange.contains(Generalmanager.list.get((num + rightNum).mod(size))))) {
                            inRange.add(Generalmanager.list.get((num + rightNum).mod(size)))
                            rightCount++
                            rightNum++
                        } else {
                            rightCount++
                            rightNum++
                        }
                    }

                }
            }
        }
        println()
        println("In Attack Range: ")
        for (i in 0..inRange.size - 1) {
            println(inRange.get(i).name)
        }
        println()

    }

    open fun setAttackRange() {
        // reset the range
        // horseRiding for MaChao is 1, for others is 0
        if (equipment != null) {
            attackRange = (equipment!!.attakRange + horseRiding)
        } // reset to 1
        else
            attackRange = (1 + horseRiding)
        // +1 if has mount -1
        if (mountDec != null) {
            attackRange += 1 + horseRiding
        }
    }

    open fun Preparationphase() {


        attackedState = false
        ac = false
        skip = false
        attackDamage = 1

        // set Attack list
        // reset inRange
        inRange = mutableListOf()
        setAttackRangeList()
        println("In Prepartion phase $name current hp is $currentHP ")
        if (currentHP <= 0)
            askForPeach()


    }


    open fun JudgementPhase() {
        invoker.executeCommand()
    }

    // n delete parameter (card)
    open fun drawJudgementCard(): Card {//拎張牌出黎
        // check cardList null
        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()

        val store = Generalmanager.cardList.removeFirst()
        println("The judgement card is: " + store.suit + ", " + store.number + ", " + store.name)

        // Sima Yi skill
        for (i in 0..Generalmanager.list.size - 1) {
            // avoid sima yi repeat when he call super
            if (Generalmanager.list.get(i) is SimaYi && this !is SimaYi) {
                // more than 1 card, execute it
                if (Generalmanager.list.get(i).handCard.size > 1)
                    return (Generalmanager.list.get(i) as SimaYi).necromancyOther()
            }
        }
        return store
    }

    open fun DrawPhase() {
        draw2Cards()
    }

    open fun draw2Cards() {
        // check card null
        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()

        handCard.add(Generalmanager.cardList.removeFirst())
        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()
        handCard.add(Generalmanager.cardList.removeFirst())
        println("$name draws 2 cards and now has ${handCard.size} card(s).")
    }

    open fun drawCards() {
        // check card null
        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()

        handCard.add(Generalmanager.cardList.removeFirst())
        println("$name draws 1 card and now has ${handCard.size} card(s).")
    }


    open fun PlayPhase() {
        if (handCard.size <= 0) {
            println("$name has no hand card. ")
            return
        }
        println("${name} is in play phase now ")
        strategy.specialskill()
        strategy.playNextCard()
        strategy.extraPlay()
        println("${name} is out play phase now ")

    }


    //being remove the placement
    open fun removePlacementCard(card: String): Card {
        println("$name's $card had been remove.")
        lateinit var cardStore: Card
        if (card.equals("mountDec")) {
            cardStore = mountDec!!
            mountDec = null
        } else if (card.equals("mountInc")) {
            cardStore = mountInc!!
            mountInc = null
        } else if (card.equals("equipment")) {
            cardStore = equipment!!
            equipment = null
        } else if (card.equals("armor")) {
            cardStore = armor!!
            armor = null
        }
        println("$name's ${cardStore.name}(placement) removed.")
        return cardStore
    }

    // other remove your hands
    fun removeHand(card: String): Card {

        if (!card.equals("base")) {
            println("error")
        }
        val store = handCard.removeFirst()

        if (this is LuXun) {
            this.Coalition()
        }

        println("$name's ${store.name} removed(hand), now has ${handCard.size} card(s)")
        return store
    }

    fun placeCard(card: Card) {

        println("play out a card, ${card.name}")
        handCard.remove(card)
        // check null
        // if not null, need remove() then place the card

        if (card is EquipmentCard) {
            if (equipment == null) {
                println("${card.name} is being place in $name set.")
                equipment = card
            } else {
                removePlacementCard("equipment")
                println("${card.name} is being place in $name set.")
                equipment = card
            }
        } else if (card is MountCardDec) {
            if (mountDec == null) {
                println("${card.name} is being place in $name set.")
                mountDec = card
            } else {
                removePlacementCard("mountDec")
                println("${card.name} is being place in $name set.")
                mountDec = card
            }
        } else if (card is MountCardInc) {
            if (mountInc == null) {
                println("${card.name} is being place in $name set.")
                mountInc = card
            } else {
                removePlacementCard("mountInc")
                println("${card.name} is being place in $name set.")
                mountInc = card
            }
        } else if (card is ArmorCard) {
            if (armor == null) {
                println("${card.name} is being place in $name set.")
                armor = card
            } else {
                removePlacementCard("armor")
                println("${card.name} is being place in $name set.")
                armor = card
            }
        }
    }

    // target others
    open fun removeTargetPlacementCard(general: General): Card {
        var placeList: MutableList<String> = mutableListOf()
        print("${general.name} has ")
        if (general.handCard.size > 0) {
            print("base, ")
            placeList.add("base")
        }
        if (general.mountDec != null) {
            print("mountDec, ")
            placeList.add("mountDec")
        }
        if (general.mountInc != null) {
            print("mountInc, ")
            placeList.add("mountInc")
        }
        if (general.armor != null) {
            print("armor, ")
            placeList.add("armor")
        }
        if (general.equipment != null) {
            print("equipment")
            placeList.add("equipment")
        }
        println()
        val store = placeList.random()
        println("$name choose ${general.name}'s $store card.")
        if (store.equals("base"))
            return general.removeHand(store)
        else
            return general.removePlacementCard(store)

    }


    open fun DiscardPhase() {

        var store: Int = 0
        if (handCard.size > currentHP) {
            store = handCard.size - currentHP
        }
        println("$name has ${handCard.size} card(s), current HP is $currentHP")

        if (currentHP > 0) {
            for (i in 0..store - 1) {
                handCard.removeAt(0)
            }
        }

        println("$name discards ${store} card(s), now has ${handCard.size} card(s). ")
    }
    // more than current hp and discard the cards

    open fun FinalPhase() {
        println()
    }


    fun templatemethod() {

        Preparationphase()
        JudgementPhase()
        if (currentHP > 0) {
            DrawPhase()
            if (!skip) {
                PlayPhase()
            }
            if (currentHP > 0) {
                Generalmanager.win()
                DiscardPhase()
                FinalPhase()
            } else {
                println("${name} has no hp, cannot go into discard phase")
                Generalmanager.win()
            }
        } else {
            println("${name} has no hp, cannot go into draw and other phase.")
            Generalmanager.win()

        }

    }

    fun beingLightningDamage() {
        currentHP -= 3
        println("$name - 3, current hp: $currentHP")

        if (currentHP <= 0) {
            askForPeach()
            Generalmanager.win()
        }
    }
}



