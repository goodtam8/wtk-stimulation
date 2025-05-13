import kotlin.system.exitProcess

object Generalmanager {

    init {

    }
    val list: MutableList<General> = mutableListOf()
    val cardList: MutableList<Card> = mutableListOf()
    var copyList: MutableList<General> = mutableListOf()

    val spadeCardFactory: SpadeCardFactory = SpadeCardFactory()
    val spadeCardFactory2: SpadeCardFactory2 = SpadeCardFactory2()
    val clubCardFactory: ClubCardFactory = ClubCardFactory()
    val clubCardFactory2: ClubCardFactory2 = ClubCardFactory2()
    val heartCardFactory: HeartCardFactory = HeartCardFactory()
    val heartCardFactory2: HeartCardFactory2 = HeartCardFactory2()
    val diamondCardFactory: DiamondCardFactory = DiamondCardFactory()
    val diamondCardFactory2: DiamondCardFactory2 = DiamondCardFactory2()

    fun createAllCard() {
        for (i in 1..13) {
            cardList.add(spadeCardFactory.createRandomCard(i))
            cardList.add(spadeCardFactory2.createRandomCard(i))
            cardList.add(clubCardFactory.createRandomCard(i))
            cardList.add(clubCardFactory2.createRandomCard(i))
            cardList.add(heartCardFactory.createRandomCard(i))
            cardList.add(heartCardFactory2.createRandomCard(i))
            cardList.add(diamondCardFactory.createRandomCard(i))
            cardList.add(diamondCardFactory2.createRandomCard(i))
        }
        // random
        cardList.shuffle()
    }


    fun addGeneral(general: General) {
        list.add(general)
        copyList.add(general)
    }

    fun removeGeneral(general: General) {
        list.remove(general)
        println("exist general: ")
        for(i in 0 .. list.size-1){
            print("($i) ${list.get(i).name}, ")
        }
        println()
    }

    var c: NonLordFactory? = null
    fun createGenerals(participant: Int) {
        var b: LordFactory = LordFactory()
        val f = b.createRandomGeneral(participant)
        this.addGeneral(f)
        var e = NonLordFactory(f.player as Lord)
        c = e


        if (b.cao == true) {
            c!!.cao = true
        } else if (b.wu == true) {
            c!!.wu = true

        }else if (b.shu == true) {
            c!!.shu = true

        }

        for (i in 1..participant) {
            val e: General = c!!.createRandomGeneral(i)
            this.addGeneral(e)

        }
        if (list.get(0) is CaoCao) {
            (list.get(0) as CaoCao).weichain = e.weichain
        } else if (list.get(0) is SunQuan) {
            (list.get(0) as SunQuan).wuchain = e.wuchain
        }
        else if(list.get(0) is LiuBei){
            (list.get(0) as LiuBei).shuchain=e.shuchain
        }

        list.get(0).player = c!!.lord

    }


    fun getGeneralCount(): Int {
        println("Total number of players:" + list.size)
        println()
        return list.size
    }

    fun deducthp(a: WeiGeneral?) {
        if (a == null) {
            return
        } else {
            for (i in 0..list.size - 1) {
                if (list.get(i) == a) {
                    list.get(i).currentHP -= 1
                    println("${list.get(i).name} helps Cao Cao dodged an attack by spending a dodge card.")
                }

            }
        }
    }
    //test

    fun gamestart() {

        //interface
        var count = list.size
        for (i in 0..list.size - 1) {
            list.get(i).strategy.size = count


        }
        // game open give four cards
        for (i in 0..list.size - 1) {
            list.get(i).gameOpenCard()
        }
        var turn=0
        var checked=0
        while(true) {//狂loop
            var size= copyList.size
            var counter=0
            while (counter<size) {
                if(list.contains(copyList.get(counter))){
                //當remove jor general size reduce but the loop still use the old list
                println("player $counter: ${copyList.get(counter).name},${copyList.get(counter).player.identity}")
                    copyList.get(counter).templatemethod()
                }
                counter++
                //size=list.size
            }
            var checkrebel= checkrebel()
            if(checkrebel==true &&checked<checkspy()){
                changestrategy()
                checked++
                println(checked)
            }
            turn++
            }
            println()
        }


    fun loyalistwin(){
        var count=0
     for(i in 0..list.size-1){
         if(list.get(i).player is Rebel||list.get(i).player is Spy ){
             count++
         }

     }
        if(count==0){
            println("There is no spy or rebel left in the game. Loyalist win")
            exitProcess(1)
        }

    }
    fun rebelwin(){

        var count=0
        for(i in 0..list.size-1){
            if(list.get(i).player is Lord ){
                count++
            }

        }
        if(count==0){
        println("There is no lord or loyalist left in the game. Rebel win")


            exitProcess(1)
        }
    }
    fun spywin(){

        var count=0
        for(i in 0..list.size-1){
            if(list.get(i).player is Lord||list.get(i).player is Loyalist||list.get(i).player is Rebel ){
                count++
            }

        }
        if(count==0){
            println("Spy win")


            exitProcess(1)
        }
    }
    fun win(){
        spywin()
        rebelwin()
        loyalistwin()
    }
    fun checkrebel():Boolean{
        var counter=0
        for(i in 0..list.size-1){
            if(list.get(i).player is Rebel)
                counter++

        }
        if(counter==0) {
            return true
        }
return false
    }
    fun changestrategy(){
        println("There is no rebel in the court. The spy is time to reveal his/her identity")
        for (i in 0..list.size - 1) {
            if (list.get(i).player is Spy) {
                println("${list.get(i).name} change its strategy to spy")
                if (list.get(i) is MaChao)
                    list.get(i).strategy = MaChaoRebelStrategy((list.get(i) as MaChao))
                else if (list.get(i) is HuanGai)
                    list.get(i).strategy = HuanGaiRebelStrategy((list.get(i) as HuanGai))
                else if (list.get(i) is DiaoChan)
                    list.get(i).strategy = DiaoChanRebelStrategy(list.get(i) as DiaoChan)
                else
                    list.get(i).strategy = RebelStrategy(list.get(i))
            }

        }
    }
    fun checkspy():Int{
        var counter=0
        for(i in 0..list.size-1){
            if(list.get(i).player is Spy)
                counter++

        }
        return counter

    }

}


fun main() {
    // input number for creating non-lord generals
    Generalmanager.createGenerals(4)
    Generalmanager.createAllCard()
    Generalmanager.getGeneralCount()
    Generalmanager.gamestart()

}