import BottlesChapter1.Companion.oldCapitalize
import java.util.*


/**
 * Refactoring now makes the making new files per chapter.
 */
data class Bottles(
    var maxBottles: Int = 99,
    val minBottles: Int = 0
) {
    fun song(): String =
        verses(maxBottles, minBottles)

    fun verses(startBottle: Int, endBottle: Int): String {
        return startBottle.downTo(endBottle)
            .joinToString(separator = "\n") {
                verse(it)
            }
//        return (startBottle downTo endBottle)
//            .joinToString(separator = "\n", transform = this::verse)
    }

    fun verse(bottleNumber: Int): String {
        val currentBottleNumber: BottleNumber = bottleNumberMapper(bottleNumber)
        val currentBottleSuccessorNumber: BottleNumber = bottleNumberMapper(currentBottleNumber.getSuccessor(maxBottles))

        return "${currentBottleNumber.getQuantity()} ${currentBottleNumber.getContainer()} $beerLocation, ".oldCapitalize() +
                "${currentBottleNumber.getQuantity()} ${currentBottleNumber.getContainer()}.\n" +
                currentBottleNumber.getAction().oldCapitalize() +
                "${currentBottleSuccessorNumber.getQuantity()} ${currentBottleSuccessorNumber.getContainer()} $beerLocation.\n"
    }

    private fun bottleNumberMapper(bottleNumber: Int): BottleNumber {
        val bottleNumbersMap: Map<Int, BottleNumber> =
            mapOf(
                0 to BottleNumber0(),
                1 to BottleNumber1(),
                6 to BottleNumber6()
            )

        return bottleNumbersMap.getOrDefault(bottleNumber, BottleNumber(bottleNumber))
    }

    private fun String.oldCapitalize(): String {
        return this.replaceFirstChar {
            if (it.isLowerCase()) {
                it.titlecase(Locale.getDefault())
            } else {
                it.toString()
            }
        }
    }

    companion object {
        const val beerLocation: String = "on the wall"
    }
}

open class BottleNumber(
    private val bottleNumber: Int = 0
) {
    open fun getContainer(): String {
        return "bottles of beer"
    }

    open fun getQuantity(): String {
        return this.bottleNumber.toString()
    }

    open fun getAction(): String {
        return getBottleAction()
    }

    private fun getBottleAction(): String {
        return if (this.bottleNumber == 1)
            "take it down and pass it around, "
        else
            "take one down and pass it around, "
    }

    /**
     * I understand that there is some smell put in by this variable in the function.
     *
     * I wanted to allow for the successor to have a variable top-end
     *  (i.e., maybe a six-pack is the "max", not 99 bottles)
     */
    open fun getSuccessor(maxBottles: Int): Int {
        return if (this.bottleNumber == 0)
            Bottles().maxBottles
        else
            this.bottleNumber - 1
    }
}

class BottleNumber0 : BottleNumber(0) {
    override fun getQuantity(): String {
        return "no more"
    }

    override fun getAction(): String {
        return "Go to the store and buy some more, "
    }

    override fun getSuccessor(maxBottles: Int): Int {
        return Bottles(maxBottles).maxBottles
    }
}

class BottleNumber1 : BottleNumber(1) {
    override fun getContainer(): String {
        return "bottle of beer"
    }

    override fun getAction(): String {
        return "Take it down and pass it around, "
    }
}

class BottleNumber6 : BottleNumber(6) {
    override fun getQuantity(): String {
        return "1"
    }

    override fun getContainer(): String {
        return "six-pack of beer"
    }
}
