import java.util.*

data class BottlesChapter3(
    val maxBottles: Int = 99,
    val minBottles: Int = 0
) {
    fun song(): String =
        verses(maxBottles, minBottles)

    fun verses(start: Int, end: Int): String =
        start.downTo(end)
            .joinToString(separator = "\n") { bottle ->
                verse(bottle)
            }

    fun verse(bottleNumber: Int): String {
        return when (bottleNumber) {
            minBottles -> {
                noBottlesOnWallString.oldCapitalize() +
                        noBottlesString +
                        beerRunString.oldCapitalize() +
                        "${this.maxBottles} ${container(bottleNumber)} of beer on the wall.\n"
            }
            1 -> {
                "$bottleNumber ${container(bottleNumber)} of beer on the wall, " +
                        "$bottleNumber $oneBottleString" +
                        takeItDownString.oldCapitalize() +
                        noBottlesOnWallPeriodString
            }
            else -> {
                "$bottleNumber ${container(bottleNumber)} of beer on the wall, " +
                        "$bottleNumber $nBottlesString" +
                        takeOneDownString.oldCapitalize() +
                        "${bottleNumber - 1} ${container(bottleNumber - 1)} of beer on the wall.\n"
            }
        }
    }

    /**
     * Chapter 3 addition
     */
    private fun container(counter: Int): String {
        if (counter == 1)
            return "bottle"
        return "bottles"
    }

    private fun String.oldCapitalize(): String {
        return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    companion object {
        const val noBottlesOnWallString: String = "no more bottles of beer on the wall, "

        const val nBottlesString: String = "bottles of beer.\n"
        const val oneBottleString: String = "bottle of beer.\n"
        const val noBottlesString: String = "no more bottles of beer.\n"

        const val takeOneDownString: String = "take one down and pass it around, "
        const val takeItDownString: String = "take it down and pass it around, "
        const val beerRunString: String = "go to the store and buy some more, "

        const val noBottlesOnWallPeriodString: String = "no more bottles of beer on the wall.\n"
    }
}
