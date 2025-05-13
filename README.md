# War of Three Kingdoms Feature Implementation 

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
on  the interaction and gameplay of strategies that I implement 
in the project.

## Part one:Game Rules and Mechanics:

## Liu Bei

In Liu Bei, he has two skills for the game. 
One is **benevolence**, which mean that Liu Bei 
can give out 1 or more cards to other in the play phase.
If Liu Bei give out two cards. He can restore one hp. 
For this skills, I have use a **state design pattern** for
this skills. Because we have to monitor the health status 
of Liu Bei to decide whether he will use the benevolence skills.
Here is the code example of the benevolence :


```Kotlin
for(i in 0 .. (maxHP - currentHP)-1){
            if(hasPeachCard())
                playPeachCard()
        }
        println("$name has ${this.handCard.size} cards.")
        if (handCard.size >= 2 && currentHP <=2) {
            // find loyalist
            var num = -1
            for (i in 0..Generalmanager.list.size - 1) {
                if(!Generalmanager.list.get(i).player.equals(this)) {
                    if (Generalmanager.list.get(i).player is Loyalist) {
                        num = i
                    } else {
                        num = Random.nextInt(0, Generalmanager.list.size - 1)
                    }
                }
            }
            if(num ==0 || num == -1){
                println("benevolence picks no one.")
                return
            }
            Generalmanager.list.get(num).handCard.add(this.handCard.removeFirst())
            Generalmanager.list.get(num).handCard.add(this.handCard.removeFirst())

            this.currentHP += 1
            println("[Benevolence] Liu Bei gives away two cards and recovers 1 HP, now his HP is ${this.currentHP}.")}
```
For this code, Liu Bei will first check whether it has a peach card in hand. If he has the peach card he will use the peach card first. 
If he does not have a peace card, it will go to the following steps. To check whether he has fulfilled the condition of benevolence by
checking his handcard size and his current hp is how many. If he fulfills the condition, Liu Bei will find the loyalist and pass the card to them  
in order to restore the hp. If it cannot find loyalists the benevolence will fail. 
And here are a code example of **state design pattern**:
```Kotlin
override fun playNextCard() {
        println("Liu Bei is in unhealthy state. ")
        println("His hand card number: " +strategy.general.handCard.size)
        if(strategy.general.handCard.size >=2) {
            (strategy.general as LiuBei).benevolence()
        }}
```
In this code, it explains that if Liu bei is in an unhealthy 
state. It will trigger the function above.

Another skill that Liu Bei has is aggression. 
**Aggression** means that Liu Bei needs an attack card to attack other
generals. He can ask another general from the same kingdom to 
give it to you. To implement this skill. I have used the **chain
of responsibility pattern** to demonstrate these skills. Since
it is a perfect design pattern to do the request processing. 
And this skill involves request processing. And here is the code
example of **the chain of responsibility method**:
```Kotlin
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

  
}
```
In this class. I created a class of Shu generals to handle 
the request of aggression. In the method of handling requests.
It will check if the Shu general has the _attackcard_. If the 
general has the _attackcard_ it will return true. Otherwise, 
it will return false. If the general have _attackcard_. Liu Bei 
will use its attack card to attack people in his own aggression
method. His **aggression** method is like this:

``` Kotlin
if (general is LiuBei)
            if (general.hasAttackCard() == false) {
                for (i in 0..general.shuchain.size-1) {
                    var shu = general.shuchain.get(i).handleRequest()
                    if (shu == true) {
                        var tar = locktarget()
                        if (tar == 87) {
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
With this code Liu Bei will check whether he has the attack card for the attack.
 If he does not have the attack card, he can call the
  handlerequest to ask for an attack card from another 
  general. And use it to attack others. And here are the
   sample output of Liu Bei using the benevolence and aggression:








