import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith

class WeiTest {

    @Test
    fun testEntcourage() {
        Generalmanager.addGeneral(CaoCao("Cao Cao", Lord()))
        var b = WeiOnlyNonLordFactory()
        for (i in 1..3) {
            Generalmanager.addGeneral(b.createRandomGeneral(i))
        }
        (Generalmanager.list.get(0) as CaoCao).weichain = b.weichain
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 3
        Generalmanager.list.get(3).currentHP = 3

        Generalmanager.addGeneral(HuanGai("Huan Gai", Lord()))
        Generalmanager.list.get(4).currentHP = 3


        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(4).strategy = RebelStrategy(Generalmanager.list.get(4))

        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(4).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(4).Preparationphase()
        Generalmanager.list.get(4).strategy.playNextCard()


    }

    @Test
    fun testTreachery() {
        Generalmanager.addGeneral(CaoCao("Cao Cao", Lord()))
        Generalmanager.addGeneral(DaQiao("Da Qiao", Rebel()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 3

        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))

        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).strategy.playNextCard()
    }

}

class WuTest {
    @Test
    fun deflectiontest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(DaQiao("Da Qiao", Rebel()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 3

        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).Preparationphase()
        Generalmanager.list.get(0).strategy.playNextCard()

    }

    @Test
    fun nationalbeautytest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(DaQiao("Da Qiao", Rebel()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 3
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))

        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "diamond", "6"))
        (Generalmanager.list.get(1) as DaQiao).nationalbeauty()


    }

    @Test
    fun humidilitytest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(DaQiao("Da Qiao", Rebel()))
        Generalmanager.addGeneral(LuXun("Lu Xun", Loyalist()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 3
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))

        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "diamond", "6"))
        (Generalmanager.list.get(1) as DaQiao).nationalbeauty()


    }

    @Test
    fun coalitionnormalattacktest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(DaQiao("Da Qiao", Rebel()))
        Generalmanager.addGeneral(LuXun("Lu Xun", Loyalist()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 3
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).Preparationphase()
        Generalmanager.list.get(2).strategy.playNextCard()


    }

    @Test
    fun coalitiontestwithothergeneralskills() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(ZhangLiao("Zhang Liao", Rebel()))
        Generalmanager.addGeneral(LuXun("Lu Xun", Loyalist()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 3
        Generalmanager.createAllCard()
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).DrawPhase()


    }

    @Test
    fun testSelfcontrol() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvMeng("Lv Meng", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5

        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(2).handCard.add(BasicCard("Dodge", "diamond", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Dodge", "diamond", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Dodge", "diamond", "6"))
        Generalmanager.list.get(2).templatemethod()

    }

    @Test
    fun testSowDiscord() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))

        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(ZhouYu("Zhou Yu", Loyalist()))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).strategy.sowdiscord()


    }

    @Test
    fun testburningbridge() {
        Generalmanager.addGeneral(CaoCao("Cao Cao", Lord()))
        var b = WeiOnlyNonLordFactory()
        for (i in 1..3) {
            Generalmanager.addGeneral(b.createRandomGeneral(i))
        }
        (Generalmanager.list.get(0) as CaoCao).weichain = b.weichain
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 3
        Generalmanager.list.get(3).currentHP = 3

        Generalmanager.addGeneral(GanNing("Guan Ning", Loyalist()))
        Generalmanager.list.get(4).currentHP = 3


        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(4).strategy = RebelStrategy(Generalmanager.list.get(4))

        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(4).handCard.add(BasicCard("Attack", "spade", "6"))
        Generalmanager.list.get(4).Preparationphase()
        (Generalmanager.list.get(4) as GanNing).ambushment()

    }
    //@Test
//    fun testSunQuanplaycard(){
//        Generalmanager.addGeneral(SunQuan("Sun Quan",Lord()))
//        Generalmanager.list.get(0).strategy=SunQuanStratrgy(Generalmanager.list.get(0))
//        Generalmanager.list.get(0).draw2Cards()
//        Generalmanager.list.get(0).draw2Cards()
//
//        Generalmanager.list.get(0).strategy.playNextCard()
//
//    }


}

class LvBuTest {

    @Test
    fun testduel() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Loyalist()))
        Generalmanager.createAllCard()
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 4

        Generalmanager.list.get(0).gameOpenCard()
        Generalmanager.list.get(1).gameOpenCard()
        Generalmanager.list.get(1).handCard.add(Duel("Duel", "Spade", "6"))
        Generalmanager.list.get(1).playDuelCard(Generalmanager.list.get(0))


    }

    @Test
    fun testNONBUduel() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.createAllCard()
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 4
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))

        Generalmanager.list.get(0).gameOpenCard()
        Generalmanager.list.get(1).gameOpenCard()
        Generalmanager.list.get(0).handCard.add(Duel("Duel", "Spade", "6"))
        Generalmanager.list.get(0).playDuelCard(Generalmanager.list.get(1))


    }

    @Test
    fun testunrival() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Rebel()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(2).Preparationphase()
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        println("${Generalmanager.list.get(2)}")
        Generalmanager.list.get(2).strategy.playNextCard()

    }
    @Test
    fun treason(){
        Generalmanager.addGeneral(YuanShu("Yuan Shu",Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Rebel()))
        Generalmanager.list.get(0).currentHP = 4
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(0).Preparationphase()
        Generalmanager.list.get(0).DrawPhase()
        Generalmanager.list.get(0).DiscardPhase()
        Generalmanager.list.get(0).Preparationphase()

        Generalmanager.list.get(0).DrawPhase()

        Generalmanager.list.get(0).DiscardPhase()


    }
    @Test
    fun taunttest(){
        Generalmanager.addGeneral(YuanShu("Yuan Shu",Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Rebel()))
        Generalmanager.list.get(0).currentHP = 4
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(0).Preparationphase()
        Generalmanager.list.get(0).DrawPhase()
        Generalmanager.list.get(0).DiscardPhase()
        Generalmanager.list.get(0).Preparationphase()

        Generalmanager.list.get(0).DrawPhase()

        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).Preparationphase()

        Generalmanager.list.get(1).strategy.playNextCard()




    }

}

class HuanGaiTest {
    @Test
    fun SacrifiesTest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        //Generalmanager.addGeneral(LvBu("Lv Bu", Loyalist()))
        Generalmanager.createAllCard()
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 3
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = HuanGaiLoyalistStrategy(Generalmanager.list.get(1) as HuanGai)


        Generalmanager.list.get(0).gameOpenCard()
        Generalmanager.list.get(1).gameOpenCard()
        Generalmanager.list.get(1).PlayPhase()
        Generalmanager.list.get(1).PlayPhase()
        Generalmanager.list.get(1).PlayPhase()
//        (Generalmanager.list.get(1).strategy as HuanGaiLoyalistStrategy).playNextCard()
//        (Generalmanager.list.get(1).strategy as HuanGaiLoyalistStrategy).playNextCard()
//        (Generalmanager.list.get(1).strategy as HuanGaiLoyalistStrategy).playNextCard()
//        (Generalmanager.list.get(1).strategy as HuanGaiLoyalistStrategy).playNextCard()
        //(Generalmanager.list.get(1).strategy as HuanGaiLoyalistStrategy).state.playNextCard()
        //(Generalmanager.list.get(1).strategy as HuanGaiLoyalistStrategy).state.playNextCard()

    }
}

class ShuTest {
    @Test
    fun godofwarTest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(GuanYuadapter(GuanYu(), Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).handCard.add(BasicCard("Dodge", "diamond", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Dodge", "diamond", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Dodge", "diamond", "6"))
        Generalmanager.list.get(2).Preparationphase()
        Generalmanager.list.get(2).strategy.attack()
        println("${Generalmanager.list.get(0).currentHP}")
    }

