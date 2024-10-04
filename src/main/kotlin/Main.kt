package ru.netology

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    //Task 1
    println(timeAgo(3600 * 21 ))

    //Task 2
    miniBank(100_000, "MC", 55_000)

}

private fun timeAgo(second: Int) =
    when {
        (second <= 60) -> "был(а) только что"
        (second > 60) && (second < 60 * 60) -> getMinAgo(second / 60)
        (second >= 60 * 60) && (second < 60 * 60 * 24) -> getHourAgo(second / (60 * 60))
        (second >= 60 * 60 * 24) && (second < 2 * 60 * 60 * 24) -> "вариант «был(а) вчера"
        (second >= 2 * 60 * 60 * 24) && (second < 3 * 60 * 60 * 24) -> "вариант «был(а) позавчера"
        else -> { "был(а) давно" }
    }

private fun getMinAgo(minute: Int) = when {
    (minute in 11..14) ||
            (minute.toString().takeLast(1).toInt() in 5..9) ||
            (minute.toString().takeLast(1) == "0") -> "был(а) " + minute + " минут назад"
    (minute.toString().takeLast(1) == "1") -> "был(а) " + minute + " минуту назад"
    else -> {
        "был(а) " + minute + " минуты назад"
    }
}

private fun getHourAgo(hour: Int) = when {
    (hour in 5 ..20) -> "был(а) " + hour + " часов назад"
    (hour == 1) || (hour == 21) -> "был(а) " + hour + " час назад"
    else -> {"был(а) " + hour + " часа назад"}
}


private fun miniBank(ammount: Int, cardType: String = "Mir", ammountLastMonth: Int = 0) {
    var fee:Int = 0

    if ((ammount > 150_000) || (ammount + ammountLastMonth > 600_000)) {
        println("Привышены лимиты операций");
        return;
    }

 when (cardType) {
     "Mir" -> {}
     "Visa" -> {
         fee = maxOf((ammount * 0.0075).toInt(), 35)
     }
     "MC" -> {
        if (ammount + ammountLastMonth >= 75_000) {
            fee = ((ammount + ammountLastMonth - 75000) * 0.006).toInt() + 20
        }
     }
     else -> {
         println("Не известный тип карты")
         return
     }
 }
    println("При переводе " + ammount + " комисия составит " + fee + " рублей. Общая сумма: " + (ammount + fee) + " руб.")
}