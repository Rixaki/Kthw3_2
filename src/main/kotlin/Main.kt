fun main(args: Array<String>) {
    var amount: Int = 100
    var cartType: String = "Maestro"
    println(fee(cartType,500,amount))
}

fun fee (cartType: String = "VKPay",
          amountCurMonth: Int = 0,
          amountThis: Int) : Int = when {
              (cartType == "VKPay") -> 0
                (cartType == "Visa") || (cartType == "Mir") -> (amountThis*0.01*0.75 + 35).toInt()

                ((cartType == "MasterCard") || (cartType == "Maestro")) &&
                (amountCurMonth in 300..75_000) &&
                (cartType != "VKPay") -> 0

              (cartType == "MasterCard") || (cartType == "Maestro") -> (amountThis*0.01*0.6 + 20).toInt()
                else -> 0
          }