    @Test
    fun braveryattack() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(ZhaoYun("Zhao Yun", Rebel()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(ZhangLiao("Zhang Liao", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(3).currentHP = 5

        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(1).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).strategy.attack()
    }

    @Test
    fun braverydodge() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(ZhaoYun("Zhao Yun", Rebel()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(ZhangLiao("Zhang Liao", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(3).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(3).strategy = LoyalistStrategy(Generalmanager.list.get(3))


        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).Preparationphase()
        Generalmanager.list.get(0).strategy.playNextCard()
    }

    @Test
    fun stargazingtest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(ZhugeLiang("Zhuge Liang", Spy()))
        Generalmanager.addGeneral(ZhangLiao("Zhang Liao", Spy()))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(3).strategy = LoyalistStrategy(Generalmanager.list.get(3))

        Generalmanager.createAllCard()
        Generalmanager.list.get(2).Preparationphase()

    }

    @Test
    fun emptycitytest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(ZhugeLiang("Zhuge Liang", Rebel()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(ZhangLiao("Zhang Liao", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(3).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(3).strategy = LoyalistStrategy(Generalmanager.list.get(3))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).Preparationphase()
        Generalmanager.list.get(0).strategy.playNextCard()
    }

    @Test
    fun berserkTest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(ZhangFei("Zhang Fei", Rebel()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(ZhangLiao("Zhang Liao", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(3).currentHP = 5

        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(3).strategy = LoyalistStrategy(Generalmanager.list.get(3))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).strategy.playNextCard()

    }


}


class CardFunctionTest {
    @Test
    fun BrotherhoodTest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(0).currentHP = 1
        Generalmanager.list.get(1).currentHP = 1
        Generalmanager.list.get(2).currentHP = 1

        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(2).handCard.add(Brotherhood("Attack", "Spade", "6"))
        (Generalmanager.list.get(2).handCard.get(0) as Brotherhood).spellExecute(Generalmanager.list.get(2))
    }

    @Test
    fun zhugecrossbowmixsimayi() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(ZhouYu("Zhou yu", Loyalist()))
        Generalmanager.addGeneral(SimaYi("Sima Yi", Rebel()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).equipment = ZhugeCrossbow("zhuge crossbow", "spade", "5")
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).strategy.attack()


    }

    @Test
    fun simamixzhangfei() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(ZhangFei("Zhang Fei", Loyalist()))
        Generalmanager.addGeneral(SimaYi("Sima Yi", Rebel()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).strategy.attack()

    }

    @Test
    fun xiamixzhugecrossbow() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(ZhouYu("HZhou Yu", Loyalist()))
        Generalmanager.addGeneral(XiahouDun("Xia Hou Dun", Rebel()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).equipment = ZhugeCrossbow("zhuge crossbow", "spade", "5")
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).strategy.attack()


    }

    @Test
    fun xiamixzhangfei() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(ZhangFei("Zhang Fei", Loyalist()))
        Generalmanager.addGeneral(XiahouDun("Xia Hou Dun ", Rebel()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))

        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).strategy.attack()

    }

    @Test
    fun benolvenceTest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Rebel()))
        var b = ShuOnlyNondLordFactory()
        b.shu = true
        for (i in 1..3) {
            Generalmanager.addGeneral(b.createRandomGeneral(i))
        }
        (Generalmanager.list.get(0) as LiuBei).shuchain = b.shuchain
        println("${Generalmanager.list.size}")
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(0).currentHP = 1
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).Preparationphase()
        (Generalmanager.list.get(0) as LiuBei).benevolence()

    }


    @Test
    fun AggressionTest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Rebel()))
        var b = ShuOnlyNondLordFactory()
        b.shu = true
        for (i in 1..3) {
            Generalmanager.addGeneral(b.createRandomGeneral(i))
        }
        (Generalmanager.list.get(0) as LiuBei).shuchain = b.shuchain
        println("${Generalmanager.list.size}")
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(3).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(4).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(0).Preparationphase()
        Generalmanager.list.get(0).strategy.playNextCard()


    }

    @Test
    fun alienationtest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(DiaoChan("Diao Chan", Rebel()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = DiaoChanRebelStrategy(Generalmanager.list.get(2) as DiaoChan)
        Generalmanager.list.get(2).Preparationphase()
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        println("${Generalmanager.list.get(2)}")
        Generalmanager.list.get(2).strategy.playNextCard()
    }

    @Test
    fun normalattack() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Rebel()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        println("${Generalmanager.list.get(2)}")
        Generalmanager.list.get(1).strategy.playNextCard()

    }

    @Test
    fun kirinbowtest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(0).mountDec = HexMark("Hex Mark", "Spade", "7")
        println("${Generalmanager.list.get(0).mountDec}")
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(2).equipment = KirinBow("Kirin Bow", "Spade", "6")
        Generalmanager.list.get(2).Preparationphase()

        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        println("${Generalmanager.list.get(2)}")
        Generalmanager.list.get(2).strategy.attack()
        println("${Generalmanager.list.get(0).mountDec}")
    }

    @Test
    fun rockcleavingaxe() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))

        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5

        Generalmanager.list.get(0).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))

        Generalmanager.list.get(2).equipment = RockCleavingAxe("Rock Cleaving axe", "Spade", "6")
        Generalmanager.list.get(2).Preparationphase()

        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        println("${Generalmanager.list.get(2)}")
        Generalmanager.list.get(2).strategy.attack()
    }

    @Test
    fun mountdec() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(ZhugeLiang("Zhuge Liang", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Rebel()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))

        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).handCard.add(RedHare("RedHare", "Spade", "6"))

        Generalmanager.list.get(3).strategy = RebelStrategy(Generalmanager.list.get(3))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).strategy.playNextCard()
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).strategy.playNextCard()


    }

    @Test
    fun mountinc() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(ZhugeLiang("Zhuge Liang", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Rebel()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(3).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(2).strategy = LoyalistStrategy(Generalmanager.list.get(2))

        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).handCard.add(ShadowRunner("Shadow Runner", "Spade", "6"))

        Generalmanager.list.get(3).strategy = RebelStrategy(Generalmanager.list.get(3))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(2).strategy.playNextCard()
        Generalmanager.list.get(1).Preparationphase()

    }

    @Test
    fun bluesteelbladetest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(1).armor = EightTrigramsFormation("Eight TrigramsFormation", "Spade", "7")
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(2).equipment = BlueSteelBlade("Blue Steel Blade", "Spade", "6")
        Generalmanager.list.get(2).Preparationphase()

        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Peach", "diamond", "6"))
        println("${Generalmanager.list.get(2)}")
        Generalmanager.list.get(2).strategy.attack()

    }

    @Test
    fun arrowraintest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5

        Generalmanager.list.get(0).handCard.add(RainingArrows("Raining Arrow", "Spade", "6"))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        (Generalmanager.list.get(0).handCard.get(0) as RainingArrows).spellExecute(Generalmanager.list.get(0))
        println("${Generalmanager.list.get(1).currentHP}")

    }

    @Test
    fun speartest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(0).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(1).equipment = SerpentSpear("Serpent Spear ", "Spade", "6")
        Generalmanager.list.get(1).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Dodge", "Spade", "6"))
        Generalmanager.list.get(1).Preparationphase()

        println("${Generalmanager.list.get(1)}")
        Generalmanager.list.get(1).strategy.attack()
        println("${Generalmanager.list.get(0).currentHP}")


    }

    @Test
    fun barbarianinvasiontest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5

        Generalmanager.list.get(0).handCard.add(BarbarianInvasion("BarbarianInvasion", "Spade", "6"))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        //Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(Negation("Negation", "Spade", "6"))
        (Generalmanager.list.get(0).handCard.get(0) as BarbarianInvasion).spellExecute(Generalmanager.list.get(0))

    }

    @Test
    fun somethingoutofnothingtest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(0).handCard.add(SomethingOutOfNothing("Somethingoutofnothing", "Spade", "6"))
        (Generalmanager.list.get(0).handCard.get(0) as SomethingOutOfNothing).spellExecute(Generalmanager.list.get(0))
        for (i in 0..Generalmanager.list.get(0).handCard.size - 1)
            println("${Generalmanager.list.get(0).handCard.get(i)}")


    }

    @Test
    fun ZhugeCrossbowTest() {
        Generalmanager.addGeneral(SunQuan("Sun Quan", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(0).currentHP = 2
        Generalmanager.list.get(1).currentHP = 2
        Generalmanager.list.get(2).currentHP = 2

        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))

        Generalmanager.list.get(2).equipment = ZhugeCrossbow("ZhugeCrossBow", "Spade", "6")
        Generalmanager.list.get(2).Preparationphase()

        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        println("${Generalmanager.list.get(2)}")
        Generalmanager.list.get(2).strategy.attack()

    }

    @Test
    fun YinYangSwordstest() {
        Generalmanager.addGeneral(LiuBei("LiuBei", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(Sunshangxiang("Sun Shang xiang", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))

        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(0).handCard.add(BasicCard("Attack", "Spade", "6"))

        Generalmanager.list.get(2).equipment = YinYangSwords("YinYangSword", "Spade", "6")
        Generalmanager.list.get(2).Preparationphase()

        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).strategy.attack()
        println("${Generalmanager.list.get(0).handCard.size}")


    }

    @Test
    fun GreenDragonBladeTest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(0).handCard.add(BasicCard("Dodge", "Spade", "6"))

        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(2).equipment = GreenDragonBlade("GreenDragonBlade", "Spade", "6")
        Generalmanager.list.get(2).Preparationphase()

        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))

        Generalmanager.list.get(2).strategy.attack()

    }

    @Test
    fun skypierchingtest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(0).currentHP = 5
        Generalmanager.list.get(2).currentHP = 5
        Generalmanager.list.get(1).currentHP = 5
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(0).handCard.add(BasicCard("Dodge", "Spade", "6"))

        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(2).equipment = SkyPiercingHalberd("SkyPiercing", "Spade", "6")
        Generalmanager.list.get(2).Preparationphase()

        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "Spade", "6"))


        Generalmanager.list.get(2).strategy.attack()


    }

    @Test
    fun bumperhavesttest() {
        Generalmanager.createAllCard()
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(2).handCard.add(BumperHarvest("bumper", "3", "3"))
        (Generalmanager.list.get(2).handCard.get(0) as BumperHarvest).spellExecute(Generalmanager.list.get(2))
    }

}

