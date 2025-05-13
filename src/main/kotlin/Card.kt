abstract class Card(val name: String, val suit: String, val number: String) {

}

class BasicCard(name: String, suit: String, number: String) : Card(name, suit, number) {
}


abstract class SpellCard(name: String, suit: String, number: String) : Card(name, suit, number) {
    // general -> user
    abstract fun spellExecute(general: General)
    fun findTarget(general: General): General {
        var store: MutableList<General> = mutableListOf()

        if (general.strategy is (LoyalistStrategy)) {
            for (i in 0..Generalmanager.list.size - 1) {
                if (!Generalmanager.list.get(i).equals(general)) {
                    if (Generalmanager.list.get(i).player is Rebel || Generalmanager.list.get(i).player is Spy) {
                        if ((this.name.equals("Acedia") && Generalmanager.list.get(i).name.equals("Lu Xun")))
                            println("Humidility activate Lu Xun can't pick as the target of acedia")
                        else
                            store.add(Generalmanager.list.get(i))

                    }
                }
            }
        } else {
            if (general.strategy is (RebelStrategy)) {
                for (i in 0..Generalmanager.list.size - 1) {
                    if (!Generalmanager.list.get(i).equals(general)) {
                        if (Generalmanager.list.get(i).player is Lord || Generalmanager.list.get(i).player is Loyalist) {
                            if ((this.name.equals("Acedia") && Generalmanager.list.get(i).name.equals("Lu Xun")))
                                println("Humidility activate Lu Xun can't pick as the target of acedia")
                            else
                                store.add(Generalmanager.list.get(i))
                        }
                    }
                }
            }
        }
        if (!store.isEmpty()) {
            return store.random()
        } else
            return Dummy("null", Spy())

    }

    //remove spell card from hand and return card
    fun removeSpellCard(general: General): Card {
        println("(spell) ${general.name} play ${this.name} card. ")
        general.handCard.remove(this)
        // [wisdom] draw one, Huang Yueying
        if (general.name.equals("Huang Yueying")) {
            (general as HuangYueying).wisdom()
        }
        return this
    }

    //used for check Negation after playing spell card and before spell execute
    fun checkNegation(general: General): Boolean {
        var boolean = false
        var num = Generalmanager.list.indexOf(general)
        var count = 1
        val size = Generalmanager.list.size
        var storeGeneral = general

        while(count <= size){
            var modSizeGeneral = Generalmanager.list.get((num + count).mod(size))
            for (j in 0..modSizeGeneral.handCard.size - 1) {
                // not same strategy
                if((modSizeGeneral.strategy is LoyalistStrategy &&
                        (storeGeneral).strategy is RebelStrategy) ||
                    (modSizeGeneral.strategy is RebelStrategy &&
                            (storeGeneral).strategy is LoyalistStrategy)) {

                    if (modSizeGeneral.handCard.get(j) is Negation) {
                        (modSizeGeneral.handCard.get(j) as Negation).spellExecute(modSizeGeneral)
                        // update store general and boolean
                        storeGeneral = modSizeGeneral
                        boolean = !boolean
                        break
                    }
                }else
                    break
            }
            count++
        }
        return boolean
    }

}

class BurningBridges(name: String, suit: String, number: String) : SpellCard(name, suit, number) {
    override fun spellExecute(general: General) {
        var normalTarget = findTarget(general)
        if (normalTarget.handCard.size == 0 || normalTarget.name.equals("null")) {
            normalTarget = findTarget(general)
        }
        if (normalTarget.handCard.size == 0 || normalTarget.name.equals("null")) {
            println("${general.name} don't want to play ${this.name}")
            return
        }

        removeSpellCard(general)
        if (checkNegation(general))
            return

        general.removeTargetPlacementCard(normalTarget)
        if (normalTarget is LuXun)
            normalTarget.Coalition()
    }
}

