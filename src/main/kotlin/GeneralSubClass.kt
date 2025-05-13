import java.util.*
import kotlin.math.max
import kotlin.random.Random

//sun
class LiuBei(name: String, var abc: Player) : ShuGeneral(name, abc) {
    override var maxHP: Int = 5
    override var gender = false
    var shuchain = LinkedList<ShuGeneral>()

    fun benevolence() {

        for(i in 0 .. (maxHP - currentHP)-1){
            if(hasPeachCard())
                playPeachCard()
        }
        println("$name has ${this.handCard.size} cards.")
        //check whether the condition of benevolence have fulfilled
        if (handCard.size >= 2 && currentHP <=2) {
            // find loyalist
            var num = -1
            for (i in 0..Generalmanager.list.size - 1) {
                if(!Generalmanager.list.get(i).player.equals(this)) {
                    if (Generalmanager.list.get(i).player is Loyalist) {
                        num = i
                        break
                    } else {
                        //random pick a loyalist general to give his card to him
                        //num = Random.nextInt(0, Generalmanager.list.size - 1)
                    }
                }
            }
            // there is no loyalist in the range. Benevlevence fail
            if(num ==0 || num == -1){
                println("benevolence picks no one.")
                return
            }
            Generalmanager.list.get(num).handCard.add(this.handCard.removeFirst())
            Generalmanager.list.get(num).handCard.add(this.handCard.removeFirst())

            this.currentHP += 1
            println("[Benevolence] Liu Bei gives away two cards and recovers 1 HP, now his HP is ${this.currentHP}.")

        }


    }
}

class ZhenJi(name: String, var abc: Player) : WeiGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = true

    override fun JudgementPhase() {

        do {
            println("$name execute [Godess of Luo River]:")
            var card = drawJudgementCard()

            if (card.suit.equals("spade") || card.suit.equals("club")) {
                println("The color is black, add to hand! Continue!")
                this.handCard.add(card)
            } else {
                println("The color is red! Stop the ability!")
                break
            }

        } while ((card.suit.equals("spade") ||
                    card.suit.equals("club"))
        )
        super.JudgementPhase()
    }

    override fun hasDodgeCard(): Boolean {
        if (super.hasDodgeCard() == false) {
            return hasBlackSuitCard()
        } else {
            return true
        }
    }

    fun hasBlackSuitCard(): Boolean {
        println("[Empress Dowager] $name activate empress dowager skills")
        for (i in 0..handCard.size - 1) {

            if (handCard.get(i).suit.equals("spade") || handCard.get(i).suit.equals("club")) {
                println("has black card. ")
                return true
            }
        }
        println("does not has black card.")
        return false
    }

    override fun playDodgeCard(): Card {
        if (armexe){
            println("$name dodge it by using Eight Trigrams Formation!")
            armexe = false
            return  BasicCard("Dodge", "heart", "2")
        }

        lateinit var card: Card
        if (super.hasDodgeCard()) {
            card = super.playDodgeCard()
        } else {
            println("[Empress Dowager] $name play a black colored suit card as a dodge.")
            for (i in 0..handCard.size-1) {

                if (handCard.get(i).suit.equals("spade") || handCard.get(i).suit.equals("club")) {
                    card = handCard.removeAt(i)
                    return card
                }
            }
        }
        return card
    }


}

class XuChu(name: String, var abc: Player) : WeiGeneral(name, abc) {
    override var maxHP: Int = 4
    override var gender = false

    override fun DrawPhase() {
        if(handCard.size >2) {
            println("$name execute [Bared Bodied] to draw one card only and increase 1 more attack or duel damage!")
            println("He now has ${handCard.size} card(s).")
            if (Generalmanager.cardList.isEmpty())
                Generalmanager.createAllCard()
            handCard.add(Generalmanager.cardList.removeFirst())
            attackDamage = 2
        }
        // draw more cards to use
        else{
            draw2Cards()
        }

    }

}

class XiahouDun(name: String, var abc: Player) : WeiGeneral(name, abc) {
    override var maxHP: Int = 4
    override var gender = false