class gamelogic {
    @Test
    fun gameendtest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(0).strategy = LoyalistStrategy(Generalmanager.list.get(0))

        Generalmanager.list.get(0).currentHP = 3
        Generalmanager.rebelwin()
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).handCard.add(BasicCard("Attack", "Spade", "6"))
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(1).strategy.attack()
        Generalmanager.list.get(1).strategy.attack()
        Generalmanager.list.get(1).strategy.attack()
        Generalmanager.rebelwin()
        println(Generalmanager.list.size)
        println("${Generalmanager.list.get(0).currentHP}")
    }

    fun locktargettest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(DiaoChan("Diao Chan", Rebel()))
        Generalmanager.list.get(0).currentHP = 3
        Generalmanager.list.get(1).currentHP = 3
        Generalmanager.list.get(0).strategy = LiuBeiStrategy((Generalmanager.list.get(0) as LiuBei))
        Generalmanager.list.get(1).strategy = DiaoChanRebelStrategy((Generalmanager.list.get(1) as DiaoChan))
        Generalmanager.list.get(0).Preparationphase()


    }

    @Test
    fun changingstrategytest() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(DiaoChan("Diao Chan", Spy()))
        Generalmanager.list.get(0).currentHP = 3
        Generalmanager.list.get(1).currentHP = 3
        Generalmanager.list.get(0).strategy = LiuBeiStrategy((Generalmanager.list.get(0) as LiuBei))
        Generalmanager.list.get(1).strategy = DiaoChanLoyalistStrategy((Generalmanager.list.get(1) as DiaoChan))
        Generalmanager.createAllCard()
        Generalmanager.gamestart()
    }
}


class WeiOnlyNonLordFactory() : NonLordFactory(Lord()) {
    override var cao = true
    override fun createRandomGeneral(abc: Int): General {
        while (true) {
            var general = super.createRandomGeneral(abc)
            if (general is WeiGeneral) {
                return general
            } else {
                println("${general.name} is discarded as he/she is not a Wu.\n")
            }


        }
    }
}

class ShuOnlyNondLordFactory() : NonLordFactory(Lord()) {
    override var shu = true
    override fun createRandomGeneral(abc: Int): General {
        while (true) {
            var general = super.createRandomGeneral(abc)
            if (general is ShuGeneral) {
                return general
            } else {
                println("${general.name} is discarded as he/she is not a Shu.\n")
            }


        }
    }
}

class WuOnlyNonLordFactory() : NonLordFactory(Lord()) {
    override var wu = true
    override fun createRandomGeneral(abc: Int): General {
        while (true) {
            var general = super.createRandomGeneral(abc)
            if (general is WuGeneral) {
                return general
            } else {
                println("${general.name} is discarded as he/she is not a Wei.\n")
            }


        }
    }


}

class TestMod() {
    @Test
    fun testMod() {
        println((0 - 1).mod(4))
    }

    public inline fun Int.mod(other: Int): Int {
        val r = this % other
        return r + (other and (((r xor other) and (r or -r)) shr 31))
    }