class AcediaCard(name: String, suit: String, number: String) : SpellCard(name, suit, number) {
    override fun spellExecute(general: General) {

        var target = findTarget(general)
        if (target.invoker.commandList.contains(Acedia(target)) || target.name.equals("null"))
            target = findTarget(general)
        if (target.invoker.commandList.contains(Acedia(target)) || target.name.equals("null")) {
            println("${general.name} don't want to play acedia card.")
            return
        }

        removeSpellCard(general)

        println("${target.name} being placed the Acedia card.")
        target.invoker.addCommand(Acedia(target))
    }
}

class LightningCard(name: String, suit: String, number: String) : SpellCard(name, suit, number) {
    override fun spellExecute(general: General) {
        removeSpellCard(general)
        println("${general.name} being placed the Lightning card.")
        general.invoker.addCommand(Lightning(general))
    }
}

// locate user
//we default all player will出殺if they have the card
class BarbarianInvasion(name: String, suit: String, number: String) : SpellCard(name, suit, number) {
    override fun spellExecute(general: General) {
        val card = removeSpellCard(general)

        var counter = 0
        var size = Generalmanager.list.size

        while (counter <= size - 1) {// bug 要轉while loop
            if (!Generalmanager.list.get(counter).equals(general)) {

                var b = Generalmanager.list.get(counter).hasAttackCard()

                var hasNeg = false
                // check negation in hand
                for (j in 0..Generalmanager.list.get(counter).handCard.size - 1) {
                    if (Generalmanager.list.get(counter).handCard.get(j) is Negation) {
                        (Generalmanager.list.get(counter).handCard.get(j) as Negation).spellExecute(Generalmanager.list.get(counter))
                        println("${Generalmanager.list.get(counter).name} play negation to get rid of $name")
                        hasNeg = true
                        break
                    }
                }
                if (hasNeg) {
                    counter++
                    size = Generalmanager.list.size
                    continue
                }
                // no negation

                if (b == false) {

                    println("${Generalmanager.list.get(counter).name} does not have an attack card to avoid the barbarian invasion")
                    Generalmanager.list.get(counter).beingDamage(1, card, general)
                    //check general is removed or not. if removed counter -1 to get the next general
                    if(size > Generalmanager.list.size)
                        counter --

                } else {
                    //add play card
                    Generalmanager.list.get(counter).playAttackCard()
                }
            }
            counter++
            size = Generalmanager.list.size
        }
    }
}

class Negation(name: String, suit: String, number: String) : SpellCard(name, suit, number) {
    override fun spellExecute(general: General) {
        removeSpellCard(general)
        println("Negation will cancel the spell!")
    }
}

//assume the  card user will use duel on lord
//heart 同diamond係紅色
//other are black
class Duel(name: String, suit: String, number: String) : SpellCard(name, suit, number) {

    fun spellExecute(general: General, opponent: General, bu: Boolean) {
        val card = removeSpellCard(general)
        if (checkNegation(general))
            return

        println("${general.name} start dueling with ${opponent.name}")
        while (bu == false) {
            var genatt = general.hasAttackCard()

            var opp = opponent.hasAttackCard()
            if (genatt == false) {

                println("${general.name} has no attack card for duel")

                general.beingDamage(1, card, opponent)

                return
            } else if (opp == false) {
                println("${opponent.name} has no attack card for duel")


                general.beingDamage(1, card, general)
                return
            }

            if (genatt == true) {
                println("${general.name} has attack card for duel")
                general.playAttackCard()
            }
            if (opp == true) {
                println("${opponent.name} has attack card for duel")

                opponent.playAttackCard()
            }

        }
        while (bu == true) {
            var genatt = general.hasAttackCard()

            var opp = opponent.has2AttackCard()
            if (genatt == false) {

                general.beingDamage(1, card, opponent)
                return
            } else if (opp == false) {

                general.beingDamage(1, card, general)
                return
            }

            if (genatt == true) {
                general.playAttackCard()
            }
            if (opp == true) {
                opponent.play2AttackCard()
            }

        }


    }
    fun diaochanduel(general: General, opponent: General, bu: Boolean) {
        val card = removeSpellCard(general)


        println("${general.name} start dueling with ${opponent.name}")
        while (bu == false) {
            var genatt = general.hasAttackCard()

            var opp = opponent.hasAttackCard()
            if (genatt == false) {

                println("${general.name} has no attack card for duel")

                general.beingDamage(1, card, opponent)

                return
            } else if (opp == false) {
                println("${opponent.name} has no attack card for duel")

                general.beingDamage(1, card, general)
                return
            }

            if (genatt == true) {
                println("${general.name} has attack card for duel")
                general.playAttackCard()
            }
            if (opp == true) {
                println("${opponent.name} has attack card for duel")

                opponent.playAttackCard()
            }

        }

    }