    override fun beingDamage(num: Int, card: Card, general: General) {
        super.beingDamage(num, card, general)
        if(currentHP <=0){
            return
        }
        println("$name execute [Stauchness] to ${general.name}. ")
        var card = this.drawJudgementCard()
        if (card.suit.equals("heart")) {
            println("nothing happen")
        } else {
            if (general.handCard.size >= 2) {
                println("${general.name} discard two cards.")
                println(general.handCard.removeFirst().name)
                println(general.handCard.removeFirst().name)
                if(general is LuXun){
                    general.Coalition()}
            } else{
                // give a null card, not in use
                general.beingDamage(1,BasicCard("None", "none","none"), this)

            }
        }
    }

}

class SimaYi(name: String, var abc: Player) : WeiGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = false

    override fun beingDamage(num: Int, card: Card, general: General) {
        super.beingDamage(num, card, general)
        if(currentHP <=0){
            return
        }
        println("$name exeute [Retailation]!")
        if (general.handCard.size > 0) {
            this.handCard.add(general.handCard.removeFirst())
            println("$name receive a card from ${general.name}(${general.name} cards num: ${general.handCard.size})")
            if(general is LuXun){
                general.Coalition()}
        } else
            println("${general.name} does not have hand card.")
    }

    //self Necromancy
    override fun drawJudgementCard(): Card {
        val superCard = super.drawJudgementCard()
        if (superCard.suit.equals("heart")) {
            return superCard
        }
        else {
            if (handCard.size > 0) {
                println("$name exeute [Necromancy]!")
                // self use, never execute acedia and lighting
                for (i in 0..handCard.size-1) {
                    if (handCard.get(i).suit.equals("heart")) {
                        println("The new judgement card is ${handCard.get(i).suit} and ${handCard.get(i).number}")
                        return handCard.get(i)
                    }
                }
                //if no red
                val card = handCard.removeFirst()
                println("The new judgement card is ${card.suit} and ${card.number}")
                return card
            } else {
                return superCard
            }
        }
    }

    //random
    fun necromancyOther():Card{
        val card = handCard.removeFirst()
        println("$name exeute [Necromancy]!")
        println("The new judgement card is ${card.suit} and ${card.number}")
        return card
    }


}

class HuanGai(name: String, var abc: Player) : WuGeneral(name, abc) {
    override var maxHP: Int = 4
    override var gender = false

    fun sacrifie() {
        if (currentHP >2) {
            println("$name: [Sacrifie] skill activate deduct hp to draw 2 cards")
            draw2Cards()
            currentHP -= 1
            if (currentHP <= 0) {
                askForPeach()

            }
            println("$name current hp is $currentHP.")
        }
    }

}

class Sunshangxiang(name: String, var abc: Player) : WuGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = true

    override fun removePlacementCard(card: String): Card {
        val store = super.removePlacementCard(card)
        println("$name execute [Daredevil] to draw two cards!")
        draw2Cards()
        return store
    }

    override fun PlayPhase() {
        if (currentHP < maxHP && handCard.size >= 2) {
            for (i in 0..Generalmanager.list.size - 1) {
                if (!(Generalmanager.list.get(i).gender) &&
                    Generalmanager.list.get(i).currentHP < Generalmanager.list.get(i).maxHP
                ) {
                    betrothment(Generalmanager.list.get(i))
                    break
                }

            }
        }
        super.PlayPhase()
    }

     fun betrothment(target: General) {
        println("$name execute [Betrothment] with ${target.name}!")
        this.handCard.removeFirst()
        this.handCard.removeFirst()
        this.currentHP += 1
        target.currentHP += 1
        println("$name discard two cards, $name and ${target.name} regain 1 HP!")
        println("$name now has ${handCard.size} cards. ")
    }

}

//sun
class LvMeng(name: String, var abc: Player) : WuGeneral(name, abc) {
    override var maxHP: Int = 4
    override var gender = true
    var selfcontrol = false

    override fun DiscardPhase() {
        if (selfcontrol == true) {
            selfcontrol = false
            println("[Self control] Lv Meng activate his self-control skill. Skip discard phase")
        } else {
            super.DiscardPhase()
        }

    }



}

//sun
class CaoCao(name: String, var abc: Player) : WeiGeneral(name, abc) {
    override var maxHP: Int = 5
    var weichain = LinkedList<WeiGeneral>()
    var beingkilled: WeiGeneral? = null
    override var gender = false


    fun entourage(): Boolean {

        for (i in 0..weichain.size - 1) {
            if (weichain.get(i).handleRequest() == true) {
                beingkilled = weichain.get(i)
                weichain.get(i).numOfCards -= 1
                println(" ${weichain.get(i).name} helps Cao Cao dodged an attack by spending a dodge card.")
                return true

            }
        }
        return false
    }

    override fun beingAttacked(general: General) {
        val card = general.playAttackCard()
        println("[Entourage] Cao Cao activates Lord Skill Entourage.")
        if (entourage() == true) {


        } else {
            println("No one can dodge the attack hp-1")
            // test
            beingDamage(1, card, general)

        }
    }

    override fun beingDamage(num: Int, card: Card, general: General) {
        super.beingDamage(num, card, general)
        if(currentHP <=0){
            return
        }
        // damage not comes from XiaHouDun
       if(!card.name.equals("None")) {
           println("[Treachery] CaoCao activate treachery skill.")
           handCard.add(card)
           println("${card.suit}")
       }

    }

    override fun beingDamage(num: Int, card: MutableList<Card>, general: General) {
        currentHP -= num
        println("[Treachery] CaoCao activate treachery skill.")
        for (i in 0..card.size-1)
            handCard.add(card.get(i))

        if (currentHP <= 0) {
            while (currentHP <= 0) {
                askForPeach()
            }
        }
        if (currentHP <= 0) {
            Generalmanager.list.remove(this)
        } else {
        }
        for(i in 0..card.size-1){
        if(!card.get(i).name.equals("None")) {
            println("[Treachery] CaoCao activate treachery skill.")
            handCard.add(card.get(i))
            println("${card.get(i).suit}")
        }}
    }


}

class SunQuan(name: String, var abc: Player) : WuGeneral(name, abc) {
    override var maxHP: Int = 5
    var wuchain = LinkedList<WuGeneral>()
    override var gender = false


    open fun rebalancing() {
        println("[Rebalancing] Sun Quan activate rebalancing skill.")
        println("Sun Quan now has ${handCard.size} card(s).")
        var num = Random.nextInt(0, handCard.size - 1)


        for (i in 0..num) {
            handCard.removeFirst()
            if (Generalmanager.cardList.isEmpty())
                Generalmanager.createAllCard()
            handCard.add(Generalmanager.cardList.removeFirst())
        }
        println("Sun Quan replaced $num cards.")
    }

    override fun askForPeach() {
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
                        // check general(i) hasPeach cards
                            if (Generalmanager.list.get(i).playSavePeachCheck(this)) {
                                if(!(Generalmanager.list.get(i) is WuGeneral)) {
                                    noOneHasPeach = false
                                    Generalmanager.list.get(i).playSavePeach()
                                    this.currentHP += 1
                                    println("$name receive a peach, HP + 1, currnet HP is ${this.currentHP}. ")
                                    break
                                }
                                else{
                                    noOneHasPeach = false
                                    Generalmanager.list.get(i).playSavePeach()
                                    this.currentHP += 2
                                    println("[Rescue] $name receive a peach from Wu kingdom general, HP + 2, currnet HP is ${this.currentHP}. ")
                                    break
                                }
                            }
                            else {
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

}

//sun
class ZhouYu(name: String, var abc: Player) : WuGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = false


    override fun DrawPhase() {
        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()

        handCard.add(Generalmanager.cardList.removeFirst())
        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()

        handCard.add(Generalmanager.cardList.removeFirst())
        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()

        handCard.add(Generalmanager.cardList.removeFirst())
        println("[Heroism]$name draws 3 cards and now has ${handCard.size} card(s)")
    }

    fun sow(general: General) {
        if (handCard.size > 0) {
            println("[Sow Discord] Zhou activate sow discord. ${general.name} need to pick a suit ")
            var gen2: MutableList<String> = listOf(
                "club",
                "diamond",
                "heart", "spade"
            ) as MutableList<String>
            var ran = gen2.random();
            println("${general.name} pick $ran as its suit")
            var zhouran = handCard.random()
            general.handCard.add(zhouran)
            handCard.remove(zhouran)
            println("${zhouran.suit} zhou yu card is this suit ")
            if (!zhouran.suit.equals(ran)) {
                println("${general.currentHP}")
                general.beingDamage(1,zhouran,this)
                println("The suit is different")
                println("${general.currentHP}")

            } else {
                println("The suit is same")
            }
        }
    }

}

//sun
class DiaoChan(name: String, var abc: Player) : NeutralGeneral(name, abc) {
    override var maxHP: Int = 3
    override var ac = true
    override var gender = true