    @Test
    fun steal() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Loyalist()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Spy()))
        Generalmanager.addGeneral(LvBu("hi", Spy()))
        Generalmanager.addGeneral(LvBu("bye", Spy()))

        val s = ShadowRunner("s", "spade", "1")
        val d = RedHare("d", "spade", "1")
        val steal = StealingSheep("steal", "spade", "1")
        val at = BasicCard("Attack", "spade", "1")

        Generalmanager.list.get(0).handCard.add(steal)
        Generalmanager.list.get(0).mountDec = d
        Generalmanager.list.get(2).handCard.add(at)
        //Generalmanager.list.get(1).mountInc = s

        //steal.spellExecute(Generalmanager.list.get(0))

    }

}

class TestZhanJi() {
    @Test
    fun judge() {
        Generalmanager.createAllCard()
        val zj = ZhenJi("Zhen Ji", Lord())
        zj.invoker.addCommand(Acedia(zj))
        //zj.invoker.addCommand( Lightning(zj))
        zj.strategy=LoyalistStrategy(zj)
        zj.currentHP=3
        //zj.JudgementPhase()
        Generalmanager.createAllCard()
        zj.templatemethod()
        for (i in 0..zj.handCard.size - 1) {
            println(zj.handCard.get(i).name)
        }
    }

    @Test
    fun dodge() {
        val zj = ZhenJi("Zhen Ji", Lord())
        zj.strategy = LoyalistStrategy(zj)
        val XuChu = XuChu("Xu Chu", Rebel())
        XuChu.strategy = RebelStrategy(XuChu)
        Generalmanager.list.add(zj)
        Generalmanager.list.add(XuChu)
        zj.currentHP = 3
        XuChu.currentHP = 3
        val card = BurningBridges("Burn", "spade", "A")
        val attack = BasicCard("Attack", "spade", "4")
        zj.Preparationphase()
        XuChu.Preparationphase()
        zj.handCard.add(card)
        XuChu.handCard.add(attack)
        XuChu.strategy.attack()
    }
}

class TestSunshangxiang() {
    @Test
    fun daredevil() {
        val zj = ZhenJi("Zhen Ji", Lord())
        val xiang = Sunshangxiang("Sun shang xiang", Rebel())
        zj.strategy = LoyalistStrategy(zj)
        xiang.strategy = RebelStrategy(xiang)

        Generalmanager.list.add(zj)
        Generalmanager.list.add(xiang)
        val card = BurningBridges("Burn", "spade", "A")
        val e1 = ZhugeCrossbow("e1", "spade", "A")
        val e2 = ZhugeCrossbow("e2", "spade", "A")
        xiang.handCard.add(e1)
        xiang.handCard.add(e2)

        zj.handCard.add(card)

        xiang.PlayPhase()
        print(xiang.handCard.size)
        zj.PlayPhase()
        print(xiang.handCard.size)
    }

    @Test
    fun betrothment() {
        val zj = ZhenJi("Zhen Ji", Lord())
        val xiang = Sunshangxiang("Sun shang xiang", Rebel())
        val l = LiuBei("liu bei", Rebel())
        val s = ZhangLiao("p", Rebel())
        l.currentHP = 2
        xiang.currentHP = 2
        zj.strategy = LoyalistStrategy(zj)
        xiang.strategy = RebelStrategy(xiang)

        Generalmanager.list.add(zj)
        Generalmanager.list.add(xiang)
        Generalmanager.list.add(l)
        Generalmanager.list.add(s)

        val e1 = ZhugeCrossbow("e1", "spade", "A")
        val e2 = ZhugeCrossbow("e2", "spade", "A")
        xiang.handCard.add(e1)
        xiang.handCard.add(e2)


        xiang.PlayPhase()
        print(xiang.handCard.size)

    }
}

class TestZhangLiao() {

    @Test
    fun incursion() {
        val zj = ZhenJi("Zhen Ji", Lord())
        val xiang = Sunshangxiang("Sun shang xiang", Rebel())
        val l = LiuBei("l", Rebel())
        val s = ZhangLiao("p", Rebel())

        Generalmanager.list.add(zj)
        Generalmanager.list.add(xiang)
        //Generalmanager.list.add(l)
        Generalmanager.list.add(s)

        val a = BasicCard("Attack", "spade", "1")
        val a1 = BasicCard("Attack", "spade", "1")
        val a2 = BasicCard("Attack", "spade", "1")
        val a3 = BasicCard("Attack", "spade", "1")
        zj.handCard.add(a)
        xiang.handCard.add(a1)
        s.handCard.add(a2)

        s.DrawPhase()
    }
}


class TestGuoJiaHuaTuoXuChu() {
    @Test
    fun jealousy() {
        val GuoJia = GuoJia("Guo Jia", Lord())
        Generalmanager.list.add(GuoJia)
        GuoJia.invoker.addCommand(Acedia(GuoJia))
        GuoJia.JudgementPhase()
    }

    @Test
    fun LastStrategyFirstAidWithXuChu() {
        val GuoJia = GuoJia("Guo Jia", Loyalist())
        Generalmanager.list.add(GuoJia)
        val XuChu = XuChu("Xu Chu", Rebel())
        XuChu.strategy = RebelStrategy(XuChu)
        Generalmanager.list.add(XuChu)
        GuoJia.strategy = LoyalistStrategy(GuoJia)
        GuoJia.currentHP = 2
        XuChu.currentHP=3
        val peach = BasicCard("Red", "diamond", "0")

        val H = HuaTuo("H", Loyalist())
        val P = BasicCard("Attack", "spade", "4")

        H.strategy = LoyalistStrategy(H)
        H.handCard.add(peach)
        Generalmanager.list.add(H)
        H.currentHP = 2
        H.handCard.add(P)


        val attack = BasicCard("Attack", "spade", "4")
        val attack2 = BasicCard("Attack", "spade", "4")
        val attack3 = BasicCard("Attack", "spade", "4")
        val attack4 = BasicCard("Attack", "spade", "4")

        XuChu.handCard.add(attack)
        XuChu.handCard.add(attack4)
        XuChu.handCard.add(attack2)
        XuChu.handCard.add(attack3)
        Generalmanager.createAllCard()
        XuChu.Preparationphase()
        XuChu.DrawPhase()
        XuChu.strategy.attack()
        H.PlayPhase()
    }
}

