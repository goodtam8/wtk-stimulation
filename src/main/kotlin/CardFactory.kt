abstract class CardFactory{
    abstract fun createRandomCard(number:Int): Card

}
class SpadeCardFactory: CardFactory(){
    val list: MutableList<String> = mutableListOf("", "Lightning",
        "Eight Trigrams Formation" ,
        "Burning Bridges" ,
        "Burning Bridges" ,
        "Green Dragon Blade" ,
        "Acedia" ,
        "Barbarian Invasion" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Negation" ,
        "Serpent Spear" ,
        "Ferghana horse")
    val suit:String = "spade"
    override fun createRandomCard(number:Int):Card{
        lateinit var card:Card
        if(number in 1..13){
            card = when(number){
                1 -> LightningCard(list.get(number), suit,"A" )
                2 -> EightTrigramsFormation(list.get(number), suit,number.toString() )
                3 -> BurningBridges(list.get(number), suit,number.toString() )
                4 -> BurningBridges(list.get(number), suit,number.toString() )
                5 -> GreenDragonBlade(list.get(number), suit, number.toString() )
                6 -> AcediaCard(list.get(number), suit,number.toString() )
                7 -> BarbarianInvasion(list.get(number), suit,number.toString() )
                8 -> BasicCard(list.get(number), suit, number.toString() )
                9 -> BasicCard(list.get(number), suit, number.toString() )
                10 -> BasicCard(list.get(number), suit, number.toString() )
                11 -> Negation(list.get(number), suit,"J" )
                12 -> SerpentSpear(list.get(number), suit, "Q" )
                13 -> FerghanaHorse(list.get(number), suit, "K" )
                else -> throw IllegalArgumentException("Invalid card.")
            }
        }
        else{
            println("number format wrong (create card)")
        }
        return card
    }
}

class SpadeCardFactory2: CardFactory(){
    val list: MutableList<String> = mutableListOf("", "Duel" ,
        "Yin-Yang Swords" ,
        "Stealing Sheep" ,
        "Stealing Sheep" ,
        "Shadowrunner" ,
        "Blue Steel Blade" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Stealing Sheep" ,
        "Burning Bridges" ,
        "Barbarian Invasion")
    val suit:String = "spade"
    override fun createRandomCard(number:Int):Card{
        lateinit var card:Card
        if(number in 1..13){
            card = when(number){
                1 -> Duel(list.get(number), suit,"A" )
                2 -> YinYangSwords(list.get(number), suit, number.toString() )
                3 -> StealingSheep(list.get(number), suit,number.toString() )
                4 -> StealingSheep(list.get(number), suit,number.toString() )
                5 -> ShadowRunner(list.get(number), suit, number.toString() )
                6 -> BlueSteelBlade(list.get(number), suit, number.toString() )
                7 -> BasicCard(list.get(number), suit, number.toString())
                8 -> BasicCard(list.get(number), suit, number.toString() )
                9 -> BasicCard(list.get(number), suit, number.toString() )
                10 -> BasicCard(list.get(number), suit, number.toString()  )
                11 -> StealingSheep(list.get(number), suit, "J" )
                12 -> BurningBridges(list.get(number), suit, "Q" )
                13 -> BarbarianInvasion(list.get(number), suit, "K")
                else -> throw IllegalArgumentException("Invalid card.")
            }
        }
        else{
            println("number format wrong (create card)")
        }
        return card
    }
}

class ClubCardFactory: CardFactory(){
    val list: MutableList<String> = mutableListOf("", "Zhuge Crossbow" ,
        "Eight Trigrams Formation" ,
        "Burning Bridges" ,
        "Burning Bridges" ,
        "Hex Mark" ,
        "Acedia" ,
        "Barbarian Invasion" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Burning Bridges" ,
        "Burning Bridges")
    val suit:String = "club"
    override fun createRandomCard(number:Int):Card{
        lateinit var card:Card
        if(number in 1..13){
            card = when(number){
                1 -> ZhugeCrossbow(list.get(number), suit, "A" )
                2 -> EightTrigramsFormation(list.get(number), suit,number.toString() )
                3 -> BurningBridges(list.get(number), suit,number.toString() )
                4 -> BurningBridges(list.get(number), suit,number.toString() )
                5 -> HexMark(list.get(number), suit,number.toString() )
                6 -> AcediaCard(list.get(number), suit,number.toString() )
                7 -> BarbarianInvasion(list.get(number), suit,number.toString() )
                8 -> BasicCard(list.get(number), suit, number.toString() )
                9 -> BasicCard(list.get(number), suit, number.toString() )
                10 -> BasicCard(list.get(number), suit, number.toString() )
                11 -> BasicCard(list.get(number), suit, "J")
                12 -> BurningBridges(list.get(number), suit,"Q" )
                13 -> BurningBridges(list.get(number), suit,"K" )
                else -> throw IllegalArgumentException("Invalid card.")
            }
        }
        else{
            println("number format wrong (create card)")
        }
        return card
    }
}
class ClubCardFactory2: CardFactory(){
    val list: MutableList<String> = mutableListOf("", "Duel" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Negation" ,
        "Negation")
    val suit:String = "club"
    override fun createRandomCard(number:Int):Card{
        lateinit var card:Card
        if(number in 1..13){
            card = when(number){
                1 -> Duel(list.get(number), suit,"A" )
                2 -> BasicCard(list.get(number), suit, number.toString() )
                3 -> BasicCard(list.get(number), suit, number.toString() )
                4 -> BasicCard(list.get(number), suit, number.toString() )
                5 -> BasicCard(list.get(number), suit, number.toString() )
                6 -> BasicCard(list.get(number), suit, number.toString() )
                7 -> BasicCard(list.get(number), suit, number.toString() )
                8 -> BasicCard(list.get(number), suit, number.toString() )
                9 -> BasicCard(list.get(number), suit, number.toString() )
                10 -> BasicCard(list.get(number), suit, number.toString() )
                11 -> BasicCard(list.get(number), suit,"J" )
                12 -> Negation(list.get(number), suit,"Q" )
                13 -> Negation(list.get(number), suit,"K" )
                else -> throw IllegalArgumentException("Invalid card.")
            }
        }
        else{
            println("number format wrong (create card)")
        }
        return card
    }
}

