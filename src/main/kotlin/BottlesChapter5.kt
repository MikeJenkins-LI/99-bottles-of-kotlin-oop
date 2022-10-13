import java.util.*

data class BottlesChapter5(
    val maxBottles: Int = 99,
    val minBottles: Int = 0
) {
    fun song(): String =
        verses(maxBottles, minBottles)

    fun verses(startBottle: Int, endBottle: Int): String {
        return startBottle.downTo(endBottle)
            .joinToString(separator = "\n") {
                verse(it)
            }
    }

    fun verse(bottleNumber: Int): String {
        val aBottleNumber = BottleNumberChapter5(bottleNumber)
        val aBottleSuccessorNumber = BottleNumberChapter5(aBottleNumber.getSuccessor(maxBottles))

        return "${aBottleNumber.getQuantity()} ${aBottleNumber.getContainer()} $beerLocation, ".oldCapitalize() +
                "${aBottleNumber.getQuantity()} ${aBottleNumber.getContainer()}.\n" +
                aBottleNumber.getAction().oldCapitalize() +
                "${aBottleSuccessorNumber.getQuantity()} ${aBottleSuccessorNumber.getContainer()} $beerLocation.\n"
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

/**
 * Chapter 5 refactor for new class
 */
data class BottleNumberChapter5(
    val bottleNumber: Int = 0
) {
    /**
     * Chapter 3 refactor "container
     */
    fun getContainer(): String {
        return if (this.bottleNumber == 1)
            "bottle of beer"
        else
            "bottles of beer"
    }

    /**
     * Chapter 4 refactor "pronoun"
     */
    private fun getBottleAction(): String {
        return if (this.bottleNumber == 1)
            "take it down and pass it around, "
        else
            "take one down and pass it around, "
    }

    /**
     * Chapter 4 refactor "quantity"
     */
    fun getQuantity(): String {
        return if (this.bottleNumber == 0)
            "no more"
        else
            this.bottleNumber.toString()
    }

    /**
     * Chapter 4 refactor "action"
     */
    fun getAction(): String {
        return if (this.bottleNumber == 0)
            "go to the store and buy some more, "
        else
            getBottleAction()
    }

    /**
     * Chapter 4 refactor "successor"
     */
    fun getSuccessor(maxBottles: Int): Int {
        return if (this.bottleNumber == 0)
            BottlesChapter5(maxBottles).maxBottles
        else
            this.bottleNumber - 1
    }
}