class TestHuangYueying() {
    @Test
    fun wisdom() {
        val h = HuangYueying("Huang Yueying", Loyalist())
        Generalmanager.createAllCard()
        Generalmanager.addGeneral(LiuBei("Liu Bei", Rebel()))
        Generalmanager.addGeneral(HuanGai("Huan Gai", Rebel()))
        Generalmanager.addGeneral(LvBu("Lv Bu", Rebel()))
        Generalmanager.addGeneral(LvBu("hi", Rebel()))
        Generalmanager.addGeneral(LvBu("bye", Rebel()))
        for (i in 0..Generalmanager.list.size - 1) {
            Generalmanager.list.get(i).gameOpenCard()
        }

        Generalmanager.list.add(h)
        Generalmanager.createAllCard()
        h.strategy = LoyalistStrategy(h)

        val t = StealingSheep("2", "spade", "4")
        h.handCard.add(t)
        h.PlayPhase()
    }

}

class TestLightning() {
    @Test
    fun lightning() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Rebel()))
        Generalmanager.addGeneral(ZhangLiao("z", Rebel()))
        Generalmanager.addGeneral(HuanGai("H", Rebel()))
        Generalmanager.addGeneral(SunQuan("S", Rebel()))
        val lightning = LightningCard("Lightning", "A", "0")
        val peach = BasicCard("Peach", "A", "0")
        Generalmanager.createAllCard()


        for (i in 0..4 - 1) {
            Generalmanager.list.get(i).strategy = RebelStrategy(Generalmanager.list.get(i))
            Generalmanager.list.get(i).currentHP = 2
        }
        Generalmanager.list.get(0).handCard.add(lightning)
        Generalmanager.list.get(1).handCard.add(peach)
        Generalmanager.list.get(0).PlayPhase()
        println()
        for (i in 0..Generalmanager.list.size - 1) {
            Generalmanager.list.get(i).JudgementPhase()
        }


        println("round 2")

        //remove not test

        for (i in 0..Generalmanager.list.size - 1) {
            Generalmanager.list.get(i).JudgementPhase()
        }
        for (i in 0..Generalmanager.list.size - 1) {
            println(Generalmanager.list.get(i).name)
        }

    }
}

class TestEightTrigramsFormation() {
    @Test
    fun armor() {
        val GuoJia = GuoJia("Guo Jia", Loyalist())
        Generalmanager.list.add(GuoJia)
        val XuChu = XuChu("Xu Chu", Rebel())
        XuChu.strategy = RebelStrategy(XuChu)
        Generalmanager.list.add(XuChu)
        GuoJia.strategy = LoyalistStrategy(GuoJia)
        GuoJia.currentHP = 3
        XuChu.currentHP = 3

        GuoJia.armor = EightTrigramsFormation("hi", "spade", "2")
        GuoJia.handCard.add(BasicCard("Dodge", "spede", "4"))
        Generalmanager.createAllCard()
        val attack = BasicCard("Attack", "spade", "4")
        XuChu.Preparationphase()
        XuChu.handCard.add(attack)
        XuChu.strategy.attack()

    }
}

class TestBeingDamage() {
    @Test
    fun test() {
        val sima = SimaYi("sima", Lord())
        val Hua = HuaXiong("Hua", Rebel())
        val Xia = XiahouDun("xia", Loyalist())
        val b = SunQuan("w", Rebel())
        b.currentHP = 3
        Xia.currentHP = 4
        Hua.currentHP = 4
        sima.currentHP = 4

        Generalmanager.createAllCard()
        Generalmanager.list.add(sima)
        Generalmanager.list.add(Hua)
        Generalmanager.list.add(Xia)
        Generalmanager.list.add(b)

        sima.strategy = LoyalistStrategy(sima)

        Hua.strategy = RebelStrategy(Hua)
        Xia.strategy = LoyalistStrategy(Xia)
        b.strategy = RebelStrategy(b)

        val a = BasicCard("Attack", "heart", "5")
        val a1 = BasicCard("Attack", "heart", "5")
        val a2 = BasicCard("Attack", "heart", "5")
        val store = BasicCard("Peach", "heart", "5")
        val s1 = SomethingOutOfNothing("SomethingOutOfNothing", "spade", "5")
        val s2 = BasicCard("Dodge", "spade", "5")
        val s3 = BasicCard("Peach", "spade", "5")

        b.handCard.add(a)
        b.handCard.add(s3)
        b.handCard.add(s2)
        b.handCard.add(s1)


        Xia.handCard.add(a1)
        Hua.handCard.add(a2)
        Hua.handCard.add(store)

        //b.Preparationphase()
        b.inRange.add(Xia)
        Xia.inRange.add(Hua)
        Hua.inRange.add(sima)


        b.PlayPhase()
        println()
        Xia.PlayPhase()
        println()
        Hua.PlayPhase()

    }

}

