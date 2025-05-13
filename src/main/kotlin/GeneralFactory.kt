import java.util.LinkedList

abstract class GeneralFactory {
    abstract fun createRandomGeneral(abc: Int): General
    abstract fun createPlayer(abc: Int): Player
}

class LordFactory : GeneralFactory() {
    var gen: MutableList<String> = listOf("Liu Bei", "Cao Cao", "Sun Quan","Yuan Shu") as MutableList<String>
    var aaa = gen.toMutableList()
    var cao = false
    var wu = false
    var shu=false

    override fun createRandomGeneral(abc: Int): General {
        var b: String = aaa.random()
        aaa.remove(b)
        if (b == "Cao Cao") {
            cao = true

        }
        if (b == "Liu Bei") {
            var ff = LiuBei("Liu Bei", createPlayer(abc))
            ff.strategy = LiuBeiStrategy(ff)
            ff.currentHP = ff.maxHP
            shu=true
            return ff

        } else if (b == "Cao Cao") {
            var ff = CaoCao("Cao Cao", createPlayer(abc))
            ff.strategy = LoyalistStrategy(ff)
            ff.currentHP = ff.maxHP
            return ff

        } else if(b=="Sun Quan"){
            var ff = SunQuan("Sun Quan", createPlayer(abc))
            ff.strategy = SunQuanStratrgy(ff)
            ff.currentHP = ff.maxHP
            wu = true
            return ff
        }
        //aa
        else
        { var ff = YuanShu("Yuan Shu", createPlayer(abc))
            ff.strategy = LoyalistStrategy(ff)
            ff.currentHP = ff.maxHP
            return ff

        }


    }

    override fun createPlayer(abc: Int): Player {//use when statement to assign the property of the player
        var b = Lord()
        //b.currentHP =

        return b

    }


}

open class NonLordFactory(var lord: Lord) : GeneralFactory() {
    private var gen2: MutableList<String> = listOf(
        "Zhen Ji",
        "Sima Yi",
        "Sun Shangxiang",
        "Huan Gai",
        "Zhou Yu",
        "Diao Chan",
        "Lv Bu",
        "Zhuge Liang",
        "Guan Yu",
        "Xiahou Dun",
        "Xu Chu",
        "Lv Meng",
        "Zhang Liao",
        "Gan Ning",
        "Hua Xiong",
        "Guo Jia",
        "Hua Tuo",
        "Huang Yueying",
        "Da Qiao",
        "Zhao Yun",
        "Zhang Fei",
        "Ma Chao"
    ) as MutableList<String>
    var aaa = gen2.toMutableList()
    open var cao = false;
    var weichain = LinkedList<WeiGeneral>()
    open var wu = false
    var wuchain = LinkedList<WuGeneral>()
var shuchain=LinkedList<ShuGeneral>()
    open var shu=false

    //late
    lateinit var player: Player


    override fun createRandomGeneral(abc: Int): General {
        val name = aaa.random()

        if (aaa.contains(name)) {
            val general: General = when (name) {
                "Zhen Ji" -> ZhenJi(name, createPlayer(abc))
                "Huan Gai" -> HuanGai(name, createPlayer(abc))
                "Guan Yu" -> GuanYuadapter(GuanYu(), createPlayer(abc))
                "Sun Shangxiang" -> Sunshangxiang(name, createPlayer(abc))
                "Lv Meng" -> LvMeng(name, createPlayer(abc))
                "Xu Chu" -> XuChu(name, createPlayer(abc))
                "Zhuge Liang" -> ZhugeLiang(name, createPlayer(abc))
                "Zhou Yu" -> ZhouYu(name, createPlayer(abc))
                "Diao Chan" -> DiaoChan(name, createPlayer(abc))
                "Lv Bu" -> LvBu(name, createPlayer(abc))
                "Sima Yi" -> SimaYi(name, createPlayer(abc))
                "Xiahou Dun" -> XiahouDun(name, createPlayer(abc))
                "Zhang Liao" -> ZhangLiao(name, createPlayer(abc))
                "Gan Ning" -> GanNing(name, createPlayer(abc))
                "Hua Xiong" -> HuaXiong(name, createPlayer(abc))
                "Guo Jia" -> GuoJia(name, createPlayer(abc))
                "Hua Tuo" -> HuaTuo(name, createPlayer(abc))
                "Huang Yueying" -> HuangYueying(name, createPlayer(abc))
                "Da Qiao"->DaQiao(name,createPlayer(abc))
                "Zhao Yun"->ZhaoYun(name,createPlayer(abc))
                "Zhang Fei"->ZhangFei(name,createPlayer(abc))
                "Ma Chao" -> MaChao(name, createPlayer(abc))



                else -> throw IllegalArgumentException("Invalid non-lord.")
            }

            // watcher
            if (general.player is Spy && lord is Lord) {
                lord.observers.add(general.player as Spy)
            }



            if(general.player is Loyalist || (general.player is Spy && general.player.revealed == false)) {
                when (general.name) {
                    "Huan Gai" -> (general as HuanGai).strategy = HuanGaiLoyalistStrategy(general)
                    "Diao Chan" -> (general as DiaoChan).strategy = DiaoChanLoyalistStrategy(general)
                    "Ma Chao" -> (general as MaChao).strategy = MaChaoLoyalStrategy(general)
                    else -> general.strategy = LoyalistStrategy(general)
                }
            }
            else if(general.player is Rebel || (general.player is Spy && general.player.revealed == true)){
                when (general.name) {
                    "Huan Gai" -> (general as HuanGai).strategy = HuanGaiRebelStrategy(general)
                    "Diao Chan" -> (general as DiaoChan).strategy = DiaoChanRebelStrategy(general)
                    "Ma Chao" -> (general as MaChao).strategy = MaChaoRebelStrategy(general)
                    else -> general.strategy = RebelStrategy(general)
                }
            }

            general.currentHP = general.maxHP

            println(general.name + ", a " + general.identity + ", has " + general.currentHP + " health point.")
            aaa.remove(name)


            if (general is WeiGeneral && cao == true) {
                weichain.add(general)
            } else if (general is WuGeneral && wu == true) {
                wuchain.add(general)
            }
            else if(general is ShuGeneral&&shu==true){
                shuchain.add(general)
            }


            return general
        } else
            throw IllegalArgumentException("Invalid non-lord.")
    }


    //lab 5
    override fun createPlayer(n: Int): Player {
        var cPlayer: Player = Lord()
        //identityList.remove(cPlayer)
        if (n == 1) {
            cPlayer = Loyalist()
        } else if (n == 2) {
            cPlayer = Rebel()
        } else if (n == 3) {
            cPlayer = Spy()
        } else if (n == 4) {
            cPlayer = Rebel()
        } else if (n == 5) {
            cPlayer = Rebel()
        } else if (n == 6) {
            cPlayer = Loyalist()
        } else if (n == 7) {
            cPlayer = Rebel()
        } else if (n == 8) {
            cPlayer = Loyalist()
        } else if (n == 9) {
            cPlayer = Spy()
        }


        player = cPlayer

        return cPlayer
    }
}