    override fun FinalPhase() {
        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()
        handCard.add(Generalmanager.cardList.removeFirst())
        println("[Beauty outshining the moon] $name now has ${handCard.size} card(s)")
    }

}

class LvBu(name: String, var abc: Player) : NeutralGeneral(name, abc) {
    override var maxHP: Int = 4
    override var gender = false
    fun Unrival(target: General){
        var b=hasAttackCard()
        if(b==false)
            return
        var dodge2=target.has2DodgeCard()
        var card=playAttackCard()
        if(dodge2==true){
            println("${target.name}spend two dodge card to dodge the attack from lvbu")
            target.play2DodgeCard()
        }else{
            println("${target.name}do not have two dodge card to dodge the attack from lvbu")

            target.beingDamage(1,card ,target)
        }
    }


}

//sun
class ZhaoYun(name: String, var abc: Player) : ShuGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = false

    //sun
    override fun beingAttacked(general: General) {
        if (hasDodgeCard() == false) {
            var brave = hasAttackCard()
            if (brave == true) {
                playAttackCard()
                println("Bravery activate Zhao Yun using attack card as Dodge card")
            } else {
                super.beingAttacked(general)
            }
        }
        else{
            super.beingAttacked(general)
        }
    }
    override fun playAttackCard(): Card {
        lateinit var card: Card
        for (i in 0..handCard.size - 1) {
            //println(i.toString() + general.handCard.get(i).name)
            if (handCard.get(i).name.equals("Attack")) {
                card = handCard.removeAt(i)
                break
            }
        }
        return card
    }



}

class HuaXiong(name: String, var abc: Player) : NeutralGeneral(name, abc) {
    override fun beingDamage(num: Int, card: Card, general: General) {
        super.beingDamage(num, card, general)
        if(currentHP <=0){
            return
        }
        if(card.name.equals("Attack")) {
            if (card.suit.equals("heart") || card.suit.equals("diamond")) {
                println("$name execute [Triumphant].")
                if (general.currentHP < general.maxHP) {
                    println("${general.name} + 1 HP. Now ${general.currentHP} hp")
                    general.currentHP += 1
                } else {
                    if (Generalmanager.cardList.isEmpty())
                        Generalmanager.createAllCard()
                    println("${general.name} draw one card.")
                    general.handCard.add(Generalmanager.cardList.removeFirst())
                }
            }
        }

    }
}

class GanNing(name: String, var abc: Player) : WuGeneral(name, abc) {
    override var maxHP: Int = 4
    override var gender = false

    // sun
    fun checkblacksuitcard(): Boolean {
        for (i in 0..handCard.size-1) {
            if (handCard.get(i).suit.equals("spade") || handCard.get(i).suit.equals("club"))
                return true
        }

        return false
    }

    //sun
    fun playblacksuitcard() {
        for (i in 0..handCard.size) {
            if (handCard.get(i).suit.equals("spade") || handCard.get(i).suit.equals("club")) {
                handCard.remove(handCard.get(i))
                return
            }

        }
    }

    //sun
    fun ambushment() {
        if (checkblacksuitcard() == true) {
            println("Ambushment activate. GanNing can use a black suit card to activate the skills")
            playblacksuitcard()
            BurningBridges("BurningBridge", "spade", "09").spellExecute(this)


        }


    }
}

class ZhangFei(name: String, var abc: Player) : ShuGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = false

    //sun
    fun Beresk(): Int {
        var count = 0
        for (i in 0..handCard.size-1) {
            if (handCard.get(i).name.equals("Attack"))
                count++
        }
        return count
    }

}


//sun
class ZhugeLiang(name: String, var abc: Player) : ShuGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = false


    //sun
    override fun Preparationphase() {
        println("[Stargazing]ZhugeLiang activate Stargazing skills. He can now watch the carddeck")

        var starStore: MutableList<Card> = mutableListOf()
        var counter=0
        while(counter<=Generalmanager.list.size-1&&counter<=Generalmanager.cardList.size-1) {
            println(Generalmanager.cardList.get(counter).name)
            var temp = Generalmanager.cardList.get(counter)
            Generalmanager.cardList.removeAt(counter)
            Generalmanager.cardList.add(temp)
            counter++
        }

        super.Preparationphase()

    }


}// Existing class

