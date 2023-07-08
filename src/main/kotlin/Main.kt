import kotlin.math.min

fun main(args: Array<String>) {
    var amount: Int = 100
    var cartType: String = "Mir"
    var fee: Int = fee(cartType, 0, amount)
    println(if (fee != -1) fee else "Превышен лимит или недоступная платжная система.")
}

fun fee(
    cartType: String = "VKPay",
    amountCurMonth: Int = 0,
    amountThis: Int = 0
): Int = when {
    (cartType == "VKPay") && isInLimit("VKPay", amountCurMonth, amountThis) -> 0

    (cartType == "Visa") || (cartType == "Mir") &&
            isInLimit("", amountCurMonth, amountThis) -> min((amountThis * 0.01 * 0.75).toInt(), 35)

    ((cartType == "MasterCard") || (cartType == "Maestro")) &&
            (amountCurMonth + amountThis in 300..75_000) &&
            isInLimit("", amountCurMonth, amountThis) -> 0

    (cartType == "MasterCard") || (cartType == "Maestro") &&
            isInLimit("", amountCurMonth, amountThis) -> (amountThis * 0.01 * 0.6 + 20).toInt()

    else -> -1
}

fun isInLimit(
    cartType: String = "VKPay",
    amountCurMonth: Int = 0,
    amountThis: Int = 0
): Boolean = when {
    (cartType == "VKPay") -> (amountThis <= 15_000) && (amountCurMonth + amountThis <= 40_000)
    else -> (amountThis <= 150_000) && (amountCurMonth + amountThis <= 600_000)
}