class LuXunSteal() {
    @Test
    fun testSteal() {
        val h = HuangYueying("Huang Yueying", Lord())
        h.strategy = LoyalistStrategy(h)
        Generalmanager.list.add(h)
        val l = LuXun("Lu Xun", Rebel())
        Generalmanager.list.add(l)
        l.strategy = RebelStrategy(l)

        val steal = StealingSheep("s", "heart", "8")
        val dodge = BasicCard("Dodge", "heart", "8")
        h.handCard.add(steal)
        l.handCard.add(dodge)
        println("luXun hand: ${l.handCard.size}")
        h.PlayPhase()

    }
}

class TestSimaYi() {
    @Test
    fun judge() {
        val h = HuangYueying("Huang Yueying", Lord())
        h.strategy = LoyalistStrategy(h)
        Generalmanager.list.add(h)
        val l = LuXun("Lu Xun", Rebel())
        Generalmanager.list.add(l)
        l.strategy = RebelStrategy(l)
        val s = SimaYi("s", Rebel())
        Generalmanager.list.add(s)
        s.strategy = RebelStrategy(s)

        val steal = StealingSheep("s", "heart", "8")
        val dodge = BasicCard("Dodge", "heart", "8")
        val n = BasicCard("Dodge", "heart", "8")
        val b = BasicCard("Dodge", "heart", "8")

        s.handCard.add(steal)
        s.handCard.add(dodge)
        s.handCard.add(n)
        s.handCard.add(b)

        h.invoker.addCommand(Acedia(h))
        s.invoker.addCommand(Acedia(s))
        h.JudgementPhase()
        s.JudgementPhase()
    }
}

class TestLiu() {
    @Test
    fun test() {
        Generalmanager.addGeneral(LiuBei("Liu Bei", Lord()))
        Generalmanager.addGeneral(ZhangLiao("z", Rebel()))
        Generalmanager.addGeneral(HuanGai("H", Rebel()))
        Generalmanager.list.get(0).strategy = LiuBeiStrategy((Generalmanager.list.get(0) as LiuBei))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))

        Generalmanager.list.get(0).currentHP = 3
        Generalmanager.list.get(1).currentHP = 3
        Generalmanager.list.get(0).attackedState = false
        Generalmanager.list.get(1).attackedState = false
        val attack = BasicCard("Attack", "heart", "8")
        val dodge = BasicCard("Attack", "heart", "8")
        val n = BasicCard("Attack", "heart", "8")
        val b = BasicCard("Attack", "heart", "8")
        val attack1 = BasicCard("Attack", "heart", "8")
        val attack2 = BasicCard("Attack", "heart", "8")
        Generalmanager.list.get(0).handCard.add(n)
        Generalmanager.list.get(0).handCard.add(b)
        Generalmanager.list.get(0).handCard.add(dodge)
        Generalmanager.list.get(0).handCard.add(attack1)
        Generalmanager.list.get(0).handCard.add(attack2)
        Generalmanager.list.get(1).handCard.add(attack)
        Generalmanager.list.get(0).Preparationphase()
        Generalmanager.list.get(1).Preparationphase()
        Generalmanager.list.get(0).PlayPhase()
        Generalmanager.list.get(1).PlayPhase()
        Generalmanager.list.get(0).Preparationphase()
        Generalmanager.list.get(0).PlayPhase()
    }
}

class Ma {
    @Test
    fun ma() {
        Generalmanager.addGeneral(MaChao("Ma Chao", Loyalist()))
        Generalmanager.addGeneral(Sunshangxiang("Sunshangxiang", Rebel()))
        Generalmanager.addGeneral(DiaoChan("Diao Chan", Rebel()))
        Generalmanager.addGeneral(SimaYi("SimaYi", Rebel()))
        Generalmanager.addGeneral(HuangYueying("HuangYueying", Rebel()))
        Generalmanager.list.get(0).strategy = MaChaoLoyalStrategy((Generalmanager.list.get(0) as MaChao))
        Generalmanager.list.get(1).strategy = RebelStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(3).strategy = RebelStrategy(Generalmanager.list.get(3))
        Generalmanager.list.get(4).strategy = RebelStrategy(Generalmanager.list.get(4))
        Generalmanager.list.get(0).currentHP = 3
        Generalmanager.list.get(1).currentHP = 3
        Generalmanager.list.get(2).currentHP = 3
        Generalmanager.list.get(3).currentHP = 3
        Generalmanager.list.get(4).currentHP = 3
        val w = EightTrigramsFormation("EightTrigramsFormation", "spade", "2")
        val weaponZ = ZhugeCrossbow("w", "w", "w") // succeed
        val weaponK = KirinBow("k", "k", "k") // succeed
        val weaponY = YinYangSwords("y", "y", "y") // succeed
        val weaponS = SerpentSpear("S", "S", "S") // succeed
        val weaponSk = SkyPiercingHalberd("S", "S", "S") // size1 succeed //size2 succeed // size 3 succeed
        val attack = BasicCard("Attack", "heart", "8")
        val attack1 = BasicCard("Attack", "heart", "8")
        val attack2 = BasicCard("Attack", "heart", "8")
        val attack3 = BasicCard("Attack", "heart", "8")
        Generalmanager.list.get(0).handCard.add(attack)
        Generalmanager.list.get(0).handCard.add(attack1)
        Generalmanager.list.get(0).handCard.add(attack2)
        Generalmanager.list.get(0).handCard.add(attack3)
        //Generalmanager.list.get(0).equipment = weaponK
        Generalmanager.list.get(2).armor = w
        Generalmanager.list.get(2).mountDec = RedHare("RedHare", "r", "R")
        Generalmanager.list.get(1).mountDec = RedHare("RedHare", "r", "R")
        Generalmanager.list.get(0).Preparationphase()
        Generalmanager.list.get(0).PlayPhase()
//        Generalmanager.list.get(0).Preparationphase()
//        Generalmanager.list.get(0).PlayPhase()
//        Generalmanager.list.get(0).Preparationphase()
//        Generalmanager.list.get(0).PlayPhase()
//        Generalmanager.list.get(0).Preparationphase()
//        Generalmanager.list.get(0).PlayPhase()


    }
}