class GuanYu {
    val maximumHP = 4

}

//sun
class GuanYuadapter(private val gen: GuanYu, abc: Player) : ShuGeneral("Guan Yu", abc) {
    override var maxHP = gen.maximumHP
    override var gender = false

    //冇攻擊牌先用god of war skill
    override fun hasAttackCard(): Boolean {
        if (super.hasAttackCard() == false) {
            return hasredsuitcard()
        } else {
            return true

        }
    }

    fun hasredsuitcard(): Boolean {
        println("[God of War] Guan Yu activate god of war skills")
        for (i in 0..handCard.size - 1) {

            if (handCard.get(i).suit.equals("diamond") || handCard.get(i).suit.equals("heart")) {

                return true
            }
        }
        return false
    }

    override fun playAttackCard(): Card {
        lateinit var card: Card
        if (hasredsuitcard() == true) {
            for (i in 0..handCard.size - 1) {
                println("I use attack card")
                if (handCard.get(i).suit.equals("diamond") || handCard.get(i).suit.equals("heart")) {
                    card = handCard.removeAt(i)
                    break

                }
            }
        } else {
            if (hasAttackCard() == true)
                card = super.playAttackCard()
        }
        return card
    }

}

class ZhangLiao(name: String, var abc: Player) : WeiGeneral(name, abc) {
    override var maxHP: Int = 4
    override var gender = false

    fun incursion(num: Int): General {
        println(
            "$name execute [Incursion] to obtain cards from ${Generalmanager.list.get(num).name} (${
                Generalmanager.list.get(num).handCard.size
            })!"
        )
        this.handCard.add(Generalmanager.list.get(num).handCard.removeFirst())
        println(
            "$name receive a card from ${Generalmanager.list.get(num).name} (handCard num: ${
                Generalmanager.list.get(num).handCard.size
            })."
        )
        if(Generalmanager.list.get(num) is LuXun){
            (Generalmanager.list.get(num) as LuXun).Coalition()}
        return Generalmanager.list.get(num)
    }

    override fun DrawPhase() {
        // check boolean
        var boolean = true
        for (i in 0..Generalmanager.list.size - 1) {
            if (Generalmanager.list.get(i).handCard.size < 1) {
                println("in the false")
                boolean = false
                break
            }
        }
        // ensure have two other players, if no -> draw2
        if (Generalmanager.list.size >= 3) {
            // means everyone has at least 1 card, if no -> draw 2
            if (boolean == true) {
                println("in specific")
                var count = 0
                lateinit var store: General

                while (count < 2) {

                    var num = Random.nextInt(0, Generalmanager.list.size)

                    if (Generalmanager.list.get(num).equals(this)) {
                        continue
                    } else {
                        //first player
                        if (count == 0) {
                            if (Generalmanager.list.get(num).handCard.size >= 1) {
                                store = incursion(num)
                                count++
                            } else {
                                continue
                            }
                        }
                        //second player
                        else if (count == 1) {
                            if (Generalmanager.list.get(num).handCard.size >= 1) {
                                // not the same player, find other
                                if (Generalmanager.list.get(num).equals(store)
                                    || Generalmanager.list.get(num).equals(this)
                                ) {
                                    continue
                                } else {
                                    store = incursion(num)
                                    count++
                                }
                            } else {
                                continue
                            }
                        }
                    }
                }
            }
            // means everyone has at least 1 card, if no -> draw 2
            else
                draw2Cards()
        }
        // ensure have two other players, if no -> draw2
        else
            draw2Cards()
    }
}

class GuoJia(name: String, var abc: Player) : WeiGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = false

    override fun drawJudgementCard(): Card {
        var card = super.drawJudgementCard()
        println("$name execte [Jealousy of God], obtain the judgement card.")
        this.handCard.add(card)
        return card
    }


    override fun beingDamage(num: Int, card: Card, general: General) {
       super.beingDamage(num, card, general)
        if(currentHP <=0){
            return
        }

        for (i in 0..num - 1) {
            println("[Last Strategy] look up two cards of dack.")

            if (this.strategy is LoyalistStrategy) {
                Generalmanager.list.get(0).draw2Cards()
            } else {
                this.draw2Cards()
            }
        }

    }
}

