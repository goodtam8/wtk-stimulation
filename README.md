# War of Three Kingdoms Feature Implementation

## War of Three Kingdoms Feature Implementation

## Introduction

The card game **Wars of the Three Kingdoms** is likely a
reference to a game inspired by the historical period
known as the Three Kingdoms era in China,
which lasted from 220 to 280 A.D. This period was
one of the most tumultuous and celebrated times
in Chinese history, characterized by the power struggle
among the states of Wei, Shu, and Wu following the fall
of the Han Dynasty. In the group project of this course,
We are required to implement this game to accurately
replicate the gameplay mechanics of the War of the
Three Kingdoms (WTK) card game. In this report,
I will introduce what I have done in the project and
explain how these functions work in the game of Wars
of the three kingdoms.This report will be
separated into two parts. The first part will
demonstrate the game rules and mechanics, involving classes
such as Generals and Players. And the second part will focus
on the interaction and gameplay of strategies that I implement
in the project.

## Part one:Game Rules and Mechanics:

## Wei General

## Cao Cao

Cao Cao is another lord general in the war of three kingdoms. And he has two skills for the game. One
is treachery, which is when a general deal damage to cao cao. Cao Cao can get the card that caused
damage to him. Another skill is entourage. Which is when cao cao needs to use dodge, he can ask the
wei kingdom character to give you a dodge card. For the entourage. I use **the chain of responsibility**
to help to implement these skills. And the code of the pattern will be:

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

It will check the wei general whether it has a dodge card. And this function will be called in the Cao
Cao’s being attack method:

override fun beingAttacked(general: General) {
val card = general.playAttackCard()
println("[Entourage] Cao Cao activates Lord Skill Entourage.")
if (entourage() == true) {

} else {
println("No one can dodge the attack hp-1")
// test
beingDamage( 1 , card, general)

}
fun entourage(): Boolean {

for (i in 0..weichain.size - 1 ) {
if (weichain.get(i).handleRequest() == true) {
beingkilled = weichain.get(i)
weichain.get(i).numOfCards -= 1
println(" ${weichain.get(i).name} helps Cao Cao dodged an attack by
spending a dodge card.")
return true

}
}
return false
}


In the being attacked function, it will check and call the entourage function in order to call the handle
request function. If another general has the dodge card. It will return true meaning it successfully dodge
the attack. If it is false cao cao will still get damaged by the attack. And for **treachery**. Cao Cao will
override the being damaged method. The override beingdamaged method of Cao Cao will be:

override fun beingDamage(num: Int, card: Card, general: General) {
super.beingDamage(num, card, general)
if(currentHP <= 0 ){
return
}
// damage not comes from XiaHouDun
if(!card.name.equals("None")) {
println("[Treachery] CaoCao activate treachery skill.")
handCard.add(card)
println("${card.suit}")
}

}

The parameter of the card will add to the handcard of CaoCao. The sample output of **treachery** and
**entourage** will be the following:



## Shu General

## Liu Bei

In Liu Bei, he has two skills for the game. One is **benevolence** , which mean that Liu Bei can give out 1
or more cards to other in the play phase.If Liu Bei give out two cards. He can restore one hp. For this
skills, I have use a **state design pattern** for this skills. Because we have to monitor the health status of
Liu Bei to decide whether he will use the benevolence skills.Here is the code example of the
benevolence :

for(i in 0 .. (maxHP - currentHP)- 1 ){
if(hasPeachCard())
playPeachCard()
}
println("$name has ${this.handCard.size} cards.")
if (handCard.size >= 2 && currentHP <= 2 ) {
// find loyalist
var num = - 1
for (i in 0..Generalmanager.list.size - 1 ) {
if(!Generalmanager.list.get(i).player.equals(this)) {
if (Generalmanager.list.get(i).player is Loyalist) {
num = i
} else {
num = Random.nextInt( 0 , Generalmanager.list.size - 1 )
}
}
}
if(num == 0 || num == - 1 ){
println("benevolence picks no one.")
return
}
Generalmanager.list.get(num).handCard.add(this.handCard.removeFirst())
Generalmanager.list.get(num).handCard.add(this.handCard.removeFirst())

this.currentHP += 1
println("[Benevolence] Liu Bei gives away two cards and recovers 1 HP, now
his HP is ${this.currentHP}.")}

For this code, Liu Bei will first check whether it has a peach card in hand. If he has the peach card he
will use the peach card first. If he does not have a peace card, it will go to the following steps. To check
whether he has fulfilled the condition of benevolence by


checking his handcard size and his current hp is how many. If he fulfills the condition, Liu Bei will find
the loyalist and pass the card to them in order to restore the hp. If it cannot find loyalists the
benevolence will fail. And here are a code example of **state design pattern** :

override fun playNextCard() {
println("Liu Bei is in unhealthy state. ")
println("His hand card number: " +strategy.general.handCard.size)
if(strategy.general.handCard.size >= 2 ) {
(strategy.general as LiuBei).benevolence()
}}

In this code, it explains that if Liu bei is in an unhealthy state. It will trigger the function above.Another
skill that Liu Bei has is aggression.Another approach is that we develop a single function to check its
state here is the code it is like the code above. Here is the code:

fun benevolence() {

for(i in 0 .. (maxHP - currentHP)- 1 ){
if(hasPeachCard())
playPeachCard()
}
println("$name has ${this.handCard.size} cards.")
//check whether the condition of benevolence have fulfilled
if (handCard.size >= 2 && currentHP <= 2 ) {
// find loyalist
var num = - 1
for (i in 0..Generalmanager.list.size - 1 ) {
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
if(num == 0 || num == - 1 ){
println("benevolence picks no one.")
return
}


Generalmanager.list.get(num).handCard.add(this.handCard.removeFirst())
Generalmanager.list.get(num).handCard.add(this.handCard.removeFirst())

this.currentHP += 1
println("[Benevolence] Liu Bei gives away two cards and recovers 1 HP, now
his HP is ${this.currentHP}.")

}

#### }

#### }

We opt to use this approach to use this to monitor the health. And the code logic is like the above.

**Aggression** means that Liu Bei needs an attack card to attack othergenerals. He can ask another
general from the same kingdom to give it to you. To implement this skill. I have used the **chain of
responsibility pattern** to demonstrate these skills. Since it is a perfect design pattern to do the request
processing. And this skill involves requestprocessing. And here is the codeexample of **the chain of
responsibility method** :

abstract class ShuGeneral (name:String, player:Player):General(name, player){

var nextHandler:ShuGeneral ?= null

fun handleRequest():Boolean{
if(hasAttackCard()==true){
println("$name have attack card")
return true
}
println("$name don't have attack card")
return false

}

#### }

In this class. I created a class of **Shu generals** to handle the request of aggression. In the method of
handling requests.It will check if the Shu general has the _attackcard_. If the general has the
_attackcard_ it will return true. Otherwise, it will return false. If the general have _attackcard_. Liu Bei


will use its attack card to attack people in his own aggression method. His **aggression** method is like
this:

if (general is LiuBei)
if (general.hasAttackCard() == false) {
for (i in 0..general.shuchain.size- 1 ) {
var shu = general.shuchain.get(i).handleRequest()
if (shu == true) {
var tar = locktarget()
if (tar == 87 ) {
println("no general in the range")
general.attackedState = true
return
}

Generalmanager.list.get(tar).beingAttacked(general.shuchain.get(i))
general.attackedState = true
return

}
}
}

}

With this code Liu Bei will check whether he has the attack card for the attack.If he does not have the
attack card, he can call the handlerequest to ask for an attack card from another general. And use it to
attack others. And here are the sample output of Liu Bei using the benevolence and aggression:



## Zhao Yun

Zhao Yun is a normal general in the game. And his skills are Bravery. Which mean zhao yun can use
attack as dodge or dodge as attack. In this case. I have make a function of bravery attack to deal with
this skill

override fun Braveryattack() {
if (general is ZhaoYun) {//有閃牌先出殺閃
if (general.hasDodgeCardCheckAll() == true) {
target = locktarget()
if (target == 87 ) {
println("no general in the range")
general.attackedState = true
return
}

Generalmanager.list.get(target).zhaoyunattackppl(general)
println("[Bravery] Zhao Yun activate the bravery skill. Using dodge as


attack")
general.attackedState = true
} else if (general.hasAttackCard() == true) {
target = locktarget()
if (target == 87 ) {
println("no general in the range")
general.attackedState = true
return
}

Generalmanager.list.get(target).beingAttacked(general)
println("[Bravery] Zhao Yun activate the bravery skill. Using dodge as
attack")
general.attackedState = true

}

#### }

#### }

For these skills, Zhao Yun will check whether he has a dodge card. If he has a dodge card. It will directly
use the dodge card to activate the bravery attack. And it will call the zhao yun specific attack:

open fun zhaoyunattackppl(general: General) {
println("$name being attacked")

val card = general.playDodgeCard()//攻擊人出殺
var dodge = this.hasDodgeCardCheckAll()

if (dodge == true) {
playDodgeCard()
} else
beingDamage(general.attackDamage, card, general)
}

Zhao Yun uses the dodge as an attack. And if he is being attacked he will override the beingattacked
method. Here is the code snippets:


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

He will see whether he has an attack card in hand. If Zhao Yun has the attack card. He will use the
attack to dodge and not get damage. And here is the sample output of Zhao Yun **bravery** attack:


## Zhuge Liang

Zhuge Liang has two skills, one is **stargazing** , another skill is **empty city strategy**. For the **stargazing**
skill. It can look at the number of cards from the deck based on the number of players in the game. And
place the card on the bottom of the deck. And for the **empty city strategy**. If Zhuge Liang does not
have cards in hand. he cannot choose as a target of duel. And Here is the sample code of **stargazing**
skills:

println("[Stargazing]ZhugeLiang activate Stargazing skills. He can now watch
the carddeck")
var starStore: MutableList<Card> = mutableListOf()
var counter= 0
while(counter<=Generalmanager.list.size-
1 &&counter<=Generalmanager.cardList.size- 1 ) {
println(Generalmanager.cardList.get(counter).name)
var temp = Generalmanager.cardList.get(counter)
Generalmanager.cardList.removeAt(counter)
Generalmanager.cardList.add(temp)
counter++
}

super.Preparationphase()


This means Zhuge Liang can watch the card deck and know the content of each card using a loop. After
watching the card,it will go back to the normal preparation phase. And for the **empty city strategy**.
Here is the code snippet of it

while (Generalmanager.list.get(target) is ZhugeLiang &&
Generalmanager.list.get(target).handCard.size == 0 && counter < 3 ) {
target = locktarget()
counter++

if (counter >= 3 ) {
println("[Empty City strategy activate] Zhuge Liang cannot pick as
an target")
return
}
if (target == 87 ) {
println("no general in the range")
general.attackedState = true
return
}

It will check if the target is zhuge Liang and its handcard is 0 or not. If it is fulfilled the condition. It will
reset the attack range. But if the range only has zhuge liang. It will return the function. Here is the
sample output of these skills:



## Zhang Fei

Zhang Fei has the skills of **berserk**. Which means zhang fei can use any number of attack cards to
attack generals. And I have make a new attack function for zhang Fei attack:

target = locktarget()
if (target == 87 )
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

In this attack Zhang Fei will call the berserk method to count how many times of attack he can make in
a single attack. After counting the time. I use a while loop to run the attack. And why set the counter
again because. Some generals such as _Sima Yi_ and _Xiahou Dun_. They have a skill of taking away other
people card. It may cause a error in it so it has to reset the counter. And the Berserk method is like this:

fun Beresk(): Int {
var count = 0
for (i in 0..handCard.size- 1 ) {
if (handCard.get(i).name.equals("Attack"))
count++
}
return count
}

It will count how many attack cards Zhang Fei has in the class. And it will return to the strategy method.
And the sample output of this function is like this:


## Guan Yu

Guan Yu has the skills of **God of war**. In this skill, Guan Yu can use any red suit card as an attack. To
implement this function, I override the hasattackcard in Guan Yu to implement this function. The Guan
Yu has attackcard is like this

override fun hasAttackCard(): Boolean {
if (super.hasAttackCard() == false) {
return hasredsuitcard()
} else {
return true

}
}

fun hasredsuitcard(): Boolean {
println("[God of War] Guan Yu activate god of war skills")
for (i in 0..handCard.size - 1 ) {

if (handCard.get(i).suit.equals("diamond") ||
handCard.get(i).suit.equals("heart")) {

return true
}


#### }

return false
}

override fun playAttackCard(): Card {
lateinit var card: Card
if (hasredsuitcard() == true) {
for (i in 0..handCard.size - 1 ) {
println("I use attack card")
if (handCard.get(i).suit.equals("diamond") ||
handCard.get(i).suit.equals("heart")) {
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

Guan Yu will first check whether he has a **red suit card** in hand. If he has a **red suit card** in hand. He
will use his **red suit card** as the attack first. If he does not has any red attack card. He will play the
normal attack card. For the **red suit card** checking, I use a function to check his handcard whether it
has **red suit** such as diamond and heart. If it has, it will return true,otherwise return false. And the
general will use the result of this function to play out the red suit card. And this is the sample output of
these skills.

## Wu General


## Zhou Yu

Zhou Yu is a general from Wu kingdom. And he has two skills for this game. They are **heroism** and **sow
discord**. Slow discord means that Zhou Yu in the play phase, he can choose a target character to pick a
suit. Targeted character will then pick and reveal a card obtained from his hand. If the card revealed is of
a different suit from the chosen one, he will deal a 1 point damage to that general. Target will keep the
cards of his hand regardless of his result. And the **heroism** means that Zhou Yu can draw more cards
from the draw phase. And to implement the **heroism**. I override the draw phase to draw 3 cards in that
phase. Here is the code snippet of that phase:

override fun DrawPhrase() {
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

#### }

And for the sow discord I made a sow discord function for the judgement. Here is the code:

fun sow(general: General) {
if (handCard.size > 0 ) {
println("[Sow Discord] Zhou activate sow discord. ${general.name} need to
pick a suit ")
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
beingDamage( 1 ,zhouran,general)
println("The suit is different")
println("${general.currentHP}")

} else {


println("The suit is same")
}
}
}

In this function it will accept the target general as the parameter. There will be a String list to store the
suit of cards. And the player will randomly pick a suit using a random function. And Zhou Yu will also
randomly pick a card. And comparing the two suits of these cards. If the suit is not the same, the
general will directly deduct one hp by the being damage function. Otherwise nothing will happen. But
the card that Zhou holds will be given to the target general. Here is the sample output of **sow discord**

## Lv Meng

Lv Meng has the skills of **self control**. In this skill, Lv Meng can skip the discard phase if he does not
use any attack function in it.To implement this function I have override the discard phase of Lv Meng.
And add a property to Lv Meng which is a boolean variable. To see whether he has /has not used any
attack in **self control**.
In the strategy, I made a function which is selfcontrol to check if he has or has not attacked. If he does
not attack, self control will turn true. And the discard phase will skip and reset back the self control to
false. Here is the code


fun lvmeng() {
if (general is LvMeng&&general.attackedState==false ) {
general.selfcontrol = true
}
}

override fun DiscardPhase() {
if (selfcontrol == true) {
selfcontrol = false
println("[Self control] Lv Meng activate his self-control skill. Skip
discard phase")
} else {
super.DiscardPhase()
}

}

And the sample code of this skill will be like this:


## Lu Xun

Lu Xun has two skills in the game. One is Humility, another is coalition. For humility it means that
Lu Xun cannot pick a target of acedia and steal. And for the coalition means that Lu Xun can draw a
card if he loses the last hand card.So for humility. I add a checking condition for the target general it is
Lu Xun like the following code:

if ((this.name.equals("Acedia") && Generalmanager.list.get(i).name.equals("Lu Xun")))
println("Humidility activate Lu Xun can't pick as the
target of acedia")
else
store.add(Generalmanager.list.get(i))

If the target is Lu Xun it will not add to the target list to execute the spell. And for the coalition the code
will be like this:


fun Coalition(){
if (handCard.size== 0 ){
println("Coalition activate. Lu Xun draw a card from deck")
drawCards()
println("${handCard.size}")
}

}

It will check his handcard. If the general does not have any hand card it will draw a card to activate the
skills. Here is the sample output of the two skills:


## Gan Ning

Gan Ning has the skills of **ambushment**. For these skills, he can use only black suit cards to play as the
burning bridge card. And for this strategy, I create a function of **ambushment** to check whether the
general has the black suit card. If the general has the black suit card. It will play out as burning bridge
card like the code below

fun ambushment() {
if (checkblacksuitcard() == true) {
println("Ambushment activated. GanNing can use a black suit card to
activate the skills")


playblacksuitcard()
BurningBridges("BurningBridge", "spade", "09").spellExecute(this)

#### }

#### }

#### }

And in the check blacksuit card function. It will check whether the general has the spade or club suit. If
the general has these two suits. It will return **true** to let the **ambushment** play burning bridges,
otherwise it will return **false**. Here is the code:

fun playblacksuitcard() {
for (i in 0..handCard.size) {
if (handCard.get(i).suit.equals("spade") ||
handCard.get(i).suit.equals("club")) {
handCard.remove(handCard.get(i))
return
}

}
}

And here is the sample output:


## Huan Gai

Huan Gai has a skill of **sacrifice** in the game. He can choose to lose 1-point health in order to draw two
cards during the play phase. To implement this function, I have to use the **state design pattern** to help
me implement this. Because in normal situations these skills will not activate when Huan Gai is in an
unhealthy state. So I made a code to monitor its situation in below:

class HuanGaiHealthyState(var strategy: Strategy) : State {
// var b = strategy

override fun playNextCard() {
if (strategy.general.currentHP <= 2 && strategy is HuanGaiLoyalistStrategy) {
(strategy as HuanGaiLoyalistStrategy).state =
HuanGaiUnHealthyState(strategy)
println("Huan Gai is not healthy")
return
} else if (strategy.general.currentHP <= 2 && strategy is


HuanGaiRebelStrategy) {
(strategy as HuanGaiRebelStrategy).state = HuanGaiUnHealthyState(strategy)
println("Huan Gai is not healthy")
return
}
(strategy.general as HuanGai).sacrifie()
}
}

class HuanGaiUnHealthyState(var strategy: Strategy) : State {
var b = strategy
override fun playNextCard() {
if (strategy.general.currentHP > 2 && strategy is HuanGaiLoyalistStrategy) {
(strategy as HuanGaiLoyalistStrategy).state =
HuanGaiHealthyState(strategy)
println("Huan Gai is healthy")
return
} else if (strategy.general.currentHP > 2 && strategy is HuanGaiRebelStrategy)
{
(strategy as HuanGaiRebelStrategy).state = HuanGaiHealthyState(strategy)
println("Huan Gai is healthy")
return
}
}
}

In the beginning, Huan Gai will be first in the healthy state. And Each time he plays the card, it will
check whether he is in a Healthy state or unhealthy state. If Huan Gai's current hp is more than 2. It
will trigger the sacrifice skill to draw cards. If his current hp is not more than 2 and it is in a healthy state.
It will turn into an unhealthy state. In an unhealthy state. It will keep checking the currenthp to see
whether he has a chance to go back to healthy state. And the function **sacifice** code is in the below :

fun sacrifie() {
println("$name: [Sacrifie] skill activate deduct hp to draw 2 cards")
draw2Cards()
currentHP -= 1
if(currentHP<= 0 ){
askForPeach()

}
println("$name current hp is $currentHP.")


#### }

#### }

And here is the sample output

And we have develop another way to deal with the healthy state by direct check his health in special
skills. Here is the example. And we choose this as our approach. Since I want to keep the code
consistency.Here is the sample:

fun sacrifie() {
if (currentHP > 2 ) {
println("$name: [Sacrifie] skill activate deduct hp to draw 2 cards")
draw2Cards()
currentHP -= 1
if (currentHP <= 0 ) {
askForPeach()

}
println("$name current hp is $currentHP.")
}
}

}


## Da Qiao

Da Qiao has two skills in the game. One is **national beauty** , another skill is **deflection**. For **national
beauty**. It means that da Qiao can use a diamond suit card in her hand as acedia card. And for the
deflection. When an attack is dealt on da qiao. Da Qiao can redirect the attack to others in her attack
range except the character who dealt the attack. To implement **national beauty**. The method is the
same as Gan Ning which I have mentioned before. And for **national beauty**. I have to override the
beingattacked function in da qiao to redirect the attack to others. And the code is in the below

fun delflectivefindtarget(){
println("Da Qiao is finding a deflective target. Now her current hp is
$currentHP")
// reset the range
// horseRiding for MaChao is 1, for others is 0
if (equipment != null) {
attackRange = (equipment!!.attakRange + horseRiding)
} // reset to 1
else
attackRange = ( 1 + horseRiding)
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
if(reflect== 87 ){
super.beingAttacked(general)
}
else{
reflectattack(Generalmanager.list.get(reflect),general)
}

#### }

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

In this code when there is a general attack da qiao. Da Qiao will run the procedure in the preparation
phase again to find whether there is a target in the range to allow her to deflect the attack. After


gathering the range. It will verify which target can be chosen using the locktarget(general) function.
And If there is a target can be locked. Da Qiao will deflect the attack to him in reflection. In the reflect
attack function it is basically like the being attacked function but with small modification. If there is no
target it can be deflected. It will run the superclass being attacked. And here is the sample output of the
two skill:


## Netural General

## Yuan Shu

Yuan Shu is a netural lord in the game and he has two skills in the game.
One is Treason. He can draw a card during the Preparation phase. But after he draw a card from the
prepartion phase. He has to discard one more card in the discard phase which mean his card limit can
hold in his handcard will be -1. Another skills is the taunt which is a passive skills. It mean that if Yuan
Shu have more card than this current hp. He will force to be the target of all general regardless of the
identity. It can be happened when other generals give him a card or a spell activate giving card to him.
To implement the skill of Treason. We can look the following code:

class YuanShu (name: String, var abc: Player):NeutralGeneral(name,abc){
override var maxHP: Int = 0
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
if (handCard.size > currentHP+ 1 ) {
store = handCard.size - currentHP+ 1
}
println("Yuan Shu have draw a card before. So it trigger the negative impact


of treason. He now have to remove $store cards ")

if (currentHP > 0 ) {
for (i in 0..store - 1 ) {
handCard.removeAt( 0 )
}
}

println("$name discards ${store} card(s), now has ${handCard.size} card(s). ")
}
else
super.DiscardPhase()
}
}

In the code there is a boolean of treason. It is for checking whether Yuan Shu has implementating the
treason skills in the prepartion phase. If it is implemented, it will be true to notify Yuan Shu has use his
skill. After that in the discard phase Yuan Shu will check whether he has use the skills before. If he use it
before his card limit in this round will be -1. And he will discard one of card if he exceed the limit,
otherwise it will go to the normal discard phase.
For the taunt skill, we can see the code in the below:

for (i in 0..general.inRange.size - 1 ) {
if(general.inRange.get(i) is YuanShu)
{

if(general.inRange.get(i).handCard.size>general.inRange.get(i).currentHP){
println("Taunt activate! Yuan Shu become the target of all the
general")
for (j in 0..Generalmanager.list.size - 1 ) {
if (Generalmanager.list.get(j).equals(general.inRange.get(i)))
return j
}

}
}
}

In the code I will first add a checking before I go to the normal locking operation, I will check whether the
inrange have the Yuan Shu. If it has Yuan Shu, I will further processs to check whether he has exceed


the card limit. If it has he will be chosen as the target. Here is the code example:

for (i in 0..general.inRange.size - 1 ) {
if(general.inRange.get(i) is YuanShu)
{

if(general.inRange.get(i).handCard.size>general.inRange.get(i).currentHP){
println("Taunt activate! Yuan Shu become the target of all the
general")
for (j in 0..Generalmanager.list.size - 1 ) {
if (Generalmanager.list.get(j).equals(general.inRange.get(i)))
return j
}

}
}
}

Here are the sample output of his two skills activate:


## Diao Chan


Diao Chan is a neutral general and she has two skills in the game. One is **alienation** , she can discard 1
card during play phase and then select two male characters to duel each other. And this cannot be
dispelled by negation. Another skill is **beauty outshining the moon**. It means that Diao Chan can draw
a card at the final phase.
For the **outshining the moon**. I override the final phase of diao chan to let her draw one more card
during the phase. And here is the code:

override fun FinalPhrase() {
if (Generalmanager.cardList.isEmpty())
Generalmanager.createAllCard()
handCard.add(Generalmanager.cardList.removeFirst())
println("[Beauty outshining the moon] $name now has ${handCard.size} card(s)")
}

And for the **alienation**. I made a diao chan strategy to play this function. Below is the code snippet

var counter = 0
var firstmale: General? = null
var secondmale: General? = null
for (i in 0..Generalmanager.list.size - 1 ) {
if (Generalmanager.list.get(i).gender == false && counter == 0 ) {
counter++
firstmale = Generalmanager.list.get(i)

}
if (Generalmanager.list.get(i).gender == false && counter == 1 ) {
counter++
secondmale = Generalmanager.list.get(i)

}

}
if (firstmale != null && secondmale != null) {
var discard = a.handCard.random()
a.handCard.remove(discard)

println("[Alienation]Diao Chan activate her alienation skills for force a
duel")
var duelcard = Duel("Duel", "99", "99")
if (firstmale != secondmale) {
duelcard.spellExecute(firstmale, secondmale, false)


#### }

#### }

At the beginning, Diao Chan will find out whether there are two male characters in the game. Gender is
**false** meaning it is a male character otherwise it is a female character. If there are two characters in the
game, Diao Chan will discard one card to force a duel between two male characters if she has more
than 1 card. And I use a specific duel which cannot be canceled by negation. Here is the sample output
of the class:

## Lv Bu

Lv Bu has a skill that is **unrivaled**. Which means that if Lv Bu uses an attack on a target character.
Target has to play 2 dodge cards to offset his attack. Any character engaged in a duel with Lv Bu will
have to play 2 attack cards each time instead.To implement this skill. I made a specific lvbu attack in
lvbu class to let the target play two dodge cards. Here is the code snippet:


fun Unrival(target: General){
var b=hasAttackCard()
if(b==false)
return
var dodge2=target.has2DodgeCard()
var card=playAttackCard()
if(dodge2==true){
println("${target.name}spend two dodge card to dodge the attack from
lvbu")
target.play2DodgeCard()
}else{
println("${target.name}do not have two dodge card to dodge the attack from
lvbu")

target.beingDamage( 1 ,card ,target)
}
}

It is like the beingattack function. But the hasdodgecard have change to has2dodgecard to see whether
the target has two dodgecard. And for the duel card implementation, we can see the function header

fun diaochanduel(general: General, opponent: General, bu: Boolean)

in the third parameter bu, it means that the duel consists of lv bu in it. If it has lv bu in it it will run the
second part of the program:

while (bu == true) {
var genatt = general.hasAttackCard()

var opp = opponent.has2AttackCard()
if (genatt == false) {

general.beingDamage( 1 , card, opponent)
return
} else if (opp == false) {
// opponent.currentHP--
// if(opponent.currentHP<=0)
// opponent.askForPeach()
general.beingDamage( 1 , card, general)
return
}


if (genatt == true) {
general.playAttackCard()
}
if (opp == true) {
opponent.play2AttackCard()
}

}

And I will explain the dual logic in the card function part. And here is the sample output

## Spell Card


## Somthing out of nothing

This card means that the general can **draw two more cards from the deck**. Here is the sample code:

override fun spellExecute(general: General) {
removeSpellCard(general)
if (checkNegation(general))
return

general.draw2Cards()
}

In this function, the general will call the draw2 cards to draw 2 more cards from the deck. Here is the
sample output.

## Brotherhood

In the BrotherHood card function, it means that **all generals can restore one hp** in the game. Here is
the code snippet:

removeSpellCard(general)
if (checkNegation(general))
return

for (i in 0..Generalmanager.list.size - 1 ) {


println("${Generalmanager.list.get(i).name} orign have
${Generalmanager.list.get(i).currentHP} health points")

if (Generalmanager.list.get(i).currentHP > 0 &&
Generalmanager.list.get(i).currentHP < Generalmanager.list.get(i).maxHP) {
Generalmanager.list.get(i).currentHP++
println("${Generalmanager.list.get(i).name} now have
${Generalmanager.list.get(i).currentHP} health points")

}

}

In this function the general will run all over the list and each general will add his hp in the game. Here is
the sample output:

## Duel

In the duel card. Two generals in the duel have to **pay their attack card to each other**. If someone
does not have the ability to pay the attack card it will receive one point damage. Here is the code
snippet:


while (bu == false) {
var genatt = general.hasAttackCard()

var opp = opponent.hasAttackCard()
if (genatt == false) {
//general.currentHP--
println("${general.name} has no attack card for duel")
// if (general.currentHP <= 0)
// general.askForPeach()
general.beingDamage( 1 , card, opponent)

return
} else if (opp == false) {
println("${opponent.name} has no attack card for duel")

// opponent.currentHP--
// if(opponent.currentHP<=0)
// opponent.askForPeach()
general.beingDamage( 1 , card, general)
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

I use a while loop to let each general keep playing their attack card during duels. The condition of the
while loop ends when one general cannot play out the duel card. His current hp will be deduct one and
get out the loop to finish the action. And Here is the output of duel:


## Bumper Harvest

Bumper Harvest means that the general who uses that card can **watch the deck** and then he **picks a
card from the card he has watched**. And **giving the remaining card to other characters**. Here is the
code snippet of it

var counter = 0

while (counter <= Generalmanager.list.size - 1 && counter <=
Generalmanager.cardList.size - 1 ) {
println(Generalmanager.cardList.get(counter).name)
counter++
}

if (Generalmanager.cardList.isEmpty())
Generalmanager.createAllCard()
general.handCard.add(Generalmanager.cardList.get( 0 ))
Generalmanager.cardList.removeFirst()
println("${general.name}pick the first card")

if (Generalmanager.cardList.size == 0 ) {


println("The deck already go out of card")
return
}
var counter2 = 0
while (counter2 <= Generalmanager.list.size - 1 && counter2 <=
Generalmanager.cardList.size - 1 ) {
if (!general.equals(Generalmanager.list.get(counter2))) {
if (Generalmanager.cardList.size == 0 ) {
Generalmanager.createAllCard()
}
var temp = Generalmanager.cardList.get(counter2)
println("${Generalmanager.list.get(counter2).name} get the card
${temp.name}")
Generalmanager.list.get(counter2).handCard.add(temp)
Generalmanager.cardList.remove(temp)
}
counter2++

}
println("end")

I use the while loop to check the condition of whether the program has read the number of cards based
on the number of generals on the field. And there is a situation when the round increases. There can be
a chance that the number of generals is more than the number of cards on the deck. So I also added a
condition to check it. If this situation happens I will create another deck for the game to avoid errors.
After picking the card of the general. Other generals will get the remaining cards. Here are the sample
output of bumper harvest:

General Liu Bei created.
General Huan Gai created.
General Lv Bu created.
(spell) Lv Bu play bumper card.
Bumper Harvest activate
Attack
Shadowrunner
Raining Arrows
Lv Bupick the first card
Liu Bei get the card Shadowrunner
Huan Gai get the card Dodge
end


## Barbarian Invasion

**Barbarian invasion** means that the **whole game in general except the player who uses this card
has to use attack**. If the general cannot use the attack card at that time. His current hp will be
deducted. Here is the code implementation of barbarian invasion:

val card = removeSpellCard(general)
// if (checkNegation(general))
// return
var counter = 0
var size = Generalmanager.list.size

while (counter <= size - 1 ) {// bug 要轉while loop
if (!Generalmanager.list.get(counter).equals(general)) {

var b = Generalmanager.list.get(counter).hasAttackCard()

var hasNeg = false
// check negation in hand
for (j in 0..Generalmanager.list.get(counter).handCard.size - 1 ) {
if (Generalmanager.list.get(counter).handCard.get(j) is Negation)
{
(Generalmanager.list.get(counter).handCard.get(j) as
Negation).spellExecute(Generalmanager.list.get(counter))
println("${Generalmanager.list.get(counter).name} play
negation to get rid of $name")
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
// if (Generalmanager.list.get(counter) is CaoCao) {
// println("[Treachery] CaoCao activate treachery skill. Gain
the $name")
// Generalmanager.list.get(counter).handCard.add(this)
// }
println("${Generalmanager.list.get(counter).name} does not have an


attack card to avoid the barbarian invasion")
// Generalmanager.list.get(counter).currentHP--
// if (Generalmanager.list.get(counter).currentHP <= 0)
// Generalmanager.list.get(counter).askForPeach()

Generalmanager.list.get(counter).beingDamage( 1 , card, general)
//check general is removed or not. if removed counter -1 to get
the next general
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

In this code I will check whether the general has the negation first. If he has negation, the general will
not get damaged by this barbarian invasion. After that I will check whether the general has the attack
card. If they have an attack card they have to play the attack card out, otherwise,current hp deducted.
And I have to update the size in the while loop. Since there can be a situation after damage by the
barbarian invasion. The current hp of general reach to 0. So the list size update. But the while loop can
not detect it and cause errors. So it has to be updated. Here is the sample output of **barbarian
invasion** :


## Raining arrow

Raining arrows have the same logic of barbarian invasion. The difference of this card is that the player
has to play out **dodge** to avoid hp being deducted. And here is the output of raining arrow:


## FerghanaHorse,VioletStallion,RedHare

These 3 cards have the effect of **reducing** the player and other player distance by 1. So that it can help
them to attack some opponent having a longer distance. When did this card take effect? It will take
effect on the preparation phase and setting attack range for the general to attack. Here is the code
implementation:

if (mountDec != null) {
attackRange += 1 + horseRiding
}

which is mean that the general can attack a further general during the attack by setting the attack range
here is a example output of demonstration:


## ShadowRunner,HexMark,Flying Lightning

These 3 pieces of equipment are the opposite of what I mentioned above. It means that the distance of
the player and other generals will be **increased** using the above equipment. So that other generals are
not easy for other generals to attack them. To implement this effect. it will do the setting on the attack
range like the below code:

if (!(Generalmanager.list.get((num -
leftNum).mod(size)).equals(Generalmanager.list.get(num)))) {
if (Generalmanager.list.get((num - leftNum).mod(size)).mountInc !=
null) {
leftCount++
}


if the general has the above equipment the distance will be increased. Here is the demonstration of
these card:

We can see that in origin, Zhuge Liang is in the attack range of Huan Gai, but after he is equipped with
the horse, he is out of Huan Gai range. Since the Horse increased the distance between Zhuge and
Huan Gai.

## Weapon Card

### Zhuge CrossBow

Zhuge CrossBow is one of the weapon cards in the game. It means that the general can **keep
attacking if they have an attack card**. So the implementation logic is same as Zhang Fei Berserk.
Here is the sample output:


### Green Dragon Blade

Green Dragon allow the general to **attack the target again when he use dodge card to dodge the
attack until his hp is deducted.** And here is the code implementation of this attack:

var orignhp = Generalmanager.list.get(target).currentHP
Generalmanager.list.get(target).beingAttacked(general)
var currenthp = Generalmanager.list.get(target).currentHP
if (orignhp == currenthp) {
println("Green dragon blade activate ")
var size = Generalmanager.list.size
while (general.hasAttackCard() == true && orignhp == currenthp && size


<= Generalmanager.list.size - 1 ) {
Generalmanager.list.get(target).beingAttacked(general)
currenthp = Generalmanager.list.get(target).currentHP

}

In the code, you can see that I use variables to store the target hp before it starts the attack. And after
finishing the attack operation. I will **compare the current hp and origin hp.** If the result is the same. It
means that the target uses the dodge card to dodge the attack. So it will enter the while loop and keep
attacking until he suffers damage. Here is the sample output:

### Kirin Bow

Kirin Bow allows the general to **detach the equipment** the target has during the attack. And here is the
function code of kirin bow:


fun kirinbowattack() {
if (a.hasAttackCard() == true) {
target = locktarget()
if (target == 87 ) {
println("no general in the range")
general.attackedState = true
return
}

var counter = 0
while (Generalmanager.list.get(target) is ZhugeLiang &&
Generalmanager.list.get(target).handCard.size == 0 && counter < 3 ) {
target = locktarget()
counter++
println("[Empty City strategy activate] Zhuge Liang cannot pick as an
target")
}
if (counter >= 3 ) {
println("[Empty City strategy activate] Zhuge Liang cannot pick as an
target")
return
}
if (Generalmanager.list.get(target).mountDec != null)
Generalmanager.list.get(target).removePlacementCard("mountDec")
else if (Generalmanager.list.get(target).mountInc != null)
Generalmanager.list.get(target).removePlacementCard("mountInc")
Generalmanager.list.get(target).beingAttacked(general)
Generalmanager.list.get(target).beingAttacked(general)

general.attackedState = true
}

}

In the above function, the weapon will check whether the target has any equipment. If they have
equipment, I will call the **remove placement card** to remove their equipment. And Here is the output of
this weapon:


We can see the placement is removed by the kirin bow.

### Blue Steel Blade

Blue steel blades mean that the general can **attack ignoring his armor**. So if the target has the armor
of EightTrigramsFormation, he cannot execute the spell of this armor to dodge the attack. So the code
of blue steel blade will be like this:

open fun BlueSteelBladeattack(general: General) {
println("$name being attacked")
val card = general.playAttackCard()
if (this.hasDodgeCard()) {
playDodgeCard()
} else
beingDamage(general.attackDamage, card, general)
}


This code will check if the general has a dodgecard but not check whether he has the armor to dodge
the attack. If he has a dodge card he will dodge the attack otherwise hp deducted. This is the
demonstration of the output :

### SkyPierching

Sky Pierching mean that the general can **attack two more target if he only have one card**. And here
is the code implementation:

fun skypiercingattack(general: General) {
var c = general.playAttackCard()
var counter = 0
var size = general.inRange.size

while (counter <= size - 1 &&size<= 3 ) {
general.inRange.get(counter).beingAttacked(c, general)
counter++
size = general.inRange.size
}


#### }

In the function, it will **attack two more generals.** And the checking handcard size will be do on this
function

if (general.hasAttackCard() == true && general.handCard.size == 1 ) {
var target = locktarget()
if (target == 87 ) {
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

If the general has more than one handcard. He will play the normal attack instead of the weapon attack.
Here is the sample output:


You can see that the Lv Bu plays one attack card; both Liu Bei and Huan Gai need to accept the
attack. This is the ability of the weapon.

### Serpent Spear

Serpent Spear allow the general to **withdraw two of their handcard as attack card** if they do not have
attack card on hand. Here is the code:

fun spear() {
if (general.handCard.size >= 2 ) {

var target = locktarget()
Generalmanager.list.get(target).spearattack(general)
//put it into spear equipment

} else {
normalattack()
}
}


The code will check if the general has 2 cards or more in their handcard. If they have more than 2, they
will activate the spear attack, otherwise go to the normal attack.
And the spear attack will be like this:

var attacked: MutableList<Card> = mutableListOf()

var card1 = general.handCard.random()
general.handCard.remove(card1)
var card2 = general.handCard.random()
general.handCard.remove(card2)
attacked.add(card1)
attacked.add(card2)

println("Spear attack! ${name} get deducted one hp")
beingDamage( 1 , attacked, this)

It will remove the general handcard and use it as an attack. And here is the sample output:

### Rock Cleaving Axe

Rock Cleaving Axe allows the general to **withdraw two cards to deduct the target hp if the target
uses dodge**. And here is the code implementation:

var orignhp = Generalmanager.list.get(target).currentHP
var currenthp = 0
Generalmanager.list.get(target)


.beingAttacked(general)//bug reason:the target is the last index of
list and it has one hp
if (target >= Generalmanager.list.size)
currenthp = 0
else
currenthp =
Generalmanager.list.get(target).currentHP
if (orignhp == currenthp && general.handCard.size >= 2 ) {
var ran1 = general.handCard.random()
general.handCard.remove(ran1)
var ran2 = general.handCard.random()
general.handCard.remove(ran2)
println("Equipment rockcleave axe activate. Hp deuct")
Generalmanager.list.get(target).currentHP--
if (Generalmanager.list.get(target).currentHP <= 0 )
Generalmanager.list.get(target).askForPeach()
}

general.attackedState = true
}

In this code, I use the same strategy that is used from the green dragon blade to compare the hp before
the general use the attack and after use the attack. If the result is still the same, which means that
the target has used a dodge card to dodge the attack. So that the general will check whether he has
more than 2 cards to **discard to deduct the target hp**. If he has a card, the target will directly deduct
hp. And here is the sample output of the rock cleaving axe:


### Yin Yang Sword

Yin Yang Sword allows the player to attack when he chooses the opposite gender of target attack. It will
trigger the abilities of the sword which is force the target to **discard one of his handcard** , or **allow the
player to draw a card from the card deck**. Here is the code implementation detail:

fun yanyangsword() {
if (a.hasAttackCard() == true) {
//play card fun
target = locktarget()
if (target == 87 ) {
println("no general in the range")
general.attackedState = true
return
}

var counter = 0
while (Generalmanager.list.get(target) is ZhugeLiang &&
Generalmanager.list.get(target).handCard.size == 0 && counter < 3 ) {
target = locktarget()


counter++
println("[Empty City strategy activate] Zhuge Liang cannot pick as an
target")
}
if (counter >= 3 ) {
println("[Empty City strategy activate] Zhuge Liang cannot pick as an
target")
return
}
if (general.gender != Generalmanager.list.get(target).gender) {
if (Generalmanager.list.get(target).handCard.size > 0 ) {
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

In the code, it will do the checking by comparing the general and the target gender to see whether the
gender of the two generals are the same. If their generals are different. It will check if the target has
more than one card to discard. If he have more than one card to discard. The weapon will force him to
randomly discard one card. Otherwise, the general will call the drawscard function to draw a card on it.
After finishing the above action. It will direct back to the normal attack logic. And here is the sample


output of this weapon:

## Part 2: Game Logic and interaction

### Game open card and draw phase

During the draw phase, each general has to **draw 2 cards** from the deck. Before drawing cards from
the deck, the game has to open the card for the player to draw from it first, to ensure each player has 4
cards before drawing. Here is the code snippet of game open card:

fun gameOpenCard() {
for (i in 0..3) {
handCard.add(Generalmanager.cardList.removeFirst())
}
}

In the function the hand card of the general will **add 4 cards** by taking the card deck using the function
of Generalmanage.cardList.removeFirst(). In the draw phrase, it also calls the similar function by calling
the function draw2 cards. Here is the function of draw2cards:

if (Generalmanager.cardList.isEmpty())
Generalmanager.createAllCard()

handCard.add(Generalmanager.cardList.removeFirst())
if (Generalmanager.cardList.isEmpty())
Generalmanager.createAllCard()


handCard.add(Generalmanager.cardList.removeFirst())
println("$name draws 2 cards and now has ${handCard.size} card(s).")

I add a check to ensure that when the deck does not have enough cards for drawing. It will create a new
deck for draw to avoid not error happen. And here is the sample output:

### Special skills

We know that there are many generals who have different **skills** in the game. Some generals have
some active skills such as Liu Bei Benevolence, Diao chan alienation in the game. So it is important to
implement a class to activate these kinds of skills. And I have a function to check the types of general
they belong to. If they belong to that type of general, It will trigger their special skills. Here is the code
below:

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

#### }

### Attack

Attack is one of the important operations in the game. In the attack. It will trigger only if the player has
the attack card and there is a general in the attack range. Here is the example code:

else if (card.name.equals("Attack") && dist != 87 ) {
if (general.attackedState == false) {
attack()
}
}

As I introduce in the above. Some generals and some weapons can have different types of attack
methods. So I have to do the checking of which types of attack they are belong to ensure it go to a
correct attack which is the code below:

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


Since the following attack I have introduced in the above, I would like to introduce a normal attack in
this section. Before that I would like to introduce how the general lock the target by using locktarget
function here is the code of locktarget function.

fun locktarget(general: General): Int {
for (i in 0..general.inRange.size - 1 ) {
if(general.inRange.get(i) is YuanShu)
{

if(general.inRange.get(i).handCard.size>general.inRange.get(i).currentHP){
println("Taunt activate! Yuan Shu become the target of all the
general")
for (j in 0..Generalmanager.list.size - 1 ) {
if (Generalmanager.list.get(j).equals(general.inRange.get(i)))
return j
}

}
}
}

for (i in 0..general.inRange.size - 1 ) {
if (general.inRange.get(i).strategy is LoyalistStrategy) {

for (j in 0..Generalmanager.list.size - 1 )
if (general.inRange.get(i).equals(Generalmanager.list.get(j)) &&
!Generalmanager.list.get(j).equals(general)
)
return j

}

}
println("there is not general in the range ")
return 87

}

In the code I call a for loop to loop over the list to see whether there is a suitable target. The two
strategy loyalist and rebel strategy have the similar function. The difference is that the function in the


loyalist will lock the rebel/spy. And the rebel will lock the lord, loyalist. After we know the mechanism of
lock target and let's look at the code of normal attack:

if (a.hasAttackCard() == true) {
//play card fun
target = locktarget()
if (target == 87 ) {
println("no general in the range")
general.attackedState = true
return
}

var counter = 0
while (Generalmanager.list.get(target) is ZhugeLiang &&
Generalmanager.list.get(target).handCard.size == 0 && counter < 3 ) {
target = locktarget()
counter++

if (counter >= 3 ) {
println("[Empty City strategy activate] Zhuge Liang cannot pick as
an target")
return
}
if (target == 87 ) {
println("no general in the range")
general.attackedState = true
return
}

println("[Empty City strategy activate] Zhuge Liang cannot pick as an
target")
}
Generalmanager.list.get(target).beingAttacked(general)

In the above, the strategy will check whether there is a general in the attack range. If there is no general
in the range, it will return. If there is general in the range, It will call the being attacked method in the
below:

println("$name being attacked")

val card = general.playAttackCard()//攻擊人出殺


var dodge = this.hasDodgeCardCheckAll()

if (dodge == true) {
playDodgeCard()
} else
beingDamage(general.attackDamage, card, general)

In the beingattacked method, it will check whether the general have the dodge card to dodge the
attack. If he has the dodge card to dodge the attack. He will play it out. If he does not have the dodge
card, I will call the being damage method to **deduct the damage** in the below:

currentHP -= num
println("$name - $num HP. Now $currentHP hp")
if (currentHP <= 0 ) {
askForPeach()
Generalmanager.win()

}


And here is the output of normal attack function:

### Discard Phase

In the discard phase of the war of three kingdoms, the general has to **discard their cards if their num
of cards is more than their current hp**. Here is the code implementation of above description:

open fun DiscardPhase() {

var store: Int = 0
if (handCard.size > currentHP) {
store = handCard.size - currentHP


#### }

println("$name has ${handCard.size} card(s), current HP is $currentHP")

if (currentHP > 0 ) {
for (i in 0..store - 1 ) {
handCard.removeAt( 0 )
}
}

println("$name discards ${store} card(s), now has ${handCard.size} card(s). ")
}

The code will check whether the hand card of the general has more than his current hp. If it is **true** , it
will remove the card from index 0. Here is the sample output:

### Strategy changing

We know that the spy is set to a loyalist strategy in the beginning. And they will be set to other strategies
once they reveal their identity. So We decide to set a spy, reveal their identity and change strategy.
**Once there is no rebel in the game**. And I use the following way to check them:

var checkrebel= checkrebel()
if(checkrebel==true &&checked<checkspy()){
changestrategy()
checked++
println(checked)
}

I will first call the rebel when the rebel is not on the field. And use two other variables to see whether I
have changed the strategy already. Since in a large number of players, there can be one or more spies.


So I have to check if I have changed all the strategies already or make an extra change. And Here is the
output of the changing strategy:

### Game Winning

To win this game, different identities have different criteria to win the game. For the loyalists, **they have
to kill all the rebels and spy to win the game**. For the rebel, **he has to be the only survivor in the
game**. Spy also. To implement this functionality. I write a function to check the condition when a general
is removed which is in the below:

fun rebelwin(){

var count= 0
for(i in 0..list.size- 1 ){
if(list.get(i).player is Lord||list.get(i).player is Loyalist ){
count++
}

}
if(count== 0 ){
println("There is no lord or loyalist left in the game. Rebel win")

exitProcess( 1 )
}
}

I will use a counter to count if there are any lords or loyalists left in the game. If there is no loyalist in it,
the rebel will win the game. Same method for other identities. And here is the sample output for running
this logic


## Conclusion

To summarize, I have dominated a huge effort on the project. And each function should be well tested in
order to stimulate the real game logic in the war of three kingdoms. The project has used different kinds
of design patterns and testing methods to facilitate the design, for example the factory method ,
template method and singleton. Using Junit to verify our function functionality and completeness. Hope
this report can allow you to understand the project code more and know how these functions work in the
game.