class textGameLoop() {
    @Test
    fun test() {
        Generalmanager.createGenerals(5)
        println()
        Generalmanager.list.get(1).currentHP = 1
        Generalmanager.createAllCard()
        Generalmanager.list.get(2).handCard.add(BasicCard("Attack", "spade", "2"))

        var i = 0
        while (i < 2) {
            //println("hi")
            var size = Generalmanager.copyList.size
            var counter = 0
            while (counter < size) {
                if (Generalmanager.list.contains(Generalmanager.copyList.get(counter))) {
                    //當remove jor general size reduce but the loop still use the old list
                    println(
                        "player $counter: ${Generalmanager.copyList.get(counter).name},${
                            Generalmanager.copyList.get(
                                counter
                            ).player.identity
                        }"
                    )
                    if (counter == 1) {
                        Generalmanager.list.get(1)
                            .beingDamage(2, BasicCard("Attack", "spade", "2"), Generalmanager.list.get(0))
                    }
                }
                counter++

            }
            i++
        }
        println()
    }


}

class DoubleNegation() {
    @Test
    fun test() {
        Generalmanager.addGeneral(MaChao("Ma Chao", Loyalist()))
        Generalmanager.addGeneral(Sunshangxiang("Sunshangxiang", Loyalist()))
        Generalmanager.addGeneral(DiaoChan("Diao Chan", Rebel()))
        Generalmanager.addGeneral(SimaYi("SimaYi", Loyalist()))
        Generalmanager.addGeneral(SunQuan("SunQuan", Rebel()))
        Generalmanager.list.get(0).strategy = MaChaoLoyalStrategy((Generalmanager.list.get(0) as MaChao))
        Generalmanager.list.get(1).strategy = LoyalistStrategy(Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = RebelStrategy(Generalmanager.list.get(2))
        Generalmanager.list.get(3).strategy = LoyalistStrategy(Generalmanager.list.get(3))
        Generalmanager.list.get(4).strategy = RebelStrategy(Generalmanager.list.get(4))
        Generalmanager.list.get(0).currentHP = 3
        Generalmanager.list.get(1).currentHP = 3
        Generalmanager.list.get(2).currentHP = 3
        Generalmanager.list.get(3).currentHP = 3
        Generalmanager.list.get(4).currentHP = 3

        Generalmanager.list.get(0).handCard.add(SomethingOutOfNothing("SomethingOutOfNothing", "", ""))
        Generalmanager.list.get(1).handCard.add(Negation("Negation", "", ""))
        Generalmanager.list.get(2).handCard.add(Negation("Negation", "", ""))
        Generalmanager.list.get(3).handCard.add(Negation("Negation", "", ""))
        //Generalmanager.list.get(4).handCard.add(Negation("Negation","",""))

        Generalmanager.list.get(0).PlayPhase()
    }
}

class TestSunQuan(){
    @Test
    fun testRescure(){
        Generalmanager.addGeneral(SunQuan("SunQuan", Lord()))
        Generalmanager.addGeneral(Sunshangxiang("Sunshangxiang", Loyalist()))
        Generalmanager.addGeneral(HuaTuo("Hua Tuo", Loyalist()))
        Generalmanager.addGeneral(SimaYi("SimaYi", Rebel()))

        Generalmanager.list.get(0).strategy = LoyalistStrategy( Generalmanager.list.get(0))
        Generalmanager.list.get(1).strategy = LoyalistStrategy( Generalmanager.list.get(1))
        Generalmanager.list.get(2).strategy = LoyalistStrategy( Generalmanager.list.get(2))
        Generalmanager.list.get(3).strategy = RebelStrategy( Generalmanager.list.get(3))

        for(i in 0 .. 3){
            Generalmanager.list.get(i).currentHP = 3
        }
        Generalmanager.list.get(1).handCard.add(BasicCard("Peach","",""))
        Generalmanager.list.get(2).handCard.add(BasicCard("Peach","",""))
        Generalmanager.list.get(2).handCard.add(BasicCard("Red", "heart",""))
        Generalmanager.list.get(0).currentHP = 1
        Generalmanager.list.get(0).beingDamage(3,BasicCard("Attack","",""), Generalmanager.list.get(3))

    }
    @Test
    fun open(){
        Generalmanager.addGeneral(SunQuan("SunQuan", Lord()))
        Generalmanager.list.get(0).strategy = SunQuanStratrgy(Generalmanager.list.get(0) as SunQuan)
        Generalmanager.list.get(0).currentHP = 4
        Generalmanager.createAllCard()
        Generalmanager.list.get(0).gameOpenCard()
        Generalmanager.list.get(0).templatemethod()
    }
}