class HuaTuo(name: String, var abc: Player) : NeutralGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = false

    fun medicalPractice() {
        // loyalist, heal lord first
        if (strategy is LoyalistStrategy) {
            // heal lord
            if (Generalmanager.list.get(0).currentHP < Generalmanager.list.get(0).maxHP) {
                println("$name execute [Medical Practice] ")
                handCard.removeFirst()
                Generalmanager.list.get(0).currentHP += 1
                println(
                    "$name heals ${Generalmanager.list.get(0).name} 1 Hp, " +
                            "(hp:${Generalmanager.list.get(0).currentHP})"
                )

            }
            //heal himself
            else if (this.currentHP < maxHP) {
                println("$name execute [Medical Practice] ")
                handCard.removeFirst()
                currentHP += 1
                println("$name heals himself 1 Hp, (hp:$currentHP)")
            }

        }
        // rebel
        else {
            // heal himself first
            if (this.currentHP < maxHP) {
                println("$name execute [Medical Practice] ")
                println("$name heals himself 1 Hp")
                handCard.removeFirst()
                currentHP += 1
            }
            // heal other rebels
            else {
                for (i in 0..Generalmanager.list.size - 1) {
                    if (Generalmanager.list.get(i).currentHP < Generalmanager.list.get(i).currentHP
                        && Generalmanager.list.get(i).strategy is RebelStrategy
                    ) {
                        println("$name execute [Medical Practice] ")
                        println("$name heals ${Generalmanager.list.get(0).name} 1 Hp")
                        handCard.removeFirst()
                        Generalmanager.list.get(0).currentHP += 1
                    }
                }
            }
        }
    }

    override fun PlayPhase() {
        medicalPractice()
        super.PlayPhase()
    }

    override fun playSavePeachCheck(general: General): Boolean {
        if ((player is Loyalist && general.player is Lord)
            || player is Lord && general.player is Loyalist
            || player is Loyalist && general.player is Loyalist
        ) {
            if(hasPeachCard())
                return true
            else
                return checkRedCard()
        }
        else if (player is Rebel && general.player is Rebel) {
            if(hasPeachCard())
                return true
            else
                return checkRedCard()
        }

        println("$name does not play peach")
        return false
    }
    fun checkRedCard():Boolean{
        for (i in 0..handCard.size - 1) {
            if (handCard.get(i).suit.equals("heart") || handCard.get(i).suit.equals("diamond")){
                println("[First Aid] $name has red card." )
                return true
            }
        }
        return false
    }

    override fun playSavePeach() {

        for (i in 0..handCard.size - 1) {
            if (handCard.get(i).name.equals("Peach")) {
                handCard.removeAt(i)
                println("$name gives out a peach")
                return
            }
        }
        for (i in 0..handCard.size - 1) {
            if (handCard.get(i).suit.equals("heart") || handCard.get(i).suit.equals("diamond")){
                println("[First Aid] $name gives out a red card, " +
                        "(${handCard.get(i).name} , ${handCard.get(i).suit}, ${handCard.get(i).number})")
                handCard.removeAt(i)
                return
            }
        }
    }
}

class HuangYueying(name: String, var abc: Player) : ShuGeneral(name, abc) {
    override var maxHP: Int = 3
    override var gender = true


    fun wisdom() {
        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()
        println("[Wisdom], $name draw a card after using spell card.")
        handCard.add(Generalmanager.cardList.removeFirst())


    }

}
class DaQiao(name: String, var abc: Player) :WuGeneral(name,abc){
    override var maxHP: Int = 3
    override var gender = true

    fun nationalbeauty(){
        var havediamondsuitcard=havediamondsuit()
        if(havediamondsuitcard==true) {
            var c = AcediaCard("Acedia", "diamond", "111")
            c.spellExecute(this)

        }
    }
    fun havediamondsuit():Boolean{
        for(i in 0..handCard.size-1){
            if(handCard.get(i).suit.equals("diamond")){
                handCard.removeAt(i)
                return true}
        }

        return false
    }
    fun delflectivefindtarget(){
        println("Da Qiao is finding a deflective target. Now her current hp is $currentHP")
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



        // set Attack list
        // reset inRange
        inRange = mutableListOf()
        setAttackRangeList()
    }