        override fun spellExecute(general: General) {

        println("test")
    }
}

class StealingSheep(name: String, suit: String, number: String) : SpellCard(name, suit, number) {
    override fun spellExecute(general: General) {
        lateinit var YueyingTarget: General
        lateinit var normalTarget: General

        if (general.name.equals("Huang Yueying")) {
            YueyingTarget = findTarget(general)

            if (YueyingTarget.handCard.size == 0 || YueyingTarget.name.equals("Lu Xun") || YueyingTarget.name.equals("null")) {
                YueyingTarget = findTarget(general)
            }
            if (YueyingTarget.handCard.size == 0 || YueyingTarget.name.equals("Lu Xun") || YueyingTarget.name.equals("null")) {
                println("${general.name} don't want to play ${this.name}")
                return
            }
        } else {
            normalTarget = StealingSheepTargetRange(general)
            if (normalTarget.handCard.size == 0 || normalTarget.name.equals("Lu Xun") || normalTarget.name.equals("null")) {
                normalTarget = StealingSheepTargetRange(general)
            }
            if (normalTarget.handCard.size == 0 || normalTarget.name.equals("Lu Xun") || normalTarget.name.equals("null")) {
                println("${general.name} don't want to play ${this.name}")
                return
            }
        }

        removeSpellCard(general)
        if (checkNegation(general))
            return

        if (general.name.equals("Huang Yueying")) {
            println("Huang Yueying [Wizardry], unlimited range. ")
            val gainCArd = general.removeTargetPlacementCard(YueyingTarget)
            general.handCard.add(gainCArd)
            println("Huang Yueying gain ${gainCArd.name} from ${YueyingTarget.name}.")
        } else {
            val gainCArd = general.removeTargetPlacementCard(normalTarget)
            general.handCard.add(gainCArd)
            println("${general.name} gain ${gainCArd.name} from ${normalTarget.name}.")
        }
    }

    public inline fun Int.mod(other: Int): Int {
        val r = this % other
        return r + (other and (((r xor other) and (r or -r)) shr 31))
    }

