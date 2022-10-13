import java.util.*

data class BottlesChapter4(
    val maxBottles: Int = 99,
    val minBottles: Int = 0
) {
    fun song(): String =
        verses(maxBottles, minBottles)

    fun verses(start: Int, end: Int): String {
        return start.downTo(end)
            .joinToString(separator = "\n") { bottle ->
                verse(bottle)
            }
    }

    fun verse(bottleNumber: Int): String {
        return "${getQuantity(bottleNumber)} ${getContainer(bottleNumber)} on the wall, ".oldCapitalize() +
                "${getQuantity(bottleNumber)} ${getContainer(bottleNumber)}.\n" +
                getAction(bottleNumber).oldCapitalize() +
                "${getQuantity(getSuccessor(bottleNumber))} ${getContainer(getSuccessor(bottleNumber))} on the wall.\n"
    }

    private fun String.oldCapitalize(): String {
        return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    /**
     * Chapter 3 refactor "container
     */
    private fun getContainer(bottleNumber: Int): String {
        return if (bottleNumber == 1)
            "bottle of beer"
        else
            "bottles of beer"
    }

    /**
     * Chapter 4 refactor "pronoun"
     */
    private fun getBottleAction(bottleNumber: Int): String {
        return if (bottleNumber == 1)
            "take it down and pass it around, "
        else
            "take one down and pass it around, "
    }

    /**
     * Chapter 4 refactor "quantity"
     */
    private fun getQuantity(bottleNumber: Int): String {
        return if (bottleNumber == 0)
            "no more"
        else
            bottleNumber.toString()
    }

    /**
     * Chapter 4 refactor "action"
     */
    private fun getAction(bottleNumber: Int): String {
        return if (bottleNumber == 0)
            "go to the store and buy some more, "
        else
            getBottleAction(bottleNumber)
    }

    /**
     * Chapter 4 refactor "successor"
     */
    private fun getSuccessor(bottleNumber: Int): Int {
        return if (bottleNumber == 0)
            maxBottles
        else
            bottleNumber - 1
    }
}