    override fun beingAttacked(general: General) {
         delflectivefindtarget()
         var reflect:Int
        if(strategy is LoyalistStrategy){
            reflect=(strategy as LoyalistStrategy).locktarget(general)}
        else {
            reflect = (strategy as RebelStrategy).locktarget(general)
        }
        if(reflect==87){
            super.beingAttacked(general)
        }
        else{
            reflectattack(Generalmanager.list.get(reflect),general)
        }





    }
    fun reflectattack(target:General,opponent:General){
        println("$name being attacked")

        val card = opponent.playAttackCard()//攻擊人出殺
        var dodge = this.hasDodgeCardCheckAll()

        if (dodge == true) {
            playDodgeCard()
        } else{
            println("Da Qiao deflect the attack to ${target.name}")
            target.beingDamage(opponent.attackDamage, card, opponent)
    }}



}
class LuXun(name: String, var abc: Player) :WuGeneral(name,abc){
    override var maxHP: Int = 3
    override var gender = false
    fun Coalition(){
        if (handCard.size==0){
            println("Coalition activate. Lu Xun draw a card from deck")
            drawCards()
            println("${handCard.size}")
        }

    }

}

class MaChao(name: String, var abc: Player) :ShuGeneral(name,abc){
    override var maxHP: Int = 4
    override var gender = false
    // like -1 mount
    override var horseRiding = 1

    fun cavalryExecute():Boolean{
        val card = drawJudgementCard()
        if(card.suit.equals("heart")||card.suit.equals("diamond")) {
            println("Ma Chao's [Cavalry] execute!")
            println("The target cannot play a dodge card!")
            return true
        }
        return false
    }
    fun cavalry(card: Card, target:General) {
        if (!inRange.isEmpty()){
            if (equipment is SkyPiercingHalberd) {
                println("using SkyPiercingHalberd")
                if (handCard.size == 0) {
                    println("execute SkyPiercingHalberd!")
                    if (inRange.size >= 3) {
                        inRange.get(0).beingDamage(attackDamage, card, this)
                        inRange.get(1).beingDamage(attackDamage, card, this)
                        inRange.get(2).beingDamage(attackDamage, card, this)

                    } else if (inRange.size == 2) {
                        inRange.get(0).beingDamage(attackDamage, card, this)
                        inRange.get(1).beingDamage(attackDamage, card, this)
                    } else if (inRange.size == 1) {
                        target.beingDamage(attackDamage, card, this)
                    }
                } else {
                    target.beingDamage(attackDamage, card, this)
                }
                attackedState = true
            } else if (equipment is ZhugeCrossbow) {
                println("using ZhugeCrossbow")
                target.beingDamage(attackDamage, card, this)
            } else if (equipment is YinYangSwords) {
                println("using YinYangSwords")
                if (target.gender == true) {
                    // draw one
                    drawCards()
                }
                target.beingDamage(attackDamage, card, this)
                attackedState = true
            } else if (equipment is KirinBow) {
                println("using KirinBow")
                val originHP = target.currentHP
                target.beingDamage(attackDamage, card, this)
                if (originHP > target.currentHP) {
                    // remove after damage
                    if (target.mountDec != null) {
                        target.removePlacementCard("mountDec")
                    } else if (target.mountInc != null) {
                        target.removePlacementCard("mountInc")
                    }
                }
                attackedState = true
            } else {
                if (equipment != null) {
                    println("using ${equipment!!.name}")
                }
                target.beingDamage(attackDamage, card, this)
                attackedState = true
            }
        }
    }

}
// dummy
class Dummy(name: String, var abc: Player):General(name,abc){
    override var maxHP: Int = 0
    override var gender = false
}
class YuanShu (name: String, var abc: Player):NeutralGeneral(name,abc){
    override var maxHP: Int = 5
    var Treason=false
    override fun Preparationphase() {
        Treason=true
        println("Treason activate, Yuan Shu can draw a card during prepartion phase")
        drawCards()
        super.Preparationphase()
    }

    override fun DiscardPhase() {
        var store: Int = 0
        if(Treason==true){
            Treason=false
        if (handCard.size > currentHP+1) {
            store = handCard.size - currentHP+1
        }
        println("Yuan Shu have draw a card before. So it trigger the negative impact of treason. He now have to remove $store cards ")

        if (currentHP > 0) {
            for (i in 0..store - 1) {
                handCard.removeAt(0)
            }
        }

        println("$name discards ${store} card(s), now has ${handCard.size} card(s). ")
    }
        else
            super.DiscardPhase()
    }
}