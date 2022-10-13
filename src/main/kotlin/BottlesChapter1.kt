import java.util.*

data class BottlesChapter1(
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
                        "${this.maxBottles} $nMinusBottlesOnWallPeriodString"
            }
            1 -> {
                "$bottleNumber $oneBottleOnWallCommaString" +
                        "$bottleNumber $oneBottleString" +
                        takeItDownString.oldCapitalize() +
                        noBottlesOnWallPeriodString
            }
            2 -> {
                "$bottleNumber $nBottlesOnWallCommaString" +
                        "$bottleNumber $nBottlesString" +
                        takeOneDownString.oldCapitalize() +
                        "${bottleNumber - 1} $oneBottlesOnWallPeriodString"
            }
            else -> {
                "$bottleNumber $nBottlesOnWallCommaString" +
                        "$bottleNumber $nBottlesString" +
                        takeOneDownString.oldCapitalize() +
                        "${bottleNumber - 1} $nMinusBottlesOnWallPeriodString"
            }
        }
    }

    companion object {
        const val nBottlesOnWallCommaString: String = "bottles of beer on the wall, "
        const val oneBottleOnWallCommaString: String = "bottle of beer on the wall, "
        const val noBottlesOnWallString: String = "no more bottles of beer on the wall, "

        const val nBottlesString: String = "bottles of beer.\n"
        const val oneBottleString: String = "bottle of beer.\n"
        const val noBottlesString: String = "no more bottles of beer.\n"

        const val takeOneDownString: String = "take one down and pass it around, "
        const val takeItDownString: String = "take it down and pass it around, "
        const val beerRunString: String = "go to the store and buy some more, "

        const val nMinusBottlesOnWallPeriodString: String = "bottles of beer on the wall.\n"
        const val oneBottlesOnWallPeriodString: String = "bottle of beer on the wall.\n"
        const val noBottlesOnWallPeriodString: String = "no more bottles of beer on the wall.\n"

        fun String.oldCapitalize(): String {
            return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
    }
}
