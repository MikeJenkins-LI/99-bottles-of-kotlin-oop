fun main(args: Array<String>) {
    val bottles = Bottles()
    val aSixPack = Bottles(6)
    val lotsOfBottles = Bottles(999, 0)

    println(bottles.song())
    println(aSixPack.song())
//    println(lotsOfBottles.song())
}