class HeartCardFactory: CardFactory(){
    val list: MutableList<String> = mutableListOf("", "Brotherhood" ,
        "Dodge" ,
        "Peach" ,
        "Peach" ,
        "Kirin Bow" ,
        "Peach" ,
        "Peach" ,
        "Peach" ,
        "Peach" ,
        "Attack" ,
        "Attack" ,
        "Peach" ,
        "Flying Lightning")
    val suit:String = "heart"
    override fun createRandomCard(number:Int):Card{
        lateinit var card:Card
        if(number in 1..13){
            card = when(number){
                1 -> Brotherhood(list.get(number), suit,"A" )
                2 -> BasicCard(list.get(number), suit, number.toString() )
                3 -> BasicCard(list.get(number), suit, number.toString() )
                4 -> BasicCard(list.get(number), suit, number.toString() )
                5 -> KirinBow(list.get(number), suit, number.toString() )
                6 -> BasicCard(list.get(number), suit, number.toString() )
                7 -> BasicCard(list.get(number), suit, number.toString() )
                8 -> BasicCard(list.get(number), suit, number.toString() )
                9 -> BasicCard(list.get(number), suit, number.toString() )
                10 -> BasicCard(list.get(number), suit, number.toString() )
                11 -> BasicCard(list.get(number), suit, "J" )
                12 -> BasicCard(list.get(number), suit,"Q" )
                13 -> FlyingLightning(list.get(number), suit,"K" )
                else -> throw IllegalArgumentException("Invalid card.")
            }
        }
        else{
            println("number format wrong (create card)")
        }
        return card
    }
}
class HeartCardFactory2: CardFactory(){
    val list: MutableList<String> = mutableListOf("", "Raining Arrows" ,
        "Dodge" ,
        "Bumper Harvest" ,
        "Bumper Harvest" ,
        "Red Hare" ,
        "Acedia" ,
        "Something out of nothing" ,
        "Something out of nothing" ,
        "Something out of nothing" ,
        "Attack" ,
        "Something out of nothing" ,
        "Burning Bridges" ,
        "Dodge")
    val suit:String = "heart"
    override fun createRandomCard(number:Int):Card{
        lateinit var card:Card
        if(number in 1..13){
            card = when(number){
                1 -> RainingArrows(list.get(number), suit,"A" )
                2 -> BasicCard(list.get(number), suit, number.toString() )
                3 -> BumperHarvest(list.get(number), suit,number.toString() )
                4 -> BumperHarvest(list.get(number), suit,number.toString() )
                5 -> RedHare(list.get(number), suit,number.toString() )
                6 -> AcediaCard(list.get(number), suit,number.toString() )
                7 -> SomethingOutOfNothing(list.get(number), suit,number.toString() )
                8 -> SomethingOutOfNothing(list.get(number), suit,number.toString() )
                9 -> SomethingOutOfNothing(list.get(number), suit,number.toString() )
                10 -> BasicCard(list.get(number), suit, number.toString() )
                11 -> SomethingOutOfNothing(list.get(number), suit,"J")
                12 -> BurningBridges(list.get(number), suit,"Q" )
                13 -> BasicCard(list.get(number), suit, "K" )
                else -> throw IllegalArgumentException("Invalid card.")
            }
        }
        else{
            println("number format wrong (create card)")
        }
        return card
    }
}
class DiamondCardFactory: CardFactory(){
    val list: MutableList<String> = mutableListOf("", "Zhuge Crossbow" ,
        "Dodge" ,
        "Dodge" ,
        "Dodge" ,
        "Dodge" ,
        "Dodge" ,
        "Dodge" ,
        "Dodge" ,
        "Dodge" ,
        "Dodge" ,
        "Dodge" ,
        "Peach" ,
        "Attack")
    val suit:String = "diamond"
    override fun createRandomCard(number:Int):Card{
        lateinit var card:Card
        if(number in 1..13){
            card = when(number){
                1 -> ZhugeCrossbow(list.get(number), suit, "A")
                2 -> BasicCard(list.get(number), suit, number.toString() )
                3 -> BasicCard(list.get(number), suit, number.toString() )
                4 -> BasicCard(list.get(number), suit, number.toString() )
                5 -> BasicCard(list.get(number), suit, number.toString() )
                6 -> BasicCard(list.get(number), suit, number.toString() )
                7 -> BasicCard(list.get(number), suit, number.toString() )
                8 -> BasicCard(list.get(number), suit, number.toString() )
                9 -> BasicCard(list.get(number), suit, number.toString() )
                10 -> BasicCard(list.get(number), suit, number.toString() )
                11 -> BasicCard(list.get(number), suit, "J" )
                12 -> BasicCard(list.get(number), suit, "Q" )
                13 -> BasicCard(list.get(number), suit, "K" )
                else -> throw IllegalArgumentException("Invalid card.")
            }
        }
        else{
            println("number format wrong (create card)")
        }
        return card
    }
}
class DiamondCardFactory2: CardFactory(){
    val list: MutableList<String> = mutableListOf("", "Duel" ,
        "Dodge" ,
        "Stealing Sheep" ,
        "Stealing Sheep" ,
        "Rock Cleaving Axe" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Attack" ,
        "Dodge" ,
        "Sky Piercing Halberd" ,
        "Violet Stallion")
    val suit:String = "diamond"
    override fun createRandomCard(number:Int):Card{
        lateinit var card:Card
        if(number in 1..13){
            card = when(number){
                1 -> Duel(list.get(number), suit, "A")
                2 -> BasicCard(list.get(number), suit, number.toString() )
                3 -> StealingSheep(list.get(number), suit, number.toString() )
                4 -> StealingSheep(list.get(number), suit, number.toString() )
                5 -> RockCleavingAxe(list.get(number), suit, number.toString() )
                6 -> BasicCard(list.get(number), suit, number.toString() )
                7 -> BasicCard(list.get(number), suit, number.toString() )
                8 -> BasicCard(list.get(number), suit, number.toString() )
                9 -> BasicCard(list.get(number), suit, number.toString() )
                10 -> BasicCard(list.get(number), suit, number.toString() )
                11 -> BasicCard(list.get(number), suit, "J" )
                12 -> SkyPiercingHalberd(list.get(number), suit, "Q" )
                13 -> VioletStallion(list.get(number), suit, "K" )
                else -> throw IllegalArgumentException("Invalid card.")
            }
        }
        else{
            println("number format wrong (create card)")
        }
        return card
    }
}