    // set up a target list
    fun StealingSheepTargetRange(general: General): General {
        var num: Int = 0
        var generalList: MutableList<General> = mutableListOf()
        val size = Generalmanager.list.size
        // find location
        for (i in 0..size - 1) {

            if (Generalmanager.list.get(i).name.equals(general.name)) {
                num = i
                break
            }
        }
        // find extra mount range
        var mountDec = 0
        if (general.mountDec != null) {
            mountDec = 1
        }
        // range (hoeseRiding in default is 0, if general is not Ma Chao
        var range = 1 + mountDec + general.horseRiding

        if (range >= Generalmanager.list.size) {
            for (i in 0..Generalmanager.list.size - 1) {
                if (!(Generalmanager.list.get(i).equals(Generalmanager.list.get(num))))
                    generalList.add(Generalmanager.list.get(i))
            }
        } else {

            // left
            var leftCount = 1
            var leftNum = 1
            while (leftCount <= range) {

                if (!(Generalmanager.list.get((num - leftNum).mod(size)).equals(Generalmanager.list.get(num)))) {
                    if (Generalmanager.list.get((num - leftNum).mod(size)).mountInc != null) {
                        leftCount++
                    }
                    if (leftCount <= range) {
                        if (!(generalList.contains(Generalmanager.list.get((num - leftNum).mod(size))))) {
                            generalList.add(Generalmanager.list.get((num - leftNum).mod(size)))
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
                    if (!(generalList.contains(Generalmanager.list.get((num + rightNum).mod(size))))) {
                        generalList.add(Generalmanager.list.get((num + rightNum).mod(size)))
                        rightCount++
                        rightNum++
                    } else {
                        rightCount++
                        rightNum++
                    }
                }
            }
        }
        println("In Range: ")
        for (i in 0..generalList.size - 1) {
            println(generalList.get(i).name)
        }
        if (!generalList.isEmpty()) {
            return generalList.random()
        } else
        // not be added
            return Dummy("null", Spy())
    }
}

class Brotherhood(name: String, suit: String, number: String) : SpellCard(name, suit, number) {
    override fun spellExecute(general: General) {
        removeSpellCard(general)
        if (checkNegation(general))
            return

        for (i in 0..Generalmanager.list.size - 1) {
            println("${Generalmanager.list.get(i).name} orign have ${Generalmanager.list.get(i).currentHP} health points")

            if (Generalmanager.list.get(i).currentHP > 0 && Generalmanager.list.get(i).currentHP < Generalmanager.list.get(
                    i
                ).maxHP
            ) {
                Generalmanager.list.get(i).currentHP++
                println("${Generalmanager.list.get(i).name} now have ${Generalmanager.list.get(i).currentHP} health points")

            }

        }
    }
}

// locate user
class RainingArrows(name: String, suit: String, number: String) : SpellCard(name, suit, number) {
    override fun spellExecute(general: General) {
        val card = removeSpellCard(general)

        var size = Generalmanager.list.size
        var counter = 0



        while (counter <= size - 1) {
            if (!Generalmanager.list.get(counter).equals(general)) {
                var b = Generalmanager.list.get(counter).hasDodgeCardCheckAll()

                var hasNeg = false
                // check negation in hand
                for (j in 0..Generalmanager.list.get(counter).handCard.size - 1) {
                    if (Generalmanager.list.get(counter).handCard.get(j) is Negation) {
                        (Generalmanager.list.get(counter).handCard.get(j) as Negation).spellExecute(Generalmanager.list.get(counter))
                        println("${Generalmanager.list.get(counter).name} play negation to get rid of $name")
                        hasNeg = true
                        break
                    }
                }
                if (hasNeg) {
                    counter++
                    size = Generalmanager.list.size
                    continue
                }
                if (b == false) {

                    println("${Generalmanager.list.get(counter).name} does not have an dodge card to avoid the raining arrows")

                    Generalmanager.list.get(counter).beingDamage(1, card, general)

                    //check general is removed or not. if removed counter -1 to get the next general
                    if(size > Generalmanager.list.size)
                        counter --
                } else
                //add play card
                    Generalmanager.list.get(counter).playDodgeCard()
            }
            counter++
            size = Generalmanager.list.size
        }
    }
}

class BumperHarvest(name: String, suit: String, number: String) : SpellCard(name, suit, number) {

    override fun spellExecute(general: General) {
        removeSpellCard(general)
        if (checkNegation(general))
            return

        println("Bumper Harvest activate")
        var counter = 0

        while (counter <= Generalmanager.list.size - 1 && counter <= Generalmanager.cardList.size - 1) {
            println(Generalmanager.cardList.get(counter).name)
            counter++
        }

        if (Generalmanager.cardList.isEmpty())
            Generalmanager.createAllCard()
        general.handCard.add(Generalmanager.cardList.get(0))
        Generalmanager.cardList.removeFirst()
        println("${general.name}pick the first card")

        if (Generalmanager.cardList.size == 0) {
            println("The deck already go out of card")
            return
        }
        var counter2 = 0
        while (counter2 <= Generalmanager.list.size - 1 && counter2 <= Generalmanager.cardList.size - 1) {
            if (!general.equals(Generalmanager.list.get(counter2))) {
                if (Generalmanager.cardList.size == 0) {
                    Generalmanager.createAllCard()
                }
                var temp = Generalmanager.cardList.get(counter2)//bug maybe need to fix like gamestart
                println("${Generalmanager.list.get(counter2).name} get the card ${temp.name}")
                Generalmanager.list.get(counter2).handCard.add(temp)
                Generalmanager.cardList.remove(temp)
            }
            counter2++

        }
        println("end")
        return
    }

}

class SomethingOutOfNothing(name: String, suit: String, number: String) : SpellCard(name, suit, number) {
    override fun spellExecute(general: General) {
        removeSpellCard(general)
        if (checkNegation(general))
            return

        general.draw2Cards()
    }
}


abstract class EquipmentCard(name: String, suit: String, number: String) : Card(name, suit, number) {

    abstract fun equipmentExecute(general: General)
    abstract val attakRange: Int

}

class ZhugeCrossbow(name: String, suit: String, number: String) : EquipmentCard(name, suit, number) {
    override fun equipmentExecute(general: General) {
        general.equipment = this
        general.handCard.remove(this)


    }

    override val attakRange: Int = 1

}

class GreenDragonBlade(name: String, suit: String, number: String) : EquipmentCard(name, suit, number) {
    override fun equipmentExecute(general: General) {

    }

    override val attakRange: Int = 3
}

class SerpentSpear(name: String, suit: String, number: String) : EquipmentCard(name, suit, number) {
    override fun equipmentExecute(general: General) {
        general.equipment = this
        general.handCard.remove(this)

    }


    override val attakRange: Int = 3
}

class YinYangSwords(name: String, suit: String, number: String) : EquipmentCard(name, suit, number) {
    override fun equipmentExecute(general: General) {
        general.equipment = this
        general.handCard.remove(this)


    }

    override val attakRange: Int = 2
}

class KirinBow(name: String, suit: String, number: String) : EquipmentCard(name, suit, number) {
    override fun equipmentExecute(general: General) {

    }

    override val attakRange: Int = 5
}

class RockCleavingAxe(name: String, suit: String, number: String) : EquipmentCard(name, suit, number) {
    override fun equipmentExecute(general: General) {
        general.equipment = RockCleavingAxe(this.name, this.suit, number)

    }

    override val attakRange: Int = 3
}

class BlueSteelBlade(name: String, suit: String, number: String) : EquipmentCard(name, suit, number) {
    override fun equipmentExecute(general: General) {
        general.equipment = this

    }

    override val attakRange: Int = 2
}

class SkyPiercingHalberd(name: String, suit: String, number: String) : EquipmentCard(name, suit, number) {
    override fun equipmentExecute(general: General) {
        general.equipment = this

    }

    fun skypiercingattack(general: General) {
        var c = general.playAttackCard()
        var counter = 0
        var size = general.inRange.size

        while (counter <= size - 1&&size<=3) {
            general.inRange.get(counter).beingAttacked(c, general)
            counter++
            size = general.inRange.size
        }


    }

    override val attakRange: Int = 4
}


//horse
abstract class MountCardInc(name: String, suit: String, number: String) : Card(name, suit, number) {
    abstract val distant: Int
}

abstract class MountCardDec(name: String, suit: String, number: String) : Card(name, suit, number) {
    abstract val distant: Int
}

class FerghanaHorse(name: String, suit: String, number: String) : MountCardDec(name, suit, number) {
    override val distant: Int = -1
}

class ShadowRunner(name: String, suit: String, number: String) : MountCardInc(name, suit, number) {
    override val distant: Int = 1
}

class HexMark(name: String, suit: String, number: String) : MountCardDec(name, suit, number) {
    override val distant: Int = 1
}

class FlyingLightning(name: String, suit: String, number: String) : MountCardInc(name, suit, number) {
    override val distant: Int = 1
}

class VioletStallion(name: String, suit: String, number: String) : MountCardDec(name, suit, number) {
    override val distant: Int = -1
}

class RedHare(name: String, suit: String, number: String) : MountCardDec(name, suit, number) {
    override val distant: Int = -1
}


abstract class ArmorCard(name: String, suit: String, number: String) : Card(name, suit, number) {
    abstract fun armorExecute(general: General): Boolean
}

class EightTrigramsFormation(name: String, suit: String, number: String) : ArmorCard(name, suit, number) {
    override fun armorExecute(general: General): Boolean {
        val card = general.drawJudgementCard()
        if (card.suit.equals("heart") || card.suit.equals("diamond")) {
            println("is red card!")
            return true
        } else {
            println("is black card!")
            return false
        }
    }
}

